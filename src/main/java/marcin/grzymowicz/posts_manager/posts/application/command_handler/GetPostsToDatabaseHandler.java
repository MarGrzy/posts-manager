package marcin.grzymowicz.posts_manager.posts.application.command_handler;

import marcin.grzymowicz.posts_manager.posts.domain.client.PostResourceApiClient;
import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsToDatabaseHandler {

    private final PostRepository postRepository;
    private final PostResourceApiClient client;

    @Autowired
    public GetPostsToDatabaseHandler(PostRepository postRepository, PostResourceApiClient client) {
        this.postRepository = postRepository;
        this.client = client;
    }

    public Boolean handle() {
        List<Post> posts = client.receivePostsListFromApi();
        if (posts.isEmpty()) { return Boolean.FALSE; }
        postRepository.saveAll(posts);
        return Boolean.TRUE;
    }
}
