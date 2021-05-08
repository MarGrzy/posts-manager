package marcin.grzymowicz.posts_manager.posts.presentation;

import lombok.AllArgsConstructor;
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

    private final PostRepository postRepository;


}
