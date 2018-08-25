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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.qlq.domain.Customer;

/**
 * 
 * 测试Dao层直接注入hibernateTemplate
 * 
 * @author liqiang
 *
 */
@Repository
public class CustomerDaoImpl2 implements CustomerDao2 {

	private static Logger log = LoggerFactory.getLogger(CustomerDaoImpl2.class);
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void saveCustomer(Customer c) {
		Serializable obj = hibernateTemplate.save(c);// 返回值是生成的主键的值
		log.info("save customer success,userId is:{}", obj.toString());
	}

	@Override
	public Customer getCustomerById(Long cusId) {
		// 第一种:session.get方法
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Customer customer = session.get(Customer.class, cusId);
		log.info("第一种方法(session获取):{}", customer.toString());

		// 第二种:HQL
		String hql = "from Customer where cust_id=" + cusId;
		Query query = session.createQuery(hql);
		Customer cus = (Customer) query.uniqueResult();
		log.info("第二种方法(HQL获取):{}", customer.toString());

		// 第三种:Criteria查询
		Criteria c = session.createCriteria(Customer.class);
		c.add(Restrictions.eq("cust_name", "ttt"));
		List list = c.list();
		log.info("Criteria方法获取的:{}", list.toString());

		// 第四种:原生SQL查询
		String sql = "select * from cst_customer where cust_id = " + cusId;
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(Customer.class);
		log.info("原生SQL查询方法获取的:{}", sqlQuery.list().toString());

		return customer;
	}

	@Override
	public Session testSessionIsSameInOneThread() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}
}
