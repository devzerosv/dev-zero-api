package dev.devzero.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dev.devzero.api.common.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("P")
@ToString
@Getter
@Setter
@RequiredArgsConstructor
public class Person extends User implements BaseEntity<Long> {

	private static final long serialVersionUID = 222434942787217L;

	@Column(length = 255, nullable = true)
	private String firstName;

	@Column(length = 255, nullable = true)
	private String middleName;

	@Column(length = 255, nullable = true)
	private String lastName;

	@Column(length = 255, nullable = true)
	private String titularName;

	@Column(length = 255, nullable = true)
	private String gender;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	/*
	 * 
	 * 
	 * @OneToMany
	 * 
	 * @JoinColumns({ @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
	 * }) Set<PersonSoftSkills> softSkillByPersonSoftSkillsSet;
	 * 
	 * @OneToMany(cascade = { CascadeType.ALL })
	 * 
	 * @JoinColumns({ @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
	 * }) Set<ProyectExperiences> proyectExperiencesSet;
	 * 
	 * @OneToMany(cascade = { CascadeType.ALL })
	 * 
	 * @JoinColumns({ @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
	 * }) Set<Education> educationSet;
	 * 
	 * @OneToMany(cascade = { CascadeType.ALL })
	 * 
	 * @JoinColumns({ @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
	 * }) Set<Interests> interestsSet;
	 * 
	 * @OneToMany
	 * 
	 * @JoinColumns({ @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
	 * }) Set<PersonTecnicalSkills> tecnicalSkillByPersonTecnicalSkillsSet;
	 */

}
