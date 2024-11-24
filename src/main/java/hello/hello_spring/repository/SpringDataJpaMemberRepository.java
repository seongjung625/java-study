
package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);

    // interface만 있으면 스프링 데이터 JPA가 JAP 리포지토리를 받고 있으면 구현체를 자동으로 만들어줌.
    // 스프링 빈에 자동으로 등록함.
    // 스프링 빈에 내가 직접 등록하는게 아니라 스프링 데이터 JPA가 구현체를 만들어서 등록해줌.
    // 그래서 그냥 가져다가 쓰면 된다.
}

