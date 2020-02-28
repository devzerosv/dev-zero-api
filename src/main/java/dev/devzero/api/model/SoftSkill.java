package dev.devzero.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class SoftSkill implements BaseEntity<Long> {

	private static final long serialVersionUID = 365693743387825L;

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(length = 255, nullable = true)
	private String name;

	@Column(length = 255, nullable = true)
	private String code;

	@Column(length = 255, nullable = true)
	private String description;

}
