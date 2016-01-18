package spring.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity 
@Table(name = "Products2Orders")
@NamedQueries({
	@NamedQuery(name = "products2orders.all", query = "Select po from Products2Orders po")
})
@AssociationOverrides({
	@AssociationOverride(name = "pk.Products", 
		joinColumns = @JoinColumn(name = "product_id")),
	@AssociationOverride(name = "pk.Orders", 
		joinColumns = @JoinColumn(name = "order_id")) })
public class Product2OrderModel {
	
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ProductModel product_id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private OrderModel order_id;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProductModel getProduct_id() {
		return product_id;
	}
	public void setProduct_id(ProductModel product_id) {
		this.product_id = product_id;
	}
	public OrderModel getOrder_id() {
		return order_id;
	}
	public void setOrder_id(OrderModel order_id) {
		this.order_id = order_id;
	}

	
}
