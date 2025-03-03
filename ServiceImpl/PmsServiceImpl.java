package com.pms.ServiceImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
//import java.time.temporal.ChronoUnit;
import com.pms.Model.*;
import com.pms.beans.EmpAndUserResponse;
import com.pms.beans.FileAndObjectTypeBean;
import com.pms.co.*;
import com.pms.dto.*;
import com.pms.dto.employeeDto.EmpResDTO;
import com.pms.dto.userDto.UserDTO;
import com.pms.mapper.*;
import com.pms.repository.*;
import com.pms.utils.EmployeeOperations;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pms.service.PmsService;
import jakarta.mail.MessagingException;

@Service
public class PmsServiceImpl implements PmsService {

	@Autowired
	private  PmsMailServiceImpl pmsMailServiceImpl;

	@Autowired
	private EmployeeStatusRepository employeeStatusRepository;

	@Autowired
	private EmployeeStatusMapper employeeStatusMapper;

	@Autowired
	private AppraiseeSelfAssessementRepository appraiseeSelfAssessementRepository;

	@Autowired
	private AppraiseeSelfAssessementMapper appraiseeSelfAssessementMapper;

//	@Autowired
//	private MasterTableDummyRepository masterTableDummyRepository;

//	@Autowired
//	private MasterTableDummyMapper masterTableDummyMapper;

	@Autowired
	private AppraiseeSelfAssessmentDraftRepository appraiseeSelfAssessmentDraftRepository;

	@Autowired
	private AppraiseeSelfAssessementDraftMapper appraiseeSelfAssessementDraftMapper;

	@Autowired
	private AppraiseeDevelopmentGoalSettingRepository appraiseeDevelopmentGoalSettingRepository;

	@Autowired
	private AppraiseeDevelopmentGoalSettingMapper appraiseeDevelopmentGoalSettingMapper;

	@Autowired
	private AppraiseeManagerAssessmentRepository appraiseeManagerAssessmentRepository;

	@Autowired
	private AppraiseeGoalSettingDraftMapper appraiseeGoalSettingDraftMapper;

	@Autowired
	private AppraiseeManagerAssessmentMapper appraiseeManagerAssessmentMapper;

	@Autowired
	private AppraiseeAnnualReviewRepository appraiseeAnnualReviewRepository;

	@Autowired
	private AppraiseeAnnualReviewMapper appraiseeAnnualReviewMapper;


	@Autowired
	private AppraiseeDetailsRepository appraiseeDetailsRepository;

	@Autowired
	private AppraiseeDetailsMapper appraiseeDetailsMapper;

	@Autowired
	private AppraiseeDevelopmentGoalSettingRepository appraisee_development_goal_setting_Repository;

	@Autowired
	private AppraiseeDevelopmentGoalSettingMapper appraisee_development_goal_setting_Mapper;

	@Autowired
	private AppraiseeGoalSettingDraftRepository appraiseeGoalSettingDraftRepository;

	@Autowired
	private AppraiseeDevelopmentGoalSettingDraftRepository appraiseeDevelopmentGoalSettingDraftRepository;

	@Autowired
	private AppraiseeDevelopmentGoalSettingDraftMapper appraiseeDevelopmentGoalSettingDraftMapper;

	@Autowired
	private AppraiseeGoalSettingRepository appraiseeGoalSettingRepository;

	@Autowired
	private AppraiseeDefaultGoalsMapper appraiseeDefaultGoalsMapper;

	@Autowired
	private PendingAppraisalRepository pendingAppraisalRepository;

	@Autowired
	private PendingAppraisalMapper pendingAppraisalMapper;



	@Autowired
	private TrainingOptionRepository trainingOptionRepository;

	@Autowired
	private TrainingOptionMapper trainingOptionMapper;

	@Autowired
	private DevelopmentOptionMapper developmentOptionMapper;

	@Autowired
	private DevelopmentOptionRepository developmentOptionRepository;

	@Autowired
	private AppraiseeDefaultRepository appraiseeDefaultRepository;

	@Autowired
	private AppraiseeGoalSettingMapper appraiseeGoalSettingMapper;

	@Autowired
    private EmployeeOperations employeeOperations;

	@Autowired
	private TargetOperatorRepository targetOperatorRepository;




	@Override
	public AppraiseeSelfAssessementDto insert(AppraiseeSelfAssessementCo assessmentCo) throws MessagingException {

		AppraiseeSelfAssessement assessment= appraiseeSelfAssessementMapper.coToEntity(assessmentCo);


		// Check if there are any drafts
		boolean hasDrafts = appraiseeSelfAssessmentDraftRepository.count() > 0;

		if (hasDrafts) {
			List<AppraiseeSelfAssessementDraft> drafts = appraiseeSelfAssessmentDraftRepository.findAll();

			for (AppraiseeSelfAssessementDraft draft : drafts) {
				AppraiseeSelfAssessement self = new AppraiseeSelfAssessement();
				self.setSubmittedOn(LocalDate.now());
				self.setOverallComments(draft.getOverallComments());
				self.setOverallRating(draft.getOverallRating());
				self.setComment(draft.getComment());
				self.setRating(draft.getRating());
				self.setEmpId(draft.getEmpId());

				// Save to appraiseeSelfAssessementRepository
				appraiseeSelfAssessementRepository.save(self);
			}
		} else {
			AppraiseeSelfAssessement self = new AppraiseeSelfAssessement();
			self.setSubmittedOn(LocalDate.now());
			self.setOverallComments(assessmentCo.getOverallComments());
			self.setOverallRating(assessmentCo.getOverallRating());
			self.setComment(assessmentCo.getComment());
			self.setRating(assessmentCo.getRating());
			self.setEmpId(assessmentCo.getEmpId());

			// Save to appraiseeSelfAssessementRepository
			appraiseeSelfAssessementRepository.save(self);
		}

		// Delete data from appraiseeSelfAssessementDraftRepository
		appraiseeSelfAssessmentDraftRepository.deleteAll();

		// Send mail
		pmsMailServiceImpl.sendMail();

		// Return the saved assessment
		return appraiseeSelfAssessementMapper.entityToDTO(assessment);
	}


	@Override
	public AppraiseeManagerAssessmentDto insertmng(AppraiseeManagerAssessmentCo reviewCo) {
		AppraiseeManagerAssessment manager= appraiseeManagerAssessmentMapper.coToEntity(reviewCo);
		manager.setReviewedOn(LocalDate.now());
		manager.setOverallMgrComments(reviewCo.getOverallMgrComments());
		manager.setOverallMgrRating(reviewCo.getOverallMgrRating());
		manager.setMgrComment(reviewCo.getMgrComment());
		manager.setMgrRating(reviewCo.getMgrRating());
		manager.setMgrId(reviewCo.getMgrId());
		return appraiseeManagerAssessmentMapper.entityToDTO(manager);

//here we have taken review and manager
	}

//    HERE IN THIS FUNCTION WE HAVE TAKEN 1 NAME AND ALL THE THREE FUNCTIONALITIES WILL BE PERFORMED USING THAT

