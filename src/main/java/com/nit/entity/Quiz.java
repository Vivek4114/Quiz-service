package com.nit.entity;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Quiz")
@Entity
public class Quiz {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Schema(hidden = true)
	private Integer id;
	private String title;
	private String level;
	private String category;
	@ElementCollection
	private List<Integer> questionIds;

}
