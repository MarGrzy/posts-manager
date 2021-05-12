package marcin.grzymowicz.posts_manager.posts.application.command_handler

import marcin.grzymowicz.posts_manager.posts.domain.entity.Post
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository
import spock.lang.Specification

class DeletePostHandlerSpec extends Specification {

    private DeletePostHandler deletePostHandler
    private PostRepository postRepository
    static final Long id = 1

    void setup() {
        postRepository = Mock(PostRepository)
        deletePostHandler = new DeletePostHandler(postRepository)
    }

    def "DeletePostHandler#handle returns boolean true and delete Post for valid input"() {
        given:
        Post post = Spy()
        when:
        Boolean result = deletePostHandler.handle(1)
        then:
        1 * postRepository.findById(id) >> Optional.of(post)
        1 * post.delete()
        1 * postRepository.save(post)
        result == Boolean.TRUE
    }
}
