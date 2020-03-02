package dev.devzero.api.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dev.devzero.api.common.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("C")
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
}
