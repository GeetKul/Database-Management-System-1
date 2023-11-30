public class Bills{
    protected int BillId;
    protected double Amount;
    protected String GeneratedDate;
    protected BillStatus billStatus;
    protected PaymentStatus paymentStatus;
    protected int OrderID;
    protected int ContractorID;
    protected int ClientID;

    public enum BillStatus {
        Issued, Pending,  Paid, Cancelled
    }

    public enum PaymentStatus{
        Unpaid, Paid, Processing, Overdue, Failed
    }

    public Bills() {
    }

    public Bills(int billId, double amount, String generatedDate, BillStatus billStatus, PaymentStatus paymentStatus, int orderID, int contractorID) {
        BillId = billId;
        Amount = amount;
        GeneratedDate = generatedDate;
        this.billStatus = billStatus;
        this.paymentStatus = paymentStatus;
        OrderID = orderID;
        ContractorID = contractorID;
    }

    public Bills(int billId, double amount, String generatedDate, int orderID, int contractorID) {
        BillId = billId;
        Amount = amount;
        GeneratedDate = generatedDate;
        OrderID = orderID;
        ContractorID = contractorID;
    }

    public Bills(int billId, double amount, String generatedDate, BillStatus billStatus, int orderID, int contractorID) {
        BillId = billId;
        Amount = amount;
        GeneratedDate = generatedDate;
        this.billStatus = billStatus;
        OrderID = orderID;
        ContractorID = contractorID;
    }

    public Bills(int billId, double amount, String generatedDate, PaymentStatus paymentStatus, int orderID, int contractorID) {
        BillId = billId;
        Amount = amount;
        GeneratedDate = generatedDate;
        this.paymentStatus = paymentStatus;
        OrderID = orderID;
        ContractorID = contractorID;
    }
    
    public Bills(int billId, String generatedDate, BillStatus billStatus, PaymentStatus paymentStatus, int orderID, int clientID) {
        this.BillId = billId;
        this.GeneratedDate = generatedDate;
        this.billStatus = billStatus;
        this.paymentStatus = paymentStatus;
        this.OrderID = orderID;
        this.ClientID = clientID;
    }

    public int getClientID() {
		return ClientID;
	}

	public void setClientID(int clientID) {
		ClientID = clientID;
	}

	public Bills(BillStatus billStatus, PaymentStatus paymentStatus) {
        this.billStatus = billStatus;
        this.paymentStatus = paymentStatus;
    }

    public int getBillId() {
        return BillId;
    }

    public double getAmount() {
        return Amount;
    }

    public String getGeneratedDate() {
        return GeneratedDate;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public int getOrderID() {
        return OrderID;
    }

    public int getContractorID() {
        return ContractorID;
    }

    public void setBillId(int billId) {
        BillId = billId;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public void setGeneratedDate(String generatedDate) {
        GeneratedDate = generatedDate;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public void setContractorID(int contractorID) {
        ContractorID = contractorID;
    }
}