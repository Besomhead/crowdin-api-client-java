package crowdin.api.client.projectsgroups;

import crowdin.api.client.core.CrowdinApi;
import crowdin.api.client.core.http.HttpClient;
import crowdin.api.client.core.http.HttpConfig;
import crowdin.api.client.core.http.JsonTransformer;
import crowdin.api.client.core.http.exceptions.HttpBadRequestException;
import crowdin.api.client.core.http.exceptions.HttpException;
import crowdin.api.client.core.model.Credentials;
import crowdin.api.client.core.model.PatchOperation;
import crowdin.api.client.core.model.ResponseList;
import crowdin.api.client.core.model.ResponseObject;
import crowdin.api.client.projectsgroups.model.AddGroupRequest;
import crowdin.api.client.projectsgroups.model.AddProjectRequest;
import crowdin.api.client.projectsgroups.model.Group;
import crowdin.api.client.projectsgroups.model.GroupResponseList;
import crowdin.api.client.projectsgroups.model.GroupResponseObject;
import crowdin.api.client.projectsgroups.model.Project;
import crowdin.api.client.projectsgroups.model.ProjectResponseList;
import crowdin.api.client.projectsgroups.model.ProjectResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProjectsGroupsApi extends CrowdinApi {

    public ProjectsGroupsApi(Credentials credentials) {
        super(credentials);
    }

    public ProjectsGroupsApi(Credentials credentials, JsonTransformer jsonTransformer) {
        super(credentials, jsonTransformer);
    }

    public ProjectsGroupsApi(Credentials credentials, HttpClient httpClient) {
        super(credentials, httpClient);
    }

    /**
     * @param parentId parent group identifier. Get via List Groups
     * @param limit    maximum number of items to retrieve (default 25)
     * @param offset   starting offset in the collection (default 0)
     * @return list of groups
     */
    public ResponseList<Group> listGroups(Integer parentId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Integer>> queryParams = HttpConfig.buildUrlParams(
                "parentId", Optional.ofNullable(parentId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        GroupResponseList groupResponseList = this.httpClient.get(this.url + "/groups", new HttpConfig(queryParams), GroupResponseList.class);
        return GroupResponseList.to(groupResponseList);
    }

    /**
     * @param request request object
     * @return newly created group
     */
    public ResponseObject<Group> addGroup(AddGroupRequest request) throws HttpException, HttpBadRequestException {
        GroupResponseObject groupResponseObject = this.httpClient.post(this.url + "/groups", request, new HttpConfig(), GroupResponseObject.class);
        return ResponseObject.of(groupResponseObject.getData());
    }

    /**
     * @param groupId group identifier
     * @return group
     */
    public ResponseObject<Group> getGroup(Integer groupId) throws HttpException, HttpBadRequestException {
        GroupResponseObject groupResponseObject = this.httpClient.get(this.url + "/groups" + groupId, new HttpConfig(), GroupResponseObject.class);
        return ResponseObject.of(groupResponseObject.getData());
    }

    /**
     * @param groupId group identifier
     */
    public void deleteGroup(Integer groupId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/groups" + groupId, new HttpConfig(), Void.class);
    }

    /**
     * @param request request object
     * @return updated group
     */
    public ResponseObject<Group> editGroup(List<PatchOperation> request) throws HttpException, HttpBadRequestException {
        GroupResponseObject groupResponseObject = this.httpClient.patch(this.url + "/groups", request, new HttpConfig(), GroupResponseObject.class);
        return ResponseObject.of(groupResponseObject.getData());
    }

    /**
     * @param groupId          group identifier (optional)
     * @param hasManagerAccess projects with manager access (default 0)
     * @param limit            maximum number of items to retrieve (default 25)
     * @param offset           starting offset in the collection (default 0)
     * @return list of projects
     */
    public ResponseList<Project> listProjects(Integer groupId, Integer hasManagerAccess, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Integer>> queryParams = HttpConfig.buildUrlParams(
                "groupId", Optional.ofNullable(groupId),
                "hasManagerAccess", Optional.ofNullable(hasManagerAccess),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ProjectResponseList projectResponseList = this.httpClient.get(this.url + "/projects", new HttpConfig(queryParams), ProjectResponseList.class);
        return ProjectResponseList.to(projectResponseList);
    }

    /**
     * @param request request object
     * @return newly created project
     */
    public ResponseObject<Project> addProject(AddProjectRequest request) throws HttpException, HttpBadRequestException {
        ProjectResponseObject projectResponseObject = this.httpClient.post(this.url + "/projects", request, new HttpConfig(), ProjectResponseObject.class);
        return ResponseObject.of(projectResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @return project
     */
    public ResponseObject<Project> getProject(Integer projectId) throws HttpException, HttpBadRequestException {
        ProjectResponseObject projectResponseObject = this.httpClient.get(this.url + "/projects" + projectId, new HttpConfig(), ProjectResponseObject.class);
        return ResponseObject.of(projectResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     */
    public void deleteProject(Integer projectId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects" + projectId, new HttpConfig(), Void.class);
    }

    /**
     * @param request request object
     * @return updated project
     */
    public ResponseObject<Project> editProject(List<PatchOperation> request) throws HttpException, HttpBadRequestException {
        ProjectResponseObject projectResponseObject = this.httpClient.patch(this.url + "/projects", request, new HttpConfig(), ProjectResponseObject.class);
        return ResponseObject.of(projectResponseObject.getData());
    }
}
