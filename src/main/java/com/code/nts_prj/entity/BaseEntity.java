package com.code.nts_prj.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
	@CreatedDate
	@Column(nullable = false, updatable = false)
	protected LocalDateTime createdAt;

	@LastModifiedDate @Column @Builder.Default protected LocalDateTime updatedAt = LocalDateTime.now();

	/**
	 * if have createdUser and updatedUser column // @CreatedBy // @Column(nullable = false, updatable
	 * = false) // protected Long createUser; // // @LastModifiedBy // @Column protected Long
	 * updateUser;
	 */
}
