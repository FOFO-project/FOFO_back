package com.fofo.core.member.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {

    @NotNull
    @CreatedDate
    protected LocalDateTime createTime;

    @NotNull
    @LastModifiedDate
    protected LocalDateTime updateTime;
}
