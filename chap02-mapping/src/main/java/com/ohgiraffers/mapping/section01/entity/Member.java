package com.ohgiraffers.mapping.section01.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "entityMember")
@Table(name = "tbl_member")
@Access(AccessType.FIELD)   // 클래스 레벨에 설정 가능하며 모든 필드를 대상으로 적용하겠다는 의미 -> default
public class Member {
    @Id
    @Column(name = "member_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberNo;

    @Access(AccessType.FIELD)   // 필드 레벨에 설정 가능하며 해당 필드를 대상으로 적용하겠다는 의미
    @Column(
            name = "member_id", unique = true,
            nullable = false, columnDefinition = "varchar(10)"
    )
    private String memberId;
    @Column(name = "member_pwd", nullable = false)
    private String memberPwd;
    @Column(name = "member_name")
    private String memberName;
//    @Transient
    @Column(name = "phone")
    private String phone;
    @Column(name = "address", length = 900)
    private String address;
    @Column(name = "enroll_date")
    private LocalDateTime enrollDate;
    @Column(name = "member_role")
    @Enumerated(EnumType.ORDINAL)
    private MemberRole memberRole;
    @Column(name = "status", columnDefinition = "char(1) default 'Y'")
    private String status;
    protected Member() {}
    public Member(
            String memberId, String memberPwd, String memberName,
            String phone, String address, LocalDateTime enrollDate,
            MemberRole memberRole, String status
    ) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.phone = phone;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }

    @Access(AccessType.PROPERTY)
    public String getMemberName() {
        System.out.println("getMemberName 메소드를 통한 Access 확인");
        return memberName + "님";
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
