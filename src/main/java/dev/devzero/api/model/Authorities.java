/***********************************************************************
 * Module:  Authorities.java
 * Author:  Mauricio Saca
 * Purpose: Defines the Class Authorities
 ***********************************************************************/
package dev.devzero.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import dev.devzero.api.common.BaseEntity;
import dev.devzero.api.enums.RoleEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "authorities")
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" })
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Authorities implements BaseEntity<Long> {

	private static final long serialVersionUID = -4511453175584683904L;

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String authority;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME", nullable = false) })
	@NotFound(action = NotFoundAction.IGNORE)
	private UserPrincipal userPrincipal;

	// Relation by enum

	public RoleEnum getRole() {
		return RoleEnum.getRoleEnum(this.authority);
	}

	public void setRole(RoleEnum role) {
		this.authority = role != null ? role.getCode() : null;
	}

}
