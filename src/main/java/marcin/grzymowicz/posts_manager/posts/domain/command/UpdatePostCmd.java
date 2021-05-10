package marcin.grzymowicz.posts_manager.posts.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostCmd {

    private Long id;
    private String title;
    private String body;
}
