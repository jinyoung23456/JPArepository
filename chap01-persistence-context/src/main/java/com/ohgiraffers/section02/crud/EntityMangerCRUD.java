package com.ohgiraffers.section02.crud;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.mapping.DenormalizedTable;

public class EntityMangerCRUD {

    private EntityManager entityManager;

    /* 1. 특정 메뉴 코드로 메뉴 조회하는 기능 */
    public Menu findMenuByMenuCode(int menuCode) {
        entityManager = EntityManagerGenerator.getInstance();
        return entityManager.find(Menu.class, menuCode);

    }

    /* 2. 새로운 메뉴 저장하는 기능 */

    public Long saveAndReturnAllCount(Menu newMenu) {
        entityManager = EntityManagerGenerator.getInstance();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(newMenu);

        entityTransaction.commit();

        return getCount(entityManager);
    }

    /* 3. 메뉴 개수 조회하는 기능 */
    private Long getCount(EntityManager entityManager) {
        // JPQL 문법 -> 나중에 별도 챕터에서 다룸
        return entityManager.createQuery("SELECT COUNT(*) FROM Section02Menu", Long.class).getSingleResult();
    }

    /* 4. 메뉴 이름 수정하는 기능 */
    public Menu modifyMenuName(int menuCode, String menuName) {
        entityManager = EntityManagerGenerator.getInstance();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        foundMenu.setMenuName(menuName);

        transaction.commit();

        return foundMenu;
    }

    /* 5. 특정 메뉴 코드로 메뉴 삭제하는 기능 */
    public Long removeAndReturnAllCount(int menuCode) {
        entityManager = EntityManagerGenerator.getInstance();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.remove(foundMenu);

        transaction.commit();

        return getCount(entityManager);
    }
}
