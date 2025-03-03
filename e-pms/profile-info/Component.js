// import React, { useState,useEffect } from 'react';
// import DataTable from 'react-data-table-component';
// import { FaCheck} from 'react-icons/fa'
// import Dropdown from 'react-dropdown';
// import 'react-dropdown/style.css';
// import { useNavigate } from 'react-router-dom';
// import Card from '@mui/material/Card';
// import CardContent from '@mui/material/CardContent';
// import { StyleSheetManager } from 'styled-components';
// import Service from '../Service';

// import { useStoreEmpId ,useStoreTabStatus} from "../GlobalStorage/ZustandStore";

// import axios from 'axios';
// import { toast } from 'react-toastify';
// const blueIconStyle = {
//   color: '#40cd40',
//   fontSize: '20px',
// };
// function Component({empId}) {

//   const navigate=useNavigate();
//   const [employeePerformance, setEmployeePerformance] = useState([]);
//   console.log(employeePerformance,"employeePerformance....9999999999.");
//   console.log(employeePerformance?.approvedOn,"employeePerformance?.approvedOn.....");
//   const [profileInfo, setProfileInfo] = useState({});
//   const date = new Date();
//   const Year = date.getFullYear();
//   const currentYear = Year.toString();
//   const currentYearRange = Year.toString() + "-" + (Year + 1).toString();
//   //const empId=1;
//    const [year, setYear] = useState(currentYear);
//    const [reviewCycle, setReviewCycle] = useState(currentYearRange);
//   const [reviewCycleOptions, setReviewCycleOptions] = useState([currentYearRange]);
//   // const options1 = ['2023', '2024', '2025'];
//   const [years, setYears] = useState([]);
//   const options1 =years;
//   const yearIndex = options1.indexOf(currentYear);
//   const defaultOption1 = options1[yearIndex];
//   //   const generateReviewCycleOptions = (year) => {
//   //   const nextYear = parseInt(year) + 1;
//   //   return [`${year}-${nextYear}`];
//   // };
//   const generateReviewCycleOptions = (years) => {
//     const nextYears = parseInt(years) + 1;
//     return [`${years}-${nextYears}`];
//   };

//   const { setEmpId } = useStoreEmpId();

//   useEffect(() => {
//     setEmpId(empId);
//   }, [empId]);

//   const {setTabStatus}=useStoreTabStatus();

//   console.warn(employeePerformance?.approvedOn!==undefined,"000000000000000000000000000000++");

//   useEffect(()=>{
//   if(employeePerformance?.approvedOn!==undefined){
//     setTabStatus("two");
//   }
//   },[])

//   // if(employeePerformance?.approvedOn.length!==0){
//   //   setTabStatus("two");
//   // }

// const[reviewCycles,setReviewCycles] = useState([]);
//   useEffect(()=>{
//     axios.get("http://43.205.24.208:9031/api/getAllReviewCycle")
//     .then((res)=>{
//       setReviewCycles(res?.data);
//     })
//     },  [])
//     console.log(reviewCycles,"reviewCycles")
//     useEffect(() => {
//       if (reviewCycles) {
//         setYears(
//           reviewCycles?.map(cycle => cycle?.split("-")[0])
//         );
//       }
//     }, [reviewCycles]);

//     console.log(years, "years");

//   console.log(reviewCycle,"reviewCycle>>>>>>>>>>>>>")
//   // useEffect(() => {
//   // //  axios.get(`http://43.205.24.208:8080/api/getPendingAppraisal/2024-2025`)
//   // //  .then(()=>{
//   // //   console.log("Fetched");
//   // //  }).catch((error)=>{
//   // //   console.log("error during fetching", error)
//   // //  });
//   //   const initialReviewCycleOptions = generateReviewCycleOptions(currentYear);
//   //   setReviewCycleOptions(initialReviewCycleOptions);
//   //   setReviewCycle(initialReviewCycleOptions[0]);
//   //   const employeeId =1;
//   //   Service.getByProfileById(employeeId).then((response) => setProfileInfo(response.data));
//   // }, [currentYear]);
//   useEffect(() => {
//     // const empId = 1;
//     if (year && reviewCycle) {
//       console.log(year, reviewCycle);
//       // Service.getByYearAndReviewCycle(empId,year,reviewCycle)
//       Service.getByYearAndReviewCycle(empId,year,reviewCycle)
//         .then((res) => {
//           setEmployeePerformance(res.data);
//           console.log(res.data,"resresresresres")
//         });
//     }
//   }, [year, reviewCycle]);

