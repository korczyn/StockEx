package stockexchange.model;

public class SellRequest{

	private Long id;
	private Long quantity;

	public SellRequest(Long id, Long quantity){
		this.id = id;
		this.quantity = quantity;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getQuantity() {
		return quantity;
	}

}
