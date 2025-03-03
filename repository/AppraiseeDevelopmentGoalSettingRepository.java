package com.pms.repository;
import com.pms.Model.AppraiseeGoalSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pms.Model.AppraiseeDevelopmentGoalSetting;

import java.util.List;
import java.util.Optional;
public interface AppraiseeDevelopmentGoalSettingRepository extends JpaRepository<AppraiseeDevelopmentGoalSetting,Long> {

//	Optional<AppraiseeDevelopmentGoalSetting>findById(Long dg_id);
List<AppraiseeDevelopmentGoalSetting> findByEmployeeIdAndSubOverallStatusId(Long employeeId, Long subOverallStatusId) ;

    AppraiseeDevelopmentGoalSetting findOneByEmployeeIdAndSubOverallStatusId(Long employeeId, Long subOverallStatusId);

    List<AppraiseeDevelopmentGoalSetting> findByEmployeeId(Long employeeId);


}
