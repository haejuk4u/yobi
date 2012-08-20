package utils;

import models.Issue;
import models.Post;
import models.Role;
import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;
import models.ModelTest;
import models.enumeration.Operation;
import models.enumeration.Resource;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

public class RoleCheckTest extends ModelTest<Role>{
    @Test
    public void permissionCheck() throws Exception {
        // Given
        Long userSessionId1 = 1l;
        Long userSessionId2 = 2l;
        Long projectId1 = 1l;
        Long projectId2 = 3l;
        // When
        boolean result1 = RoleCheck.permissionCheck(userSessionId1, projectId1, Resource.PROJECT_SETTING, Operation.WRITE, null);
        boolean result2 = RoleCheck.permissionCheck(userSessionId2, projectId2, Resource.BOARD_POST, Operation.READ, null);
        // Then
        assertThat(result1).isEqualTo(true);
        assertThat(result2).isEqualTo(false);
    }
    
    @Test
    public void authorCheck() throws Exception {
        // Given
        Long userId1 = 2l;
        Long resourceId1 = 1l;
        Finder<Long, Post> postFinder = new Finder<Long, Post>(Long.class, Post.class);
        Long userId2 = 3l;
        Long resourceId2 = 1l;
        Finder<Long, Issue> issueFinder = new Finder<Long, Issue>(Long.class, Issue.class);
        // When
        boolean result1 = RoleCheck.authorCheck(userId1, resourceId1, postFinder);
        boolean result2 = RoleCheck.authorCheck(userId2, resourceId2, issueFinder);
        // Then
        assertThat(result1).isEqualTo(true);
        assertThat(result2).isEqualTo(false);
    }
}
