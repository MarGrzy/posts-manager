package marcin.grzymowicz.posts_manager.posts.presentation;

import lombok.RequiredArgsConstructor;
import marcin.grzymowicz.posts_manager.posts.application.command_handler.GetPostsListHandler;
import marcin.grzymowicz.posts_manager.posts.application.command_handler.UpdatePostHandler;
import marcin.grzymowicz.posts_manager.posts.domain.command.UpdatePostCmd;
import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import marcin.grzymowicz.posts_manager.shared_kernel.domain.custom_exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final GetPostsListHandler getPostsListHandler;
    private final UpdatePostHandler updatePostHandler;

    @GetMapping(value = "/list")
    public ResponseEntity<List<Post>> getList(@RequestParam(required = false) String title) {
        List<Post> posts = getPostsListHandler.handle(title);
        return (Objects.isNull(posts) || posts.isEmpty()) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                ResponseEntity.ok(posts);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Post> update(@RequestBody UpdatePostCmd updatePostCmd)
            throws EntityNotFoundException {
        return ResponseEntity.ok(updatePostHandler.handle(updatePostCmd));
    }

}
