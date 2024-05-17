package com.ohgiraffers.jpql.section06.join;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JoinRepositoryTests {

    @Autowired
    public JoinRepository joinRepository;

    @DisplayName("내부조인을 이용한 조회 테스트")
    @Test
    public void testSelectByInnerJoin() {
        //given
        //when
        List<Menu> menuList = joinRepository.selectByInnerJoin();
        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @DisplayName("외부조인을 이용한 조회 테스트")
    @Test
    public void testSelectByOuterJoin() {
        //given
        //when
        List<Object[]> menuList = joinRepository.selectByOuterJoin();
        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(
                row -> {
                    for (Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );
    }

    @DisplayName("컬렉션조인을 이용한 조회 테스트")
    @Test
    public void testSelectByCollectionJoin() {
        //given
        //when
        List<Object[]> categoryList = joinRepository.selectByCollectionJoin();
        //then
        Assertions.assertNotNull(categoryList);
        categoryList.forEach(
                row -> {
                    for (Object col : row) {
                        System.out.print(col + " ");
                    }
                    System.out.println();
                }
        );
    }

    @DisplayName("세타조인을 이용한 조회 테스트")
    @Test
    public void testSelectByThetaJoin() {
        //given
        //when
        List<Object[]> categoryList = joinRepository.selectByThetaJoin();
        //then
        Assertions.assertNotNull(categoryList);
        categoryList.forEach(
                row -> {
                    for (Object col : row) {
                        System.out.print(col + " ");
                    }
                    System.out.println();
                }
        );
    }


    @DisplayName("페치조인을 이용한 조회 테스트")
    @Test
    public void testSelectByFetchJoin() {
        //given
        //when
        List<Menu> menuList = joinRepository.selectByFetchJoin();
        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

}