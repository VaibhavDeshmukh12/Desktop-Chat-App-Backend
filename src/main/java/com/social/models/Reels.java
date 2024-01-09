package com.social.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reels {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reel_id;
	
	private String title;
	
	private String video;
	
	@ManyToOne
	private User user;

}
