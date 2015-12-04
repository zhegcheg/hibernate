package com.hib.demo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hib.demo.entity.Student;

public class StudentService {
	private static Configuration conf;
	private static SessionFactory sf;
	private static Transaction tx;

	static {
		try {
			conf = new Configuration().configure();
			sf = conf.buildSessionFactory();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public static Session getSession() {
		return sf.openSession();         //过去老的方法，不需要使用事务
		//return sf.getCurrentSession(); // 新的方法，需要和事务一起使用，可以保证每个用户创建的session独立，需要在配置文件中配置
										//<property name="current_session_context_class">thread</property>
	}

	/**
	 * 获取所有学生列表
	 * 
	 * @return
	 */
	public List<Student> GetAllStudent() {
		List<Student> list = null;
		Session session = getSession();
		if (session != null) {
			try {
				String hql = "from Student";
				Query query = session.createQuery(hql);
				list = query.list();
			} catch (HibernateException e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		return list;
	}

	/**
	 * 获取单个学生信息
	 * 
	 * @param stuNo
	 * @return
	 */
	public Student GetStudentBystuNo(String stuNo) {
		Student stu = null;
		Session session = getSession();
		if (session != null) {
			try {
				// get如果没有查询到数据，则返回null
				stu = (Student) session.get(Student.class, stuNo);

				// load如果没有查询到数据，则抛出异常
				//stu = (Student) session.load(Student.class, stuNo);
			} catch (HibernateException e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		return stu;
	}

	/**
	 * 添加一个学生
	 * 
	 * @param stu
	 * @author Administrator
	 */
	public boolean AddStudent(Student stu) {
		boolean b = false;
		Session session = getSession();
		if (session != null) {

			try {
				// 开启一个事务
				tx = session.beginTransaction();
				// 保存
				session.save(stu);
				// 提交事务
				tx.commit();

				return true;

			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.close();
			}
		}
		return b;
	}

	/**
	 * 更新一个学生
	 * 
	 * @param stu
	 * @author Administrator
	 */
	public boolean UpdateStudent(String stuNo, String newName) {
		boolean b = false;
		Session session = getSession();
		if (session != null) {

			try {
				// 开启一个事务
				tx = session.beginTransaction();

				// 获取一个学生对象
				Student stu = (Student) session.load(Student.class, stuNo);

				// 更新某个属性
				stu.setStuName(newName);

				// 提交事务
				tx.commit();

				return true;

			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.close();
			}
		}
		return b;
	}

	/**
	 * 更新一个学生
	 * 
	 * @param stu
	 * @author Administrator
	 */
	public boolean DeleteStudent(String stuNo) {
		boolean b = false;
		Session session = getSession();
		if (session != null) {

			try {
				// 开启一个事务
				tx = session.beginTransaction();

				// 获取一个学生对象
				Student stu = (Student) session.load(Student.class, stuNo);

				// 删除操作
				session.delete(stu);

				// 提交事务
				tx.commit();

				return true;

			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				session.close();
			}
		}
		return b;
	}
}
