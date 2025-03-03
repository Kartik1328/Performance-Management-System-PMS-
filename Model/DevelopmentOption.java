package com.pms.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="developmentoption_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DevelopmentOption {
    @Id
    private Long developmentSubId;
    private Long developmentOptionId;
    private String developmentOptionName;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "trainingOptionId", referencedColumnName = "trainingOptionId")
    private TrainingOption trainingOption;
}
