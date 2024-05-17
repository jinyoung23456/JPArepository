package com.ohgiraffers.mapping.section01.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Member member) {
        entityManager.persist(member);
    }

    public String findNameById(String memberId) {
        String jpql = "SELECT m.memberName From entityMember m WHERE m.memberId = '" + memberId + "'";
        return entityManager.createQuery(jpql, String.class).getSingleResult();
    }
}
