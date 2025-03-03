/* eslint-disable react/jsx-pascal-case */
import Login from "./Component/Login";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Profile from "./Pages/Profile";
import Employees from "./Pages/Employee";
import DocumentForm from "./Pages/DocumentForm";
import Calander from "./Component/Calander";
import AllAnnouncements from "./Anouncement/AllAnnouncements";
import AnnouncementDetails from "./Anouncement/AnnouncementDetails";
import LayoutDashboard from "./Component/LayoutDashboard";
import PayrollTable from "./Component/PayrollTable";
import Payslip from "./Component/Payslip";
import {  ExitForm } from "./WF_Forms/ExitForm";
import CreateAnnouncements from "./Anouncement/CreateAnnouncements";
import AllHolidays from "./Anouncement/AllHolidays";
import HolidayList from "./Anouncement/HolidayList";
import { ClaimForm } from "./WF_Forms/ClaimForm";
import { LeaveForm } from "./WF_Forms/LeaveForm";
import TabsRender from "./WF_Forms/ExitWorkFlow";
import ExitFlowLogin from "./WF_Forms/ExitFlowLogin";
import LeaveDashboard from "./Leaves/LeaveDashboard";
import ApplyLeave from "./Leaves/ApplyLeave";
import NewNav from "./NewSideNav/NewNav";
import  PrivateRoutes  from "./Component/PrivateRoutes";
import { JsonForm } from "./WF_Forms/JsonForm";
import FormFields from "./WF_Forms/FormFields";
import DashBoard from "./Pages/DashBoard";
import EmployeeWorkAnnirversary from "./Pages/EmployeeWorkAnnirversary";
import TabsRenderMedClaim from "./WF_Forms/MediClaim";
import Folder from "./documentViewer/Folder";
import Pdf from "./documentViewer/Pdf";
import PdfAll from "./documentViewer/PdfAll";
import Folderinfo from "./documentViewer/Folderinfo";
import QuestionBankAdd from "./playQuiz/pages/quiz/QuestionBankAdd";
import Register from "./playQuiz/Component/Register";
import UserQuizManagement from "./playQuiz/Component/UserQuizManagement";
import QuizPlay from "./playQuiz/pages/quiz/QuizPlay";
import QuizResult from "./playQuiz/pages/quiz/QuizResult";
import QuestionBank from "./playQuiz/pages/quiz/QuestionBank";
import QuizGenerator from "./playQuiz/pages/quiz/QuizGenerator";
import QuizManagement from "./playQuiz/pages/quiz/QuizManagement";
import ShowUserResponse from "./playQuiz/ShowUserResponse";
import IT_Declaration from "./itDecleration/IT_Declaration";
import IT_Declaration_Display from "./itDecleration/IT_Declaration_Display";
import Select_Regime from "./itDecleration/Select_Regime";
import DeclarationSummary from "./itDecleration/DeclarationSummary";
import IT_Declaration_Update from "./itDecleration/IT_Declaration_Update";
import IT_Declaration_Preview from "./itDecleration/IT_Declaration_Preview";
import Proof_of_Investment_Display from "./itDecleration/Proof_of_Investment_Display";
import Proof_Attach from "./itDecleration/Proof_Attach";
import Proof_Of_Investment_Update from "./itDecleration/Proof_Of_Investment_Update";
import OtpLogin from "./otp/OtpLogin";
import AnnualReviewByEmp from "./e-pms/annualReview/AnnualReviewByEmp";
import AnnualReviewByMgr from "./e-pms/annualReview/AnnualReviewByMgr";
import ManagerScreen from "./e-pms/manager_screen/ManagerScreen";
import ManagerScreen2 from "./e-pms/manager_screen/ManagerScreen2";
import RouteToMgrView from "./e-pms/manager_screen/RouteToMgrView";
import MangerReview from "./e-pms/managerReview/ManagerReview";
import SelfAppraisel from "./e-pms/selfAppraisel/SelfAppraisel";
import ProfileInfonfoFile from "./e-pms/profile-info/ProfileInfoFile";
import Open from "./e-pms/GoalSetting/Open";

