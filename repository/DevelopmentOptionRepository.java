package com.pms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pms.Model.DevelopmentOption;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevelopmentOptionRepository extends JpaRepository<DevelopmentOption,Long>{

//List<DevelopmentOption> findByTrainingOptionId(Long trainingOptionId);
//    List<DevelopmentOption> findByTrainingOption_TrainingOptionId(Long trainingOptionId);

    List<DevelopmentOption> findByTrainingOption_TrainingOptionId(Long trainingOptionId);
}