	@Override
	public AppraiseeSelfAssessementDraftDto insert(AppraiseeSelfAssessementDraftCo draftCo) {
		AppraiseeSelfAssessementDraft selfDraft=appraiseeSelfAssessementDraftMapper.coToEntity(draftCo);
//    	selfDraft.setReviewedOn(LocalDate.now());
		selfDraft.setOverallComments(draftCo.getOverallComments());
		selfDraft.setOverallRating(draftCo.getOverallRating());
		selfDraft.setComment(draftCo.getComment());
		selfDraft.setRating(draftCo.getRating());
		selfDraft.setEmpId(draftCo.getEmpId());
		selfDraft.setSelfAssessment(draftCo.getSelfAssessment());
		return appraiseeSelfAssessementDraftMapper.entityToDTO(appraiseeSelfAssessmentDraftRepository.save(selfDraft));
//      here we have taken review and manager
	}
//     this is for the master dummy table

	@Override
	public List<AppraiseeGoalSetting> getByKra(Long empId, Long subOverallStatusId){
		List<AppraiseeGoalSetting> selfAll=appraiseeGoalSettingRepository.findByEmpIdAndSubOverallStatusId(empId,subOverallStatusId);
		return selfAll;
	}

//    HERE I HAVE TAKEN ALL BECAUSE THE DATA IS IN THE FORM OF THE ARRAY OR THE LIST.

	@Override
	public AppraiseeDevelopmentGoalSettingDto getByDgId(Long employee_id, Long sub_overall_status_id) {
		AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting=appraiseeDevelopmentGoalSettingRepository.findOneByEmployeeIdAndSubOverallStatusId(employee_id, sub_overall_status_id);
		return appraisee_development_goal_setting_Mapper.entityToDTO(appraiseeDevelopmentGoalSetting);
	}

	@Override
	public AppraiseeSelfAssessementDto getById(Long empId, Long subOverallStatusId) {
		AppraiseeSelfAssessement appraiseeSelfAssessement=appraiseeSelfAssessementRepository.findByEmpIdAndSubOverallStatusId(empId,subOverallStatusId).orElse(null);
		return appraiseeSelfAssessementMapper.entityToDTO(appraiseeSelfAssessement);
	}

	@Override
	public AppraiseeSelfAssessementDraftDto getByDraftId(Long empId) {
		AppraiseeSelfAssessementDraft appraiseeSelfAssessementDraft=appraiseeSelfAssessmentDraftRepository.findByempId(empId).orElse(null);
		return appraiseeSelfAssessementDraftMapper.entityToDTO(appraiseeSelfAssessementDraft);
	}

	@Override
	public EmpAndUserResponse getByProfileId(Long employeeId) throws ExecutionException, InterruptedException {
		CompletableFuture<EmpAndUserResponse> appraiseeDetails=employeeOperations.getEmpByEmpId(employeeId);
		EmpAndUserResponse empAndUserResponse = appraiseeDetails.get();
		return empAndUserResponse;
	}

	@Override
	public AppraiseeManagerAssessmentDto getByManagerId(Long ma_id) {
		AppraiseeManagerAssessment appraiseeManagerAssessment=appraiseeManagerAssessmentRepository.findBymgrId(ma_id).orElse(null);
		return appraiseeManagerAssessmentMapper.entityToDTO(appraiseeManagerAssessment);
	}

	@Override
	public AppraiseeAnnualReviewDto getByAnnualId(Long empId) {
		AppraiseeAnnualReview appraiseeAnnualReview=appraiseeAnnualReviewRepository.findById(empId).orElse(null);
		return appraiseeAnnualReviewMapper.entityToDTO(appraiseeAnnualReview);
	}

	@Override
	public AppraiseeAnnualReviewDto insert(AppraiseeAnnualReviewCo annualReviewCo) {
		AppraiseeAnnualReview annual_review=appraiseeAnnualReviewMapper.coToEntity(annualReviewCo);
		annual_review = appraiseeAnnualReviewRepository.save(annual_review);
		return appraiseeAnnualReviewMapper.entityToDTO(annual_review);
	}

	//----------DO WE HAVE TO ADD THESE LINES IN THE ABOVE insert method for annual review??????
	//annual_review.setId(genId(annual_review.getAreaOfImporovement()));
	//setModifier(annual_review);
	//these two lines are the part of the Base Service class.
	//-------------------------------------------------------------------------------------------

	@Override
	public AppraiseeDevelopmentGoalSettingDto insert(AppraiseeDevelopmentGoalSettingCo developmentGoalsCo) {
		AppraiseeDevelopmentGoalSetting development_goals=appraiseeDevelopmentGoalSettingMapper.coToEntity(developmentGoalsCo);
		development_goals = appraisee_development_goal_setting_Repository.save(development_goals);
		return appraisee_development_goal_setting_Mapper.entityToDTO(development_goals);
	}

	@Override
	public boolean deleteSelfAsmDraft(Long empId) {
		appraiseeSelfAssessmentDraftRepository.deleteById(empId);
		return true;
	}

	//    CONTROLLER "GET API" FOR employee status(earlier master table dummy) TABLE
// 	--new method created for manager screen 2 where the status of all the employee is shown-----------


	@Override
	public List<EmployeeStatus> getByMgrId(String mgrId) {
		return employeeStatusRepository.findByMgrId(mgrId);
	}

	@Override
	public List<TotalEmployeeStatusDto> getAllEmployeeDetailsAndStatus(List<TotalEmployeeStatusCo> totalEmployeeStatusCoList, String quarter, String reviewCycle) {
		System.out.println(totalEmployeeStatusCoList+" "+quarter+" "+reviewCycle+"(9999999999999999999999999");
		List<TotalEmployeeStatusDto> totalEmployeeStatusDtoList = new ArrayList<>();

		if(totalEmployeeStatusCoList.size()>=1){

		for (TotalEmployeeStatusCo employeeStatusCo : totalEmployeeStatusCoList) {
			TotalEmployeeStatusDto totalEmployeeStatusDto = new TotalEmployeeStatusDto();

			EmployeeStatus employeeStatusObj = employeeStatusRepository.findByEmpIdAndAppraisalCycleAndReviewCycle(
					employeeStatusCo.getEmpId(), quarter, reviewCycle);

			if (employeeStatusObj != null) {

				if (employeeStatusObj.getSubmittedOn() != null) {

					System.out.println("hello 1234");


					// coming from react as employee service
					totalEmployeeStatusDto.setEmpId(employeeStatusCo.getEmpId());
					totalEmployeeStatusDto.setEmployeeCode(employeeStatusCo.getEmployeeCode());
					totalEmployeeStatusDto.setDesignation(employeeStatusCo.getDesignation());
					totalEmployeeStatusDto.setFullName(employeeStatusCo.getFullName());
					totalEmployeeStatusDto.setExperience(employeeStatusCo.getExperience());

					// emp status
					totalEmployeeStatusDto.setSubmittedOn(employeeStatusObj.getSubmittedOn());
					totalEmployeeStatusDto.setApprovedOn(employeeStatusObj.getApprovedOn());
					totalEmployeeStatusDto.setSubmittedOnn(employeeStatusObj.getSubmittedOnn());
					totalEmployeeStatusDto.setReviewedOnn(employeeStatusObj.getReviewedOnn());
				}
			}

//			} else {
//				// Handle the case where no data exists, e.g., setting default/null values
//				totalEmployeeStatusDto.setEmpId(employeeStatusCo.getEmpId());
//				totalEmployeeStatusDto.setEmployeeCode(employeeStatusCo.getEmployeeCode());
//				totalEmployeeStatusDto.setDesignation(employeeStatusCo.getDesignation());
//				totalEmployeeStatusDto.setFullName(employeeStatusCo.getFullName());
//				totalEmployeeStatusDto.setExperience(employeeStatusCo.getExperience());
//
//				totalEmployeeStatusDto.setSubmittedOn(null);
//				totalEmployeeStatusDto.setApprovedOn(null);
//				totalEmployeeStatusDto.setSubmittedOnn(null);
//				totalEmployeeStatusDto.setReviewedOnn(null);
//			}

			System.out.println(totalEmployeeStatusDto + "totalEmployeeStatusDto");

			totalEmployeeStatusDtoList.add(totalEmployeeStatusDto);
			}
		}

		return totalEmployeeStatusDtoList;
	}

