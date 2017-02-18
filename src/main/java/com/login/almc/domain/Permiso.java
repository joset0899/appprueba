package com.login.almc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the almc_perm database table.
 * 
 */
@Entity
@Table(name="almc_perm")
public class Permiso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_PERM")
	private int codPerm;

	@Column(name="TXT_PERM")
	private String txtPerm;

	//bi-directional many-to-one association to AltdPermPerf
	@OneToMany(mappedBy="almcPerm", fetch=FetchType.EAGER)
	private List<RolePermiso> altdPermPerfs;

	public Permiso() {
	}

	public int getCodPerm() {
		return this.codPerm;
	}

	public void setCodPerm(int codPerm) {
		this.codPerm = codPerm;
	}

	public String getTxtPerm() {
		return this.txtPerm;
	}

	public void setTxtPerm(String txtPerm) {
		this.txtPerm = txtPerm;
	}

	public List<RolePermiso> getAltdPermPerfs() {
		return this.altdPermPerfs;
	}

	public void setAltdPermPerfs(List<RolePermiso> altdPermPerfs) {
		this.altdPermPerfs = altdPermPerfs;
	}

	public RolePermiso addAltdPermPerf(RolePermiso altdPermPerf) {
		getAltdPermPerfs().add(altdPermPerf);
		altdPermPerf.setAlmcPerm(this);

		return altdPermPerf;
	}

	public RolePermiso removeAltdPermPerf(RolePermiso altdPermPerf) {
		getAltdPermPerfs().remove(altdPermPerf);
		altdPermPerf.setAlmcPerm(null);

		return altdPermPerf;
	}

}