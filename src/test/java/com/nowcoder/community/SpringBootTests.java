package com.nowcoder.community;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;

@SpringBootTest
public class SpringBootTests {

    @BeforeTestClass
    public static void beforeClass() {
        System.out.println("beforeClass");
    }

    @AfterTestClass
    public static void afterClass() {
        System.out.println("afterClass");
    }

    @BeforeEach
    public void before() {
        System.out.println("before");
    }

    @AfterEach
    public void after() {
        System.out.println("after");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

}