	@Override
	public EmployeeStatus findParticularRow(Long empId, String quarter, String reviewCycle) {
		return employeeStatusRepository.findByEmpIdAndAppraisalCycleAndReviewCycle(empId, quarter, reviewCycle);
	}


	@Override
	public Boolean setApprovedOn(Long empId, String quarter, String reviewCycle) {
		EmployeeStatus employeeStatus=employeeStatusRepository.findByEmpIdAndAppraisalCycleAndReviewCycle(empId, quarter, reviewCycle);
		if(employeeStatus.getApprovedOn()==null){
			employeeStatus.setApprovedOn(LocalDate.now());
			String status = "Approved";
			employeeStatus.setCurrentStatus(status);
            employeeStatusRepository.save(employeeStatus);
            return true;
		}
		else{
			return null;
		}
	}
//	@Override
//  public EmployeeStatus postmanagerApproval(EmployeeStatus employeeStatus) throws MessagingException {
//     Long id = employeeStatus.getEmployeeStatusId();
//     Optional<EmployeeStatus> optionalEmployeeStatus = employeeStatusRepository.findById(id);
//     if (optionalEmployeeStatus.isPresent()) {
//        EmployeeStatus existingEmployeeStatus = optionalEmployeeStatus.get();
//        LocalDate today = LocalDate.now();
//        existingEmployeeStatus.setApprovedOn(today);
//        String status = "Approved";
//        existingEmployeeStatus.setCurrentStatus(status);
//        existingEmployeeStatus.setEmployeeStatusId(id);
//        pmsMailServiceImpl.sendApprovesMail();
//        return employeeStatusRepository.save(existingEmployeeStatus);
//     } else {
//        throw new IllegalArgumentException("EmployeeStatus with ID " + id + " not found");
//     }

	//THIS IS FOR THE EMPLOYEE STATUS TABLE.

	//	@Override
	public List<EmployeeStatusDto> getemployeePerformanceDates() {
		List<EmployeeStatus> employeeStatus=employeeStatusRepository.findAll();
		return employeeStatusMapper.entityListToDTOList(employeeStatus);
	}

	//	@Override
	public EmployeeStatusDto postemployeePerformanceDates(EmployeeStatusCo employeeStatusCo) {
		// TODO Auto-generated method stub
		EmployeeStatus employeeStatus = employeeStatusMapper.coToEntity(employeeStatusCo);
		LocalDate today = LocalDate.now();
		employeeStatus.setSubmittedOn(today);
		return employeeStatusMapper.entityToDTO(employeeStatusRepository.save(employeeStatus));
	}

	@Override
	public  List<AppraiseeDevelopmentGoalSettingDto> updateDevGoals(List<AppraiseeDevelopmentGoalSetting> appraiseeDevelopmentGoalSettingList) {
		List<AppraiseeDevelopmentGoalSetting>appraiseeDevelopmentGoalSettingDtoList=appraiseeDevelopmentGoalSettingRepository.saveAll(appraiseeDevelopmentGoalSettingList);
		return appraiseeDevelopmentGoalSettingMapper.entityListToDTOList(appraiseeDevelopmentGoalSettingDtoList);
//		System.out.println(String.valueOf(LappraiseeDevelopmentGoalSetting_co)+"vvvvvvvvvvvvvvvvvvvv");
//		//AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting=appraiseeDevelopmentGoalSettingMapper.coToEntity();
//		appraiseeDevelopmentGoalSettingMapper.dto
//		return appraiseeDevelopmentGoalSettingMapper.entityToDTO(appraiseeDevelopmentGoalSettingRepository.save(appraiseeDevelopmentGoalSettingDtoList));
	}

	@Override
	public List<AppraiseeGoalSetting> updateAppraiseeGoalSetting(List<AppraiseeGoalSetting> goals, Long empId) {
		return appraiseeGoalSettingRepository.saveAll(goals);
	}

//	----------------------------------------------Amulya's'-------------------------------------------------------------------------

	@Override
	public List<AppraiseeGoalSettingDto> getEmployeeKraByEmpIdAndsubOverallStatusId(Long empId, Long subOverallStatusId) {
		List<AppraiseeGoalSetting> AprraiseeKra = appraiseeGoalSettingRepository.findByEmpIdAndSubOverallStatusId(empId, subOverallStatusId);
		return appraiseeGoalSettingMapper.entityListToDTOList(AprraiseeKra);
	}

	/**
	 * it will List the Goals after submitting
	 *
	 * @param appraiseeGoalSetting - for setting goals of the employee
	 * @param empId-Id             of the Employee
	 * @return
	 * @throws MessagingException-it is used for mail sending
	 */

	@Override
	@Transactional
	public List<AppraiseeGoalSetting> submitAppraiseeGoalSetting(List<AppraiseeGoalSetting> appraiseeGoalSetting, Long empId) throws MessagingException {

		boolean result = appraiseeGoalSettingDraftRepository.existsByEmpId(empId);
		List<AppraiseeGoalSetting> savedAppraiseeGoalSettings;
		if (result) {
			List<AppraiseeGoalSettingDraft> getAll = appraiseeGoalSettingDraftRepository.findByEmpId(empId);
			List<AppraiseeGoalSetting> goalSettingsFromDrafts = convertDraftsToGoalSettings(getAll);
			savedAppraiseeGoalSettings = appraiseeGoalSettingRepository.saveAll(goalSettingsFromDrafts);
			appraiseeGoalSettingDraftRepository.deleteByEmpId(empId);

		} else {
			savedAppraiseeGoalSettings = appraiseeGoalSettingRepository.saveAll(appraiseeGoalSetting);
		}
		pmsMailServiceImpl.sendKraSubmittedMail();
		return savedAppraiseeGoalSettings;
	}

	private List<AppraiseeGoalSetting> convertDraftsToGoalSettings(List<AppraiseeGoalSettingDraft> drafts) {
		List<AppraiseeGoalSetting> goalSettings = new ArrayList<>();
		for (AppraiseeGoalSettingDraft draft : drafts) {
			AppraiseeGoalSetting goalSetting = new AppraiseeGoalSetting();
			goalSetting.setEmpId(draft.getEmpId());
			goalSetting.setTarget(draft.getTarget());
			goalSetting.setTotalWeightage(draft.getTotalWeightage());
			goalSetting.setWeightage(draft.getWeightage());
			goalSetting.setMeasurement(draft.getMeasurement());
			goalSetting.setKra(draft.getKra());
			goalSetting.setGoals(draft.getGoals());
			goalSetting.setSubOverallStatusId(draft.getSubOverallStatusId());
			goalSettings.add(goalSetting);
		}
		return goalSettings;
	}

	/**
	 * it is used to display the message after submission
	 *
	 * @return
	 */

	@Override
	public String getmes() {
		return "hello world";
	}

