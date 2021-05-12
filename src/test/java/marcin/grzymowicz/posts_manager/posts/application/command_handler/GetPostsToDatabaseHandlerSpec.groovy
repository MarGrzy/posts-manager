package marcin.grzymowicz.posts_manager.posts.application.command_handler

import marcin.grzymowicz.posts_manager.posts.domain.client.PostResourceApiClient
import marcin.grzymowicz.posts_manager.posts.domain.entity.Post
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository
import spock.lang.Specification

class GetPostsToDatabaseHandlerSpec extends Specification {

    private PostRepository postRepository
    private PostResourceApiClient client
    private GetPostsToDatabaseHandler getPostsToDatabaseHandler

    void setup() {
        postRepository = Mock(PostRepository)
        client = Mock(PostResourceApiClient)
        getPostsToDatabaseHandler = new GetPostsToDatabaseHandler(postRepository, client)
    }

    def "GetPostsToDatabaseHandler#handle returns boolean true for not empty list received from Api"() {
        given:
        List<Post> posts = Stub()
        when:
        Boolean result = getPostsToDatabaseHandler.handle()
        then:
        1 * client.receivePostsListFromApi() >> posts
        1 * postRepository.saveAll(posts)
        result == Boolean.TRUE
    }

    def "GetPostsToDatabaseHandler#handle returns boolean false for empty list received from Api"() {
        given:
        List<Post> posts = new ArrayList<Post>()
        when:
        Boolean result = getPostsToDatabaseHandler.handle()
        then:
        1 * client.receivePostsListFromApi() >> posts
        result == Boolean.FALSE
    }
}
