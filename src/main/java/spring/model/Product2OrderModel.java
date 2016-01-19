package spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity 
@Table(name = "Products2Orders")
@NamedQueries({
	@NamedQuery(name = "products2orders.all", query = "Select po from Products2Orders po")
})
public class Product2OrderModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ProductModel product;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private OrderModel order;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct_id(ProductModel product) {
		this.product = product;
	}
	public OrderModel getOrder() {
		return order;
	}
	public void setOrder_id(OrderModel order) {
		this.order = order;
	}

	
}
