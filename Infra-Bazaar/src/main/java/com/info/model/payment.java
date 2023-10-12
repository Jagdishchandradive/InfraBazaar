package com.info.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class payment {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    
    @Column(nullable = false,length = 50)
	private String name;

    @Column(nullable = false,length = 50)
    private long number;

    @Column(nullable = false,length = 50)
    private long expiry_date;

    @Column(nullable = false,length = 50)
    private int cvv;
	
	
	
	public payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public payment(int id, String name, long number, long expiry_date, int cvv) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.expiry_date = expiry_date;
		this.cvv = cvv;
	}
	@Override
	public String toString() {
		return "payment [id=" + id + ", name=" + name + ", number=" + number + ", expiry_date=" + expiry_date + ", cvv="
				+ cvv + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public long getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(long expiry_date) {
		this.expiry_date = expiry_date;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
