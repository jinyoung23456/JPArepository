package com.ohgiraffers.jpql.section07.subquery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
public class SubQueryRepositoryTests {

    @Autowired
    private SubQueryRepository subQueryRepository;

    @DisplayName("서브쿼리를 이용한 메뉴 조회 테스트")
    @Test
    public void testSelectWithSubQuery() {
        //given
        String categoryName = "한식";
        //when
        List<Menu> menuList = subQueryRepository.selectWithSubQuery(categoryName);
        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

}
