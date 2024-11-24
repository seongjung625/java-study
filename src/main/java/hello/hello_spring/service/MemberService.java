package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service // Spring이 올라올 때 스프링이 스프링 컨테이너에 멤버 서비스를 딱 등록해준다.
@Transactional // JPA는 모든 데이터 변경이 Transactional에서 실행 되야한다.
public class MemberService{
    // private final MemberRepository memberRepository = new MemoryMemberRepository(); // 객체를 새로 생성해서 MemberServiceTest에 있는 MemoryMemberRepository와 다름.
    private final MemberRepository memberRepository;

    //  @Autowired // 스프링이 생성을 할 때 스프링이 서비스를 보고 스프링 컨테이너에 등록을 하면서 생성자를 또 호출한다.
    // 생성자를 호출하고나서 memberRepository가 필요하다는걸 알 수 있음. 스프링 컨테이너에 있는 memberRepository를 넣어줌.
    public MemberService(MemberRepository memberRepository){ // DI (Dependency Injection)
        this.memberRepository=memberRepository;
    }

    /*
    회원 가입
     */

    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 X.
        validateDuplicateMember(member); // 중복 회원 검증.
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result= memberRepository.findByName(member.getName());
        // Optional은 Optional안에 member라는 객체가 있는것.
        result.ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다.");});
    }

    /*
    전체 회원 조회.
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}