	/**
	 * use to delete the goals
	 *
	 * @param goalDraftId-Auto-generated id
	 */
	@Override
	@Transactional
	public void delete(Long goalDraftId) {
		appraiseeGoalSettingDraftRepository.deleteByGoalDraftId(goalDraftId);
	}


	/**
	 * it will List the Goals after saving it in draft
	 *
	 * @param appraiseeGoalSettingDraft- for setting goals of the employee
	 * @return
	 */
	@Override
	public List<AppraiseeGoalSettingDraft> submitAppraiseeGoalSettingDraft(
			List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft) {
		return appraiseeGoalSettingDraftRepository.saveAll(appraiseeGoalSettingDraft);
	}


	@Override
	public List<AppraiseeDevelopmentGoalSettingDto> getDevelopmentGoalsBySubOverallStatusId(Long employeeId, Long subOverallStatusId) {
		List<AppraiseeDevelopmentGoalSetting> devGoal = appraiseeDevelopmentGoalSettingRepository.findByEmployeeIdAndSubOverallStatusId(employeeId,subOverallStatusId);
		return appraiseeDevelopmentGoalSettingMapper.entityListToDTOList(devGoal);
	}

	/**
	 //     * @param appraiseeDevelopmentGoalSetting-for setting development goals of employee
	 * @return
	 */

//	@Override
//	public AppraiseeDevelopmentGoalSettingDto insertdev(AppraiseeDevelopmentGoalSettingCo appraiseeDevelopmentGoalSetting_co) {
//		AppraiseeDevelopmentGoalSetting entity = appraiseeDevelopmentGoalSettingMapper.coToEntity(appraiseeDevelopmentGoalSetting_co);
//		AppraiseeDevelopmentGoalSetting savedEntity = appraiseeDevelopmentGoalSettingRepository.save(entity);
//		return appraiseeDevelopmentGoalSettingMapper.entityToDTO(savedEntity);
//	}


	@Override
	public List<AppraiseeDevelopmentGoalSettingDto> insertDevSubmitAppraiseeDevelopmentGoalSetting(
			List<AppraiseeDevelopmentGoalSetting> devGoalsDraft) {
		System.out.println(String.valueOf(devGoalsDraft));
		System.out.println(devGoalsDraft.get(0).getDg_id()+"dgId");

		List<AppraiseeDevelopmentGoalSetting> savedEntityList = appraiseeDevelopmentGoalSettingRepository.saveAll(devGoalsDraft);
		return appraiseeDevelopmentGoalSettingMapper.entityListToDTOList(savedEntityList);
	}






	/**
	 * used to delete the development goal
	 *
	 * @param -Id of the Employee
	 * @return
	 */

	@Transactional
	@Override
	public boolean deleteDevelopmentGoal(Long dgDraftId) {
		appraiseeDevelopmentGoalSettingDraftRepository.deleteBydgDraftId(dgDraftId);
		return true;
	}

	/**
	 * used to List the Development Goals based on EmpId
	 *
	 * @param empId-Id of the Employee
	 * @return
	 */

	@Override
	public List<AppraiseeDevelopmentGoalSettingDto> getEmployeeDevelopmentEmpId(Long empId) {
		List<AppraiseeDevelopmentGoalSetting> developmentGoals = appraiseeDevelopmentGoalSettingRepository.findAll();
		return appraiseeDevelopmentGoalSettingMapper.entityListToDTOList(developmentGoals);
	}

	@Override
	public List<AppraiseeDevelopmentGoalSettingDraftDto> submitAppraiseeDevelopmentGoalSettingDraft(
			List<AppraiseeDevelopmentGoalSettingDraft> devGoalsDraft) {

		List<AppraiseeDevelopmentGoalSettingDraft> savedEntityList = appraiseeDevelopmentGoalSettingDraftRepository.saveAll(devGoalsDraft);
		return appraiseeDevelopmentGoalSettingDraftMapper.entityListToDTOList(savedEntityList);
	}


	/**
	 * used to display the Employee status
	 *
	 * @param empId-Id of the Employee
	 * @return
	 */

	@Override
	public List<EmployeeStatusDto> getEmployeePerformanceByEmpId(Long empId) {
		List<EmployeeStatus> employeeStatusList = employeeStatusRepository.findByempId(empId);
		return employeeStatusMapper.entityListToDTOList(employeeStatusList);
	}

	/**
	 * @param employeeStatus -used to set the Submitted On date
	 * @return
	 */

	@Override
	public EmployeeStatus postemployeePerformance(EmployeeStatus employeeStatus) {
		LocalDate today = LocalDate.now();
		employeeStatus.setSubmittedOn(today);
		String status = "Submitted To Manager";
		employeeStatus.setCurrentStatus(status);
		return employeeStatusRepository.save(employeeStatus);
	}

	@Override
	public EmployeeStatus postDraftSaved(EmployeeStatus employeeStatus) {
		String status = "Save As Draft";
		employeeStatus.setCurrentStatus(status);
		return employeeStatusRepository.save(employeeStatus);
	}


	/**
	 * @param empId-used to set Approved on date
	 * @return
	 * @throws MessagingException-used to send mail notification
	 */

//	@Override
//	public EmployeeStatus postmanagerApproval(EmployeeStatus employeeStatus) throws MessagingException {
//		Long id = employeeStatus.getEmployeeStatusId();
//		Optional<EmployeeStatus> optionalEmployeeStatus = employeeStatusRepository.findById(id);
//		if (optionalEmployeeStatus.isPresent()) {
//			EmployeeStatus existingEmployeeStatus = optionalEmployeeStatus.get();
//			LocalDate today = LocalDate.now();
//			existingEmployeeStatus.setApprovedOn(today);
//			String status = "Approved";
//			existingEmployeeStatus.setCurrentStatus(status);
//			existingEmployeeStatus.setEmployeeStatusId(id);
//			pmsMailServiceImpl.sendApprovesMail();
//			return employeeStatusRepository.save(existingEmployeeStatus);
//		} else {
//			throw new IllegalArgumentException("EmployeeStatus with ID " + id + " not found");
//		}
//	}


	@Override
	public EmployeeStatus postmanagerApproval(Long empId,Long employeeStatusId ) throws MessagingException {
		EmployeeStatus employeeStatus=employeeStatusRepository.findByEmpIdAndEmployeeStatusId(empId, employeeStatusId);
		LocalDate today = LocalDate.now();
		employeeStatus.setApprovedOn(today);
		return employeeStatusRepository.save(employeeStatus);
	}



	private boolean checkIfWithinRange(LocalDate startDate, LocalDate endDate, LocalDate today) {
		return !today.isBefore(startDate) && !today.isAfter(endDate);
	}

