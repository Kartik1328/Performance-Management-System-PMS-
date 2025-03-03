package com.pms.service;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.pms.Model.*;
import com.pms.beans.EmpAndUserResponse;
import com.pms.co.*;
import com.pms.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//ALL THE NEW IMPORTS ARE BELOW-----------------------------------------


@Service
public interface PmsService {

	public AppraiseeSelfAssessementDto insert(AppraiseeSelfAssessementCo assessment) throws MessagingException;

	public AppraiseeSelfAssessementDraftDto insert(AppraiseeSelfAssessementDraftCo assessment_draft);

	public List<AppraiseeGoalSetting> getByKra(Long empId, Long subOverallStatusId);

	public AppraiseeSelfAssessementDto getById(Long empId, Long subOverallStatusId);

	public EmpAndUserResponse getByProfileId(Long employeeId) throws ExecutionException, InterruptedException;

	public AppraiseeManagerAssessmentDto insertmng(AppraiseeManagerAssessmentCo review);

	public AppraiseeManagerAssessmentDto getByManagerId(Long ma_id);

	public AppraiseeAnnualReviewDto getByAnnualId(Long empId);

	public AppraiseeDevelopmentGoalSettingDto getByDgId(Long employeeId, Long subOverallStatusId);

	public AppraiseeDevelopmentGoalSettingDto insert(AppraiseeDevelopmentGoalSettingCo development_goals);

	public boolean deleteSelfAsmDraft(Long empid);

	public AppraiseeSelfAssessementDraftDto getByDraftId(Long emp_id);

	public AppraiseeAnnualReviewDto insert(AppraiseeAnnualReviewCo annual_review);


	public List<EmployeeStatusDto> getemployeePerformanceDates();

	public EmployeeStatusDto postemployeePerformanceDates(EmployeeStatusCo employeeStatusCo);

	//	Object updateDevGoals(AppraiseeDevelopmentGoalSettingCo devCo);
//	AppraiseeDevelopmentGoalSettingDto updateDevGoals(AppraiseeDevelopmentGoalSettingCo appraiseeDevelopmentGoalSetting_co);

	List<AppraiseeDevelopmentGoalSettingDto> updateDevGoals(List<AppraiseeDevelopmentGoalSetting> appraiseeDevelopmentGoalSettingList);


	List<AppraiseeGoalSetting> updateAppraiseeGoalSetting(List<AppraiseeGoalSetting> goals, Long empId);


//	--------------------------new method created for manager screen 2 where the status of all the employee is shown----------------------------------------------

	List<EmployeeStatus> getByMgrId(String mgrId);

	List<TotalEmployeeStatusDto> getAllEmployeeDetailsAndStatus(List<TotalEmployeeStatusCo> totalEmployeeStatusCoList, String quarter, String reviewCycle);

	EmployeeStatus findParticularRow(Long empId, String quarter, String reviewCycle);


	Boolean setApprovedOn(Long empId, String quarter, String reviewCycle);
//	----------------------------------------------------------------------------------------


	public List<EmployeeStatusDto> getEmployeePerformanceByEmpId(Long empId);

	public List<AppraiseeGoalSettingDto> getEmployeeKraByEmpIdAndsubOverallStatusId(Long empId, Long subOverallStatusId);

	@Transactional
	public List<AppraiseeGoalSetting> submitAppraiseeGoalSetting(List<AppraiseeGoalSetting> appraiseeGoalSetting, Long empId) throws MessagingException;

	String getmes();

	void delete(Long id);

	public List<AppraiseeGoalSettingDraft> submitAppraiseeGoalSettingDraft(List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft);

	//List<PendingAppraisalDto> getPendingAppraisalEmpIdAndReviewCycle(Long empId, String reviewCycle);
//List<PendingAppraisalDto> getPendingAppraisalByReviewCycle(String reviewCycle);
	void deletedevelopmentdraftbyempid();

//	public List<AppraiseeDevelopmentGoalSettingDto> getDevelopmentGoals();

	public List<AppraiseeDevelopmentGoalSettingDto> getDevelopmentGoalsBySubOverallStatusId(Long employeeId, Long subOverallStatusId);

//	AppraiseeDevelopmentGoalSettingDto insertdev(AppraiseeDevelopmentGoalSettingCo appraiseeDevelopmentGoalSetting_co);

	List<AppraiseeDevelopmentGoalSettingDto> insertDevSubmitAppraiseeDevelopmentGoalSetting(List<AppraiseeDevelopmentGoalSetting> devGoals);


	boolean deleteDevelopmentGoal(Long dgDraftId);

	public List<AppraiseeDevelopmentGoalSettingDto> getEmployeeDevelopmentEmpId(Long empId);

	List<AppraiseeDevelopmentGoalSettingDraftDto> submitAppraiseeDevelopmentGoalSettingDraft(List<AppraiseeDevelopmentGoalSettingDraft> devGoalsDraft);

	EmployeeStatus postemployeePerformance(EmployeeStatus employeeStatus);

	EmployeeStatus postDraftSaved(EmployeeStatus employeeStatus);

	public List<TrainingOptionDto> getAllTrainingDropdown();
//	public List<EmployeeStatusDto> getDataByYearAndReviewCycleAndEmpId(String year, String reviewCycle, Long empId);

	List<EmployeeStatusDto> getByCurrentStatusAndManager(String currentStatus, String manager);

	EmployeeStatusDto postreverted(EmployeeStatusCo employeeStatus_co) throws MessagingException;


	public List<AppraiseeDefaultGoalsDto> getAppraiseeDefaultGoalsByDepartmentAndDesignation(String department, String designation);


	public List<PendingAppraisal> getpendingAppraisal(String reviewCycle);

	List<TrainingOptionDto> getTrainingByTrainingId(Long trainingOptionId);

	List<String> getTrainingByTrainingName(String trainingName);

//	EmployeeStatus postmanagerApproval(EmployeeStatus employeeStatus) throws MessagingException;

	EmployeeStatus postmanagerApproval(Long empId, Long employeeStatusId) throws MessagingException;


	List<TargetOperator> getTargetOperatorBytargetOperatorId(Long targetOperatorId);

	public List<TargetOperator> getAllTargretOperator();

	Map<String, Object> processGoals(Map<String, Object> requestData);


	boolean addEmpIdEmployeeStatus(Long empId, String year, String reviewCycle);

//	---------------------NEW LOGIC FOR APPRAISAL CYCLE TABLE AND EMPLOYEE STATUS TABLE-----------------

	List<PendingAppraisalDto> cratePendingAppraisal(List<PendingAppraisalCo> pendingAppraisalCoList) throws ExecutionException, InterruptedException;

	List<String> getAllReviewCycle();

	public List<EmployeeStatusDto> getDataByReviewCycleAndEmpId( String reviewCycle, Long empId) throws ExecutionException, InterruptedException;


	List<PendingAppraisalDto> getPendingAppraisalByReviewCycle(String reviewCycle);

	boolean preventFromNavigationToKra(Long empId,Long sId);

}