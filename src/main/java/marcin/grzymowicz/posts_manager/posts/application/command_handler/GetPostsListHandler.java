package marcin.grzymowicz.posts_manager.posts.application.command_handler;

import lombok.AllArgsConstructor;
import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository;
import marcin.grzymowicz.posts_manager.shared_kernel.domain.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetPostsListHandler extends CommandHandler<String, List<Post>> {

    private final PostRepository postRepository;

    @Override
    public List<Post> handle(String title) {
        List<Post> posts = new ArrayList<Post>();

        posts = (title == null) ? postRepository.findAll() : postRepository.findByTitleContaining(title);

        return posts;
    }
}