import PendingAppraisal from "./e-pms/manager_screen/PendingAppraisal";
import { GetToken } from "./loginConfig/components/GetToken";
import TokenGenerator from "./loginConfig/components/TokenGenerator";
import EditAnnouncements from "./Anouncement/EditAnnouncements";
import DocumentViewer from "./documentViewer/DocumentViewer";
import ManagerAdvApproval from "./claims/components/ManagerAdvApproval";
import MgrPendingReqDashboard from "./claims/components/MgrPendingReqDashboard";
import MgrPendingReqHomePage from "./claims/components/MgrPendingReqHomePage";
import DocViewer from "./claims/components/DocViewer";
import ClaimsDashBoard from "./claims/components/ClaimsDashBoard";
import ClaimsHome from "./claims/components/ClaimsHome";
import Signin from "./newHelp_Desk/Signin";
import Helpdesk from "./newHelp_Desk/Helpdesk";
import EmployeeRequisition from "./erf/components/EmployeeRequisition";
import  AdminDashboard  from "./dashboards/AdminDashboard";
import CreateEvent from "./events/CreateEvents";
import  EditEvents  from "./events/EditEvents";
import CreateCelebratedEvent from "./events/CelebratedEvents";
import HRDashboard from "./dashboards/HRDashboard";
import Unauthorized from "./Component/Unauthorized";
import HRNavHead from "./navbar/HRNavHead";
import AdminNavHead from "./navbar/AdminNavHead";
import MediclaimEnrollement from "./mediclaim/MediclaimEnrollement";
import Plan from "./mediclaim/Plan";
import ManagerApproval from "./claims/components/ManagerApproval";
import SelfExitForm from "./eSeperation/components/E-separation/SelfExitForm";
import Exit from "./eSeperation/components/E-separation/Exit";
import ManagerDashBoardPendingExitForm from "./eSeperation/components/E-separation/ManagerDashBoardPendingExitForm";
import ExitDashBoard from "./eSeperation/components/E-separation/ExitDashBoard";
import MainExit from "./eSeperation/components/E-separation/MainExit";
import PendingRequestForDepartmentMembers from "./eSeperation/components/E-separation/PendingRequestForDepartmentMembers";
import ManagerView from "./e-pms/GoalSetting/ManagerView";
import ExitStatusDisplay from "./WF_Forms/ExitStatusDisplay";
import ITAdmin from "./itDecleration/ITAdmin";
import WorkflowStatusTracker from "./eSeperation/components/WorkflowStatusTracker";
import EmployeeDetail from "./Pages/EmployeeDetail";





