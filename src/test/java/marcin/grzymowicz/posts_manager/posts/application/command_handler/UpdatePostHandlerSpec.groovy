package marcin.grzymowicz.posts_manager.posts.application.command_handler

import marcin.grzymowicz.posts_manager.posts.domain.command.UpdatePostCmd
import marcin.grzymowicz.posts_manager.posts.domain.entity.Post
import marcin.grzymowicz.posts_manager.posts.domain.mapper.PostMapper
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository
import spock.lang.Specification

class UpdatePostHandlerSpec extends Specification {

    private UpdatePostHandler updatePostHandler
    private PostRepository postRepository
    private PostMapper postMapper

    void setup() {
        postMapper = Mock(PostMapper)
        postRepository = Mock(PostRepository)
        updatePostHandler = new UpdatePostHandler(postRepository, postMapper)
    }

    def "UpdatePostHandler#handle returns updated Post for valid input"() {
        given:
        Post post = Spy(Post)
        UpdatePostCmd cmd = Stub(UpdatePostCmd)
        when:
        Post returnedUpdatedPost = updatePostHandler.handle(cmd)
        then:
        1 * postRepository.findById(cmd.getId()) >> Optional.of(post)
        1 * postMapper.updatePostFromCmd(cmd, post)
        1 * post.update()
        1 * postRepository.save(post)
        returnedUpdatedPost == post
    }
}
