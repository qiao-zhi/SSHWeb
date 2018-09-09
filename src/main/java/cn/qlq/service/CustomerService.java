package cn.qlq.service;

import java.util.List;

import cn.qlq.domain.Customer;

public interface CustomerService {
	public boolean saveCustomer(Customer c);

	/**
	 * 测试Dao层直接注入hibernateTemplate
	 * @param c
	 * @return
	 */
	public boolean saveCustomer2(Customer c);

	/**
	 * 根据ID查询
	 * 
	 * @param cusId
	 * @return
	 */
	public Customer getCustomerById(Long cusId);

	/**
	 * 测试通过hibernateTemplete获取的session是否同一线程是一样的
	 */
	public boolean testSessionIsSameInOneThread();
	
	/**
	 * 查询所有的客户信息
	 * 
	 * @return
	 */
	public List<Customer> listAllCustomers();
}
