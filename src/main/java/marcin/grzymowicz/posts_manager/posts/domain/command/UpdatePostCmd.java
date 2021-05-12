package marcin.grzymowicz.posts_manager.posts.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostCmd {

    @NotNull(message = "{id.notNull}")
    @Positive(message = "{id.positive}")
    private Long id;

    @NotNull(message = "{title.notNull}")
    @NotBlank(message = "{title.notBlank}")
    private String title;

    @NotNull(message = "{body.notNull}")
    @NotBlank(message = "{body.notBlank}")
    private String body;
}
