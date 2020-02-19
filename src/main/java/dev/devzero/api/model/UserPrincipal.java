/***********************************************************************
 * Module:  UserPrincipal.java
 * Author:  Mauricio Saca
 * Purpose: Defines the Class UserPrincipal
 ***********************************************************************/
package dev.devzero.api.model;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.devzero.api.common.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" })
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails, BaseEntity<Long> {

	private static final long serialVersionUID = -5934124525822881508L;

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String lastName;

	@JsonIgnore
	@NotEmpty
	@Column(nullable = false)
	private String username;

	@JsonIgnore
	@NotEmpty
	@Column(nullable = false)
	private String password;

	@JsonIgnore
	@Column(nullable = false)
	private Boolean enabled;

	@Column(nullable = true)
	private String email;

	@OneToMany(mappedBy = "userPrincipal", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Authorities> roles = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> roleName = new ArrayList<>();
		this.roles.forEach(objRole -> {
			roleName.add(objRole.getAuthority());
		});
		return roleName.stream().map(SimpleGrantedAuthority::new).collect(toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
