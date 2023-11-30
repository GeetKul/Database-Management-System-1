public class ResponseToBill {
	protected int ResponseToBillID;
	protected int BillID;
	protected int ClientID;
	protected String ResponseDate;
	protected String Note;
	protected PaymentStatus paymentStatus;

	public enum PaymentStatus {
		Paid, Unpaid
	}

	public ResponseToBill() {

	}

	public ResponseToBill(int responseToBillID, int billID, int clientID, String responseDate, String note,
			PaymentStatus paymentStatus) {
		ResponseToBillID = responseToBillID;
		BillID = billID;
		ClientID = clientID;
		ResponseDate = responseDate;
		Note = note;
		this.paymentStatus = paymentStatus;
	}

	public ResponseToBill(int responseToBillID, int billID, int clientID, String responseDate, String note) {
		ResponseToBillID = responseToBillID;
		BillID = billID;
		ClientID = clientID;
		ResponseDate = responseDate;
		Note = note;
	}

	public ResponseToBill(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getResponseToBillID() {
		return ResponseToBillID;
	}

	public void setResponseToBillID(int responseToBillID) {
		ResponseToBillID = responseToBillID;
	}

	public int getBillID() {
		return BillID;
	}

	public void setBillID(int billID) {
		BillID = billID;
	}

	public int getClientID() {
		return ClientID;
	}

	public void setClientID(int clientID) {
		ClientID = clientID;
	}

	public String getResponseDate() {
		return ResponseDate;
	}

	public void setResponseDate(String responseDate) {
		ResponseDate = responseDate;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}