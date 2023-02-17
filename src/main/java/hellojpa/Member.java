package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    // // 기간
    // @Embedded
    // private Period workPeriod;

    // 집 주소
    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID")) // 실제 DB 테이블 명 & FK 컬럼 명
    @Column(name = "FOOD_NAME") // 예외적으로 값이 하나이기 때문에 @Column을 통해 매핑이 가능하도록 설정
    private Set<String> favoriteFoods = new HashSet<>();

    // // 사실 아래의 코드는 권장되지 않음 (일대다 관계 사용을 고려해야 함)
    // @ElementCollection
    // @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID")) // 실제 DB 테이블 명 & FK 컬럼 명
    // private List<Address> addressHistory = new ArrayList<>();

    // 일대다 단방향으로 개선
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    // // 회사 주소
    // @Embedded
    // @AttributeOverrides({@AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
    //         @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET")),
    //         @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE"))})
    // private Address workAddress;

    // @ManyToOne(fetch = FetchType.LAZY) // team을 프록시 객체로 조회
    // @JoinColumn(name = "TEAM_ID")
    // private Team team;

    // @OneToOne
    // @JoinColumn(name = "LOCKER_ID")
    // private Locker locker;

    // @OneToMany(mappedBy = "member")
    // private List<MemberProduct> memberProducts = new ArrayList<>();

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

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    // public List<Address> getAddressHistory() {
    //     return addressHistory;
    // }
    //
    // public void setAddressHistory(List<Address> addressHistory) {
    //     this.addressHistory = addressHistory;
    // }

    // public Period getWorkPeriod() {
    //     return workPeriod;
    // }
    //
    // public void setWorkPeriod(Period workPeriod) {
    //     this.workPeriod = workPeriod;
    // }
    //
    // public Address getHomeAddress() {
    //     return homeAddress;
    // }
    //
    // public void setHomeAddress(Address homeAddress) {
    //     this.homeAddress = homeAddress;
    // }
    //
    // public Address getWorkAddress() {
    //     return workAddress;
    // }
    //
    // public void setWorkAddress(Address workAddress) {
    //     this.workAddress = workAddress;
    // }


    // public Team getTeam() {
    //     return team;
    // }
    //
    // public void setTeam(Team team) {
    //     this.team = team;
    // }

    // public Locker getLocker() {
    //     return locker;
    // }
    //
    // public void setLocker(Locker locker) {
    //     this.locker = locker;
    // }

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
