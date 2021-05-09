package marcin.grzymowicz.posts_manager.shared_kernel.domain.utils;

import marcin.grzymowicz.posts_manager.shared_kernel.infrastructure.exception.RestTemplateResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResourceRestTemplateBuilder extends RestTemplateBuilder {

    @Override
    public RestTemplate build() {
        return super.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }
}