	/**
	 * used to List the Quarter status
	 *
	 * @param empId-id of the Employee
	 * @return
	 */

////
////	@Override
////	public List<PendingAppraisalDto> getPendingAppraisalEmpIdAndReviewCycle(Long empId, String reviewCycle) {
////		String[] quartersStart = {"1 Apr", "1 Jul", "1 Oct", "1 Jan"};
////		String[] quartersEnd = {"30 Jun", "31 Sep", "31 Dec", "31 Mar"};
////		List<PendingAppraisal> pendingAppraisalList = new ArrayList<>();
////
////		LocalDate date = LocalDate.now();
////		String strDate = date.toString().substring(0, 4);
////		String newReviewCycle = reviewCycle.split("-")[0];
////
////		if (Integer.parseInt(newReviewCycle) < Integer.parseInt(strDate)) {
////			for (int i = 0; i < quartersStart.length; i++) {
////				PendingAppraisal pendingAppraisal = new PendingAppraisal();
////				pendingAppraisal.setStatus("Pending/Completed");
////				pendingAppraisalList.add(pendingAppraisal);
////			}
////		} else if (Integer.parseInt(newReviewCycle) > Integer.parseInt(strDate)) {
////			for (int i = 0; i < quartersStart.length; i++) {
////				PendingAppraisal pendingAppraisal = new PendingAppraisal();
////				pendingAppraisal.setStatus("InActive");
////				pendingAppraisalList.add(pendingAppraisal);
////			}
////		} else {
////			for (int i = 0; i < quartersStart.length; i++) {
////				LocalDate today = LocalDate.now();
////				int currentYear = today.getYear();
////				String start = quartersStart[i] + " " + currentYear;
////				String end = quartersEnd[i] + " " + currentYear;
////				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
////				LocalDate startDate = LocalDate.parse(start, formatter);
////				LocalDate endDate = LocalDate.parse(end, formatter);
////				boolean isWithinRange = checkIfWithinRange(startDate, endDate, today);
////				PendingAppraisal pendingAppraisal = new PendingAppraisal();
////				pendingAppraisal.setStatus(isWithinRange ? "Active" : "InActive");
////				pendingAppraisalList.add(pendingAppraisal);
////			}
////		}
//
//		List<PendingAppraisal> pendingAppraisal = pendingAppraisalRepository.findByEmpIdAndReviewCycle(empId, reviewCycle);
//
//		for (int i = 0; i < Math.min(pendingAppraisal.size(), pendingAppraisalList.size()); i++) {
//			pendingAppraisal.get(i).setStatus(pendingAppraisalList.get(i).getStatus());
//		}
//
//		pendingAppraisalRepository.saveAll(pendingAppraisal);
//
//		return pendingAppraisalMapper.entityListToDTOList(pendingAppraisal);
//	}
//




	/**
	 * delete the draft data by empId
	 */

	@Override
	public void deletedevelopmentdraftbyempid() {
		appraiseeDevelopmentGoalSettingDraftRepository.deleteAll();
	}


	/**
	 * use to List the training options
	 *
	 * @return
	 */
	@Override
	public List<TrainingOptionDto> getAllTrainingDropdown() {
		List<TrainingOption> trainingOptions = trainingOptionRepository.findAll();
		return trainingOptionMapper.entityListToDTOList(trainingOptions);
	}



//	@Override
//	public List<EmployeeStatusDto> getDataByYearAndReviewCycleAndEmpId(String year, String reviewCycle, Long empId) {
//		List<EmployeeStatus> employeeStatus = employeeStatusRepository.findByYearAndReviewCycleAndEmpId(year, reviewCycle, empId);
//		return employeeStatusMapper.entityListToDTOList(employeeStatus);
//	}


	@Override
	public List<EmployeeStatusDto> getByCurrentStatusAndManager(String currentStatus, String manager) {
		List<EmployeeStatus> employeeStatus = employeeStatusRepository.findByCurrentStatusAndManager(currentStatus, manager);
		return employeeStatusMapper.entityListToDTOList(employeeStatus);
	}
	@Override
	public EmployeeStatusDto postreverted(EmployeeStatusCo employeeStatus_co) throws MessagingException {
		Long id = employeeStatus_co.getEmpId();
		String comments = employeeStatus_co.getMgrRevertComments();
		EmployeeStatus existingEmployeeStatus = employeeStatusRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("EmployeeStatus with ID " + id + " not found"));

		existingEmployeeStatus.setCurrentStatus("Reverted");
		System.out.println(comments+"+++++++++++++++");
		existingEmployeeStatus.setMgrRevertComments(comments);
		existingEmployeeStatus.setSubmittedOn(null);
		existingEmployeeStatus.setEmployeeStatusId(id);

		EmployeeStatus updatedEmployeeStatus = employeeStatusRepository.save(existingEmployeeStatus);
		return employeeStatusMapper.entityToDTO(updatedEmployeeStatus);

	}

	@Override
	public List<AppraiseeDefaultGoalsDto> getAppraiseeDefaultGoalsByDepartmentAndDesignation(String department, String designation) {
		List<AppraiseeDefaultGoals> employeeStatus = appraiseeDefaultRepository.findByDepartmentAndDesignation(department, designation);
		return appraiseeDefaultGoalsMapper.entityListToDTOList(employeeStatus);
	}

//	@Override
//	public List<PendingAppraisalDto> getPendingAppraisalByReviewCycle(String reviewCycle) {
//		String[] quartersStart = {"1 Apr", "1 Jul", "1 Oct", "1 Jan"};
//		String[] quartersEnd = {"30 Jun", "31 Sep", "31 Dec", "31 Mar"};
//		List<PendingAppraisal> pendingAppraisalList = new ArrayList<>();
//
//		LocalDate date = LocalDate.now();
//		String strDate = date.toString().substring(0, 4);
//		String newReviewCycle = reviewCycle.split("-")[0];
//
//		if (Integer.parseInt(newReviewCycle) < Integer.parseInt(strDate)) {
//			for (int i = 0; i < quartersStart.length; i++) {
//				PendingAppraisal pendingAppraisal = new PendingAppraisal();
//				pendingAppraisal.setStatus("Pending/Completed");
//				pendingAppraisalList.add(pendingAppraisal);
//			}
//		} else if (Integer.parseInt(newReviewCycle) > Integer.parseInt(strDate)) {
//			for (int i = 0; i < quartersStart.length; i++) {
//				PendingAppraisal pendingAppraisal = new PendingAppraisal();
//				pendingAppraisal.setStatus("InActive");
//				pendingAppraisalList.add(pendingAppraisal);
//			}
//		} else {
//			for (int i = 0; i < quartersStart.length; i++) {
//				LocalDate today = LocalDate.now();
//				int currentYear = today.getYear();
//				String start = quartersStart[i] + " " + currentYear;
//				String end = quartersEnd[i] + " " + currentYear;
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
//				LocalDate startDate = LocalDate.parse(start, formatter);
//				LocalDate endDate = LocalDate.parse(end, formatter);
//				boolean isWithinRange = checkIfWithinRange(startDate, endDate, today);
//				System.out.println(isWithinRange+"isWithinRange"+ quartersStart[i]);
//				PendingAppraisal pendingAppraisal = new PendingAppraisal();
//				pendingAppraisal.setStatus(isWithinRange ? "Active" : "InActive");
//				pendingAppraisalList.add(pendingAppraisal);
//			}
//		}
//
//		List<PendingAppraisal> pendingAppraisal = pendingAppraisalRepository.findByReviewCycle(reviewCycle);
//
//
//		for (int i = 0; i < Math.min(pendingAppraisal.size(), pendingAppraisalList.size()); i++) {
//			pendingAppraisal.get(i).setStatus(pendingAppraisalList.get(i).getStatus());
//		}
//
//
//
//		pendingAppraisalRepository.saveAll(pendingAppraisal);
//
//		return pendingAppraisalMapper.entityListToDTOList(pendingAppraisal);
//	}
//


