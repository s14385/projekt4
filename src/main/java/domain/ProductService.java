package domain;

import java.util.ArrayList;
import java.util.List;

public class ProductService{
	
	static private List <Product> products = new ArrayList <Product> ();

	public List<Product> getProducts() {
		return products;
	}
	
	public static void setProducts(List<Product> products) {
		ProductService.products = products;
	}
	
	public Product getProductById(int id){
		
		for(Product product : products){
			
			if(product.getId() == id){
				
				return product;
			}
		}
		
		return null;
	}
}