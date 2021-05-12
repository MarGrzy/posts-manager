package marcin.grzymowicz.posts_manager.posts.domain.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcin.grzymowicz.posts_manager.posts.domain.client.PostResourceApiClient;
import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import marcin.grzymowicz.posts_manager.posts.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class PostsCyclicUpdater {

    private final PostRepository postRepository;
    private final PostResourceApiClient client;

    // Method runs every each day, one minute after midnight
    @Scheduled(cron = "${entity-update.schedule-cron}")
    public void updatePosts() {
        List<Post> currentDataList = postRepository.findAllByIsDeletedIsFalseAndUpdatedAtIsNull();
        List<Long> currentDataIds = currentDataList.stream().map(Post::getId).collect(Collectors.toList());
        List<Post> updateDataList = client.receivePostsListFromApi();
        updateDataList =
                updateDataList.stream().filter(updateData -> currentDataIds.contains(
                        updateData.getId())).collect(Collectors.toList());
        postRepository.saveAll(updateDataList);
        log.info("Posts are successfully updated at time {}", LocalTime.now());
    }
}
