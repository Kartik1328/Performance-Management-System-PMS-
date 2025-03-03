package com.pms.repository;
import com.pms.Model.AppraiseeDevelopmentGoalSettingDraft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//import java.util.List;

public interface AppraiseeDevelopmentGoalSettingDraftRepository extends JpaRepository<AppraiseeDevelopmentGoalSettingDraft, Long> {
    void deleteAll();
    void deleteBydgDraftId(Long dgDraftId);

    List<AppraiseeDevelopmentGoalSettingDraft> findByEmployeeIdAndSubOverallStatusId(Long empId, Long overallStatusId);
}