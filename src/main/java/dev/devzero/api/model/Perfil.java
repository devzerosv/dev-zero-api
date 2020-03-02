package dev.devzero.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dev.devzero.api.common.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table()
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" })
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Perfil implements BaseEntity<Long> {

	private static final long serialVersionUID = -223457852545L;

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = true)
	private Boolean canAdd;

	@Column(nullable = true)
	private Boolean canEdit;

	@Column(nullable = true)
	private Boolean canDelete;

	@Column(nullable = true)
	private Boolean canView;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
	private Role role;

	@OneToMany
	@JoinColumns({ @JoinColumn(name = "PERFIL_ID", referencedColumnName = "ID") })
	private Set<UserPerfil> userPrincipalByUserPerfilSet;

}
