package stockexchange.model;

public class Stats {
	private double maxMoney;
	private Long stocksBought;
	private Long stocksSold;
	private int sells;
	private int buys;
	
	public Stats() {
		this.maxMoney = 0;
		this.stocksBought = 0L;
		this.stocksSold = 0L;
		this.sells = 0;
		this.buys = 0;
	}
	
	public void maxMoney(double money){
		if(money > this.maxMoney){
			this.maxMoney = money;
		}
	}
	
	public void addStocksBought(long quantity){
		this.stocksBought = this.stocksBought + quantity;
	}
	
	public void addStocksSold(long quantity){
		this.stocksSold = this.stocksSold + quantity;
	}
	
	public void addSell(){
		this.sells++;
	}
	
	public void addBuy(){
		this.buys++;
	}
	
	public void printStats(){
		System.out.println("====Stats====");
		System.out.println("Max money " + this.maxMoney);
		System.out.println("Stocks bougth " + this.stocksBought);
		System.out.println("Stocks sold " + this.stocksSold);
		System.out.println("Buy transactions " + this.buys);
		System.out.println("Sell transactions " + this.sells);
	}
	
}
