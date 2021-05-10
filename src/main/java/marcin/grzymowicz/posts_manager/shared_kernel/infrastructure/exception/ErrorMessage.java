package marcin.grzymowicz.posts_manager.shared_kernel.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ErrorMessage {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private String description;
}