//	@Override
//	@Transactional
//	public List<PendingAppraisal> getpendingAppraisal(String reviewCycle) {
//		List<PendingAppraisal> pendingAppraisalList = pendingAppraisalRepository.findByReviewCycle(reviewCycle);
//		System.out.println(pendingAppraisalList+"pendingAppraisalList");
//		for(PendingAppraisal pendingAppraisal:pendingAppraisalList){
//			List<EmployeeStatus> empObj = employeeStatusRepository.findByReviewCycle(reviewCycle);
//			if(empObj.size()<pendingAppraisalList.size()){
//				EmployeeStatus employeeStatus = new EmployeeStatus();
//				employeeStatus.setAppraisalQuarterId(pendingAppraisal.getPendingAppraisalId());
//				employeeStatus.setAppraisalCycle(pendingAppraisal.getAppraisalQuarter());
//				employeeStatus.setReviewCycle(pendingAppraisal.getReviewCycle());
////               employeeStatus.setEmpId(pendingAppraisal.getEmpId());
//				employeeStatus.setCurrentStatus(pendingAppraisal.getStatus().equals("Active")? "Open" : "disable");
//				employeeStatus.setYear(pendingAppraisal.getReviewCycle().substring(0,4));
//				employeeStatusRepository.save(employeeStatus);
//			}else{
//				return null;
//			}
//		}
//		return pendingAppraisalList;
//	}
//



	@Override
	@Transactional
	public List<PendingAppraisal> getpendingAppraisal(String reviewCycle) {

		List<PendingAppraisal> pendingAppraisalList = pendingAppraisalRepository.findByReviewCycle(reviewCycle);
		System.out.println(pendingAppraisalList+"pendingAppraisalList");
		for(PendingAppraisal pendingAppraisal:pendingAppraisalList){
			List<EmployeeStatus> empObj = employeeStatusRepository.findByReviewCycle(reviewCycle);
			if(empObj.size()<pendingAppraisalList.size()){
				EmployeeStatus employeeStatus = new EmployeeStatus();
				employeeStatus.setAppraisalQuarterId(pendingAppraisal.getPendingAppraisalId());
				employeeStatus.setAppraisalCycle(pendingAppraisal.getAppraisalQuarter());
				employeeStatus.setReviewCycle(pendingAppraisal.getReviewCycle());

				employeeStatus.setCurrentStatus(pendingAppraisal.getStatus().equals("Active")? "Open" : "disable");
				employeeStatus.setYear(pendingAppraisal.getReviewCycle().substring(0,4));
				employeeStatusRepository.save(employeeStatus);
			}else{
				return null;
			}
		}
		return pendingAppraisalList;
	}
















	@Override
	public List<TrainingOptionDto> getTrainingByTrainingId(Long trainingOptionId) {
		List<TrainingOption> trainingOption = trainingOptionRepository.findBytrainingOptionId(trainingOptionId);
		return trainingOptionMapper.entityListToDTOList(trainingOption);
	}