//   const handleSelect1 = (selectedOption) => {
//     const selectedYear = selectedOption.value;
//     setYear(selectedYear);
//     const newReviewCycleOptions = generateReviewCycleOptions(selectedYear);
//     setReviewCycleOptions(newReviewCycleOptions);
//     setReviewCycle(newReviewCycleOptions[0]);
//   };
//   const handleSelect2 = (selectedOption) => {
//     setReviewCycle(selectedOption.value);
//   };
//  const [preventNavigation,setPreventNavigation]=useState();
//   useEffect(()=>{
//     axios.get(`43.205.24.208:9031/api/preventDataFromNavigatingToKRA/${empId}/${employeePerformance?.employeeStatusId}`)
//     .then((response)=>{
//       setPreventNavigation(response?.data);
//     }).catch(()=>{
//       toast.error("data not found");
//     })
//     },  [])
// //     console.log(preventNavigation,"preventNavigation")
//    const columns=[
//     // {
//     //   name:"Appraisal Cycle",
//     //   selector:row=>row.appraisalCycle,
//     //   font:"bold",
//     //   conditionalCellStyles: [
//     //     {
//     //       when: (row) => row.submittedOn === null,
//     //       style: {color: ' #6dadd3'},
//     //     },
//     //   ],
//     //    },

//     {
//       name: "Appraisal Cycle",
//       selector: row => {
//         if (row.appraisalCycle === "1") {
//           return "Quarter 1";
//         } else if (row.appraisalCycle === "2") {
//           return "Quarter 2";
//         } else if (row.appraisalCycle === "3") {
//           return "Quarter 3";
//         } else if (row.appraisalCycle === "4") {
//           return "Quarter 4";
//         }
//       },
//       font: "bold",
//       conditionalCellStyles: [
//         {
//           when: (row) => row.submittedOn === null,
//           style: { color: '#6dadd3' },
//         },
//       ],
//     },
//     {
//       name:" Submitted for Approval",
//       cell: (row) => (row.submittedOn !==null ? <FaCheck style={blueIconStyle}/> : ""),
//   },
//      {
//       name:" Submitted On",
//       selector:row=>row.submittedOn,
//     },
//     {
//       name:"Manager Approval",
//       cell: (row) => (row.approvedOn !==null ? <FaCheck style={blueIconStyle}/> : ""),
//       },
//     {
//       name:" Approved on",
//       selector:row=>row.approvedOn,
//        },
//     {
//       name:" self review",
//       cell: (row) => (row.submittedOnn !==null ? <FaCheck style={blueIconStyle}/> : ""),
//        },
//      {
//       name:" Submitted On",
//       selector:row=>row.submittedOnn,
//      },
//     {
//       name: "Manager",
//       selector: row => row.manager,
//       conditionalCellStyles: [
//         {
//           when: (row) => row.submittedOn === null,
//           style: { color: '#6dadd3' },
//         },
//       ],
//       cell: (row) => {
//         if (row.manager === "Mathimaran.P") {
//           return (
//             <button to="/managerView" onClick={() => renderMV(row.manager)}>{profileInfo.mgrName}</button>
//           );
//         }
//         else {
//           return row.manager;
//         }
//       },
//     },
//     {
//       name:" Manager Review  ",
//       cell: (row) => (row.reviewedOnn !==null ? <FaCheck style={blueIconStyle}/> : ""),
//     },
//     {
//       name:" Reviewed On ",
//       selector:row=>row.reviewedOnn,
//     },
//     {
//       name: "current Status",
//       selector: (row) => row.currentStatus,
//       wrap: true,
//       conditionalCellStyles: [
//         {
//           when: (row) => row.submittedOn === null,
//           style: { color: "#6dadd3" },
//         },

