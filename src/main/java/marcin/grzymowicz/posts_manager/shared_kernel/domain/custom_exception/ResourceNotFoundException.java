package marcin.grzymowicz.posts_manager.shared_kernel.domain.custom_exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final String MESSAGE = "RESOURCE NOT FOUND";

    public ResourceNotFoundException() { super(MESSAGE); }

    public ResourceNotFoundException(String message) { super(message); }
}
