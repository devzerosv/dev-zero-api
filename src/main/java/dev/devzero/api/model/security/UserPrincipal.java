package dev.devzero.api.model.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dev.devzero.api.model.Role;
import dev.devzero.api.model.User;
import dev.devzero.api.model.UserPerfil;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = -2465468732137889L;

	@NonNull
	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		if (isAuthenticateUser()) {
			authorities = getUserAuthorities();
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return this.user.getEnabled();
	}

	private boolean isAuthenticateUser() {
		boolean isValid = false;
		if (this.user != null) {
			boolean isNotNullAndNotEmptyProfile = this.user.getPerfilByUserPerfilSet() != null
					&& !this.user.getPerfilByUserPerfilSet().isEmpty();
			if (isNotNullAndNotEmptyProfile) {
				isValid = true;
			}

		}
		return isValid;
	}

	private List<GrantedAuthority> getUserAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		this.user.getPerfilByUserPerfilSet().forEach(profile -> {
			GrantedAuthority profileAuthority = getUserProfile(profile);
			GrantedAuthority roleAuthority = getUserProfileRole(profile);
			authorities.add(profileAuthority);
			authorities.add(roleAuthority);
		});

		return authorities;
	}

	private GrantedAuthority getUserProfile(UserPerfil userProfile) {
		GrantedAuthority profileAuthority = new SimpleGrantedAuthority(userProfile.getPerfil().getProfileName());
		return profileAuthority;
	}

	private GrantedAuthority getUserProfileRole(UserPerfil userProfile) {
		Role userRole = userProfile.getPerfil().getRole();
		GrantedAuthority roleAuthority = new SimpleGrantedAuthority(userRole.getRoleName());
		return roleAuthority;
	}

}
