package myProject;

public class reservation {
	private int rid;
	private String vModel;
	private String cName;
	private String pick_address;
	private String pick_time;
	private String pick_date;
	private String return_address;
	private String return_time;
	private String return_date;
	private int days;
	private double payment;

	//constructor
	public reservation(int rid, String vModel, String cName, String pick_address, String pick_time, String pick_date,
			String return_address, String return_time, String return_date, int days, double payment) {
		super();
		this.rid = rid;
		this.vModel = vModel;
		this.cName = cName;
		this.pick_address = pick_address;
		this.pick_time = pick_time;
		this.pick_date = pick_date;
		this.return_address = return_address;
		this.return_time = return_time;
		this.return_date = return_date;
		this.days = days;
		this.payment = payment;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getvModel() {
		return vModel;
	}

	public void setvModel(String vModel) {
		this.vModel = vModel;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getPick_address() {
		return pick_address;
	}

	public void setPick_address(String pick_address) {
		this.pick_address = pick_address;
	}

	public String getPick_time() {
		return pick_time;
	}

	public void setPick_time(String pick_time) {
		this.pick_time = pick_time;
	}

	public String getPick_date() {
		return pick_date;
	}

	public void setPick_date(String pick_date) {
		this.pick_date = pick_date;
	}

	public String getReturn_address() {
		return return_address;
	}

	public void setReturn_address(String return_address) {
		this.return_address = return_address;
	}

	public String getReturn_time() {
		return return_time;
	}

	public void setReturn_time(String return_time) {
		this.return_time = return_time;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;

	}

}