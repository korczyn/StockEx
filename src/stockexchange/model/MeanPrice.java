package stockexchange.model;

public class MeanPrice {

	private double sum;
	private Long days;
	
	public MeanPrice(double sum){
		this.sum = sum;
		this.days = 1L;
	}
	
	public void addValue(double value){
		this.sum = this.sum + value;
		this.days++;
	}
	
	public double getMean(){
		 return (double) Math.round((sum / days) * 100) / 100;
	}
}
