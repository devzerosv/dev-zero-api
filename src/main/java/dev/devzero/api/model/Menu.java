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
public class Menu implements BaseEntity<Long> {

	private static final long serialVersionUID = -267864531L;

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(length = 255, nullable = true)
	private String displayNameOption;

	@Column(length = 255, nullable = true)
	private String url;

	@Column(length = 255, nullable = true)
	private String viewName;

	@Column(nullable = true)
	private Integer menuOrder;

	@Column(nullable = true)
	private byte[] icon;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "MENU_PARENT", referencedColumnName = "ID") })
	private Menu menu;

	@OneToMany(mappedBy = "menu")
	private Set<RolMenu> roleByRolMenuSet;

}
