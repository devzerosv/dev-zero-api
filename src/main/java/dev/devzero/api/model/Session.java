package dev.devzero.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table()
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" })
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Session implements BaseEntity<Long> {

	private static final long serialVersionUID = 156079308141137L;

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date accessDate;

	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startSession;

	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endSession;

	@Column(length = 255, nullable = true)
	private String sessionId;

	@Column(nullable = true)
	private Boolean sessionActive;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "USERPRINCIPAL_ID", referencedColumnName = "ID") })
	private UserPrincipal userPrincipal;

}
