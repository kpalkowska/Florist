package spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity 
@Table(name = "Products")
@NamedQueries({
	@NamedQuery(name = "products.all", query = "Select p from ProductModel p")
})
public class ProductModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private String description;
	private String price;

	public ProductModel(String Name, String Description, String Price) {
		this.name = Name;
		this.description = Description;
		this.price = Price; 
	}
	
}
