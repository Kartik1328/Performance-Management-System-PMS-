package com.pms.controller;
import java.time.LocalDate;
import java.util.List;
import com.pms.Model.*;
import com.pms.beans.EmpAndUserResponse;
import com.pms.co.*;
import com.pms.dto.*;
import com.pms.mapper.AppraiseeDevelopmentGoalSettingMapper;
import com.pms.repository.EmployeeStatusRepository;
import com.pms.service.PmsService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
//ALL THE NEW IMPORTS ARE BELOW--------------------------------------------------------------
import jakarta.validation.Valid;
import com.pms.utils.ResponseUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Slf4j

public class PmsController {
    @Autowired
    private PmsService pmsService;

//    CONTROLLER "GET API" FOR employee status(earlier master table dummy) TABLE
// 	--new method created for manager screen 2 where the status of all the employee is shown-----------


    @GetMapping("/getByMasterDummy/{mgr_id}/{appraisal_quarter_id}")
    public ResponseEntity<?> getByMgrId(@PathVariable String mgr_id) {
        List<EmployeeStatus> data = pmsService.getByMgrId(mgr_id);
        if (!data.isEmpty()) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
        }
    }

    @GetMapping("/getBySelfAsm/{emp_id}/{sub_overall_status_id}")
    public ResponseEntity<ResponseUtil<AppraiseeSelfAssessementDto>> getById(@Valid @PathVariable Long emp_id, @PathVariable Long sub_overall_status_id) {
        AppraiseeSelfAssessementDto assessment = pmsService.getById(emp_id, sub_overall_status_id);
        log.info(String.valueOf(assessment));

        ResponseUtil<AppraiseeSelfAssessementDto> response = ResponseUtil.<AppraiseeSelfAssessementDto>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("getBySelfAsm retrieved successfully")
                .data(assessment)
                .build();
        return ResponseEntity.ok(response);

    }
    //HERE IT IS SHOWING ERROR BECAUSE "AppraiseeSelfAssessementDto" HAS TO BE UPDATED IN IMPL AND SERVICE ALSO.....

    @GetMapping("/getBySelfAsmDraft/{emp_id}")
    public ResponseEntity<ResponseUtil<AppraiseeSelfAssessementDraftDto>> getBydraftId(@Valid @PathVariable Long emp_id) {
        AppraiseeSelfAssessementDraftDto assessment = pmsService.getByDraftId(emp_id);
        ResponseUtil<AppraiseeSelfAssessementDraftDto> response = ResponseUtil.<AppraiseeSelfAssessementDraftDto>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("getBySelfAsmDraft  retrieved successfully")
                .data(assessment)
                .build();
        return ResponseEntity.ok(response);
    }

    //  http://localhost:8080/api/getByProfile/5
    @GetMapping("/getByProfile/{employeeId}")
//    public ResponseEntity<ResponseUtil<EmpAndUserResponse>> getByProfile(@Valid @PathVariable Long employeeId) {

    public ResponseEntity<EmpAndUserResponse> getByProfile(@Valid @PathVariable Long employeeId) throws ExecutionException, InterruptedException {
        EmpAndUserResponse details = pmsService.getByProfileId(employeeId);
//        ResponseUtil<EmpAndUserResponse> response = ResponseUtil.<AppraiseeDetailsDto>builder()
//                .status(HttpStatus.OK.value())
//                .success(true)
//                .message("getByProfile  retrieved successfully")
//                .data(details)
//                .build();
//        return ResponseEntity.ok(response);
        return ResponseEntity.ok(details);

    }

