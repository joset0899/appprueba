package com.login.almc.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the altd_perm_perf database table.
 * 
 */
@Entity
@Table(name="altd_perm_perf")
public class RolePermiso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_PERM_PERF")
	private int codPermPerf;

	//bi-directional many-to-one association to AlmcPerf
	@ManyToOne
	@JoinColumn(name="COD_PERF")
	private Role almcPerf;

	//bi-directional many-to-one association to AlmcPerm
	@ManyToOne
	@JoinColumn(name="COD_PERM")
	private Permiso almcPerm;

	public RolePermiso() {
	}

	public int getCodPermPerf() {
		return this.codPermPerf;
	}

	public void setCodPermPerf(int codPermPerf) {
		this.codPermPerf = codPermPerf;
	}

	public Role getAlmcPerf() {
		return this.almcPerf;
	}

	public void setAlmcPerf(Role almcPerf) {
		this.almcPerf = almcPerf;
	}

	public Permiso getAlmcPerm() {
		return this.almcPerm;
	}

	public void setAlmcPerm(Permiso almcPerm) {
		this.almcPerm = almcPerm;
	}

}