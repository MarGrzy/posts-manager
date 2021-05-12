package marcin.grzymowicz.posts_manager.posts.application.command_handler;

import lombok.AllArgsConstructor;
import marcin.grzymowicz.posts_manager.posts.domain.command.UpdatePostCmd;
import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import marcin.grzymowicz.posts_manager.posts.domain.mapper.PostMapper;
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository;
import marcin.grzymowicz.posts_manager.shared_kernel.domain.custom_exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UpdatePostHandler {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public Post handle(UpdatePostCmd cmd) throws EntityNotFoundException {
        Post post = postRepository.findById(cmd.getId()).orElseThrow(EntityNotFoundException::new);
        postMapper.updatePostFromCmd(cmd, post);
        post.update();
        postRepository.save(post);
        return post;
    }
}
