package ptgl.shopping.model;

import java.util.Date;

import lombok.Data;

@Data
public class Products {

	private String id;
	private String name;
	private String type;
	private String description;
	private Double price;
	private Long amount;
	private Date updatedTime;
	
	
}
