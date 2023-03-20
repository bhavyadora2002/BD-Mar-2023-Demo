package demojava;

public class Customer {
	  private int id;
	  private String name;
	  private String mobile;
	  private String email;
	  private int itemspurchased;
	  public Customer(int id,String name,String mobile,String email,int itemspurchased) {
	    this.id=id;
	    this.name = name;
	    this.mobile = mobile;
	    this.email=email;
	    this.itemspurchased=itemspurchased;
	  }
	  public int getId() {
	    return id;
	  }
	  public String getName() {
	    return name;
	  }
	  public String getPhn() {
	    return mobile;
	  }
	  public String getMail() {
		  return email;
	  }
	  public int getItems() {
		  return itemspurchased;
	  }
}
