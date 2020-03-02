/***********************************************************************
 * Module:  UserPrincipal.java
 * Author:  Mauricio Saca
 * Purpose: Defines the Class UserPrincipal
 ***********************************************************************/
package dev.devzero.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dev.devzero.api.common.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" })
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class UserPrincipal implements BaseEntity<Long> {

	private static final long serialVersionUID = -5934124525822881508L;

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.TABLE)
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

	// @OneToMany
	// @JoinColumns({ @JoinColumn(name = "USERPRINCIPAL_ID", referencedColumnName =
	// "ID") })
	// Set<UserPerfil> perfilByUserPerfilSet;

	// @OneToMany
	// @JoinColumns({ @JoinColumn(name = "USERPRINCIPAL_ID", referencedColumnName =
	// "ID") })
	// Set<ParticipatingMembers> eventsByParticipatingMembersSet;

//	@OneToMany(mappedBy = "userPrincipal", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Authorities> roles = new ArrayList<>();
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<String> roleName = new ArrayList<>();
//		this.roles.forEach(objRole -> {
//			roleName.add(objRole.getAuthority());
//		});
//		return roleName.stream().map(SimpleGrantedAuthority::new).collect(toList());
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
}
