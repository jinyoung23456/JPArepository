package com.ohgiraffers.jpql.section03.projection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProjectionTests {

    @Autowired
    private ProjectionService projectionService;

    @Autowired
    ProjectionRepository projectionRepository;

    @DisplayName("단일 엔티티 프로젝션")
    @Test
    void testSingleEntityProjection() {
        List<Menu> menuList = projectionService.singleEntityProjection();
        assertNotNull(menuList);
    }

    @DisplayName("임베디드 타입 프로젝션")
    @Test
    void  testEmbeddedTypeProjection() {
        List<MenuInfo> menuInfoList = projectionRepository.embeddedTypeProjection();
        assertNotNull(menuInfoList);
    }

    @DisplayName("스칼라 타입 프로젝션")
    @Test
    void testScalarTypeProjection() {
        List<Object[]> categoryList = projectionRepository.scalarTypeProjection();
        assertNotNull(categoryList);
        categoryList.forEach(
                row -> {
                    for(Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );
    }

    @DisplayName("new 명령어를 활용한 프로젝션")
    @Test
    void testNewCommandProjection() {
        List<CategoryInfo> categoryInfoList = projectionRepository.newCommandProjection();
        assertNotNull(categoryInfoList);
        categoryInfoList.forEach(System.out::println);
    }
}
