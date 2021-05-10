package marcin.grzymowicz.posts_manager.posts.application.command_handler;

import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository;
import marcin.grzymowicz.posts_manager.shared_kernel.domain.utils.ResourceRestTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GetPostsToDatabaseHandler {

    @Value("${posts.resource.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate = new ResourceRestTemplateBuilder().build();

    private final PostRepository postRepository;

    @Autowired
    public GetPostsToDatabaseHandler(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Boolean handle() {
        Post[] response;
        try {
            response = restTemplate.getForObject(apiUrl, Post[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        List<Post> posts = (response != null && response.length > 0)
                ? Arrays.asList(response)
                : new ArrayList<Post>();
        postRepository.saveAll(posts);
        return Boolean.TRUE;
    }
}
