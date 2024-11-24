package hello.hello_spring.domain;

import jakarta.persistence.*;

@Entity // JPA에서 해당 클래스를 Entity로 지정하기 위해 사용하는 어노테이션.
// 엔티티는 데이터베이스 테이블과 매핑되는 클래스이며 각 객체는 테이블의 행과 매핑.
// 이 어노테이션을 통해 JPA는 해당 클래스가 데이터베이스와 연관된 데이터 객체임을 인식함.
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
    위에 GeneratedValue는

    h2 데이터베이스에서 id bigint genrated by default as identity를 생각해보자.

    데이터들을 삽입한다는 가정 하에, id만 삽입을 안한다 해도

    자동으로 id가 삽입됨. 그래서 GenetatedValue에 IDENTITY를 쓴거임.
     */
    private Long id;

    @Column(name = "username") // DB에 컬럼이름이 username으로 바뀜.
    private String name;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}




