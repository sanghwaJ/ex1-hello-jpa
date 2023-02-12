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
            // 회원 등록
            // Member member = new Member();
            // member.setId(1L);
            // member.setName("hello");
            // em.persist(member);

            // 회원 수정
            // em.persist()를 사용하지 않고 객체의 값만 바꾸어도, JPA에서 객체를 관리하기 때문에, 변경사항이 있으면 자동으로 Update 처리
            // Member findMember = em.find(Member.class, 1L);
            // findMember.setName("HelloJPA");

            // 회원 조회 (JPQL 사용)
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0) // 페이징 시작점
                    .setMaxResults(5) // 페이징 Max
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
