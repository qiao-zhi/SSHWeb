package cn.qlq.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.qlq.domain.Customer;
import cn.qlq.service.CustomerService;

/**
 * 客户Action
 * 
 * @author liqiang
 *
 */
@Namespace("/cus")
@ParentPackage("default")
@SuppressWarnings("all")
public class CustomerAction {

	private static Logger log = LoggerFactory.getLogger(CustomerAction.class);
	@Autowired
	private CustomerService customerService;

	private Map responseMap = new HashMap();

	private Customer c;// 对象驱动保存对象

	@Action(value = "saveCus", results = {
			@Result(name = "success", type = "json", params = { "root", "responseMap" }) })
	public String saveCus() {
		try {
			customerService.saveCustomer(c);
		} catch (Exception e) {
			log.error("", e);
			responseMap.put("msg", "保存客户失败！");
			return "success";
		}
		responseMap.put("msg", "保存客户成功！");
		return "success";
	}

	/**
	 * 测试Dao层直接注入hibernateTemplate
	 * 
	 * @return
	 */
	@Action(value = "saveCus2", results = {
			@Result(name = "success", type = "json", params = { "root", "responseMap" }) })
	public String saveCus2() {
		try {
			customerService.saveCustomer2(c);
		} catch (Exception e) {
			log.error("", e);
			responseMap.put("msg", "保存客户失败！");
			return "success";
		}
		responseMap.put("msg", "保存客户成功！");
		return "success";
	}

	@Action(value = "getCusById", results = {
			@Result(name = "success", type = "json", params = { "root", "responseMap" }) })
	public String getCusById() {
		Customer cus = null;
		try {
			cus = customerService.getCustomerById(15l);
		} catch (Exception e) {
			responseMap.put("msg", "查询客户失败！");
			return "success";
		}

		responseMap.put("msg", "查询客户成功！");
		responseMap.put("data", cus);
		return "success";
	}

	@Action(value = "testSessionIsSameInOneThread", results = {
			@Result(name = "success", type = "json", params = { "root", "responseMap" }) })
	public String testSessionIsSameInOneThread() {
		responseMap.put("data", customerService.testSessionIsSameInOneThread());
		return "success";
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}

	public Map getResponseMap() {
		return responseMap;
	}

	public void setResponseMap(Map responseMap) {
		this.responseMap = responseMap;
	}

}