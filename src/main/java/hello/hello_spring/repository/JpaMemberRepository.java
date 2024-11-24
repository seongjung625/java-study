package hello.hello_spring.repository;
import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;


import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;
    /*
    JPA는 EntityManager라는 걸로 모든게 동작 된다.
    EntityManager는 JPA에서 엔티티 객체를 생성, 수정, 삭제 , 조회 하는등의 작업을 담당하는 중앙 관리 객체임.
    EntityManager를 통해 데이터베이스와 상호작용하며, 엔티티 객체의 상태를 관리함.
     */


    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 엔티티를 영속성 컨텍스트에 저장하고, 데이터베이스에 INSERT 쿼리를 실행함.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member=em.find(Member.class, id); // 데이터 조회 (조회 할 타입, 식별자 pk값)
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
       List<Member> result = em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();

       return result.stream().findAny();

    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m",Member.class)
                .getResultList();
        return result;

        // 보통은 테이블을 대상으로 sql을 날림. 객체를 대상으로 쿼리를 날림.
        // select m from Member m Member 자체를 select
    }


}