package io.yqx.modules.campusshare.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 注册用户
 * 
 * @author yq.x
 * @email yq.xu@unicall.net.cn
 * @date 2017-12-29 13:46:12
 */
public class CRegisterEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户名
	private String username;
	//密码
	private String password;
	//盐
	private String salt;
	//邮箱
	private String email;
	//手机号
	private String mobile;
	//状态  0：禁用   1：正常
	private Integer status;
	//创建时间
	private Date createTime;
	//地址
	private String address;
	//学院Id
	private Integer collegeId;
	//年级
	private Integer grade;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：盐
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * 获取：盐
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：状态  0：禁用   1：正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态  0：禁用   1：正常
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：学院Id
	 */
	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}
	/**
	 * 获取：学院Id
	 */
	public Integer getCollegeId() {
		return collegeId;
	}
	/**
	 * 设置：年级
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	/**
	 * 获取：年级
	 */
	public Integer getGrade() {
		return grade;
	}
}
