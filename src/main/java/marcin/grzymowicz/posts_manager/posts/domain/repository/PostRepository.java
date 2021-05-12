package marcin.grzymowicz.posts_manager.posts.domain.repository;

import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingAndIsDeletedIsFalse(String title);
    List<Post> findAllByIsDeletedIsFalse();
    List<Post> findAllByIsDeletedIsFalseAndUpdatedAtIsNull();
}
