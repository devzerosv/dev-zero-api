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
public class UserPerfil implements BaseEntity<Long> {

	private static final long serialVersionUID = 644173572157501L;

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "USERPRINCIPAL_ID", referencedColumnName = "ID") })
	private UserPrincipal userPrincipal;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "PERFIL_ID", referencedColumnName = "ID") })
	private Perfil perfil;

}
