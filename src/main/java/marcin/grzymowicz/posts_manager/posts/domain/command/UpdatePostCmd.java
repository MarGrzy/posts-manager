package marcin.grzymowicz.posts_manager.posts.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostCmd {

    private Long id;
    private String title;
    private String body;
}
