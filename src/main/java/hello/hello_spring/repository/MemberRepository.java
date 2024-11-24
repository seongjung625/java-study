package hello.hello_spring.repository;
import hello.hello_spring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

    /*
    Optional은 findById, findByName을 통해서 정보를 가져올텐데 null일수도 있음.
    null을 반환하는것보다 Optional이라는걸로 감싸서 받음.
     */

}





