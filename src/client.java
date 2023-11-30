
public class client {
    protected int ClientID;    
    protected String FirstName;
    protected String LastName;
    protected String Password;
    protected String Address;
    protected String CreditCardInfo;
    protected String PhoneNumber;
    protected String Email;
    protected int TotalTreesCut;
    protected String generatedDate;
    protected BillStatus billStatus;
    protected PaymentStatus paymentStatus;   
    protected  double totalDueAmount;
    protected  double totalPaidAmount;
    protected  String dateOfWorkDone;

   

	// constructors
    public enum BillStatus {
        Issued, Pending , Paid, Cancelled
    }

    public enum PaymentStatus{
        Unpaid, Paid, Processing, Overdue,Failed
    }
    
    public client() {
    }

    public client(String email) {
        this.Email = email;
    }
    
    public client(int clientID, String firstName, String lastName) {
        this.ClientID = clientID;        
        this.FirstName = firstName;
        this.LastName = lastName;
        
    }
    
    public client(int clientID, String firstName, String lastName, String generatedDate, PaymentStatus paymentStatus) {
        this.ClientID = clientID;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.generatedDate = generatedDate;
        this.paymentStatus = paymentStatus;
    }
   
   
    public client(int clientID, String firstName, String lastName, String generatedDate, BillStatus billStatus, PaymentStatus paymentStatus) {
        this.ClientID = clientID;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.generatedDate = generatedDate;
        this.billStatus = billStatus;
        this.paymentStatus = paymentStatus;
    }

    public client(String email, String firstName, String lastName, String password, String address, String creditCardInfo, String phoneNumber) {
        this.Email = email;
        this.Password = password;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Address = address;
        this.CreditCardInfo = creditCardInfo;
        this.PhoneNumber = phoneNumber;
    }
    
    public client(int clientID, String firstName, String lastName, int totalTreesCut) {
        this.ClientID = clientID;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.TotalTreesCut = totalTreesCut;
    }
    
  

    public client(int clientID,  String firstName, String lastName,String password, String address, String creditCardInfo, String phoneNumber, String email) {
        this.ClientID = clientID;       
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Password = password;
        this.Address = address;
        this.CreditCardInfo = creditCardInfo;
        this.PhoneNumber = phoneNumber;
        this.Email = email;
    }
    
    public client(int clientID, String firstName, String lastName,int totalTreesCut, double totalDueAmount, double totalPaidAmount, String dateOfWorkDone) {
    	this.ClientID = clientID;
    	this.FirstName = firstName;
    	this.LastName = lastName;
    	this.TotalTreesCut = totalTreesCut;
    	this.totalDueAmount = totalDueAmount;
    	this.totalPaidAmount = totalPaidAmount;
    	this.dateOfWorkDone = dateOfWorkDone;
    	}

    //getter and setter methods

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }
    
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCreditCardInfo() {
        return CreditCardInfo;
    }

    public void setCreditCardInfo(String creditCardInfo) {
        CreditCardInfo = creditCardInfo;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
    
    public int getTotalTreesCut() {
        return TotalTreesCut;
    }

   
    public void setTotalTreesCut(int totalTreesCut) {
        TotalTreesCut = totalTreesCut;
    }
    

    public String getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(String generatedDate) {
		this.generatedDate = generatedDate;
	}

	public BillStatus getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(BillStatus billStatus) {
		this.billStatus = billStatus;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public double getTotalDueAmount() {
        return totalDueAmount;
    }

    public void setTotalDueAmount(double totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

    public double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public String getDateOfWorkDone() {
        return dateOfWorkDone;
    }

    public void setDateOfWorkDone(String dateOfWorkDone) {
        this.dateOfWorkDone = dateOfWorkDone;
    }
}