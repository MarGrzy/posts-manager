package marcin.grzymowicz.posts_manager.posts.application.command_handler

import marcin.grzymowicz.posts_manager.posts.domain.entity.Post
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository
import spock.lang.Specification

class GetPostsListHandlerSpec extends Specification {

    private GetPostsListHandler getPostsListHandler;
    private PostRepository postRepository;
    static final String title = "dolorem"

    void setup() {
        postRepository = Mock(PostRepository)
        getPostsListHandler = new GetPostsListHandler(postRepository)
    }

    def "GetPostsListHandler#handle without parameter returns Posts for valid input"() {
        given:
        List<Post> posts = Stub()
        when:
        List<Post> result = getPostsListHandler.handle(null)
        then:
        1 * postRepository.findAllByIsDeletedIsFalse() >> posts
        result == posts
    }

    def "GetPostsListHandler#handle with (String) title parameter returns Posts for valid input"() {
        given:
        List<Post> posts = Stub()
        when:
        List<Post> result = getPostsListHandler.handle(title)
        then:
        1 * postRepository.findByTitleContainingAndIsDeletedIsFalse(title) >> posts
        result == posts
    }
}
