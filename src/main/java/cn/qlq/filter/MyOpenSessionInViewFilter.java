package cn.qlq.filter;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;

/**
 * 重写OpenSessionInViewFilter的openSession方法过滤器去掉session的默认只读属性
 * 
 * @author liqiang
 *
 */
public class MyOpenSessionInViewFilter extends OpenSessionInViewFilter {

	@Override
	protected Session openSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
		Session session = sessionFactory.openSession();
		session.setFlushMode(FlushMode.AUTO);
		return session;
	}
}
