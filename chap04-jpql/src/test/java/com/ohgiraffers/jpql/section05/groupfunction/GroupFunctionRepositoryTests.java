package com.ohgiraffers.jpql.section05.groupfunction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GroupFunctionRepositoryTests {

    @Autowired
    private GroupFunctionRepository groupFunctionRepository;

    @DisplayName("특정 카테고리에 등록 된 메뉴 수 조회")
    @Test
    void testCountMenuOfCategory() {
        //given
        int categoryCode = 555;
        //when
        long countOfMenu = groupFunctionRepository.countMenuOfCategory(categoryCode);
        //then
        Assertions.assertTrue(countOfMenu >= 0);
        System.out.println("[ countOfMenu ] " + countOfMenu);
    }

    @DisplayName("COUNT를 제외한 다른 그룹 함수의 조회결과가 없는 경우 테스트")
    @Test
    public void testOthersWithNoResult() {
        //given
        int categoryCode = 555;
        //when
//        long sumOfMenu = groupFunctionRepository.otherWithNoResult(categoryCode);
//        //then
//        Assertions.assertTrue(sumOfMenu >= 0);
        Assertions.assertDoesNotThrow(
                () -> {
                    Long sumOfMenu = groupFunctionRepository.otherWithNoResult(categoryCode);
                    System.out.println("sumOfMenu = " + sumOfMenu);
                }
        );
    }

    @DisplayName("GROUP BY절과 HAVING절을 사용한 조회 테스트")
    @Test
    public void testSelectByGroupbyHaving() {
        //given
//         int minPrice = 50000;
        long minPrice = 50000L;
        //when
        List<Object[]> sumPriceOfCategoryList
                = groupFunctionRepository.selectByGroupByHaving(minPrice);
        //then
        Assertions.assertNotNull(sumPriceOfCategoryList);

    }
}