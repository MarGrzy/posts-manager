package marcin.grzymowicz.posts_manager.posts.domain.service

import marcin.grzymowicz.posts_manager.posts.domain.client.PostResourceApiClient
import marcin.grzymowicz.posts_manager.posts.domain.entity.Post
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository
import spock.lang.Specification

class PostsCyclicUpdaterSpec extends Specification {

    private PostRepository postRepository
    private PostResourceApiClient client
    private PostsCyclicUpdater cyclicUpdater

    void setup() {
        postRepository = Mock(PostRepository)
        client = Mock(PostResourceApiClient)
        cyclicUpdater = new PostsCyclicUpdater(postRepository, client)
    }

    def "PostsCyclicUpdater#updatePosts execute update on List<Post>"() {
        given:
        List<Post> currentData = new ArrayList<Post>()
        Post toUpdate = Stub(Post) {
            getId() >> 1
            getTitle() >> "Test"
        }
        currentData << toUpdate
        List<Post> updateData = new ArrayList<Post>()
        Post updated = Stub(Post) {
            getId() >> 1
            getTitle() >> "UpdatedTest"
        }
        Post toAbandon = Stub(Post) {
            getId() >> 2
            getTitle() >> "AbandonTest"
        }
        updateData << updated
        updateData << toAbandon
        when:
        cyclicUpdater.updatePosts()
        then:
        1 * postRepository.findAllByIsDeletedIsFalseAndUpdatedAtIsNull() >> currentData
        1 * client.receivePostsListFromApi() >> updateData
    }
}
