/***********************************************************************
 * Module:  User.java
 * Author:  Mauricio Saca
 * Purpose: Defines the Class User
 ***********************************************************************/
package dev.devzero.api.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dev.devzero.api.common.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance
@DiscriminatorColumn(name = "USER_TYPE_PROFILE")
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" })
@Getter
@Setter
public abstract class User implements BaseEntity<Long> {

	private static final long serialVersionUID = -5934124525822881508L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(length = 255, nullable = true)
	private String username;

	@Column(length = 255, nullable = true)
	private String password;

	@Column(length = 255, nullable = true)
	private String email;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dateJoined;

	@Column(nullable = true)
	private byte[] picture;

	@Column(nullable = false)
	private Boolean enabled;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID") })
	private Address address;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserPerfil> perfilByUserPerfilSet;

}
