package marcin.grzymowicz.posts_manager.posts.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String body;

    @JsonIgnore
    private LocalDateTime updatedAt;

    @JsonIgnore
    private Boolean isDeleted = Boolean.FALSE;

    @JsonIgnore
    private Long userId;

    public void update() {
        this.updatedAt = LocalDateTime.now();
    }

    public void delete() { this.isDeleted = Boolean.TRUE; }
}