//       ],
//       cell: (row) => {
//       // if (row.currentStatus === "Open" || row.currentStatus === "Save As Draft" || row.currentStatus === "Submitted To Manager" || row.currentStatus === "Reverted" ) {
//       //   return (
//       //     <button onClick={() => render(row)}>
//       //       {row.currentStatus === "Open" ? "Open" : row.currentStatus === "Save As Draft" ? "Save As Draft" : row.currentStatus === "Submitted To Manager" ? "Submitted To Manager": "Reverted"}
//       //     </button>
//       //   );
//       if (row.currentStatus === "Open" || row.currentStatus === "Save As Draft" || row.currentStatus === "Submitted To Manager" || row.currentStatus === "Submitted"|| row.currentStatus === "Approved"|| row.currentStatus === "Reverted"|| row.currentStatus === "Disable" ) {
//         return (
//           <button onClick={() => render(row)}>
//           {row.currentStatus === "Open"
//             ? "Open"
//             : row.currentStatus === "Save As Draft"
//             ? "Save As Draft"
//             : row.currentStatus === "Submitted To Manager"
//             ? "Submitted To Manager"
//             : row.currentStatus === "Approved"
//             ? "Approved"
//             : row.currentStatus === "Reverted"
//             ? "Reverted"
//              : row.currentStatus === "Disable"
//             ? "Disable"
//             : "Submitted"}
//         </button>

//         );
//       } else {
//         return <>{row.currentStatus}</>;
//       }
//     },
//   },

// {
//   name: "status",
//   selector: (row) => row.status,
//   style: { minWidth: "120px", maxWidth: "200px" },
//   center: true,
//   cell: row => {
//     if (!row.submittedOn) {
//       return (
//         <div className='text-sm ml-2'>
//         <button
//         style={{backgroundColor: "gray",padding: "6px",fontWeight: "bold",color: "white",borderRadius: "5px",width: "120px",textAlign: "center"}}
//         className='text-gray-500'>
//       No Due Date
//       </button>
//       </div>

//       );
//   }
//     const requestedDate = new Date(row.submittedOn);
//     const approvedDate = row.approvedOn ? new Date(row.approvedOn) : null;
//     const currentDate = new Date();

//     // Use approvedDate if it exists, otherwise fall back to requestedDate
//     const effectiveDate = approvedDate || requestedDate;

//     const timeDiff = Math.abs(currentDate.getTime() - effectiveDate.getTime());
//     const diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
//     return (
//       <div className='text-sm ml-2'>
//         <button
//           style={{backgroundColor: "gray",padding: "6px",fontWeight: "bold",color: "white",borderRadius: "5px",width: "120px",textAlign: "center"}}
//           className='text-gray-500'>
//           Due in {diffDays} Days
//         </button>
//       </div>
//     );
//   }
// }
//    ];
// const newStyle = {
//   headCells: {
//     style: {
//       fontSize: "12px",
//       fontWeight: "700",
//     }
//   }
// };
// const render = (row) => {
//   console.log(row,"row")
//   navigate("/open", { state: { data: row } });
// };
// const renderMV = (row) => {
//   navigate("/managerView", { state: { data: row } });
// };
// return (
//   <div style={{ backgroundColor: '#DCDCDC', padding: '10px' }}>
//   <Card sx={{ borderRadius: '20px' }}>
//     <CardContent>
// <StyleSheetManager shouldForwardProp={(prop) => prop !== 'sortActive'}>
//     <div className='ml-5 mt-1'>
//         <div className='flex flex-col space-y-5 items-center sm:flex-row sm:space-x-28 sm:items-center'>
//             <div>
//                 <h1 className='font-bold text-lg mt-[-5px]'>Year</h1>
//             </div>
//             <div className='w-full sm:w-1/6 font-bold'>
//             <Dropdown
//                     options={options1}
//                     onChange={handleSelect1}
//                     value={year || defaultOption1}
//                     placeholder="Select an option"
//                   />
//             </div>
//         </div>
//         <div className='flex flex-col space-y-5 items-center mt-1 sm:flex-row sm:space-x-12 sm:mt-0'>
//             <div>
//                 <h1 className='font-bold text-lg mt-[-5px]'>Review Cycle</h1>
//             </div>
//             <div className='w-full sm:w-1/6 font-bold'>
//             <Dropdown
//                     options={reviewCycleOptions}
//                     onChange={handleSelect2}
//                     value={reviewCycle}
//                     placeholder="Select an option"
//                   />
//             </div>
//         </div>
//     </div>
//     <DataTable data={employeePerformance} data-testid="comp" columns={columns} responsive customStyles={newStyle}/>

