public class QuoteRequest {
    protected int RequestID;
    protected int ClientID;
    protected String RequestDate;
    protected int NumberOfTrees;     
    protected String Note;
    protected String Size;
    protected double Height;
    protected String Location;
    protected double ProximityToHouse;
    protected Status status; 
    protected int NumberOfTreesCut;
    protected String DateOfCut;

    // Enum for status
    public enum Status {
        Pending,
        Opened,
        Accepted
    }

    // Constructors
    public QuoteRequest() {
    }

    public QuoteRequest(String note) {
        this.Note = note;
    }
    
    public QuoteRequest(int requestID, int clientID, String requestDate, int numberOfTrees,  String size, double height  ) {
        this.RequestID = requestID;
        this.ClientID = clientID;
        this.RequestDate = requestDate;
        this.NumberOfTrees = numberOfTrees;
        this.Size = size;
        this.Height = height;
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

	public QuoteRequest(int clientID, String requestDate, String note, String size, double height, String location, double proximityToHouse, int numberOfTrees, Status status) {
        this.ClientID = clientID;
        this.RequestDate = requestDate;       
        this.Note = note;
        this.Size = size;
        this.Height = height;
        this.Location = location;
        this.ProximityToHouse = proximityToHouse;
        this.NumberOfTrees = numberOfTrees;
        this.status = status;
    }

    public QuoteRequest(int requestID, int clientID, String requestDate, int numberOfTrees, String note, String size, double height, String location, double proximityToHouse,Status status ) {
        this.RequestID = requestID;
        this.ClientID = clientID;
        this.RequestDate = requestDate;
        this.NumberOfTrees = numberOfTrees;       
        this.Note = note;
        this.Size = size;
        this.Height = height;
        this.Location = location;
        this.ProximityToHouse = proximityToHouse;
        this.status = status;
        
    }

    // Getter and setter methods
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

    public String getRequestDate() {
        return RequestDate;
    }

    public void setRequestDate(String requestDate) {
        RequestDate = requestDate;
    }

    public int getNumberOfTrees() {
        return NumberOfTrees;
    }

    public void setNumberOfTrees(int numberOfTrees) {
        NumberOfTrees = numberOfTrees;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        status = status;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public double getProximityToHouse() {
        return ProximityToHouse;
    }

    public void setProximityToHouse(double proximityToHouse) {
        ProximityToHouse = proximityToHouse;
    }
}