//    -----------------------KRA TABLE REPLACED BY GOAL SETTING------------------------------------------
// now here every data of the gaols and development goals filled are fetched through PROPS in the frontend.
    @GetMapping("/getBySelfAsmAll/{emp_id}/{sub_overall_status_id}")
    public ResponseEntity<?> getByKra(@Valid @PathVariable Long emp_id, @PathVariable Long sub_overall_status_id) {
        List<AppraiseeGoalSetting> assessment = pmsService.getByKra(emp_id,sub_overall_status_id);
        if (!assessment.isEmpty()) {
            return ResponseEntity.ok(assessment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
        }
    }

//    ---------------------------------------------------------------------------------------------------

    @PostMapping("/postBySelfAsm")
    public ResponseEntity<ResponseUtil<AppraiseeSelfAssessementDto>> insert(@Valid @RequestBody AppraiseeSelfAssessementCo assessment) throws MessagingException {
        AppraiseeSelfAssessementDto savedAssessment = pmsService.insert(assessment);
        ResponseUtil<AppraiseeSelfAssessementDto> response = ResponseUtil.<AppraiseeSelfAssessementDto>builder()
                .status(HttpStatus.CREATED.value())
               .success(true)
                .message(" inserted successfully")
               .data(savedAssessment)
               .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/postBySelfAsmDraft")
    public ResponseEntity<ResponseUtil<AppraiseeSelfAssessementDraftDto>> insert( @Valid @RequestBody AppraiseeSelfAssessementDraftCo assessment_draft) {
        AppraiseeSelfAssessementDraftDto savedAssessment = pmsService.insert(assessment_draft);
        ResponseUtil<AppraiseeSelfAssessementDraftDto> response = ResponseUtil.<AppraiseeSelfAssessementDraftDto>builder()
                .status(HttpStatus.CREATED.value())
                .success(true)
                .message(" inserted successfully")
                .data(savedAssessment)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/deleteSelfAsmDraft/{sad_id}")
    public ResponseEntity<ResponseUtil<String>> deleteSelfAsmDraft(@Valid @PathVariable Long sad_id) {
        pmsService.deleteSelfAsmDraft(sad_id);
        ResponseUtil<String> response = ResponseUtil.<String>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .success(true)
                .message(" deleted successfully")
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

//    HERE I HAVE IMPLEMENTED THE EMAIL TRIGGER IN THE SELF ASSSESSEMNT.

    @PostMapping("/postByMng")
    public ResponseEntity<ResponseUtil<AppraiseeManagerAssessmentDto>> insert(@Valid @RequestBody AppraiseeManagerAssessmentCo manager_assessement) {
        AppraiseeManagerAssessmentDto savedAssessment = pmsService.insertmng(manager_assessement);
        ResponseUtil<AppraiseeManagerAssessmentDto> response = ResponseUtil.<AppraiseeManagerAssessmentDto>builder()
                .status(HttpStatus.CREATED.value())
                .success(true)
                .message(" inserted successfully")
                .data(savedAssessment)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getByManagerAsm/{ma_id}")
    public ResponseEntity<ResponseUtil<AppraiseeManagerAssessmentDto>> getByManagerId(@Valid @PathVariable Long ma_id) {
        AppraiseeManagerAssessmentDto assessment = pmsService.getByManagerId(ma_id);
        ResponseUtil<AppraiseeManagerAssessmentDto> response = ResponseUtil.<AppraiseeManagerAssessmentDto>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("getByManagerAsm  retrieved successfully")
                .data(assessment)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getByAnnual/{ar_id}")
    public ResponseEntity<ResponseUtil<AppraiseeAnnualReviewDto>> getByAnnualId(@Valid @PathVariable Long ar_id) {
        System.out.print(ar_id);
        AppraiseeAnnualReviewDto review = pmsService.getByAnnualId(ar_id);
        ResponseUtil<AppraiseeAnnualReviewDto> response = ResponseUtil.<AppraiseeAnnualReviewDto>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("getByAnnual  retrieved successfully")
                .data(review)
                .build();
        return ResponseEntity.ok(response);

    }

    @PostMapping("/postByAnnRev")
    public ResponseEntity<ResponseUtil<AppraiseeAnnualReviewDto>> insert(@Valid @RequestBody AppraiseeAnnualReviewCo annual_review) {
        System.out.print("hello");
        System.out.print(annual_review + "....");
        AppraiseeAnnualReviewDto savedAnnualReview = pmsService.insert(annual_review);
        ResponseUtil<AppraiseeAnnualReviewDto> response = ResponseUtil.<AppraiseeAnnualReviewDto>builder()
                .status(HttpStatus.CREATED.value())
                .success(true)
                .message(" inserted successfully")
                .data(savedAnnualReview)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

//    ------------------------------------------------------------------------------------------------------------------

    @GetMapping("/getByDevGoals/{employee_id}/{sub_overall_status_id}")
    public ResponseEntity<ResponseUtil<AppraiseeDevelopmentGoalSettingDto>> getByDg(@Valid @PathVariable Long employee_id, @PathVariable Long sub_overall_status_id) {
        AppraiseeDevelopmentGoalSettingDto devgoals = pmsService.getByDgId(employee_id, sub_overall_status_id);
        ResponseUtil<AppraiseeDevelopmentGoalSettingDto> response = ResponseUtil.<AppraiseeDevelopmentGoalSettingDto>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("getByDevGoals  retrieved successfully")
                .data(devgoals)
                .build();
        return ResponseEntity.ok(response);
    }

//    --------------------------------------------------------------------------------------------------------------------------------------

    @PostMapping("/postByDg")
    public ResponseEntity<ResponseUtil<AppraiseeDevelopmentGoalSettingDto>> insert(@Valid @RequestBody AppraiseeDevelopmentGoalSettingCo development_goals) {
        System.out.print(development_goals + "....");
        AppraiseeDevelopmentGoalSettingDto savedDevelopmentGoals = pmsService.insert(development_goals);
        ResponseUtil<AppraiseeDevelopmentGoalSettingDto> response = ResponseUtil.<AppraiseeDevelopmentGoalSettingDto>builder()
                .status(HttpStatus.CREATED.value())
                .success(true)
                .message(" inserted successfully")
                .data(savedDevelopmentGoals)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //NOW THE APIs FOR THE EMPLOYEE STATUS TABLE
    @GetMapping("/getByEmployeeStatus")
    public List<EmployeeStatusDto> getemployeePerformanceDates() {
        return pmsService.getemployeePerformanceDates();
    }
//    since List is used therefore no particular id is needed to be written in the API.


//    @PostMapping("/postemployeePerformance")
//    public ResponseEntity<?> postemployeePerformance(@Valid @RequestBody EmployeeStatus employeeStatusCo){
//        return new ResponseEntity<>(pmsService.postemployeePerformance(employeeStatusCo), HttpStatus.OK);
//    }

    @PostMapping("/postDraftSaved")
    public ResponseEntity<?> postDraftSaved(@Valid @RequestBody EmployeeStatus employeeStatusCo){
        return new ResponseEntity<>(pmsService.postDraftSaved(employeeStatusCo), HttpStatus.OK);
    }

    @PostMapping("/postByEmployeeStatus")
    public ResponseEntity<?> postemployeePerformanceDates(@Valid @RequestBody EmployeeStatusCo employeeStatusCo) {
        return new ResponseEntity<>(pmsService.postemployeePerformanceDates(employeeStatusCo), HttpStatus.OK);
    }


    @Autowired
    private EmployeeStatusRepository employeeStatusRepository;


//    public String getQuarter() {
//        LocalDate date = LocalDate.now();
//        int month = date.getMonthValue();
//
//        if (month >= 1 && month <= 3) {
//            return "Quarter 4";
//        } else if (month >= 4 && month <= 6) {
//            return "Quarter 1";
//        } else if (month >= 7 && month <= 9) {
//            return "Quarter 2";
//        } else {
//            return "Quarter 3";
//        }
//    }



    public String getQuarter() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();

        if (month >= 1 && month <= 3) {
            return "4";
        } else if (month >= 4 && month <= 6) {
            return "1";
        } else if (month >= 7 && month <= 9) {
            return "2";
        } else {
            return "3";
        }
    }




    public String getReviewCycle()
    {
        LocalDate date = LocalDate.now();
        return date.getYear() + "-" +String.valueOf(Integer.valueOf(date.getYear())+1);
    }

//-----------------------------------------------------------------------------------------------
//
//    @Transactional
//    @PostMapping("/postemployeeKraAndDevGoalsRating/{empId}")
//    public ResponseEntity<ResponseUtil<Object>> updateAppraiseeGoalSettingAndRatingComments(
//            @RequestBody PmsGoalsAndDevelopmentGoalsAndRatingComments combinedGoalsAndRatingDTO,
//            @PathVariable Long empId) throws MessagingException {
//
//        try {
//            List<AppraiseeDevelopmentGoalSetting> devGoals = combinedGoalsAndRatingDTO.getDevGoalsList().stream()
////                    .distinct()
//                    .collect(Collectors.toList());
//
//            List<AppraiseeDevelopmentGoalSettingDto> savedDevGoals = devGoals.stream()
//                    .map(devGoal -> {
//                        AppraiseeDevelopmentGoalSettingCo devCo = new AppraiseeDevelopmentGoalSettingCo();
//                        mapDevGoalToCo(devGoal, devCo); // Map once
////                        return pmsService.insertdev(devCo); // Save once
//                        return pmsService.updateDevGoals(devCo); // Save once
//
//                    })
//                    .collect(Collectors.toList());
//
//            OverAllRatingAndComments  overAllRatingAndComments = combinedGoalsAndRatingDTO.getOverAllGoalsAndRatingComments();
//            System.out.println(overAllRatingAndComments+"overAllRatingAndComments");
//
//            List<AppraiseeGoalSetting> goals = combinedGoalsAndRatingDTO.getGoalsList();
////            List<AppraiseeGoalSetting> savedAppGoals = pmsService.submitAppraiseeGoalSetting(goals, empId);
//            List<AppraiseeGoalSetting> savedAppGoals = pmsService.updateAppraiseeGoalSetting(goals, empId);
//
//            Map<String, Object> responseData = new HashMap<>();
//            responseData.put("savedDevelopmentGoals", savedDevGoals);
//            responseData.put("savedAppraiseeGoals", savedAppGoals);
//
//
//            // here i am inserting submittedOnn date into employee status------------------------------------------------------------
//            LocalDate date=LocalDate.now();
//            responseData.put("submittedOnn",date);
//            String currentQuarter=getQuarter();
//            String reviewCycle=getReviewCycle();
//
//            Long currentEmpId= combinedGoalsAndRatingDTO.getDevGoalsList().get(0).getEmployeeId();
//            System.out.println(currentQuarter+" "+reviewCycle+" "+currentEmpId);
//            EmployeeStatus employeeStatus=employeeStatusRepository.findByEmpIdAndAppraisalCycleAndReviewCycle(currentEmpId,currentQuarter,reviewCycle);
//
//            // self overall comment and rating
//
//
//
//
//            if (employeeStatus.getSubmittedOnn() == null) {
//                employeeStatus.setSelfOverallComments(overAllRatingAndComments.getOverallComments());
//                employeeStatus.setSelfOverallRating(Integer.parseInt(String.valueOf(overAllRatingAndComments.getOverallRating())));
//                employeeStatus.setSubmittedOnn(date);
//            } else {
//                employeeStatus.setReviewedOnn(date);
//                employeeStatus.setManagerOverallComments(overAllRatingAndComments.getManagerOverallComments());
//                employeeStatus.setManagerOverallRating(Integer.parseInt(String.valueOf(overAllRatingAndComments.getManagerOverallRating())));
//            }
//            System.out.println(employeeStatus+"employeeStatus");
//            System.out.println(employeeStatus.getManagerOverallComments()+employeeStatus.getManagerOverallComments()+employeeStatus.getSelfOverallRating()+employeeStatus.getSelfOverallComments());
//            employeeStatusRepository.save(employeeStatus);
//
//
//            ResponseUtil<Object> response = ResponseUtil.builder()
//                    .status(HttpStatus.CREATED.value())
//                    .success(true)
//                    .message("Goals and Development successfully created")
//                    .data(responseData)
//                    .build();
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        } catch (Exception e) {
//            ResponseUtil<Object> errorResponse = ResponseUtil.builder()
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                    .success(false)
//                    .message("An error occurred while processing the request")
//                    .data(null)
//                    .build();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
//    }
//
//
//
//
//
//
//
//
//    @Transactional
//    @PostMapping("/postemployeeKraAndDevGoalsRating/{empId}")
//    public ResponseEntity<ResponseUtil<Object>> updateAppraiseeGoalSettingAndRatingComments(
//            @RequestBody PmsGoalsAndDevelopmentGoalsAndRatingComments combinedGoalsAndRatingDTO,
//            @PathVariable Long empId) throws MessagingException {
//
//        try {
//            // Extract and save development goals
//            List<AppraiseeDevelopmentGoalSetting> devGoals = combinedGoalsAndRatingDTO.getDevGoalsList().stream()
//                    .collect(Collectors.toList());
//
//            List<AppraiseeDevelopmentGoalSettingDto> savedDevGoals = devGoals.stream()
//                    .map(devGoal -> {
//                        AppraiseeDevelopmentGoalSettingCo devCo = new AppraiseeDevelopmentGoalSettingCo();
//                        mapDevGoalToCo(devGoal, devCo);
//                        return pmsService.updateDevGoals(devCo); // Save once
//                    })
//                    .collect(Collectors.toList());
//
//            // Log and extract overall rating and comments
//            OverAllRatingAndComments overAllRatingAndComments = combinedGoalsAndRatingDTO.getOverAllGoalsAndRatingComments();
//            System.out.println("Overall Comments: " + overAllRatingAndComments.getOverallComments());
//            System.out.println("Overall Rating: " + overAllRatingAndComments.getOverallRating());
//            System.out.println("Manager Comments: " + overAllRatingAndComments.getManagerOverallComments());
//            System.out.println("Manager Rating: " + overAllRatingAndComments.getManagerOverallRating());
//
//            // Extract and save appraisee goals
//            List<AppraiseeGoalSetting> goals = combinedGoalsAndRatingDTO.getGoalsList();
//            List<AppraiseeGoalSetting> savedAppGoals = pmsService.updateAppraiseeGoalSetting(goals, empId);
//
//            // Prepare response data
//            Map<String, Object> responseData = new HashMap<>();
//            responseData.put("savedDevelopmentGoals", savedDevGoals);
//            responseData.put("savedAppraiseeGoals", savedAppGoals);
//
//            // Insert the current date and review cycle into employee status
//            LocalDate date = LocalDate.now();
//            responseData.put("submittedOnn", date);
//            String currentQuarter = getQuarter();
//            String reviewCycle = getReviewCycle();
//
//            Long currentEmpId = combinedGoalsAndRatingDTO.getDevGoalsList().get(0).getEmployeeId();
//            System.out.println("Quarter: " + currentQuarter + ", Review Cycle: " + reviewCycle + ", Employee ID: " + currentEmpId);
//
//            EmployeeStatus employeeStatus = employeeStatusRepository.findByEmpIdAndAppraisalCycleAndReviewCycle(
//                    currentEmpId, currentQuarter, reviewCycle);
//
//            if (employeeStatus != null) {
//                // Update employee status fields based on submittedOnn
//                if (employeeStatus.getSubmittedOnn() == null) {
//                    employeeStatus.setSelfOverallComments(overAllRatingAndComments.getOverallComments());
//                    if (overAllRatingAndComments.getOverallRating() != null) {
//                        employeeStatus.setSelfOverallRating(Integer.parseInt(String.valueOf(overAllRatingAndComments.getOverallRating())));
//                    }
//                    employeeStatus.setSubmittedOnn(date);
//                } else {
//                    employeeStatus.setReviewedOnn(date);
//                    employeeStatus.setManagerOverallComments(overAllRatingAndComments.getManagerOverallComments());
//                    if (overAllRatingAndComments.getManagerOverallRating() != null) {
//                        employeeStatus.setManagerOverallRating(Integer.parseInt(String.valueOf(overAllRatingAndComments.getManagerOverallRating())));
//                    }
//                }
//
//                // Log the updated employee status
//                System.out.println("Updated EmployeeStatus: " + employeeStatus);
//                employeeStatusRepository.save(employeeStatus);
//            } else {
//                System.out.println("No EmployeeStatus found for the given parameters.");
//            }
//
//            // Build success response
//            ResponseUtil<Object> response = ResponseUtil.builder()
//                    .status(HttpStatus.CREATED.value())
//                    .success(true)
//                    .message("Goals and Development successfully created")
//                    .data(responseData)
//                    .build();
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        } catch (Exception e) {
//            // Log the exception
//            e.printStackTrace();
//
//            // Build error response
//            ResponseUtil<Object> errorResponse = ResponseUtil.builder()
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                    .success(false)
//                    .message("An error occurred while processing the request")
//                    .data(null)
//                    .build();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
//    }
//







    @Autowired
    private AppraiseeDevelopmentGoalSettingMapper appraiseeDevelopmentGoalSettingMapper;
    @Transactional
    @PostMapping("/postemployeeKraAndDevGoalsRating/{empId}")
    public ResponseEntity<ResponseUtil<Object>> updateAppraiseeGoalSettingAndRatingComments(
            @RequestBody PmsGoalsAndDevelopmentGoalsAndRatingComments combinedGoalsAndRatingDTO,
            @PathVariable Long empId) {

        try {

            System.out.println(combinedGoalsAndRatingDTO.getGoalsList()+" "+combinedGoalsAndRatingDTO.getDevGoalsList()+" "+combinedGoalsAndRatingDTO.getOverAllGoalsAndRatingComments());
            // Process Development Goals
            List<AppraiseeDevelopmentGoalSetting> devGoals = combinedGoalsAndRatingDTO.getDevGoalsList();
            List<AppraiseeDevelopmentGoalSettingDto>appraiseeDevelopmentGoalSettingDtoList= pmsService.updateDevGoals(devGoals);

//            List<AppraiseeDevelopmentGoalSettingDto> savedDevGoals = devGoals.stream()
//                    .map(devGoal -> {
//                        AppraiseeDevelopmentGoalSettingCo devCo = new AppraiseeDevelopmentGoalSettingCo();
//                        mapDevGoalToCo(devGoal, devCo);
//                        return pmsService.updateDevGoals(devCo);
//                    })
//                    .collect(Collectors.toList());

            // Process Overall Rating and Comments
            OverAllRatingAndComments overAllRatingAndComments = combinedGoalsAndRatingDTO.getOverAllGoalsAndRatingComments();
            System.out.println(overAllRatingAndComments+"overAllRatingAndComments");

            // Process Appraisee Goals
            List<AppraiseeGoalSetting> goals = combinedGoalsAndRatingDTO.getGoalsList();
            List<AppraiseeGoalSetting> savedAppGoals = pmsService.updateAppraiseeGoalSetting(goals, empId);

            // Prepare Response Data
            Map<String, Object> responseData = new HashMap<>();
//            responseData.put("savedDevelopmentGoals", savedDevGoals);
            responseData.put("savedDevelopmentGoals", appraiseeDevelopmentGoalSettingDtoList);

            responseData.put("savedAppraiseeGoals", savedAppGoals);

            // Set Submitted Date
            LocalDate date = LocalDate.now();
            responseData.put("submittedOnn", date);

            String currentQuarter = getQuarter();

            String reviewCycle = getReviewCycle();
            if(currentQuarter.equals("4")){
               reviewCycle=String.valueOf(Integer.parseInt(reviewCycle.split("-")[0])-1)+"-"+reviewCycle.split("-")[0];
            }
            Long currentEmpId = devGoals.get(0).getEmployeeId();

            EmployeeStatus employeeStatus = employeeStatusRepository
                    .findByEmpIdAndAppraisalCycleAndReviewCycle(currentEmpId, currentQuarter, reviewCycle);
            System.out.print(currentQuarter+"currentQuarter"+reviewCycle+"reviewCycle");

            System.out.println("Overall Comments: " + overAllRatingAndComments.getOverallComments());
            System.out.println("Overall Rating: " + overAllRatingAndComments.getOverallRating());
            System.out.println("Manager Comments: " + overAllRatingAndComments.getManagerOverallComments());
            System.out.println("Manager Rating: " + overAllRatingAndComments.getManagerOverallRating());


            // Update Self Overall Comment and Rating

            // employee

            // submitted Onn  & Reviewed Onn
            if (employeeStatus.getSubmittedOnn() == null) {
                employeeStatus.setSelfOverallComments(overAllRatingAndComments.getOverallComments());
                employeeStatus.setSelfOverallRating((int) Double.parseDouble(overAllRatingAndComments.getOverallRating()));
                employeeStatus.setSubmittedOnn(date);

            }
            // manager
            else {
                // Update Manager Overall Comment and Rating
                employeeStatus.setReviewedOnn(date);
                employeeStatus.setManagerOverallComments(overAllRatingAndComments.getManagerOverallComments());
                employeeStatus.setManagerOverallRating((int) Double.parseDouble(overAllRatingAndComments.getManagerOverallRating()));
            }




            System.out.println(employeeStatus.getManagerOverallComments()+" "+employeeStatus.getManagerOverallRating()+" "+employeeStatus.getSelfOverallComments()+" "+employeeStatus.getSelfOverallRating());

            employeeStatusRepository.save(employeeStatus);

            // Return Success Response
            ResponseUtil<Object> response = ResponseUtil.builder()
                    .status(HttpStatus.CREATED.value())
                    .success(true)
                    .message("Goals and Development successfully created")
                    .data(responseData)
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            // Return Error Response
            ResponseUtil<Object> errorResponse = ResponseUtil.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .success(false)
                    .message("An error occurred while processing the request")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @GetMapping("/getUniqueEmployeeStatus/{empId}")
    public ResponseEntity<?> getUniqueEmployeeStatus(@PathVariable Long empId) {

        String reviewCycle = getReviewCycle();
        if(getQuarter().equals("4")){
            reviewCycle=String.valueOf(Integer.parseInt(reviewCycle.split("-")[0])-1)+"-"+reviewCycle.split("-")[0];
        }
        EmployeeStatus employeeStatus=pmsService.findParticularRow(empId,getQuarter(),reviewCycle);
        System.out.println(empId+" "+getQuarter()+" "+reviewCycle);
        if(employeeStatus!=null){
            return ResponseEntity.ok(employeeStatus);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    // here we are adding empId in employee overall status table
    @GetMapping("/addEmpIdEmployeeStatus/{empId}/{year}/{reviewCycle}")
    public ResponseEntity<?> addEmpIdEmployeeStatus(@PathVariable Long empId,@PathVariable String year,@PathVariable String reviewCycle) {
         boolean result=pmsService.addEmpIdEmployeeStatus(empId,year,reviewCycle);
        if(result){
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.ok(false);
        }
    }





    @PostMapping("/getAllEmployeeDetailsAndStatus")
    public ResponseEntity<ResponseUtil<List<TotalEmployeeStatusDto>>> getAllEmployeeDetailsAndStatus(@RequestBody List<TotalEmployeeStatusCo> totalEmployeeStatusCoList) {
        String reviewCycle = getReviewCycle();
        if(getQuarter().equals("4")){
            reviewCycle=String.valueOf(Integer.parseInt(reviewCycle.split("-")[0])-1)+"-"+reviewCycle.split("-")[0];
        }

        System.out.println("hi iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
       // System.out.println(totalEmployeeStatusCoList.get(0).toString());
        List<TotalEmployeeStatusDto> getAllEmployeeDetailsAndStatus=pmsService.getAllEmployeeDetailsAndStatus(totalEmployeeStatusCoList,getQuarter(),reviewCycle);
        ResponseUtil<List<TotalEmployeeStatusDto>> response = ResponseUtil.<List<TotalEmployeeStatusDto>>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("succesfully fetched")
                .data(getAllEmployeeDetailsAndStatus)
                .build();
        return ResponseEntity.ok(response);
    }



    @GetMapping("/setApprovedOn/{empId}")
    public ResponseEntity<ResponseUtil<Boolean>> setApprovedOn(@PathVariable Long empId) {
        String reviewCycle = getReviewCycle();
        if(getQuarter().equals("4")){
            reviewCycle=String.valueOf(Integer.parseInt(reviewCycle.split("-")[0])-1)+"-"+reviewCycle.split("-")[0];
        }
        Boolean result=pmsService.setApprovedOn(empId,getQuarter(),reviewCycle);
        ResponseUtil<Boolean> response = ResponseUtil.<Boolean>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("Successfully Set ApprovedOn")
                .data(result)
                .build();
        return ResponseEntity.ok(response);
    }













//	 Amulya's APIs:
// 	 ---------------------------------------------------------------------------------------------------------------------



    @PostMapping("/postemployeePerformance")
    public EmployeeStatus postemployeePerformance(@RequestBody EmployeeStatus employeeStatus) {
        EmployeeStatus status = pmsService.postemployeePerformance(employeeStatus);
        return status;
    }

//    @PostMapping("/postmanagerApproval/{employeeStatusId}")
//    public EmployeeStatus postmanagerApproval(@PathVariable Long employeeStatusId, @RequestBody EmployeeStatus employeeStatus) throws MessagingException {
//        EmployeeStatus status = pmsService.postmanagerApproval(employeeStatus);
//        return status;
//    }


    @PostMapping("/postmanagerApproval/{empId}/{employeeStatusId}")
    public EmployeeStatus postmanagerApproval(@PathVariable Long empId,@PathVariable Long employeeStatusId) throws MessagingException {
        EmployeeStatus status = pmsService.postmanagerApproval(empId,employeeStatusId);
        return status;
    }

    @GetMapping("/getDevelopmentGoalsBySubId/{employeeId}/{subOverallStatusId}")
    public ResponseEntity<ResponseUtil<List<AppraiseeDevelopmentGoalSettingDto>>> getDevelopmentGoalsBysubOverallStatusId(@PathVariable Long employeeId, @PathVariable Long subOverallStatusId) {
        List<AppraiseeDevelopmentGoalSettingDto> developmentOption = pmsService.getDevelopmentGoalsBySubOverallStatusId(employeeId, subOverallStatusId);
        ResponseUtil<List<AppraiseeDevelopmentGoalSettingDto>> response = ResponseUtil.<List<AppraiseeDevelopmentGoalSettingDto>>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("succesfully fetched")
                .data(developmentOption)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getemployeeKraOrDraft/{empId}/{subOverallStatusId}")
    public ResponseEntity<ResponseUtil<List<AppraiseeGoalSettingDto>>> findKraId(@PathVariable Long empId, @PathVariable Long subOverallStatusId) {
        List<AppraiseeGoalSettingDto> goals = pmsService.getEmployeeKraByEmpIdAndsubOverallStatusId(empId, subOverallStatusId);
        ResponseUtil<List<AppraiseeGoalSettingDto>> response = ResponseUtil.<List<AppraiseeGoalSettingDto>>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("succesfully fetched")
                .data(goals)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getmess")
    public String getmes() {
        return pmsService.getmes();
    }

    @GetMapping("/getemployeePerformance/{empId}")
    public ResponseEntity<ResponseUtil<List<EmployeeStatusDto>>> getEmployeeStatusByEmpId(@PathVariable Long empId) {
        List<EmployeeStatusDto> employeeStatusList = pmsService.getEmployeePerformanceByEmpId(empId);
        ResponseUtil<List<EmployeeStatusDto>> response = ResponseUtil.<List<EmployeeStatusDto>>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("Successfully fetched")
                .data(employeeStatusList)
                .build();
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/getPendingAppraisalEmpIdAndReviewCycle/{empId}/{reviewCycle}")
//    public ResponseEntity<ResponseUtil<List<PendingAppraisalDto>>> getPendingAppraisal(@PathVariable Long empId, @PathVariable String reviewCycle) {
//        System.out.println(empId + "empId");
//        List<PendingAppraisalDto> pending = pmsService.getPendingAppraisalEmpIdAndReviewCycle(empId, reviewCycle);
//        ResponseUtil<List<PendingAppraisalDto>> response = ResponseUtil.<List<PendingAppraisalDto>>builder()
//                .status(HttpStatus.OK.value())
//                .success(true)
//                .message("Successfully fetched")
//                .data(pending)
//                .build();
//        return ResponseEntity.ok(response);
//    }


//    @GetMapping("/getPendingAppraisalByReviewCycle/{reviewCycle}")
//    public ResponseEntity<ResponseUtil<List<PendingAppraisalDto>>> getPendingAppraisal(@PathVariable String reviewCycle) {
//        List<PendingAppraisalDto> pending = pmsService.getPendingAppraisalByReviewCycle(reviewCycle);
//        ResponseUtil<List<PendingAppraisalDto>> response = ResponseUtil.<List<PendingAppraisalDto>>builder()
//                .status(HttpStatus.OK.value())
//                .success(true)
//                .message("Successfully fetched")
//                .data(pending)
//                .build();
//        return ResponseEntity.ok(response);
//    }
    @GetMapping("/getTrainingOption")
    public ResponseEntity<ResponseUtil<List<TrainingOptionDto>>> getAllTrainings() {
        List<TrainingOptionDto> trainingOptions = pmsService.getAllTrainingDropdown();
        ResponseUtil<List<TrainingOptionDto>> response = ResponseUtil.<List<TrainingOptionDto>>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("Successfully fetched")
                .data(trainingOptions)
                .build();
        return ResponseEntity.ok(response);
    }


    @PostMapping("/postemployeeKra/{empId}")
    public ResponseEntity<ResponseUtil<Object>> submitAppraiseeGoalSetting(
            @RequestBody PmsGoalsAndDevelopmentGoals combinedGoalsDTO,
            @PathVariable Long empId) throws MessagingException {

        try {
            List<AppraiseeDevelopmentGoalSetting> devGoals = combinedGoalsDTO.getDevGoalsList().stream()
                    .collect(Collectors.toList());

            System.out.println(devGoals+"devGoals>>>>>>");

//            List<AppraiseeDevelopmentGoalSettingDto> savedDevGoals = devGoals.stream()
//                    .map(devGoal -> {
//                        AppraiseeDevelopmentGoalSettingCo devCo = new AppraiseeDevelopmentGoalSettingCo();
//                        mapDevGoalToCo(devGoal, devCo); // Map once
//                        return pmsService.insertdev(devCo); // Save once
//                    })
//                    .collect(Collectors.toList());


            List<AppraiseeDevelopmentGoalSettingDto> savedDevGoals =
                    pmsService.insertDevSubmitAppraiseeDevelopmentGoalSetting(devGoals);


            List<AppraiseeGoalSetting> goals = combinedGoalsDTO.getGoalsList();
            List<AppraiseeGoalSetting> savedAppGoals = pmsService.submitAppraiseeGoalSetting(goals, empId);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("savedDevelopmentGoals", savedDevGoals);
            responseData.put("savedAppraiseeGoals", savedAppGoals);

            ResponseUtil<Object> response = ResponseUtil.builder()
                    .status(HttpStatus.CREATED.value())
                    .success(true)
                    .message("Goals and Development successfully created")
                    .data(responseData)
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ResponseUtil<Object> errorResponse = ResponseUtil.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .success(false)
                    .message("An error occurred while processing the request")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/postemployeeKraDrafts/{empId}")
    public ResponseEntity<ResponseUtil<Map<String, Object>>> saveGoalsAndDrafts(
            @RequestBody PmsDraftGoalsAndDraftDevelopmentGoals combinedDraftsDTO,
            @PathVariable Long empId) {
        try {
            List<AppraiseeDevelopmentGoalSettingDraft> devGoalsDraft = combinedDraftsDTO.getDevGoalsDraftList().stream()
                    .collect(Collectors.toList());
            List<AppraiseeDevelopmentGoalSettingDraftDto> savedDevGoalsDraft =
                    pmsService.submitAppraiseeDevelopmentGoalSettingDraft(devGoalsDraft);

            // Handle Goal Drafts
            List<AppraiseeGoalSettingDraft> goalDrafts = combinedDraftsDTO.getGoalsDraftList();
            List<AppraiseeGoalSettingDraft> savedGoalDrafts = pmsService.submitAppraiseeGoalSettingDraft(goalDrafts);


            // Build Response
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("savedDevelopmentGoals", savedDevGoalsDraft);
            responseData.put("savedAppraiseeGoals", savedGoalDrafts);

            ResponseUtil<Map<String, Object>> response = ResponseUtil
                    .<Map<String, Object>>builder()
                    .status(HttpStatus.CREATED.value())
                    .success(true)
                    .message("Goals, Development Goals, and Drafts successfully created")
                    .data(responseData)
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            ResponseUtil<Map<String, Object>> errorResponse = ResponseUtil
                    .<Map<String, Object>>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .success(false)
                    .message("An error occurred while processing the request")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/postmanagerrevert/{employeeStatusId}")
    public ResponseEntity<ResponseUtil<EmployeeStatusDto>> postReverted(
            @PathVariable Long employeeStatusId, @RequestBody EmployeeStatusCo employeeStatus_co) throws MessagingException {
        employeeStatus_co.setEmpId(employeeStatusId);
        EmployeeStatusDto employeeStatusDTO = pmsService.postreverted(employeeStatus_co);

        ResponseUtil<EmployeeStatusDto> response = ResponseUtil
                .<EmployeeStatusDto>builder()
                .status(HttpStatus.CREATED.value())
                .success(true)
                .message("Reverted status updated successfully")
                .data(employeeStatusDTO)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //delete all draft data
    @DeleteMapping("/deletedevelopmentdraftbyempid/{empId}")
    public void deletedevelopmentdraftbyempid(@PathVariable Long empId) {
        pmsService.deletedevelopmentdraftbyempid();
    }

    //delete individual
    @DeleteMapping("/deleteDevelopmentGoal/{dgDraftId}")
    public ResponseEntity<String> deleteDevelopmentGoal(@PathVariable Long dgDraftId) {
        boolean deleted = pmsService.deleteDevelopmentGoal(dgDraftId);
        if (deleted) {
            return ResponseEntity.ok("Development goal deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Development goal not found");
        }
    }

    //delete individual
    @DeleteMapping("/deleteEmployeeKraOrDraft/{goalDraftId}")
    public ResponseEntity<String> deleteEmployeeKraOrDraft(@PathVariable Long goalDraftId) {
        try {
            pmsService.delete(goalDraftId); // Assuming delete method handles deletion by goalDraftId

            return ResponseEntity.ok("Data deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting data: " + e.getMessage());
        }
    }


//    @GetMapping("/getByYearAndReviewCycle/{empId}/{year}/{reviewCycle}")
//    public ResponseEntity<?> getDataByYearAndReviewCycle(@PathVariable("year") String year, @PathVariable("reviewCycle") String reviewCycle, @PathVariable("empId") Long empId) {
//        List<EmployeeStatusDto> allEmpStatus = pmsService.getDataByYearAndReviewCycleAndEmpId(year, reviewCycle, empId);
//        if (!allEmpStatus.isEmpty()) {
//            return ResponseEntity.ok(allEmpStatus);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found based on year and review cycle");
//        }
//    }

    @GetMapping("/pendingAppraisalRequest/{currentStatus}/{manager}")
    public ResponseEntity<ResponseUtil<List<EmployeeStatusDto>>> getByCurrentStatusAndManager(@PathVariable String currentStatus, @PathVariable String manager) {
        List<EmployeeStatusDto> pendingRequest = pmsService.getByCurrentStatusAndManager(currentStatus, manager);
        ResponseUtil<List<EmployeeStatusDto>> response = ResponseUtil.<List<EmployeeStatusDto>>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("Successfully fetched")
                .data(pendingRequest)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getPendingAppraisal/{reviewCycle}")
    public ResponseEntity<?> getpendingAppraisal(@PathVariable String reviewCycle) {
        System.out.println(reviewCycle+"reviewCycle");
        List<PendingAppraisal> pendingAppraisalList = pmsService.getpendingAppraisal(reviewCycle);
        if (pendingAppraisalList != null) {
            return ResponseEntity.ok(pendingAppraisalList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("review cycle is not found");
        }
    }

    @GetMapping("/getTrainingOptions/{trainingOptionId}")
    public ResponseEntity<?> getTrainingByTrainingId(@PathVariable Long trainingOptionId) {
        List<TrainingOptionDto> trainingOptions = pmsService.getTrainingByTrainingId(trainingOptionId);
        if (trainingOptions != null) {
            return ResponseEntity.ok(trainingOptions);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }

    @GetMapping("/getTrainingOptionsByName/{trainingName}")
    public ResponseEntity<?> getTrainingByTrainingName(@PathVariable String trainingName) {
        List<String> trainingOptions = pmsService.getTrainingByTrainingName(trainingName);
        if (trainingOptions != null) {
            return ResponseEntity.ok(trainingOptions);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }

    //not using
    @GetMapping("getByTargetOperatorId/{targetOperatorId}")
    public ResponseEntity<List<TargetOperator>> getTargetOperatorsById(@PathVariable Long targetOperatorId) {
        List<TargetOperator> targetOperators = pmsService.getTargetOperatorBytargetOperatorId(targetOperatorId);
        return targetOperators.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(targetOperators);
    }

    @GetMapping("/getAllOperators")
    public List<TargetOperator> getTargetOperators() {
        return pmsService.getAllTargretOperator();
    }


    private void mapDevGoalToCo(AppraiseeDevelopmentGoalSetting devGoal, AppraiseeDevelopmentGoalSettingCo devCo) {
        devCo.setGoal(devGoal.getGoal());
        devCo.setDescription(devGoal.getDescription());
        devCo.setTrainingOptionId(devGoal.getTrainingOptionId());
        devCo.setEmployeeId(devGoal.getEmployeeId());
        devCo.setSelfAssessment(devGoal.getSelfAssessment());
        devCo.setSubOverallStatusId(devGoal.getSubOverallStatusId());
        devCo.setManagerAssessment(devGoal.getManagerAssessment());
        devCo.setDevelopmentSubId(devGoal.getDevelopmentSubId());
        devCo.setTraining(devGoal.getTraining());

    }

//    private void mapDevGoalDraftToCo(AppraiseeDevelopmentGoalSettingDraft devDraftGoal, AppraiseeDevelopmentGoalSettingDraftCo devDraft) {
//        devDraft.setGoal(devDraftGoal.getGoal());
//        devDraft.setDescription(devDraftGoal.getDescription());
//        devDraft.setTrainingOptionId(devDraftGoal.getTrainingOptionId());
//        devDraft.setEmployeeId(devDraftGoal.getEmployeeId());
//        devDraft.setSelfAssessment(devDraftGoal.getSelfAssessment());
//        devDraft.setSubOverallStatusId(devDraftGoal.getSubOverallStatusId());
//        devDraft.setManagerAssessment(devDraftGoal.getManagerAssessment());
//        devDraft.setDevelopmentSubId(devDraftGoal.getDevelopmentSubId());
//        devDraft.setTraining(devDraftGoal.getTraining());
//
//    }

    @GetMapping("/getdefaultgoals")
    public ResponseEntity<Map<String, Object>> getDefaultGoals(
            @RequestParam String division,
            @RequestParam String role,
            @RequestParam Long emp_id,
            @RequestParam Long overall_statusId) {
        try {
            // Prepare the data as a map to pass to the service
            Map<String, Object> requestData = Map.of(
                    "division", division,
                    "role", role,
                    "emp_id", emp_id,
                    "overall_statusId", overall_statusId
            );
            // Call the service to process goals and get the response
            Map<String, Object> response = pmsService.processGoals(requestData);
            // Return the processed response from the service
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // If there's an error, return 500 status with the error message
            return ResponseEntity.status(500).body(Map.of("error", "An error occurred: " + e.getMessage()));
        }
    }

    //	---------------------NEW LOGIC FOR APPRAISAL CYCLE TABLE AND EMPLOYEE STATUS TABLE-----------------


    @PostMapping("/postAppraisalCycle")
    public ResponseEntity<ResponseUtil<List<PendingAppraisalDto>>> cratePendingAppraisal(
            @RequestBody List<PendingAppraisalCo> pendingAppraisalCoList) throws ExecutionException, InterruptedException {
        List<PendingAppraisalDto>pendingAppraisalDtoList= pmsService.cratePendingAppraisal(pendingAppraisalCoList);
        ResponseUtil< List<PendingAppraisalDto>> response = ResponseUtil
                .<List<PendingAppraisalDto>>builder()
                .status(HttpStatus.CREATED.value())
                .success(true)
                .message("Reverted status updated successfully")
                .data(pendingAppraisalDtoList)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getAllReviewCycle")
    public ResponseEntity<?> getAllReviewCycle() {
        List<String> reviewCycle = pmsService.getAllReviewCycle();
        if (!reviewCycle.isEmpty()) {
            return ResponseEntity.ok(reviewCycle);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }

    @Transactional
    @GetMapping("/getByReviewCycle/{empId}/{reviewCycle}")
    public ResponseEntity<?> getDataReviewCycle(
            @PathVariable("reviewCycle") String reviewCycle,
            @PathVariable("empId") Long empId) throws ExecutionException, InterruptedException {
        List<EmployeeStatusDto>employeeStatusDtoList=pmsService.getDataByReviewCycleAndEmpId(reviewCycle, empId);
        System.out.println(reviewCycle+" reviewCycle"+empId+"empId");
        if(!employeeStatusDtoList.isEmpty()){
            return ResponseEntity.ok(employeeStatusDtoList);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getPendingAppraisalByReviewCycle/{reviewCycle}")
    public ResponseEntity<ResponseUtil<List<PendingAppraisalDto>>> getPendingAppraisal(@PathVariable String reviewCycle) {
        List<PendingAppraisalDto> pending = pmsService.getPendingAppraisalByReviewCycle(reviewCycle);
        ResponseUtil<List<PendingAppraisalDto>> response = ResponseUtil.<List<PendingAppraisalDto>>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("Successfully fetched")
                .data(pending)
                .build();
        return ResponseEntity.ok(response);
    }



    @GetMapping("/preventFromNavigationToKra/{empId}/{sId}")
    public boolean preventFromNavigationToKra(@PathVariable Long empId, @PathVariable Long sId) {
        System.out.println(empId+" "+sId);
       boolean result= pmsService.preventFromNavigationToKra(empId,sId);
       return result ? true :false;
    }
}




