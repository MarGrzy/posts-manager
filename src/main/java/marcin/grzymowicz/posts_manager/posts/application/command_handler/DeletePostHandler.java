package marcin.grzymowicz.posts_manager.posts.application.command_handler;

import lombok.AllArgsConstructor;
import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository;
import marcin.grzymowicz.posts_manager.shared_kernel.domain.custom_exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeletePostHandler {

    private final PostRepository postRepository;

    public Boolean handle(Long id) throws EntityNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        post.delete();
        postRepository.save(post);
        return Boolean.TRUE;
    }
}
