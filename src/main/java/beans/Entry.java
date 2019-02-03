package beans;

import java.util.Date;

public class Entry {

	private Long id;

	private Long objectId;

	private Long userId;

	private Date date;

	private int quantity;

	public Entry() {
		super();
	}

	public Entry(Long objectId, Long userId, Date date, int quantity) {
		super();
		this.objectId = objectId;
		this.userId = userId;
		this.date = date;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", objectId=" + objectId + ", userId=" + userId + ", date=" + date + ", quantity="
				+ quantity + "]";
	}

}