package com.boot.hibernate.trying.association;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "USER_ORDER")
public class OrderDetail implements Serializable {
     
    @Id
    @GeneratedValue
    @Column(name="ORDER_ID")
    private Long orderId;
     
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private UserLazy user;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public UserLazy getUser() {
		return user;
	}

	public void setUser(UserLazy user) {
		this.user = user;
	}
 
    // standard setters and getters
    // also override equals and hashcode
 
}
