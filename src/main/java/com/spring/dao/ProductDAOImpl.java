package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.ProductModel;

@Repository
@Transactional
public class ProductDAOImpl extends HibernateDaoSupport implements ProductDAO {

	@Autowired
	public ProductDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void addProduct(ProductModel product) {
		getHibernateTemplate().save(product);
	}

	@Override
	public void deleteProduct(ProductModel product) {
		getHibernateTemplate().delete(product);
	}

	@Override
	public void updateProduct(ProductModel product) {
		getHibernateTemplate().update(product);
	}

	@Override
	public List<ProductModel> getAllProducts() {
		return getHibernateTemplate().loadAll(ProductModel.class);
	}

	@Override
	public List<ProductModel> findProductByType(String type) {
		return getHibernateTemplate().execute(new HibernateCallback<List<ProductModel>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<ProductModel> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(ProductModel.class);
				criteria.add(Restrictions.eq("type", type));
				return criteria.list();
			}
		});
	}

	@Override
	public List<ProductModel> findProductByColor(String color) {
		return getHibernateTemplate().execute(new HibernateCallback<List<ProductModel>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<ProductModel> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(ProductModel.class);
				criteria.add(Restrictions.eq("color", color));
				return criteria.list();
			}
		});
	}
	
	@Override
	public List<ProductModel> findProductByName(String name) {
		return getHibernateTemplate().execute(new HibernateCallback<List<ProductModel>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<ProductModel> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(ProductModel.class);
				criteria.add(Restrictions.eq("name", name));
				return criteria.list();
			}
		});
	}

	@Override
	public ProductModel findProduct(ProductModel product) {
		return getHibernateTemplate().get(ProductModel.class, product.getId());
	}
	
	@Override
	public ProductModel findProductById (long productId) {
		return getHibernateTemplate().execute(new HibernateCallback<ProductModel>() {
			@Override
			public ProductModel doInHibernate(Session session) throws HibernateException {
				ProductModel product = (ProductModel) session.createQuery(ProductModel.PRODUCTS_BY_ID)
						.setLong("id", productId).uniqueResult();
				return product;
			}
		});
	}
	
	@Override
	public ProductModel findProductByTypeRose() {
		return getHibernateTemplate().execute(new HibernateCallback<ProductModel>() {
			@Override
			public ProductModel doInHibernate(Session session) throws HibernateException {
				ProductModel product = (ProductModel) session.getNamedQuery(ProductModel.PRODUCTS_BY_NAME)
						.setParameter("name", "rose").list().get(2);
				return product;
			}
		});
	}
	
	@Override
	public ProductModel findProductByTypeTulip() {
		return getHibernateTemplate().execute(new HibernateCallback<ProductModel>() {
			@Override
			public ProductModel doInHibernate(Session session) throws HibernateException {
				ProductModel product = (ProductModel) session.getNamedQuery(ProductModel.PRODUCTS_BY_NAME)
						.setParameter("name", "tulip").list().get(3);
				return product;
			}
		});
	}
	
}
