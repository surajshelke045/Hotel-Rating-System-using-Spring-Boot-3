package com.user.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "micro_users")
public class User {
	
	@Id
	@Column(name = "id")
	private String userId;
	
	@Column(name = "name",length = 20)
	private String name;
	
	private String email;
	
	private String about;
	
	@Transient
	private List<Rating> ratings=new ArrayList<>();

}
