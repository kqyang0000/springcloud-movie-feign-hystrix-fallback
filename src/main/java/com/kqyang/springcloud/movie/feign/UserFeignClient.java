package com.kqyang.springcloud.movie.feign;

import com.kqyang.springcloud.movie.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kqyang on 2019/8/23
 */
@FeignClient(name = "springcloud-provider-user", fallback = UserFeignClient.FeignClientFallback.class,
        fallbackFactory = UserFeignClient.FeignClientFallbackFactory.class)
public interface UserFeignClient {

    /**
     * request interface of test by feign
     *
     * @param id
     * @return
     * @auther kqyang
     */
    @GetMapping(value = "/{id}")
    User findById(@PathVariable("id") Long id);

    /**
     * 回退需要实现FeignClient接口
     */
    @Component
    class FeignClientFallback implements UserFeignClient {
        @Override
        public User findById(Long id) {
            User user = new User();
            user.setId(-1L);
            user.setName("默认用户");
            return user;
        }
    }

    /**
     * 回退状态打印
     */
    @Component
    class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
        private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

        @Override
        public UserFeignClient create(final Throwable throwable) {
            return (id -> {
                LOGGER.error("fallback reason: " + throwable);
                User user = new User();
                user.setId(-1L);
                user.setName("默认用户");
                return user;
            });
        }
    }
}