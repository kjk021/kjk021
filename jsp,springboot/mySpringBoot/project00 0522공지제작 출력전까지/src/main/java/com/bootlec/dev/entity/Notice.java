package com.bootlec.dev.entity;

import java.time.LocalDateTime;

import com.bootlec.dev.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Notice {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(length = 200)
	    private String subject;

	    @Column(columnDefinition = "TEXT")
	    private String content;

	    private LocalDateTime createDate;
	    
	    @ManyToOne
	    private SiteUser author;
	    
	    private LocalDateTime modifyDate;
	    

}