// </StyleSheetManager>
// </CardContent>
//     </Card>
//   </div>
//    );
// }
// export default Component;

import React, { useState, useEffect } from "react";
import DataTable from "react-data-table-component";
import { FaCheck } from "react-icons/fa";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";
import { useNavigate } from "react-router-dom";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import { StyleSheetManager } from "styled-components";
import Service from "../Service";

import {
  useStoreEmpId,
  useStoreTabStatus,
} from "../GlobalStorage/ZustandStore";

import axios from "axios";
import { toast } from "react-toastify";
const blueIconStyle = {
  color: "#40cd40",
  fontSize: "20px",
};
function Component({ empId }) {
  const navigate = useNavigate();
  const [employeePerformance, setEmployeePerformance] = useState([]);
  console.log(employeePerformance, "employeePerformance....9999999999.");
  console.log(
    employeePerformance?.approvedOn,
    "employeePerformance?.approvedOn....."
  );
  const [profileInfo, setProfileInfo] = useState({});
  const date = new Date();
  const Year = date.getFullYear();
  const currentYear = Year.toString();
  const currentYearRange = Year.toString() + "-" + (Year + 1).toString();
  const [year, setYear] = useState(currentYear);
  const [reviewCycle, setReviewCycle] = useState(currentYearRange);
  const [reviewCycleOptions, setReviewCycleOptions] = useState([
    currentYearRange,
  ]);
  const [years, setYears] = useState([]);
  const options1 = years;
  const yearIndex = options1.indexOf(currentYear);
  const defaultOption1 = options1[yearIndex];

  const generateReviewCycleOptions = (years) => {
    const nextYears = parseInt(years) + 1;
    return [`${years}-${nextYears}`];
  };

  const { setEmpId } = useStoreEmpId();

  useEffect(() => {
    setEmpId(empId);
  }, [empId]);

  const { setTabStatus } = useStoreTabStatus();

  console.warn(
    employeePerformance?.approvedOn !== undefined,
    "000000000000000000000000000000++"
  );

  useEffect(() => {
    if (employeePerformance?.approvedOn !== undefined) {
      setTabStatus("two");
    }
  }, []);

  // if(employeePerformance?.approvedOn.length!==0){
  //   setTabStatus("two");
  // }

  const [reviewCycles, setReviewCycles] = useState([]);
  // useEffect(()=>{
  //   axios.get("http://43.205.24.208:9031/api/getAllReviewCycle")
  //   .then((res)=>{
  //     setReviewCycles(res?.data);
  //   })
  //   },  [])
  //   console.log(reviewCycles,"reviewCycles")
  //   useEffect(() => {
  //     if (reviewCycles) {
  //       setYears(
  //         reviewCycles?.map(cycle => cycle?.split("-")[0])
  //       );
  //     }
  //   }, [reviewCycles]);

  console.log(reviewCycles, "reviewCycles =======>>>>>>>>>>>>>");
  useEffect(() => {
    axios
      .get("http://43.205.24.208:9031/api/getAllReviewCycle")
      .then((res) => {
        if (Array.isArray(res?.data)) {
          setReviewCycles(res.data); // Directly set review cycles
        } else {
          console.error("Invalid data format:", res?.data);
        }
      })
      .catch((err) => {
        console.error("Error fetching review cycles:", err);
      });
  }, []);

  console.log(years, "years");

  console.log(reviewCycle, "reviewCycle>>>>>>>>>>>>>");

  useEffect(() => {
    // const empId = 1;
    if (reviewCycle) {
      console.log(year, reviewCycle);
      // Service.getByYearAndReviewCycle(empId,year,reviewCycle)
      Service.getByReviewCycle(empId, reviewCycle).then((res) => {
        setEmployeePerformance(res.data);
        console.log(res.data, "resresresresres");
      });
    }
  }, [reviewCycle]);

  const handleSelect2 = (selectedOption) => {
    setReviewCycle(selectedOption.value);
  };

  const columns = [
    {
      name: "Appraisal Cycle",
      selector: (row) => {
        if (row.appraisalCycle === "1") {
          return "Quarter 1";
        } else if (row.appraisalCycle === "2") {
          return "Quarter 2";
        } else if (row.appraisalCycle === "3") {
          return "Quarter 3";
        } else if (row.appraisalCycle === "4") {
          return "Quarter 4";
        }
      },
      font: "bold",
      conditionalCellStyles: [
        {
          when: (row) => row.submittedOn === null,
          style: { color: "#6dadd3" },
        },
      ],
    },
    {
      name: " Submitted for Approval",
      cell: (row) =>
        row.submittedOn !== null ? <FaCheck style={blueIconStyle} /> : "",
    },
    {
      name: " Submitted On",
      selector: (row) => row.submittedOn,
    },
    {
      name: "Manager Approval",
      cell: (row) =>
        row.approvedOn !== null ? <FaCheck style={blueIconStyle} /> : "",
    },
    {
      name: " Approved on",
      selector: (row) => row.approvedOn,
    },
    {
      name: " self review",
      cell: (row) =>
        row.submittedOnn !== null ? <FaCheck style={blueIconStyle} /> : "",
    },
    {
      name: " Submitted On",
      selector: (row) => row.submittedOnn,
    },
    {
      name: "Manager",
      selector: (row) => row.manager,
      conditionalCellStyles: [
        {
          when: (row) => row.submittedOn === null,
          style: { color: "#6dadd3" },
        },
      ],
      cell: (row) => {
        if (row.manager === "Mathimaran.P") {
          return (
            <button to="/managerView" onClick={() => renderMV(row.manager)}>
              {profileInfo.mgrName}
            </button>
          );
        } else {
          return row.manager;
        }
      },
    },
    {
      name: " Manager Review  ",
      cell: (row) =>
        row.reviewedOnn !== null ? <FaCheck style={blueIconStyle} /> : "",
    },
    {
      name: " Reviewed On ",
      selector: (row) => row.reviewedOnn,
    },
    {
      name: "current Status",
      selector: (row) => row.currentStatus,
      wrap: true,
      conditionalCellStyles: [
        {
          when: (row) => row.submittedOn === null,
          style: { color: "#6dadd3" },
        },
      ],
      cell: (row) => {
        // if (row.currentStatus === "Open" || row.currentStatus === "Save As Draft" || row.currentStatus === "Submitted To Manager" || row.currentStatus === "Reverted" ) {
        //   return (
        //     <button onClick={() => render(row)}>
        //       {row.currentStatus === "Open" ? "Open" : row.currentStatus === "Save As Draft" ? "Save As Draft" : row.currentStatus === "Submitted To Manager" ? "Submitted To Manager": "Reverted"}
        //     </button>
        //   );
        if (
          row.currentStatus === "Open" ||
          row.currentStatus === "Save As Draft" ||
          row.currentStatus === "Submitted To Manager" ||
          row.currentStatus === "Submitted" ||
          row.currentStatus === "Approved" ||
          row.currentStatus === "Reverted" ||
          row.currentStatus === "Disable"
        ) {
          return (
            <button onClick={() => render(row)}>
              {row.currentStatus === "Open"
                ? "Open"
                : row.currentStatus === "Save As Draft"
                ? "Save As Draft"
                : row.currentStatus === "Submitted To Manager"
                ? "Submitted To Manager"
                : row.currentStatus === "Approved"
                ? "Approved"
                : row.currentStatus === "Reverted"
                ? "Reverted"
                : row.currentStatus === "Disable"
                ? "Disable"
                : "Submitted"}
            </button>
          );
        } else {
          return <>{row.currentStatus}</>;
        }
      },
    },

    {
      name: "status",
      selector: (row) => row.status,
      style: { minWidth: "120px", maxWidth: "200px" },
      center: true,
      cell: (row) => {
        if (!row.submittedOn) {
          return (
            <div className="text-sm ml-2">
              <button
                style={{
                  backgroundColor: "gray",
                  padding: "6px",
                  fontWeight: "bold",
                  color: "white",
                  borderRadius: "5px",
                  width: "120px",
                  textAlign: "center",
                }}
                className="text-gray-500"
              >
                No Due Date
              </button>
            </div>
          );
        }
        const requestedDate = new Date(row.submittedOn);
        const approvedDate = row.approvedOn ? new Date(row.approvedOn) : null;
        const currentDate = new Date();

        // Use approvedDate if it exists, otherwise fall back to requestedDate
        const effectiveDate = approvedDate || requestedDate;

        const timeDiff = Math.abs(
          currentDate.getTime() - effectiveDate.getTime()
        );
        const diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
        return (
          <div className="text-sm ml-2">
            <button
              style={{
                backgroundColor: "gray",
                padding: "6px",
                fontWeight: "bold",
                color: "white",
                borderRadius: "5px",
                width: "120px",
                textAlign: "center",
              }}
              className="text-gray-500"
            >
              Due in {diffDays} Days
            </button>
          </div>
        );
      },
    },
  ];
  const newStyle = {
    headCells: {
      style: {
        fontSize: "12px",
        fontWeight: "700",
      },
    },
  };
  const render = (row) => {
    console.log(row, "row");
    navigate("/open", { state: { data: row } });
  };
  const renderMV = (row) => {
    navigate("/managerView", { state: { data: row } });
  };
  return (
    <div style={{ backgroundColor: "#DCDCDC", padding: "10px" }}>
      <Card sx={{ borderRadius: "20px" }}>
        <CardContent>
          <StyleSheetManager
            shouldForwardProp={(prop) => prop !== "sortActive"}
          >
            <div className="ml-5 mt-1">
              <div className="flex flex-col space-y-5 items-center sm:flex-row sm:space-x-28 sm:items-center">
                {/* <div>
                <h1 className='font-bold text-lg mt-[-5px]'>Year</h1>
            </div> */}
                {/* <div className='w-full sm:w-1/6 font-bold'>
            <Dropdown
                    options={options1}
                    onChange={handleSelect1}
                    value={year || defaultOption1}
                    placeholder="Select an option"
                  />
            </div> */}
              </div>
              <div className="flex flex-col space-y-5 items-center mt-1 sm:flex-row sm:space-x-12 sm:mt-0">
                <div>
                  <h1 className="font-bold text-lg mt-[20px]">Review Cycle</h1>
                </div>
                <div className="w-full sm:w-1/6 font-bold">
                  {/* <Dropdown
                    options={reviewCycleOptions}
                    onChange={handleSelect2}
                    value={reviewCycle}
                    placeholder="Select an option"
                  /> */}
                  <div className="w-full sm:w-1/6 font-bold">
                    <Dropdown
                      options={reviewCycles.map((cycle) => ({
                        label: cycle,
                        value: cycle,
                      }))}
                      onChange={(selectedOption) =>
                        setReviewCycle(selectedOption.value)
                      }
                      value={reviewCycle}
                      placeholder="Select Review Cycle"
                    />
                  </div>
                </div>
              </div>
            </div>
            <DataTable
              className="relative max-h-[400px] overflow-hidden  rounded-lg"
              data={employeePerformance}
              data-testid="comp"
              columns={columns}
              responsive
              customStyles={newStyle}
            />
          </StyleSheetManager>
        </CardContent>
      </Card>
    </div>
  );
}
export default Component;
