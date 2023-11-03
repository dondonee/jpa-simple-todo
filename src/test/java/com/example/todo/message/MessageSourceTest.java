package com.example.todo.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void defaultLang() {
        String result = ms.getMessage("page.addTask", null, null);
        Assertions.assertThat(result).isEqualTo("새로운 TODO 등록");
    }

    @Test
    void enLang() {
        assertThat(ms.getMessage("page.addTask", null, Locale.ENGLISH)).isEqualTo("Add new task");
    }

}
