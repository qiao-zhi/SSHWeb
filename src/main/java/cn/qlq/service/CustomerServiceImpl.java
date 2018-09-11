package cn.qlq.service;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlq.dao.CustomerDao;
import cn.qlq.dao.CustomerDao2;
import cn.qlq.domain.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private CustomerDao2 customerDao2;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 删除缓存
	 */
	@CacheEvict(value = "cache_test", key = "'allCus'")
	@Override
	public boolean saveCustomer(Customer c) {
		log.info("进入service方法----saveCustomer");
		customerDao.saveCustomer(c);
		return true;
	}

	@Override
	public boolean saveCustomer2(Customer c) {
		customerDao2.saveCustomer(c);
		return true;
	}

	@Override
	public Customer getCustomerById(Long cusId) {
		return customerDao.getCustomerById(cusId);
	}

	@Override
	public boolean testSessionIsSameInOneThread() {
		// getCurrentSession获取与线程绑定的session(返回true)，而openSession不是同一个(会返回false)
		// Session serviceSession =
		// hibernateTemplate.getSessionFactory().openSession();
		Session serviceSession = hibernateTemplate.getSessionFactory().getCurrentSession();
		log.info("serviceSession---------------{}", serviceSession.toString());
		Session daoSession = customerDao.testSessionIsSameInOneThread();
		log.info("daoSession---------------{}", daoSession.toString());
		log.info("daoSession.equals(serviceSession) is :{}", daoSession.equals(serviceSession));
		return daoSession.equals(serviceSession);
	}

	@Cacheable(value = "cache_test", key = "'allCus'")
	@Override
	public List<Customer> listAllCustomers() {
		log.info("进入service方法----listAllCustomers");
		return customerDao2.listAllCustomers();
	}
}
