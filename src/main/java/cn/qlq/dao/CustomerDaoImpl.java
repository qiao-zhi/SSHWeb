package cn.qlq.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.qlq.domain.Customer;
import cn.qlq.domain.LinkMan;

/**
 * dao操作一般继承HibernateDaoSupport，里面获取hibernateTemplete也可以进行许多操作
 * 
 * @author liqiang
 *
 */
@Repository
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	private static Logger log = LoggerFactory.getLogger(CustomerDaoImpl.class);

	@Override
	public void saveCustomer(Customer c) {
		// 模拟为每个客户添加两个联系人
		LinkMan l1 = new LinkMan();
		LinkMan l2 = new LinkMan();
		l1.setLkm_name("联系人1");
		l2.setLkm_name("联系人2");
		c.getLinkMens().add(l1);
		c.getLinkMens().add(l2);

		Serializable obj = getHibernateTemplate().save(c);// 返回值是生成的主键的值(保存客户会级联保存联系人)
		log.info("save customer success,userId is:{}", obj.toString());
	}

	@Resource
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	@Override
	public Customer getCustomerById(Long cusId) {
		// 第一种:session.get方法
		Session session = getSessionFactory().openSession();
		Customer customer = session.get(Customer.class, cusId);
		log.info("第一种方法(session获取):{}", customer.toString());

		// 第二种:HQL
		/*
		 * String hql = "from Customer where cust_id=" + cusId; Query query =
		 * session.createQuery(hql); Customer cus = (Customer)
		 * query.uniqueResult(); log.info("第二种方法(HQL获取):{}",
		 * customer.toString());
		 */

		// 第三种:Criteria查询
		/*
		 * Criteria c = session.createCriteria(Customer.class);
		 * c.add(Restrictions.eq("cust_name", "ttt")); List list = c.list();
		 * log.info("Criteria方法获取的:{}", list.toString());
		 */

		// 第四种:原生SQL查询
		/*
		 * String sql = "select * from cst_customer where cust_id = " + cusId;
		 * SQLQuery sqlQuery = session.createSQLQuery(sql);
		 * sqlQuery.addEntity(Customer.class); log.info("原生SQL查询方法获取的:{}",
		 * sqlQuery.list().toString());
		 */

		return customer;
	}

	@Override
	public Session testSessionIsSameInOneThread() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession();// 原理是通过ThreadLocal获取到与线程绑定session
	}
}
