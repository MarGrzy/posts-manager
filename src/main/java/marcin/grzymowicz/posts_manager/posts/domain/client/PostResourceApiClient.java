package marcin.grzymowicz.posts_manager.posts.domain.client;

import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import marcin.grzymowicz.posts_manager.shared_kernel.domain.utils.ResourceRestTemplateBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PostResourceApiClient {

    @Value("${posts.resource.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate = new ResourceRestTemplateBuilder().build();

    public List<Post> receivePostsListFromApi() {
        Post[] response;
        try {
            response = restTemplate.getForObject(apiUrl, Post[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Post>();
        }
        return (Objects.nonNull(response) && response.length > 0)
                ? Arrays.asList(response)
                : new ArrayList<Post>();
    }
}
