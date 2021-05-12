package marcin.grzymowicz.posts_manager.posts.domain.client;

import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PostResourceApiClient {

    private final RestTemplate restTemplate;
    @Value("${posts.resource.api.url}")
    private String apiUrl;

    @Autowired
    public PostResourceApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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