//	@Override
//	public List<String> getTrainingByTrainingName(String trainingName) {
//		TrainingOption trainingOption = trainingOptionRepository.findByTrainingName(trainingName);
//		Long trainingOptionId = trainingOption.getTrainingOptionId();
//		List<DevelopmentOption> developmentOptionList = developmentOptionRepository.findByTrainingOption_TrainingOptionId(trainingOptionId);
//		List<String> developmentOptionStrings = developmentOptionList.stream()
//				.map(development -> development.getDevelopmentSubId() + " - " + development.getDevelopmentOptionName())
//				.collect(Collectors.toList());
//		return developmentOptionStrings;
//
//	}

	@Override
	public List<String> getTrainingByTrainingName(String trainingName) {
		TrainingOption trainingOption = trainingOptionRepository.findByTrainingName(trainingName);
		Long trainingOptionId = trainingOption.getTrainingOptionId();
		List<DevelopmentOption> developmentOptionList = developmentOptionRepository.findByTrainingOption_TrainingOptionId(trainingOptionId);
		List<String> developmentOptionStrings = developmentOptionList.stream()
				.map(development -> development.getDevelopmentSubId() + " - " + development.getDevelopmentOptionName())
				.collect(Collectors.toList());
		return developmentOptionStrings;

	}


	@Override
	public List<TargetOperator> getTargetOperatorBytargetOperatorId(Long targetOperatorId) {
		return targetOperatorRepository.findByTargetOperatorId(targetOperatorId);
	}

	@Override
	public List<TargetOperator> getAllTargretOperator() {
		List<TargetOperator> targetOperatorList= targetOperatorRepository.findAll();
		return targetOperatorRepository.findAll();
	}
	@Override
	public Map<String, Object> processGoals(Map<String, Object> requestData) {
		// Extract fields from requestData
		String department = (String) requestData.get("division");
		String designation = (String) requestData.get("role");
		Long empId = Long.valueOf(requestData.get("emp_id").toString());
		Long overallStatusId = Long.valueOf(requestData.get("overall_statusId").toString());
		// Initialize response map
		Map<String, Object> response = new HashMap<>();
		// Fetch data from repositories for non-draft tables
		List<AppraiseeGoalSetting> goalsData = appraiseeGoalSettingRepository.findByEmpIdAndSubOverallStatusId(empId, overallStatusId);
		List<AppraiseeDevelopmentGoalSetting> devGoalsData = appraiseeDevelopmentGoalSettingRepository.findByEmployeeIdAndSubOverallStatusId(empId, overallStatusId);
		// Fetch data from repositories for draft tables
		List<AppraiseeGoalSettingDraft> goalsDraftData = appraiseeGoalSettingDraftRepository.findByEmpIdAndSubOverallStatusId(empId, overallStatusId);
		List<AppraiseeDevelopmentGoalSettingDraft> devGoalsDraftData = appraiseeDevelopmentGoalSettingDraftRepository.findByEmployeeIdAndSubOverallStatusId(empId, overallStatusId);
		// If goals or devGoals data exists, process them
		if (!goalsData.isEmpty() || !devGoalsData.isEmpty() || !goalsDraftData.isEmpty() || !devGoalsDraftData.isEmpty()) {
			// Map non-draft entities to DTOs
			List<AppraiseeGoalSettingDto> goalsList = goalsData.stream()
					.map(this::mapToGoalSettingDto)
					.collect(Collectors.toList());
			List<AppraiseeDevelopmentGoalSettingDto> devGoalsList = devGoalsData.stream()
					.map(this::mapToDevelopmentGoalSettingDto)
					.collect(Collectors.toList());
			// Map draft entities to DTOs
			List<AppraiseeGoalSettingDraftDto> goalsDraftList = goalsDraftData.stream()
					.map(this::mapToGoalSettingDraftDto)
					.collect(Collectors.toList());
			List<AppraiseeDevelopmentGoalSettingDraftDto> devGoalsDraftList = devGoalsDraftData.stream()
					.map(this::mapToDevelopmentGoalSettingDraftDto)
					.collect(Collectors.toList());
			// Add DTO lists to response
			response.put("goalsList", goalsList);
			response.put("devGoalsList", devGoalsList);
			response.put("goalsDraftList", goalsDraftList);
			response.put("devGoalsDraftList", devGoalsDraftList);
		} else {
			// If no data exists in regular and draft tables, fetch default goals
			List<AppraiseeDefaultGoals> defaultGoals = appraiseeDefaultRepository.findByDepartmentAndDesignation(department, designation);
			List<AppraiseeDefaultGoalsDto> defaultGoalsDtoList = defaultGoals.stream()
					.map(this::mapToDefaultGoalsDto)
					.collect(Collectors.toList());
			// Add default goals to response
			response.put("defaultGoals", defaultGoalsDtoList);
		}
		// Return the response
		return response;
	}

	@Override
	public boolean addEmpIdEmployeeStatus(Long empId,String year,String reviewCycle) {
		boolean result=employeeStatusRepository.existsByEmpIdAndYearAndReviewCycle(empId,year,reviewCycle);
		if(result){
			return true;
		}
		else{
		   return false;
		}
	}

	// Map entities to DTOs for regular goals
	private AppraiseeGoalSettingDto mapToGoalSettingDto(AppraiseeGoalSetting goalSetting) {
		return new AppraiseeGoalSettingDto(
				goalSetting.getGoalId(),
				goalSetting.getEmpId(),
				goalSetting.getKra(),
				goalSetting.getGoals(),
				goalSetting.getMeasurement(),
				goalSetting.getWeightage(),
				goalSetting.getTotalWeightage(),
				goalSetting.getTarget(),
				goalSetting.getDesignation(),
				goalSetting.getDepartment(),
				goalSetting.getSubOverallStatusId(),
				goalSetting.getSelfComment(),
				goalSetting.getSelfRating(),
				goalSetting.getManagerComment(),
				goalSetting.getManagerRating()
		);
	}
	// Map entities to DTOs for development goals
	private AppraiseeDevelopmentGoalSettingDto mapToDevelopmentGoalSettingDto(AppraiseeDevelopmentGoalSetting devGoalSetting) {
		return new AppraiseeDevelopmentGoalSettingDto(
				devGoalSetting.getDg_id(),
				devGoalSetting.getEmployeeId(),
				devGoalSetting.getTrainingOptionId(),
				devGoalSetting.getDevelopmentSubId(),
				devGoalSetting.getSelfAssessment(),
				devGoalSetting.getManagerAssessment(),
				devGoalSetting.getTraining(),
				devGoalSetting.getDescription(),
				devGoalSetting.getGoal(),
				devGoalSetting.getSubOverallStatusId()
		);
	}
	// Map draft entities to DTOs for goal drafts
	private AppraiseeGoalSettingDraftDto mapToGoalSettingDraftDto(AppraiseeGoalSettingDraft goalSettingDraft) {

		System.out.println("Mapping: " + goalSettingDraft);

		return new AppraiseeGoalSettingDraftDto(
				goalSettingDraft.getGoalDraftId(),
				goalSettingDraft.getEmpId(),
				goalSettingDraft.getKra(),
				goalSettingDraft.getGoals(),
				goalSettingDraft.getMeasurement(),
				goalSettingDraft.getWeightage(),
				goalSettingDraft.getTotalWeightage(),
				goalSettingDraft.getTarget(),
				goalSettingDraft.getDesignation(),
				goalSettingDraft.getDepartment(),
				goalSettingDraft.getSubOverallStatusId(),
				goalSettingDraft.getSelfComment(),
				goalSettingDraft.getSelfRating(),
				goalSettingDraft.getManagerComment(),
				goalSettingDraft.getManagerRating()
		);
	}

	// Map draft entities to DTOs for development goal drafts
	private AppraiseeDevelopmentGoalSettingDraftDto mapToDevelopmentGoalSettingDraftDto(AppraiseeDevelopmentGoalSettingDraft devGoalSettingDraft) {
		return new AppraiseeDevelopmentGoalSettingDraftDto(
				devGoalSettingDraft.getDgDraftId(),
				devGoalSettingDraft.getEmployeeId(),
				devGoalSettingDraft.getTrainingOptionId(),
				devGoalSettingDraft.getDevelopmentSubId(),
				devGoalSettingDraft.getSelfAssessment(),
				devGoalSettingDraft.getManagerAssessment(),
				devGoalSettingDraft.getTraining(),
				devGoalSettingDraft.getDescription(),
				devGoalSettingDraft.getGoal(),
				devGoalSettingDraft.getSubOverallStatusId()
		);
	}
	// Map entities to DTOs for default goals
	private AppraiseeDefaultGoalsDto mapToDefaultGoalsDto(AppraiseeDefaultGoals defaultGoal) {
		return new AppraiseeDefaultGoalsDto(
				defaultGoal.getDefaultGoalsId(),
//				defaultGoal.getEmpId(),
				defaultGoal.getKra(),
				defaultGoal.getGoals(),
				defaultGoal.getMeasurement(),
				defaultGoal.getWeightage(),
				defaultGoal.getTotalWeightage(),
				defaultGoal.getTarget(),
				defaultGoal.getDesignation(),
				defaultGoal.getDepartment()
		);
	}

