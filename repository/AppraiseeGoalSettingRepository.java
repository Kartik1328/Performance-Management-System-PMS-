package com.pms.repository;
import com.pms.Model.AppraiseeDevelopmentGoalSetting;
import com.pms.Model.AppraiseeGoalSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppraiseeGoalSettingRepository  extends JpaRepository<AppraiseeGoalSetting,Long> {
    void deleteByGoalId(Long goalId);

    List<AppraiseeGoalSetting> findByEmpId(Long empId);
    List<AppraiseeGoalSetting> findByEmpIdAndSubOverallStatusId(Long empId,Long subOverallStatusId) ;


//    List<AppraiseeDevelopmentGoalSetting> findByEmployeeId(Long employeeId);
//    List<AppraiseeGoalSetting> findByEmpId(Long empId);
}
