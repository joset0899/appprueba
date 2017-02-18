package com.login.almc.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.login.almc.utils.DateUtils;

import java.util.Date;


/**
 * The persistent class for the altd_usua_login database table.
 * 
 */
@Entity
@Table(name="altd_usua_login")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="COD_LOGIN")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECH_LOGIN")
	private Date loginDate;
	
	@ManyToOne
    private User user;

	public Login() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFormatLoginDate() {
		return DateUtils.getDateFormated(loginDate);
	}
	
	

}