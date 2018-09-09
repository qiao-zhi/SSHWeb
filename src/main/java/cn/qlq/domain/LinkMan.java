package cn.qlq.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//联系人实体
@Entity
public class LinkMan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long lkm_id;
	private Character lkm_gender;
	private String lkm_name;
	private String lkm_phone;
	private String lkm_email;
	private String lkm_qq;
	private String lkm_mobile;
	private String lkm_memo;
	private String lkm_position;

	// 表达多对一关系
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id") // 定义在linkman的客户的外键列名称
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getLkm_id() {
		return lkm_id;
	}

	public void setLkm_id(Long lkm_id) {
		this.lkm_id = lkm_id;
	}

	public Character getLkm_gender() {
		return lkm_gender;
	}

	public void setLkm_gender(Character lkm_gender) {
		this.lkm_gender = lkm_gender;
	}

	public String getLkm_name() {
		return lkm_name;
	}

	public void setLkm_name(String lkm_name) {
		this.lkm_name = lkm_name;
	}

	public String getLkm_phone() {
		return lkm_phone;
	}

	public void setLkm_phone(String lkm_phone) {
		this.lkm_phone = lkm_phone;
	}

	public String getLkm_email() {
		return lkm_email;
	}

	public void setLkm_email(String lkm_email) {
		this.lkm_email = lkm_email;
	}

	public String getLkm_qq() {
		return lkm_qq;
	}

	public void setLkm_qq(String lkm_qq) {
		this.lkm_qq = lkm_qq;
	}

	public String getLkm_mobile() {
		return lkm_mobile;
	}

	public void setLkm_mobile(String lkm_mobile) {
		this.lkm_mobile = lkm_mobile;
	}

	public String getLkm_memo() {
		return lkm_memo;
	}

	public void setLkm_memo(String lkm_memo) {
		this.lkm_memo = lkm_memo;
	}

	public String getLkm_position() {
		return lkm_position;
	}

	public void setLkm_position(String lkm_position) {
		this.lkm_position = lkm_position;
	}

	@Override
	public String toString() {
		return "LinkMan [lkm_id=" + lkm_id + ", lkm_gender=" + lkm_gender + ", lkm_name=" + lkm_name + ", lkm_phone="
				+ lkm_phone + ", lkm_email=" + lkm_email + ", lkm_qq=" + lkm_qq + ", lkm_mobile=" + lkm_mobile
				+ ", lkm_memo=" + lkm_memo + ", lkm_position=" + lkm_position + ", customer=" + customer + "]";
	}

}
