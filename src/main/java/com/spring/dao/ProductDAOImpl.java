package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.ProductModel;
import com.spring.model.UserModel;

@Repository
@Transactional
public class ProductDAOImpl extends HibernateDaoSupport implements ProductDAO {
		
		@Autowired
		public ProductDAOImpl(SessionFactory sessionFactory) {
			super.setSessionFactory(sessionFactory);
		}
	
	    public void addProduct(ProductModel product) {
	        getHibernateTemplate().save(product);
	    }
	
	    public void deleteProduct(ProductModel product) {
	    	getHibernateTemplate().delete(product);
	    }
	
	    public void updateProduct(ProductModel product) {
	    	getHibernateTemplate().update(product);
	    }
	
		public List<ProductModel> getAllProducts() {
			return getHibernateTemplate().loadAll(ProductModel.class);
		}
	
	   

		@Override
		public ProductModel findProductByType(String type) {
			return getHibernateTemplate().execute(new HibernateCallback<ProductModel>() {
				@Override
				public ProductModel doInHibernate(Session session) throws HibernateException {
					Criteria criteria = session.createCriteria(ProductModel.class);
	                criteria.add(Restrictions.eq("type", type));
					return (ProductModel) criteria.uniqueResult();
				}
		});
		}

		@Override
		public ProductModel findProductByColor(String color) {
			return getHibernateTemplate().execute(new HibernateCallback<ProductModel>() {
				@Override
				public ProductModel doInHibernate(Session session) throws HibernateException {
					Criteria criteria = session.createCriteria(ProductModel.class);
	                criteria.add(Restrictions.eq("color", color));
					return (ProductModel) criteria.uniqueResult();
				}
		});
		}

		@Override
		public List<ProductModel> findProductByName(String name) {
			// TODO Auto-generated method stub
			return null;
		}
}
