package marcin.grzymowicz.posts_manager.posts.presentation;

import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import marcin.grzymowicz.posts_manager.shared_kernel.domain.utils.ResourceRestTemplateBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/resource")
public class PostResourceController {

    @Value("${posts.resource.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new ResourceRestTemplateBuilder().build();

    @GetMapping("/posts")
    public List<Post> getPostsList() {
        Post[] response = restTemplate.getForObject(apiUrl, Post[].class);
        return (response != null) ? Arrays.asList(response) : new ArrayList<Post>();
    }
}
