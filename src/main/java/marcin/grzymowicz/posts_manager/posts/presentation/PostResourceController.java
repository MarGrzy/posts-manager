package marcin.grzymowicz.posts_manager.posts.presentation;

import lombok.RequiredArgsConstructor;
import marcin.grzymowicz.posts_manager.posts.application.command_handler.GetPostsToDatabaseHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resource")
public class PostResourceController {

    private final GetPostsToDatabaseHandler getPostsToDatabaseHandler;

    @GetMapping("/posts")
    public Boolean getPostsToDatabase() {
        return getPostsToDatabaseHandler.handle();
    }
}
