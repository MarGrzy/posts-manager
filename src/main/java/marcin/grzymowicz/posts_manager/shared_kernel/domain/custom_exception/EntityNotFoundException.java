package marcin.grzymowicz.posts_manager.shared_kernel.domain.custom_exception;

public class EntityNotFoundException extends Exception {
  private static final String MESSAGE = "ENTITY NOT FOUND";

  public EntityNotFoundException() {
    super(MESSAGE);
  }

  public EntityNotFoundException(String message) {
    super(message);
  }
}
