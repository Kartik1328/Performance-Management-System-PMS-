package com.pms.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="trainings_tbl")
public class TrainingOption {
	@Id
	private Long trainingOptionId;
	private Long trainingMainId;
	private String trainingName;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "trainingOption")
	private List<DevelopmentOption> developmentOptions;
}
	
   
