package marcin.grzymowicz.posts_manager.posts.application.command_handler;

import lombok.RequiredArgsConstructor;
import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPostsListHandler {

    private final PostRepository postRepository;

    public List<Post> handle(String title) {
        List<Post> posts = new ArrayList<Post>();

        posts = (title == null) ? postRepository.findAll() : postRepository.findByTitleContaining(title);

        return posts;
    }
}
