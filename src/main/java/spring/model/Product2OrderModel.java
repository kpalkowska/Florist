package spring.model;

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

import lombok.Data;

@Data
@Entity 
@Table(name = "Products2Orders")
@NamedQueries({
	@NamedQuery(name = "products2orders.all", query = "Select po from Product2OrderModel po")
})
public class Product2OrderModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "product")
	private ProductModel product;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "orders")
	private OrderModel orders;

	public Product2OrderModel(OrderModel order, ProductModel product) {
		this.orders = order;
		this.product = product;
	}

}
