public class OrderOfWork {
    protected int OrderID;
    protected int RequestID;
    protected int ClientID;
    protected String StartDate;
    protected String EndDate;
    protected Status status; // Enum type for Status
    protected int NumberOfTreesCut;
    protected String DateOfCut;
    protected String ContractorID;
    
    public enum Status {
        Started,
        InProgress,
        Pending,
        Completed
    }

    // Constructors
    public OrderOfWork() {
    }

    public OrderOfWork(int orderID, int requestID, int clientID, String startDate, String endDate, Status status,
            int numberOfTreesCut, String dateOfCut, String contractorID) {
    	this.OrderID = orderID;
    	this.RequestID = requestID;
    	this.ClientID = clientID;
    	this.StartDate = startDate;
    	this.EndDate = endDate;
    	this.status = status; // Directly assign the status as a string
    	this.NumberOfTreesCut = numberOfTreesCut;
    	this.DateOfCut = dateOfCut;
    	this.ContractorID = contractorID;
}

    // Getter and setter methods
    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getRequestID() {
        return RequestID;
    }

    public void setRequestID(int requestID) {
        RequestID = requestID;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        status = status;
    }

    public int getNumberOfTreesCut() {
        return NumberOfTreesCut;
    }

    public void setNumberOfTreesCut(int numberOfTreesCut) {
        NumberOfTreesCut = numberOfTreesCut;
    }

    public String getDateOfCut() {
        return DateOfCut;
    }

    public void setDateOfCut(String dateOfCut) {
        DateOfCut = dateOfCut;
    }

    public String getContractorID() {
        return ContractorID;
    }

    public void setContractorID(String contractorID) {
        ContractorID = contractorID;
    }
}
