package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// @Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long,Member> store = new HashMap<>(); // Map으로 저장.
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // ID마다 member객체를 value로 넣기 위함.
        store.put(member.getId(),member); // 맵에 key와 value 넣기
        return member; // ID와 Name이 담긴 member 객체 반환.
    }

    @Override
    public Optional<Member> findById(Long id) { // Optional 클래스에 담겨져 있는 Member형 반환.
        return Optional.ofNullable(store.get(id)); // Map에서 get은 객체에 대한 value값을 나오게함. member가 나올 것임을 추측 할 수 있음.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 멤버 객체들을 stream형으로 바꿈.
                .filter(member -> member.getName().equals(name)) // 바꾼 stream형에서 member 객체들중에 매개변수 name과 member 객체의 name과 같은 것들만 골라냄.
                .findAny(); // 필터에서 조건에 맞는 여러개의 객체중 한개만 반환.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 객체들을 리스트로 모음.
    }

    public void clearStore(){
        store.clear(); // Test를 할 때 메서드가 임의로 실행되는데 순서에 관계없이 메서드가 실행되도 오류가 안나게 하기 위한 코드.
    }
}


