package ptgl.shopping.model;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class ShoppingCarts {

	private String id;
	private List<Products> products;
	private Double totalAmount;
	private Date updatedTime;
}
