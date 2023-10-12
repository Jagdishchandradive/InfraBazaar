package com.info.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "checkout")
public class checkout {
    
    public checkout() {
    }
    public checkout(int id, String firstName, String lastName, String address, long postcode, String city, long phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.phone = phone;
    }
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Override
    public String toString() {
        return "checkout [address=" + address + ", city=" + city + ", firstName=" + firstName + ", id=" + id
                + ", lastName=" + lastName + ", phone=" + phone + ", postcode=" + postcode + "]";
    }
    private String firstName;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public long getPostcode() {
        return postcode;
    }
    public void setPostcode(long postcode) {
        this.postcode = postcode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public long getPhone() {
        return phone;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }
    private String lastName;
    private String address;
    private long postcode;
    private String city;
    private long phone;

//     // @ManyToAny(cascade = CascadeType.DETACH,  fetch = FetchType.EAGER)
//     private User user;
        

}
