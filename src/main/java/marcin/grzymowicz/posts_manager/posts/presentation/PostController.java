package marcin.grzymowicz.posts_manager.posts.presentation;

import lombok.RequiredArgsConstructor;
import marcin.grzymowicz.posts_manager.posts.application.command_handler.GetPostsListHandler;
import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final GetPostsListHandler getPostsListHandler;

    @GetMapping(value = "/list")
    public ResponseEntity<List<Post>> getList(@RequestParam(required = false) String title) {
        List<Post> posts = getPostsListHandler.handle(title);
        return (Objects.isNull(posts) || posts.isEmpty()) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                ResponseEntity.ok(posts);
    }

}
