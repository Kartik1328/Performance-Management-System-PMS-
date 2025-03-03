package com.pms.repository;
import com.pms.Model.AppraiseeGoalSettingDraft;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppraiseeGoalSettingDraftRepository extends JpaRepository<AppraiseeGoalSettingDraft,Long> {
    void deleteByEmpId(Long empId);
    boolean existsByEmpId(Long empId);

    List<AppraiseeGoalSettingDraft> findByEmpId(Long empId);

    List<AppraiseeGoalSettingDraft> findByEmpIdAndSubOverallStatusId(Long empId, Long overallStatusId);

    void deleteByGoalDraftId(Long goalDraftId);

//    List<AppraiseeGoalSettingDraft> findByEmpIdAndSubmiitedKraId(Long empId,Long submittedKraId);
}
