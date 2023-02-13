package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 1)
public class Member {
    @Id // PK
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name", nullable = false) // DB Column 명이 name
    private String username;

    // private Integer age;
    //
    // @Enumerated(EnumType.STRING)
    // private RoleType roleType;

    // @Temporal(TemporalType.TIMESTAMP) // DB는 날짜 관련 데이터가 DATE, TIME, DATETIME이 있음
    // private Date createdDate;
    //
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date lastModifiedDate;

    // private LocalDate testLocalDate; // 년, 월, 일 (DATE)
    // private LocalDateTime testLocalDateTime; // 년, 월, 일, 시간 (DATETIME)
    //
    // @Lob // varchar를 넘어서는 큰 데이터
    // private String description;

    // JPA는 동적으로 객체를 생성하기 때문에, 아래와 같은 기본 생성자가 필요함
    public Member() {
    }

    // public Long getId() {
    //     return id;
    // }
    //
    // public void setId(Long id) {
    //     this.id = id;
    // }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // public Integer getAge() {
    //     return age;
    // }
    //
    // public void setAge(Integer age) {
    //     this.age = age;
    // }
    //
    // public RoleType getRoleType() {
    //     return roleType;
    // }
    //
    // public void setRoleType(RoleType roleType) {
    //     this.roleType = roleType;
    // }
    //
    // public Date getCreatedDate() {
    //     return createdDate;
    // }
    //
    // public void setCreatedDate(Date createdDate) {
    //     this.createdDate = createdDate;
    // }
    //
    // public Date getLastModifiedDate() {
    //     return lastModifiedDate;
    // }
    //
    // public void setLastModifiedDate(Date lastModifiedDate) {
    //     this.lastModifiedDate = lastModifiedDate;
    // }
    //
    // public String getDescription() {
    //     return description;
    // }
    //
    // public void setDescription(String description) {
    //     this.description = description;
    // }
}
