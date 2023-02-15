package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
// @SequenceGenerator(
//         name = "MEMBER_SEQ_GENERATOR",
//         sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
//         initialValue = 1, allocationSize = 1)
public class Member {
    // PK
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    // 연관관계 편의 메소드 2
    // public void changeTeam(Team team) {
    //     this.team = team;
    //     team.getMembers().add(this);
    // }

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
    // public Member() {
    // }
}
