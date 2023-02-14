package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // EntityManagerFactory는 애플리케이션 로딩 시점에 딱 하나만 만들어야 함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // EntityManager는 트랜잭션 단위마다 만들어져야 함 (쓰레드 간 공유하면 안되며, 사용하고 버려야 함)
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장 (단방향)
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            team.addMember(member); // 연관관계 편의 메소드 1
            // member.changeTeam(team); // 연관관계 편의 메소드 2
            em.persist(member);

            // 조회 (양방향 2) 에서 필요 => team에도 member 반영
            // team.getMembers().add(member);

            // em.flush();
            // em.clear();

            // 조회 (단방향)
            // Member findMember = em.find(Member.class, member.getId());
            // Team findTeam = findMember.getTeam();
            // System.out.println("findTeam.name = " + findTeam.getName());

            // 조회 (양방향)
            // List<Member> members = findMember.getTeam().getMembers();
            // for (Member m : members) {
            //     System.out.println("m = " + m.getUsername());
            // }

            // 조회 (양방향 2)
            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            // 회원 등록
            // 비영속
            // Member member1 = new Member(3L, "helloA");
            // Member member2 = new Member(4L, "helloB");
            // 영속
            // em.persist(member1);
            // em.persist(member2);

            // 회원 수정
            // em.persist()를 사용하지 않고 객체의 값만 바꾸어도, JPA에서 객체를 관리하기 때문에, 변경사항이 있으면 자동으로 Update 처리
            // Member findMember = em.find(Member.class, 1L);
            // findMember.setName("HelloJPA");

            // 회원 조회 (JPQL 사용)
            // List<Member> result = em.createQuery("select m from Member as m", Member.class)
            //         .setFirstResult(0) // 페이징 시작점
            //         .setMaxResults(5) // 페이징 Max
            //         .getResultList();
            // for (Member member : result) {
            //     System.out.println("member.name = " + member.getName());
            // }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
