package com.login.almc.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the almc_perf database table.
 * 
 */
@Entity
@Table(name="almc_perf")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_PERF")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FEC_CREA")
	private Date fecCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FEC_MODI")
	private Date fecModi;

	@Column(name="TXT_NOMB_PERF")
	private String txtNombPerf;

	@Column(name="TXT_USUA_CREA")
	private String txtUsuaCrea;

	@Column(name="TXT_USUA_MODI")
	private String txtUsuaModi;

	//bi-directional many-to-one association to AltcUsua
	@OneToMany(mappedBy="role", fetch=FetchType.EAGER)
	private Set<User> users;

	//bi-directional many-to-one association to AltdPermPerf
	@OneToMany(mappedBy="almcPerf", fetch=FetchType.EAGER)
	private List<RolePermiso> altdPermPerfs;

	public Role() {
	}

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getFecCrea() {
		return this.fecCrea;
	}

	public void setFecCrea(Date fecCrea) {
		this.fecCrea = fecCrea;
	}

	public Date getFecModi() {
		return this.fecModi;
	}

	public void setFecModi(Date fecModi) {
		this.fecModi = fecModi;
	}

	public String getTxtNombPerf() {
		return this.txtNombPerf;
	}

	public void setTxtNombPerf(String txtNombPerf) {
		this.txtNombPerf = txtNombPerf;
	}

	public String getTxtUsuaCrea() {
		return this.txtUsuaCrea;
	}

	public void setTxtUsuaCrea(String txtUsuaCrea) {
		this.txtUsuaCrea = txtUsuaCrea;
	}

	public String getTxtUsuaModi() {
		return this.txtUsuaModi;
	}

	public void setTxtUsuaModi(String txtUsuaModi) {
		this.txtUsuaModi = txtUsuaModi;
	}

	

	public Set<User> getUsers() {
		return users;
	}



	public void setUsers(Set<User> users) {
		this.users = users;
	}



	public List<RolePermiso> getAltdPermPerfs() {
		return this.altdPermPerfs;
	}

	public void setAltdPermPerfs(List<RolePermiso> altdPermPerfs) {
		this.altdPermPerfs = altdPermPerfs;
	}

	public RolePermiso addAltdPermPerf(RolePermiso altdPermPerf) {
		getAltdPermPerfs().add(altdPermPerf);
		altdPermPerf.setAlmcPerf(this);

		return altdPermPerf;
	}

	public RolePermiso removeAltdPermPerf(RolePermiso altdPermPerf) {
		getAltdPermPerfs().remove(altdPermPerf);
		altdPermPerf.setAlmcPerf(null);

		return altdPermPerf;
	}

}