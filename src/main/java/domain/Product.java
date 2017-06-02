package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
	
	@NamedQuery(name="product.all", query="SELECT p FROM Product p"),
	@NamedQuery(name="product.id", query="FROM Product p WHERE p.id=:productId"),
	@NamedQuery(name="product.price", query="FROM Product p WHERE p.price>=:priceFrom AND p.price <=:priceTo"),
	@NamedQuery(name="product.category", query="FROM Product p WHERE p.productType=:type"),
	@NamedQuery(name="product.name", query="FROM Product p WHERE p.name=:productName"),
})
public class Product{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int price;
	private ProductType productType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
}