package com.pms.repository;

import com.pms.Model.TargetOperator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TargetOperatorRepository extends JpaRepository<TargetOperator ,Long> {

    List<TargetOperator> findByTargetOperatorId(Long targetOperatorId);
}
