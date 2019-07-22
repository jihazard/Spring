package com.spring.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

    try {
        Team team = new Team();
        team.setName("개발팀");

        em.persist(team);


        Member member = new Member();
        member.setAge(36);
        member.setName("윤지환");
        member.setRole(MemberType.USER);
        member.setRegDate(new Date());
        member.setTeam(team);
        em.persist(member);


        Member findMember = em.find(Member.class, member.getId());


        System.out.println(findMember.toString());
        Team team1 = em.find(Team.class, member.getId());
        System.out.println(team1.toString());

        tx.commit();
    }catch (Exception e){
        tx.rollback();
    }finally {
        emf.close();
    }


    }
}
