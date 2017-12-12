package ptgl.shopping.model;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class Products {
	
	public Products(){
		UUID id = UUID.randomUUID();
		this.id = id;
		Date now = new Date();
		this.updatedTime = now;
	}

	private UUID id;
	private String name;
	private String type;
	private String description;
	private Double price;
	private Long amount;
	private Date updatedTime;
	
	
}