function App() {

  return (
    <>
   <div className="flex flex-col min-h-screen">
 <BrowserRouter>
  <Routes>
    <Route path="/" element={<Login />} />
    <Route path="/unauthorized" element={<Unauthorized/>}/>
    <Route path="/admin/otp" element={<OtpLogin />} />
    <Route path="/Dashboard" element={<PrivateRoutes Component={DashBoard} requiredRoles={['admin', 'CMS Employee']} />} />
    <Route path="/AdminDashboard" element={<PrivateRoutes Component={AdminDashboard} requiredRoles={['admin']} />} />
    <Route path="/AdminNavHead" element={<PrivateRoutes Component={AdminNavHead} requiredRoles={['admin']} />} />
    <Route path="/HRDashboard" element={<PrivateRoutes Component={HRDashboard} requiredRoles={['CMS Employee','HR ROLE']} />} />
    <Route path="/HRNavHead" element={<PrivateRoutes Component={HRNavHead} requiredRoles={['CMS Employee','HR ROLE']} />} />
    <Route path="/Employee" element={<PrivateRoutes Component={Employees} requiredRoles={['admin', 'CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/employee/:email" element={<EmployeeDetail />} />
    <Route path="/get-token" element={<GetToken />} />
    <Route path="/token-generator" element={<TokenGenerator />} />
    <Route path="/workanniversary" element={<PrivateRoutes Component={EmployeeWorkAnnirversary} requiredRoles={['CMS Employee','HR ROLE']} />} />
    <Route path="/Profile" element={<PrivateRoutes Component={Profile} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/calendar" element={<PrivateRoutes Component={Calander} requiredRoles={['CMS Employee']} />} />
    <Route path="/documentForm" element={<PrivateRoutes Component={DocumentForm} requiredRoles={['CMS Employee','HR ROLE', 'admin']} />} />
    <Route path="/NewNav" element={<PrivateRoutes Component={NewNav} requiredRoles={['CMS Employee']} />} />
    <Route path="/allAnnouncements" element={<PrivateRoutes Component={AllAnnouncements} requiredRoles={['admin', 'CMS Employee','HR ROLE']} />} />
    <Route path="/announcementDetails/:id" element={<PrivateRoutes Component={AnnouncementDetails} requiredRoles={['admin', 'CMS Employee','HR ROLE']} />} />
    <Route path="/LayoutDashboard" element={<PrivateRoutes Component={LayoutDashboard} requiredRoles={['admin']} />} />
    <Route path="/paysliptable" element={<PrivateRoutes Component={PayrollTable} requiredRoles={['CMS Employee','HR ROLE']} />} />
    <Route path="/payslip" element={<PrivateRoutes Component={Payslip} requiredRoles={['CMS Employee','CMS Manager','HR ROLE']} />} />
    {/* <Route path="/ExitForm" element={<PrivateRoutes Component={ExitForm} requiredRoles={['CMS Employee','HR ROLE']} />} /> */}
    <Route path="/ClaimForm" element={<PrivateRoutes Component={ClaimForm} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/LeaveForm" element={<PrivateRoutes Component={LeaveForm} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/holidaylist" element={<PrivateRoutes Component={HolidayList} requiredRoles={['CMS Employee','HR ROLE']} />} />
    <Route path="/create-announcements" element={<PrivateRoutes Component={CreateAnnouncements} requiredRoles={['admin','CMS Employee','HR ROLE']} />} />
    <Route path="/create-event" element={<PrivateRoutes Component={CreateEvent} requiredRoles={['admin', 'CMS Employee','HR ROLE']} />} />
    <Route path="/create-celebratedEvents" element={<PrivateRoutes Component={CreateCelebratedEvent} requiredRoles={['admin', 'CMS Employee','HR ROLE']} />} />
    <Route path="/edit-event" element={<PrivateRoutes Component={EditEvents} requiredRoles={['admin', 'CMS Employee','HR ROLE']} />} />
    <Route path="/edit-announcements" element={<PrivateRoutes Component={EditAnnouncements} requiredRoles={['admin','CMS Employee','HR ROLE']} />} />
    <Route path="/allAnnouncements" element={<PrivateRoutes Component={AllAnnouncements} requiredRoles={['admin']} />} />
    <Route path="/announcementDetails/:id" element={<PrivateRoutes Component={AnnouncementDetails} requiredRoles={['admin','CMS Employee','HR ROLE']} />} />
    <Route path="/allHolidays" element={<PrivateRoutes Component={AllHolidays} requiredRoles={['CMS Employee','HR ROLE']} />} />
    <Route path="/exitlogin" element={<PrivateRoutes Component={ExitFlowLogin} requiredRoles={['CMS Employee']} />} />
    <Route path="/tabsrender" element={<PrivateRoutes Component={TabsRender} requiredRoles={['admin']} />} />
    <Route path="/tabsrenderMediclaim" element={<PrivateRoutes Component={TabsRenderMedClaim} requiredRoles={['admin']} />} />
    <Route path="/Leaves" element={<PrivateRoutes Component={LeaveDashboard} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/ApplyLeave" element={<PrivateRoutes Component={ApplyLeave} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/jsonform" element={<PrivateRoutes Component={JsonForm} requiredRoles={['admin']} />} />
    <Route path="/formfield" element={<PrivateRoutes Component={FormFields} requiredRoles={['admin']} />} />
    <Route path="/document" element={<PrivateRoutes Component={Pdf} requiredRoles={['admin','CMS Employee','CMS Manager','HR ROLE']} />} />
    <Route path="/pdfAll" element={<PrivateRoutes Component={PdfAll} requiredRoles={['admin','CMS Employee','CMS Manager','HR ROLE']} />} />
    <Route path="/folder" element={<PrivateRoutes Component={Folder} requiredRoles={['admin','CMS Employee','CMS Manager','HR ROLE']} />} />
    <Route path="/folderInfo" element={<PrivateRoutes Component={Folderinfo} requiredRoles={['admin','CMS Employee','HR ROLE']} />} />
    <Route path='/doc-viewer/:fileName/:docName/:view?' element={<PrivateRoutes Component={DocumentViewer} requiredRoles={['admin', 'CMS Employee','HR ROLE']} />} />
    <Route path="/doc-viewerClaim/:docId" element={<DocViewer />} />
    {/* <Route path="/admin" element={<PrivateRoutes Component={QuestionBankAdd} requiredRoles={['admin','CMS Employee','CMS Manager','HR ROLE']} />} /> */}
    <Route path="/register" element={<PrivateRoutes Component={Register} requiredRoles={['admin']} />} />
    <Route path="/user" element={<PrivateRoutes Component={UserQuizManagement} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/quiz" element={<PrivateRoutes Component={QuizPlay} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/quiz/result" element={<PrivateRoutes Component={QuizResult} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/admin/question-bank" element={<PrivateRoutes Component={QuestionBank} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/admin/generate-quiz" element={<PrivateRoutes Component={QuizGenerator} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/userQuiz" element={<PrivateRoutes Component={QuizManagement} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/admin/manage-quiz" element={<PrivateRoutes Component={QuizManagement} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/admin/validate-answer" element={<PrivateRoutes Component={ShowUserResponse} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
   
    <Route path="/annualReviewByEmp" element={<PrivateRoutes Component={AnnualReviewByEmp} requiredRoles={['CMS Employee']} />} />
    <Route path="/AnnualReviewByMgr" element={<PrivateRoutes Component={AnnualReviewByMgr} requiredRoles={['manager']} />} />
    <Route path="/claimsHome" element={<PrivateRoutes Component={ClaimsHome} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/doc-viewer/:docId" element={<PrivateRoutes Component={DocViewer} requiredRoles={['CMS Employee']} />} />
    <Route path="/erf" element={<PrivateRoutes Component={EmployeeRequisition} requiredRoles={['CMS Employee','HR ROLE']} />} />
    <Route path="/claimdashboard" element={<PrivateRoutes Component={ClaimsDashBoard} requiredRoles={['admin','CMS Employee','HR ROLE']} />} />
    <Route path="/mediclaim" element={<PrivateRoutes Component={MediclaimEnrollement} requiredRoles={['admin','CMS Employee','HR ROLE']} />} />
    <Route path="/plan" element={<PrivateRoutes Component={Plan} requiredRoles={['admin','CMS Employee','HR ROLE']} />} />
    <Route path="/signin" element={<PrivateRoutes Component={Helpdesk} requiredRoles={['admin','CMS Employee','HR ROLE']} />} />
    <Route path="/manager_approval" element={<PrivateRoutes Component={ManagerApproval} requiredRoles={['CMS Employee','HR ROLE','MANAGER ROLE']} />} />
    <Route path="/approvals/:requestType" element={<PrivateRoutes Component={MgrPendingReqDashboard} requiredRoles={['CMS Employee','HR ROLE','MANAGER ROLE']}/>} />
    <Route path="/MgrPendingHome" element={<PrivateRoutes Component={MgrPendingReqHomePage} requiredRoles={['CMS Employee','HR ROLE','MANAGER ROLE']} />} />
    {/* <Route path="/ExitForm" element={<ExitForm/>}/>       */}
    <Route path="/ExitForm" element={<PrivateRoutes Component={SelfExitForm}requiredRoles={['CMS Employee','HR ROLE','MANAGER ROLE']} />}/>  
    <Route path='/exit' element={<PrivateRoutes Component={Exit} requiredRoles={['CMS Employee','HR ROLE','MANAGER ROLE']} />}/> 
    <Route exact path="/request" element={<PrivateRoutes Component={ManagerDashBoardPendingExitForm} requiredRoles={['CMS Employee','HR ROLE','MANAGER ROLE']} />}/>
    <Route path="/exitDashboard" element={<PrivateRoutes Component={ExitDashBoard} requiredRoles={['CMS Employee','HR ROLE','MANAGER ROLE']} />}/>

    <Route exact path="/workflowstatusTracker" element={<PrivateRoutes Component={WorkflowStatusTracker} requiredRoles={['CMS Employee','HR ROLE','MANAGER ROLE']} />}/>
    <Route path="/managerDashboard" element={<PrivateRoutes Component={ManagerDashBoardPendingExitForm} requiredRoles={['CMS Employee','HR ROLE','MANAGER ROLE']} />}/>
    <Route path="/hrdash" element={<PrivateRoutes Component={MainExit} requiredRoles={['CMS Employee','HR ROLE','MANAGER ROLE']}/>}/>
    <Route path="/allpendingRequests" element={<PrivateRoutes Component={PendingRequestForDepartmentMembers} requiredRoles={['CMS Employee','HR ROLE','MANAGER ROLE']} />}/>
    <Route path="/e-pms" element={<PrivateRoutes Component={ProfileInfonfoFile} requiredRoles={['CMS Employee','CMS Employee','HR ROLE']} />} />
    <Route path="/SelfAppraisel" element={<PrivateRoutes Component={SelfAppraisel} requiredRoles={['CMS Employee']} />} />
    {/* <Route path="/managerView" element={<PrivateRoutes Component={ManagerView} requiredRoles={['Manager']} />} /> */}
    <Route path="/open" element={<PrivateRoutes Component={Open} requiredRoles={['CMS Employee']} />} />
    <Route path="/pendingMgrReview" element={<PrivateRoutes Component={PendingAppraisal}  requiredRoles={['CMS Employee']}/>} ></Route>
    <Route path="/ManagerScreen" element={<PrivateRoutes Component={ManagerScreen} requiredRoles={['CMS Employee','CMS Manager']} />} />
    <Route path="/ManagerScreen2" element={<PrivateRoutes Component={ManagerScreen2} requiredRoles={['CMS Employee','CMS Manager']} />} />
    <Route path="/RouteToMgrView" element={<PrivateRoutes Component={RouteToMgrView} requiredRoles={['CMS Employee','CMS Manager']} />} />
    <Route path="/MangerReview" element={<PrivateRoutes Component={MangerReview} requiredRoles={['CMS Employee','CMS Manager']} />} />
    {/* <Route path="/managerView" element={<ManagerView />} /> */}
    <Route path="/managerView" element={<PrivateRoutes Component={ManagerView} requiredRoles={['CMS Employee','CMS Manager']} />} />        




    {/* ------------------------------------------------IT DEC------------------------------------------------------------------------------- */}

    <Route path="/it_Dec" element={<PrivateRoutes Component={IT_Declaration}  requiredRoles={['CMS Employee','CMS Manager','HR ROLE']} />}></Route>
            <Route  path="/declaration-dashboard"  element={<PrivateRoutes Component={IT_Declaration_Display} requiredRoles={['CMS Employee','CMS Manager','HR ROLE']} />} ></Route>
            <Route path="/select-regime" element={<PrivateRoutes Component={Select_Regime} requiredRoles={['CMS Employee','CMS Manager','HR ROLE']} />}></Route>
            <Route  path="/declaration-summary"  element={<PrivateRoutes Component={DeclarationSummary } requiredRoles={['CMS Employee','CMS Manager','HR ROLE']} />} ></Route>
            <Route  path="/declaration-update"  element={<PrivateRoutes Component={IT_Declaration_Update}  requiredRoles={['CMS Employee','CMS Manager','HR ROLE']} />} ></Route>
            <Route path="/preview" element={<PrivateRoutes Component={IT_Declaration_Preview}  requiredRoles={['CMS Employee','CMS Manager','HR ROLE']} />}></Route>
            <Route  path="/display-proof-of-investment"  element={<PrivateRoutes Component={Proof_of_Investment_Display} requiredRoles={['CMS Employee','CMS Manager','HR ROLE']} />} ></Route>

            <Route path="/proof-of-investment-update"  element={<PrivateRoutes Component={Proof_Attach}  requiredRoles={['CMS Employee','CMS Manager','HR ROLE']} />}></Route>
  
            <Route path="/admin"  element={<PrivateRoutes Component={ITAdmin}  requiredRoles={['CMS Employee','CMS Manager','HR ROLE']} />}></Route>

            <Route path="/exdp"  element={<PrivateRoutes Component={ExitStatusDisplay}  requiredRoles={['CMS Employee','CMS Manager','HR ROLE']} />}></Route>
            <Route path="/proof-of-investment-edit" element={<PrivateRoutes Component={Proof_Of_Investment_Update}  requiredRoles={['CMS Employee','CMS Manager','HR ROLE']} />}></Route>
  </Routes>
</BrowserRouter>

      </div>
      
    </>
  );
}

export default App;
