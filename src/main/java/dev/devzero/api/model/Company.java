package dev.devzero.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dev.devzero.api.common.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table()
@ToString
@Getter
@Setter
@RequiredArgsConstructor
public class Company extends UserPrincipal implements BaseEntity<Long> {

	private static final long serialVersionUID = 769505734164091L;

	@Column(length = 255, nullable = true)
	private String name;

	@Column(length = 255, nullable = true)
	private String type;

	@Column(length = 255, nullable = true)
	private String description;

	@Column(length = 255, nullable = true)
	private String rubro;

	@Column(length = 255, nullable = true)
	private String phoneContact;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID") })
	private Address address;
}
