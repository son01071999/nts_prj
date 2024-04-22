package com.code.nts_prj.entity;

import com.code.nts_prj.enums.Role;
import com.code.nts_prj.validation.PhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class AccountEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;

	@Column(nullable = false, unique = true)
	@Size(min = 6, max = 20)
	private String userName;

	@Column(nullable = false)
	private String password;

	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(unique = true)
	@PhoneNumber
	private String phoneNumber;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer age;

}
