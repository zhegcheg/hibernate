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
		return sf.openSession();         //��ȥ�ϵķ���������Ҫʹ������
		//return sf.getCurrentSession(); // �µķ�������Ҫ������һ��ʹ�ã����Ա�֤ÿ���û�������session��������Ҫ�������ļ�������
										//<property name="current_session_context_class">thread</property>
	}

	/**
	 * ��ȡ����ѧ���б�
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
	 * ��ȡ����ѧ����Ϣ
	 * 
	 * @param stuNo
	 * @return
	 */
	public Student GetStudentBystuNo(String stuNo) {
		Student stu = null;
		Session session = getSession();
		if (session != null) {
			try {
				// get���û�в�ѯ�����ݣ��򷵻�null
				stu = (Student) session.get(Student.class, stuNo);

				// load���û�в�ѯ�����ݣ����׳��쳣
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
	 * ���һ��ѧ��
	 * 
	 * @param stu
	 * @author Administrator
	 */
	public boolean AddStudent(Student stu) {
		boolean b = false;
		Session session = getSession();
		if (session != null) {

			try {
				// ����һ������
				tx = session.beginTransaction();
				// ����
				session.save(stu);
				// �ύ����
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
	 * ����һ��ѧ��
	 * 
	 * @param stu
	 * @author Administrator
	 */
	public boolean UpdateStudent(String stuNo, String newName) {
		boolean b = false;
		Session session = getSession();
		if (session != null) {

			try {
				// ����һ������
				tx = session.beginTransaction();

				// ��ȡһ��ѧ������
				Student stu = (Student) session.load(Student.class, stuNo);

				// ����ĳ������
				stu.setStuName(newName);

				// �ύ����
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
	 * ����һ��ѧ��
	 * 
	 * @param stu
	 * @author Administrator
	 */
	public boolean DeleteStudent(String stuNo) {
		boolean b = false;
		Session session = getSession();
		if (session != null) {

			try {
				// ����һ������
				tx = session.beginTransaction();

				// ��ȡһ��ѧ������
				Student stu = (Student) session.load(Student.class, stuNo);

				// ɾ������
				session.delete(stu);

				// �ύ����
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
