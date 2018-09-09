package cn.qlq.dao;

import java.util.List;

import org.hibernate.Session;

import cn.qlq.domain.Customer;

public interface CustomerDao {
	/**
	 * 保存客户
	 * 
	 * @param c
	 */
	public void saveCustomer(Customer c);

	/**
	 * 根据ID查询
	 * 
	 * @param cusId
	 * @return
	 */
	public Customer getCustomerById(Long cusId);

	/**
	 * 测试通过hibernate获取的session是否同一线程是一样的
	 */
	public Session testSessionIsSameInOneThread();

}
