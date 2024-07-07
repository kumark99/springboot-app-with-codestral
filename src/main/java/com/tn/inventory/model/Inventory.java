package com.tn.inventory.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String inv_name;
    private String inv_desc;
    private int quantity;
    
    //Constructors
      
    //Constructor with fields
	public Inventory(Long id, String inv_name, String inv_desc, int quantity) {
		super();
		this.id = id;
		this.inv_name = inv_name;
		this.inv_desc = inv_desc;
		this.quantity = quantity;
	}
    
    
    //Default Constructor
	public Inventory() {
	
	}

    //Getters and Setters

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * @return the inv_name
	 */
	public String getInv_name() {
		return inv_name;
	}



	/**
	 * @param inv_name the inv_name to set
	 */
	public void setInv_name(String inv_name) {
		this.inv_name = inv_name;
	}



	/**
	 * @return the inv_desc
	 */
	public String getInv_desc() {
		return inv_desc;
	}



	/**
	 * @param inv_desc the inv_desc to set
	 */
	public void setInv_desc(String inv_desc) {
		this.inv_desc = inv_desc;
	}



	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}



	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "Inventory [id=" + id + ", inv_name=" + inv_name + ", inv_desc=" + inv_desc + ", quantity=" + quantity
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, inv_desc, inv_name, quantity);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return Objects.equals(id, other.id) && Objects.equals(inv_desc, other.inv_desc)
				&& Objects.equals(inv_name, other.inv_name) && quantity == other.quantity;
	}

	



	
	
	
	
	


    
    
}
