package hellojpa;

import org.hibernate.Hibernate;

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

            /**
             * 회원 등록
             */
            // 비영속
            // Member member1 = new Member(3L, "helloA");
            // Member member2 = new Member(4L, "helloB");
            // 영속
            // em.persist(member1);
            // em.persist(member2);

            /**
             * 회원 수정
             */
            // em.persist()를 사용하지 않고 객체의 값만 바꾸어도, JPA에서 객체를 관리하기 때문에, 변경사항이 있으면 자동으로 Update 처리
            // Member findMember = em.find(Member.class, 1L);
            // findMember.setName("HelloJPA");

            /**
             * 회원 조회 (JPQL 사용)
             */
            // List<Member> result = em.createQuery("select m from Member as m", Member.class)
            //         .setFirstResult(0) // 페이징 시작점
            //         .setMaxResults(5) // 페이징 Max
            //         .getResultList();
            // for (Member member : result) {
            //     System.out.println("member.name = " + member.getName());
            // }

            /**
             * 저장 (단방향)
             */
            // Team team = new Team();
            // team.setName("TeamA");
            // em.persist(team);

            // Member member = new Member();
            // member.setUsername("member1");
            // team.addMember(member); // 연관관계 편의 메소드 1
            // member.changeTeam(team); // 연관관계 편의 메소드 2
            // em.persist(member);

            // 조회 (양방향 2) 에서 필요 => team에도 member 반영
            // team.getMembers().add(member);

            // em.flush();
            // em.clear();

            /**
             * 조회 (단방향)
             */
            // Member findMember = em.find(Member.class, member.getId());
            // Team findTeam = findMember.getTeam();
            // System.out.println("findTeam.name = " + findTeam.getName());

            /**
             * 조회 (양방향)
             */
            // List<Member> members = findMember.getTeam().getMembers();
            // for (Member m : members) {
            //     System.out.println("m = " + m.getUsername());
            // }

            /**
             * 조회 (양방향 2)
             */
            // Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
            // List<Member> members = findTeam.getMembers();
            // for (Member m : members) {
            //     System.out.println("m.getUsername() = " + m.getUsername());
            // }

            /**
             * 상속 관계 테스트
             */
            // Movie movie = new Movie();
            // movie.setDirector("aaa");
            // movie.setActor("bbb");
            // movie.setName("바람과함께사라지다");
            // movie.setPrice(10000);
            //
            // em.persist(movie);
            //
            // em.flush();
            // em.clear();
            //
            // Movie findMovie = em.find(Movie.class, movie.getId());
            // System.out.println("findMovie = " + findMovie);

            /**
             * 프록시 테스트
             */
            // // Member findMember = em.find(Member.class, member.getId());
            // Member findMember = em.getReference(Member.class, member1.getId()); // findMember는 프록시 객체(가짜 클래스)
            // System.out.println("findMember.getId() = " + findMember.getId());
            // // getReference를 사용하면, 이 시점에 쿼리를 날림
            // System.out.println("findMember.getUsername() = " + findMember.getUsername());

            // Member findMember1 = em.find(Member.class, member1.getId());
            // Member findMember2 = em.find(Member.class, member2.getId());
            //
            // // == true
            // logic(findMember1, findMember2);

            // Member findMember1 = em.find(Member.class, member1.getId());
            // Member findMember2 = em.getReference(Member.class, member2.getId());
            //
            // // == false
            // logic(findMember1, findMember2);

            /**
             * 프록시 테스트 2
             */
            // Member refMember = em.getReference(Member.class, member1.getId());
            // System.out.println("refMember.getClass() = " + refMember.getClass()); // Proxy

            // Member findMember = em.find(Member.class, member1.getId());
            // System.out.println("findMember.getClass() = " + findMember.getClass()); // Member X, Proxy O
            //
            // // JPA는 아래를 참으로 보장해줌
            // System.out.println("(refMember == findMember) = " + (refMember == findMember));

            // em.detach(refMember); // 영속성 컨텍스트에서 제거
            //
            // refMember.getUsername(); // 영속성 컨텍스트에서 제거되었으나, 초기화를 요청하는 경우 에러 발생

            // 영속성 컨텍스트에 해당 엔티티가 있는지 확인
            // System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

            // 강제 초기화
            // Hibernate.initialize(refMember);

            /**
             * 지연로딩 테스트
             */
            // Team team = new Team();
            // team.setName("team1");
            // em.persist(team);
            //
            // Member member1 = new Member();
            // member1.setUsername("member1");
            // member1.setTeam(team);
            // em.persist(member1);
            //
            // em.flush();
            // em.clear();
            //
            // Member m = em.find(Member.class, member1.getId());
            //
            // System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass()); // team은 프록시로 가져옴
            //
            // System.out.println("================================");
            // m.getTeam().getName(); // 프록시 객체 초기화, team을 DB에 접근해서 가져옴
            // System.out.println("================================");

            /**
             * 영속성 전이 테스트
             */
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            // cascade = CascadeType.ALL 설정으로, parent만 persist 했지만, child1과 child2도 persist cascade
            em.persist(parent);

            em.flush();
            em.clear();

            // orphanRemoval = true 설덩으로, parent가 삭제되면 child도 삭제함
            Parent findParent = em.find(Parent.class, parent.getId());
            em.remove(findParent);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(Member member1, Member member2) {
        // System.out.println("(findMember1.getClass() == findMember2.getClass()) = " + (member1.getClass() == member2.getClass()));
        System.out.println("(member1 instanceof Member) = " + (member1 instanceof Member));
        System.out.println("(member2 instanceof Member) = " + (member2 instanceof Member));
    }
}
