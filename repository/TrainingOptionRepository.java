package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.Model.TrainingOption;

import java.util.List;

public interface TrainingOptionRepository extends JpaRepository<TrainingOption,Long>{

    List<TrainingOption> findBytrainingOptionId(Long trainingOptionId);

    TrainingOption findByTrainingName(String trainingName);
}