//	---------------------NEW LOGIC FOR APPRAISAL CYCLE TABLE AND EMPLOYEE STATUS TABLE-----------------
//@Override
//public List<PendingAppraisalDto> cratePendingAppraisal(List<PendingAppraisalCo> pendingAppraisalCoList) throws ExecutionException, InterruptedException {
//	List<FileAndObjectTypeBean> allEmps = null;
//	List<PendingAppraisal>pendingAppraisalList=pendingAppraisalMapper.coListToEntityList(pendingAppraisalCoList);
//	pendingAppraisalList=pendingAppraisalRepository.saveAll(pendingAppraisalList);
//	List<PendingAppraisalDto> pendingAppraisalDtoList=pendingAppraisalMapper.entityListToDtoList(pendingAppraisalList);
//	for(PendingAppraisal pendingAppraisal : pendingAppraisalList){
//		CompletableFuture<List<FileAndObjectTypeBean>> allEmpsCompFutureList = employeeOperations.getAllEmp();
//		allEmps = allEmpsCompFutureList.get();
//		for(FileAndObjectTypeBean fileAndObjectTypeBean : allEmps){
//			EmpResDTO empResDTO = fileAndObjectTypeBean.getEmpResDTO();
//			EmployeeStatus employeeStatus = new EmployeeStatus();
//			employeeStatus.setEmpId(empResDTO.getEmpId());
//			employeeStatus.setUserId(empResDTO.getUserId());
//			employeeStatus.setAppraisalQuarterId(pendingAppraisal.getPendingAppraisalId());
//			employeeStatus.setAppraisalCycle(pendingAppraisal.getAppraisalQuarter().equalsIgnoreCase("Quarter 1")?"1" : pendingAppraisal.getAppraisalQuarter().equalsIgnoreCase("Quarter 2")?"2" : pendingAppraisal.getAppraisalQuarter().equalsIgnoreCase("Quarter 3")?"3" : pendingAppraisal.getAppraisalQuarter().equalsIgnoreCase("Quarter 4")?"4": null);
//			employeeStatus.setManager(empResDTO.getReportingManager());
//			employeeStatus.setMgrId(String.valueOf(Long.valueOf(empResDTO.getReportTo())));
//			employeeStatus.setYear(String.valueOf(LocalDate.now().getYear()));
//			employeeStatus.setReviewCycle(pendingAppraisal.getReviewCycle());
//			employeeStatusRepository.save(employeeStatus);
//		}
//	}
//	return pendingAppraisalDtoList;
//}

	@Override
	public List<PendingAppraisalDto> cratePendingAppraisal(List<PendingAppraisalCo> pendingAppraisalCoList) throws ExecutionException, InterruptedException {
		List<PendingAppraisal> pendingAppraisalList = pendingAppraisalMapper.coListToEntityList(pendingAppraisalCoList);

		// Check if data already exists for each record
		for (PendingAppraisal pendingAppraisal : pendingAppraisalList) {
			boolean exists = pendingAppraisalRepository.existsByAppraisalQuarterAndReviewCycle(
					pendingAppraisal.getAppraisalQuarter(),
					pendingAppraisal.getReviewCycle()
			);
			if (exists) {
				return null;
			}
		}
		pendingAppraisalList = pendingAppraisalRepository.saveAll(pendingAppraisalList);
		return pendingAppraisalMapper.entityListToDtoList(pendingAppraisalList);
	}
	@Override
	public List<String> getAllReviewCycle() {
		Set<String> ReviewCycle = new LinkedHashSet<>();

		List<PendingAppraisal> pendingAppraisals = pendingAppraisalRepository.findAll();
		for(PendingAppraisal i:pendingAppraisals){
			ReviewCycle.add(i.getReviewCycle());
		}
		List<String> ReviewcycleList = new ArrayList<>();

		for(String i:ReviewCycle){
			ReviewcycleList.add(i);

		}
		Collections.sort(ReviewcycleList);
		return ReviewcycleList;
	}

	@Override
	public List<EmployeeStatusDto> getDataByReviewCycleAndEmpId(String reviewCycle, Long empId) throws ExecutionException, InterruptedException {
		List<EmployeeStatus> employeeStatusList = employeeStatusRepository.findByReviewCycleAndEmpId(reviewCycle, empId);
		System.out.println(employeeStatusList+"employeeStatusList11");
		System.out.println(employeeStatusList);
		List<EmployeeStatus> returnEmpStatus = new ArrayList<>();

		if (employeeStatusList.isEmpty()) {
			System.out.println("hi");
			CompletableFuture<EmpAndUserResponse> empAndUserResponseCompletableFuture = employeeOperations.getEmpByEmpId(empId);
			EmpAndUserResponse empAndUserResponse = empAndUserResponseCompletableFuture.get();
			EmpResDTO empResDTO = empAndUserResponse.getFileAndObjectTypeBean().getEmpResDTO();
			UserDTO userDTO = empAndUserResponse.getUserDTO();

			List<PendingAppraisal> pendingAppraisalList = pendingAppraisalRepository.findByReviewCycle(reviewCycle);
			if (pendingAppraisalList != null) {
				for (PendingAppraisal pendingAppraisal : pendingAppraisalList) {
					EmployeeStatus employeeStatus = new EmployeeStatus();
					employeeStatus.setEmpId(empId);
					employeeStatus.setUserId(userDTO.getUserId());
					employeeStatus.setAppraisalQuarterId(pendingAppraisal.getPendingAppraisalId());
					employeeStatus.setAppraisalCycle(pendingAppraisal.getAppraisalQuarter());
					employeeStatus.setManager(empResDTO.getReportingManager());
					employeeStatus.setMgrId(empResDTO.getReportTo());
					employeeStatus.setCurrentStatus(pendingAppraisal.getStatus().equals("Active") ? "Open" : "Disable");
					employeeStatus.setReviewCycle(pendingAppraisal.getReviewCycle());
//					employeeStatus.setYear(pendingAppraisal.getReviewCycle().substring(0, 4));
					returnEmpStatus.add(employeeStatusRepository.save(employeeStatus));
				}
				return employeeStatusMapper.entityListToDTOList(returnEmpStatus);
			} else {
				return null;
			}
		}
		else {
			return employeeStatusMapper.entityListToDTOList(employeeStatusList);
		}
	}



	@Override
	public List<PendingAppraisalDto> getPendingAppraisalByReviewCycle(String reviewCycle) {
		String[] quartersStart = {"1 Apr", "1 Jul", "1 Oct", "1 Jan"};
		String[] quartersEnd = {"30 Jun", "31 Sep", "31 Dec", "31 Mar"};
		List<PendingAppraisal> pendingAppraisalList = new ArrayList<>();

		LocalDate date = LocalDate.now();
		int month = date.getMonthValue();
		String strDate = date.toString().substring(0, 4);
		System.out.println(strDate+"strDate");
//        String newReviewCycle = reviewCycle.split("-")[0];
		String newReviewCycle = (month >= 1 && month <= 3)
				? reviewCycle.split("-")[1]
				: reviewCycle.split("-")[0];
		System.out.println(newReviewCycle+"newReviewCycle");

		if (Integer.parseInt(newReviewCycle) < Integer.parseInt(strDate)) {
			System.out.println("IF");
			for (int i = 0; i < quartersStart.length; i++) {
				PendingAppraisal pendingAppraisal = new PendingAppraisal();
				pendingAppraisal.setStatus("Pending/Completed");
				pendingAppraisalList.add(pendingAppraisal);
			}
		} else if (Integer.parseInt(newReviewCycle) > Integer.parseInt(strDate)) {
			System.out.println("ELSEIF");
			for (int i = 0; i < quartersStart.length; i++) {
				PendingAppraisal pendingAppraisal = new PendingAppraisal();
				pendingAppraisal.setStatus("InActive");
				pendingAppraisalList.add(pendingAppraisal);
			}
		} else {
			for (int i = 0; i < quartersStart.length; i++) {
				System.out.println("ELSE");
				LocalDate today = LocalDate.now();
				int currentYear = today.getYear();
				String start = quartersStart[i] + " " + currentYear;
				String end = quartersEnd[i] + " " + currentYear;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
				LocalDate startDate = LocalDate.parse(start, formatter);
				LocalDate endDate = LocalDate.parse(end, formatter);
				boolean isWithinRange = checkIfWithinRange(startDate, endDate, today);
				PendingAppraisal pendingAppraisal = new PendingAppraisal();
				pendingAppraisal.setStatus(isWithinRange ? "Active" : "InActive");
				pendingAppraisalList.add(pendingAppraisal);
			}
		}

		List<PendingAppraisal> pendingAppraisal = pendingAppraisalRepository.findByReviewCycle(reviewCycle);

		for (int i = 0; i < Math.min(pendingAppraisal.size(), pendingAppraisalList.size()); i++) {
			pendingAppraisal.get(i).setStatus(pendingAppraisalList.get(i).getStatus());
		}

		pendingAppraisalRepository.saveAll(pendingAppraisal);


		return pendingAppraisalMapper.entityListToDTOList(pendingAppraisal);
	}

	@Override
	@Transactional
	public boolean preventFromNavigationToKra(Long empId, Long sId) {
		List<AppraiseeDevelopmentGoalSetting> appraiseeDevelopmentGoalSettingList = appraiseeDevelopmentGoalSettingRepository.findByEmployeeIdAndSubOverallStatusId(empId, sId);
		if(appraiseeDevelopmentGoalSettingList.size()>=1) {
			AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting = appraiseeDevelopmentGoalSettingList.get(0);
			if (appraiseeDevelopmentGoalSetting != null) {
				Long employeeId = appraiseeDevelopmentGoalSetting.getEmployeeId();
				Long subId = appraiseeDevelopmentGoalSetting.getSubOverallStatusId();
				if (employeeId != null && subId != null) {
					boolean result = employeeStatusRepository.existsByEmpIdAndEmployeeStatusId(employeeId, subId);
					if (result) {
						return true;
					}
				}
			}
		}
		return false;
	}


}


