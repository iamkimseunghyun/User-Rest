package io.laaf.rest.service.logic;

import io.laaf.rest.entity.User;
import io.laaf.rest.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceLogicTest {

    @Autowired
    private UserService userService;

    private User kim;
    private User lee;

    @BeforeEach
    public void startUp() {
        // 테스트를 위한 기본 데이터 셋팅
        this.kim = new User("Kim", "kim@noridan.org");
        this.lee = new User("Lee", "lee@noridan.org");
        this.userService.register(kim);
        this.userService.register(lee);
    }

    @Test
    void registerTest() {
        User sample = User.sample();
        assertThat(this.userService.register(sample)).isEqualTo(sample.getId());
        assertThat(this.userService.findAll().size()).isEqualTo(3);
        this.userService.remove(sample.getId());
    }

    @Test
    void find() {
        assertThat(this.userService.find(lee.getId())).isNotNull();
        assertThat(this.userService.find(lee.getId()).getEmail()).isEqualTo(lee.getEmail());
    }

    @AfterEach
    void cleanUp() {
        // 테스트가 끝난 후에 데이터 셋팅
        this.userService.remove(kim.getId());
        this.userService.remove(lee.getId());
    }

}