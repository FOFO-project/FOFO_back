package com.fofo.core.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter @Setter
public class MemberImage {
    @Id @GeneratedValue
    @Column(name = "member_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    private String type;

    private String imageUrl;

    private char status;

    private Timestamp createdTime;

    private Timestamp updatedTime;
}
