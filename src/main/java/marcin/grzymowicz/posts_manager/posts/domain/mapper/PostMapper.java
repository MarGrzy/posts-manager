package marcin.grzymowicz.posts_manager.posts.domain.mapper;

import marcin.grzymowicz.posts_manager.posts.domain.command.UpdatePostCmd;
import marcin.grzymowicz.posts_manager.posts.domain.entity.Post;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePostFromCmd(UpdatePostCmd cmd, @MappingTarget Post entity);
}

