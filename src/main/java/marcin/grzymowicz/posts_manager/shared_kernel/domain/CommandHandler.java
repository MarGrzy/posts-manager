package marcin.grzymowicz.posts_manager.shared_kernel.domain;

public abstract class CommandHandler<I, O> {

    public abstract O handle(I input) throws Exception;
}
