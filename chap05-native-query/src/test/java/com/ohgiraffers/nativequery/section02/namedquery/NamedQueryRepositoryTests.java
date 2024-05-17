package com.ohgiraffers.nativequery.section02.namedquery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NamedQueryRepositoryTests {

    @Autowired
    private NamedQueryRepository namedQueryRepository;

    @DisplayName("NamedNativeQuery를 이용한 조회 테스트")
    @Test
    public void testSelectByNamedNativeQuery() {
        //given
        //when
        List<Object[]> categoryList
                = namedQueryRepository.selectByNamedNativeQuery();
        //then
        Assertions.assertNotNull(categoryList);
        categoryList.forEach(
                row -> {
                    for(Object col : row) {
                        System.out.print(col + "/");
                    }
                    System.out.println();
                }
        );
    }

}
