package rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Product;
import domain.ProductType;

@Path("/products")
@Stateless
public class ProductResources{
	
	@PersistenceContext
	EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Product> showAllProducts(){
		
		return em.createNamedQuery("product.all", Product.class)
				.getResultList();
	}
	
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductById(@PathParam("id") int id){
		
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", id)
				.getSingleResult();
		
		if(result == null){
			
			return Response.status(404).build();
		}
		
		return Response.ok(result).build();
	}
	
	@PUT
	@Path("/id/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProduct(@PathParam("id") int id, Product product){
		
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", id)
				.getSingleResult();
		
		if(result == null){
			
			return Response.status(404).build();
		}
		else{
			
			result.setName(product.getName());
			result.setPrice(product.getPrice());
			result.setProductType(product.getProductType());
			
			em.persist(result);
		}
		
		return Response.ok().build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(Product product){
		
		em.persist(product);
		return Response.ok(product.getId()).build();
	}
	
	@GET
	@Path("/price/{priceFrom}/{priceTo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List <Product> getProductsInThePriceRange(@PathParam("priceFrom") int priceFrom, @PathParam("priceTo") int priceTo){
		
		return em.createNamedQuery("product.price", Product.class)
				.setParameter("priceFrom", priceFrom)
				.setParameter("priceTo", priceTo)
				.getResultList();
	}
	
	@GET
	@Path("/category/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public List <Product> getProductsByCategory(@PathParam("type") ProductType type){
		
		return em.createNamedQuery("product.category", Product.class)
				.setParameter("type", type)
				.getResultList();
	}
	
	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List <Product> getProductsByName(@PathParam("name") String name){
		
		return em.createNamedQuery("product.name", Product.class)
				.setParameter("productName", name)
				.getResultList();
	}
}