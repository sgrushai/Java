package ua.lviv.lgs;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class GoodsDao {
	public List<Goods> findAll() {
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			List<Goods> goods = session.createCriteria(Goods.class).list();
			return goods;
		} finally {
			closeSession(session);
		}
	}

	public Goods findById(int id) {
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			Goods goods = (Goods) session.get(Goods.class, id);
			return goods;
		} finally {
			closeSession(session);
		}
	}

	public void create(Goods goods) {
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.save(goods);
			transaction.commit();
		} finally {
			closeSession(session);
		}
	}

	public List<Goods> findByName(String name) {
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			Query query = session
					.createSQLQuery(
							"select * from Goods g where g.name = :paramName")
					.addEntity(Goods.class).setParameter("paramName", name);
			List<Goods> result = query.list();
			return result;
		} finally {
			closeSession(session);
		}
	}

	public List<Goods> findBySubCategoryId(String subCategoryId) {
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();

			Criteria cr = session.createCriteria(Goods.class);
			cr.add(Restrictions.eq("subCategoryId", subCategoryId));
			List<Goods> result = cr.list();
			return result;
		} finally {
			closeSession(session);
		}
	}

	public void delete(Goods good) {
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(good);
			transaction.commit();
		} finally {
			closeSession(session);
		}
	}

	private void closeSession(Session session) {
		if ((session != null) && (session.isOpen())) {
			session.close();
		}
	}
}
