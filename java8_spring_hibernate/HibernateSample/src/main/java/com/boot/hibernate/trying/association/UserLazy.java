package com.boot.hibernate.trying.association;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.NamedAttributeNode;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "USER")
@NamedEntityGraph(name = "graph.User.orderDetail", attributeNodes = @NamedAttributeNode("orderDetail"))
public class UserLazy implements Serializable {
 
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long userId;
    
    @Column(name = "NAME")
    private String name;
 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<OrderDetail> orderDetail = new HashSet<OrderDetail>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Set<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
 
    // standard setters and getters
    // also override equals and hashcode
    
}
