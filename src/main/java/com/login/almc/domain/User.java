package com.login.almc.domain;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the altc_usua database table.
 * 
 */
@Entity
@Table(name="altc_usua")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_USUA")
	private Long id;

	@Column(name="BIT_USUA_ADD")
	private boolean isAdd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FEC_CREA")
	private Date fecCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FEC_MODI")
	private Date fecModi;

	@Column(name="TXT_LOGI_USUA",unique = true)
	private String nombreUser;

	@Column(name="TXT_NOMB_USUA")
	private String txtNombUsua;

	@Column(name="TXT_PASS_USUA")
	private String password;

	@Column(name="TXT_USUA_CREA")
	private String txtUsuaCrea;

	@Column(name="TXT_USUA_EMAIL",unique = true)
	private String email;

	@Column(name="TXT_USUA_MODI")
	private String txtUsuaModi;

	//bi-directional many-to-one association to AlmcPerf
	@ManyToOne
	@JoinColumn(name="COD_PERF")
	private Role role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Login> logins;
	
	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	public Date getFecCrea() {
		return fecCrea;
	}

	public void setFecCrea(Date fecCrea) {
		this.fecCrea = fecCrea;
	}

	public Date getFecModi() {
		return fecModi;
	}

	public void setFecModi(Date fecModi) {
		this.fecModi = fecModi;
	}

	public String getNombreUser() {
		return nombreUser;
	}

	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}

	public String getTxtNombUsua() {
		return txtNombUsua;
	}

	public void setTxtNombUsua(String txtNombUsua) {
		this.txtNombUsua = txtNombUsua;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTxtUsuaCrea() {
		return txtUsuaCrea;
	}

	public void setTxtUsuaCrea(String txtUsuaCrea) {
		this.txtUsuaCrea = txtUsuaCrea;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTxtUsuaModi() {
		return txtUsuaModi;
	}

	public void setTxtUsuaModi(String txtUsuaModi) {
		this.txtUsuaModi = txtUsuaModi;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Login> getLogins() {
		return logins;
	}

	public void setLogins(List<Login> logins) {
		this.logins = logins;
	}
	
	
	

}