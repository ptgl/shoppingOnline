package ptgl.shopping.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ShoppingCarts {

	public ShoppingCarts(){
		UUID id = UUID.randomUUID();
		this.id = id;
		Date now = new Date();
		this.updatedTime = now;
		
	}
	
	private UUID id;
	private List<Products> products;
	private Double totalAmount;
	private Date updatedTime;
}
