import React, { useEffect, useState } from "react";
import DataTable from "react-data-table-component";
import { CiFilter } from "react-icons/ci";
import MenuItem from "@mui/material/MenuItem";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import { useStoreEmpId, useStoreManagerId, useStoreSID } from "../GlobalStorage/ZustandStore";
import AdminNavHead from "../../navbar/AdminNavHead";
import HRNavHead from "../../navbar/HRNavHead";
import NavHead from "../../Component/NavHead";
import { ToastContainer, toast } from 'react-toastify';

function ManagerScreen2() {
  const [value, setValue] = useState([]);
  const [filter, setFilter] = useState(false);
  const [filterType, setFilterType] = useState(1); // Default to "All"
  const [empDetails, setEmpDetails] = useState([]);
  const [employeesDetailsList, setEmployeesDetailsList] = useState([]);
  const [completeEmployeeStatus, setCompleteEmployeeStatus] = useState([]);

  const location = useLocation();
  console.warn(location, "locationlllllllllllllllllllll");

  const pendingScreenInfo = location?.state?.data;
  console.log(pendingScreenInfo, "quarterInfo1@@@@@@@@@2");

  const { setEmpId } = useStoreEmpId();
  const { setSID } = useStoreSID();

  const navigate = useNavigate();

  const managerId = sessionStorage.getItem("managerId");

  console.log("managherdnjvnjd",managerId);

  
  const {mgrId}=useStoreManagerId();
  console.log(mgrId,"mgrId..... +mgrId");

  // const {empId}=useStoreEmpId();




  //9085389

  useEffect(() => {
    // Fetch employee data from the API
    const fetchAllEmployeeFromEmployeeService = async () => {
      try {
        // const res = await axios.get(
        //   "http://43.205.24.208:9020/employee/report/to/9085389"
        // );

        const res = await axios.get(
          `http://43.205.24.208:9020/employee/report/to/${mgrId}`
        );
        setEmpDetails(res?.data);


      } catch (err) {
        console.error(err);
      }
    };

    fetchAllEmployeeFromEmployeeService();
  }, []);

  console.log(empDetails, "empDetails11111!!!!");

  useEffect(() => {
    // Map empDetails to required format
    const newEmployeesList = empDetails.map((i) => {
      const { fileAndObjectTypeBean } = i;

      const empId = fileAndObjectTypeBean?.empResDTO?.empId;
      // Extract required fields with optional chaining
      const empCode = fileAndObjectTypeBean?.empResDTO?.empCode;
      const fullName = fileAndObjectTypeBean?.empResDTO?.fullNameAsAadhaar;
      const designation =
        fileAndObjectTypeBean?.empResDTO?.designationResDTO?.designationName;
      const experience =
        fileAndObjectTypeBean?.empResDTO?.expWithCurrentCompany;

      return {
        empId: empId,
        employeeCode: empCode,
        fullName: fullName,
        designation: designation,
        experience: experience,
        submittedOn: null, // Mock data
        submittedOnn: null, // Mock data
        approvedOn: null, // Mock data
        reviewedOnn: null, // Mock data
      };
    });

    setEmployeesDetailsList(newEmployeesList);
  }, [empDetails]);

  console.log(employeesDetailsList, "employeesDetailsList.....????");


  useEffect(() => {
    console.log(employeesDetailsList, "employeesDetailsList111444");

    axios.post("http://43.205.24.208:9031/api/getAllEmployeeDetailsAndStatus", employeesDetailsList)
      .then(res => {
        const filteredList = res?.data?.data.filter(item =>
          Object.values(item).some(value => value !== null)
        );

        console.log(filteredList, "Filtered employee status");

        setCompleteEmployeeStatus(filteredList || []);
      })
      .catch(err => {
        console.error("Error fetching employee status:", err);
      });
  }, [employeesDetailsList]);


  



  console.dir(completeEmployeeStatus[2],"ppppppppppppppppppppppppp");

  console.dir(completeEmployeeStatus,{depth:null},"uyyyyyyyyyyyyyyyyyyrrrrrrruuuuuuuuuu")

  // =======================================================================================
  // Filter function
  const getFilteredData = () => {
    if (filterType === 2) {
      // Pending with Employee: Filter rows where `submittedOn` or `submittedOnn` is `null`
      return completeEmployeeStatus.filter(
        (row) => !row.submittedOn || !row.submittedOnn
      );
    } else if (filterType === 3) {
      // Pending with Manager: Filter rows where `approvedOn` or `reviewedOnn` is `null`
      return completeEmployeeStatus.filter(
        (row) => !row.approvedOn || !row.reviewedOnn
      );
    }
    // All: Show rows with pending status in any column
    return completeEmployeeStatus.filter(
      (row) =>
        !row.submittedOn ||
        !row.submittedOnn ||
        !row.approvedOn ||
        !row.reviewedOnn
    );
  };

  const handlePendingClick1 = (row) => {
    setEmpId(row?.empId);

    console.log(row?.empId, "row?.empId");
    axios
      .get(`http://43.205.24.208:9031/api/getUniqueEmployeeStatus/${row?.empId}`)
      .then((res) => {
        setSID(res?.data?.employeeStatusId);
      })
      .catch(()=>{
       // toast.warn("querter details is new");
      })

    console.log("row///", row);
  };

  // =================================================================================================

  const columns = [
    {
      name: (
        <div className="text-sm font-medium text-black ml-16">Employee</div>
      ),
      minWidth: "100px",
      cell: (row) => (
        <div
          className="flex mb-2 hover:cursor-pointer"
          onClick={() => handleNavigate(row)}
        >
          <img
            src={require('../profille.jpg')}
            className="rounded-full h-[34px] w-[36px] ml-6 mt-4"
            alt="Profile"
          />
          <div>
            <h1 className="text-sm font-normal text-black pl-4 pt-2">
              <span className="text-blue-600 font-medium">
                {row.employeeCode}
              </span>{" "}
              -{" "}
              <span className="text-blue-600 font-medium">{row?.fullName}</span>
            </h1>
            <div className="ml-12">
              <h3 className="text-sm text-gray-500 -ml-8">{row.designation}</h3>
              <h3 className="text-m text-gray-500 -ml-8">{row.experience}</h3>
            </div>
          </div>
        </div>
      ),
    },
    {
      name: (
        <div className="text-sm font-medium text-black ml-4">
          Employee Submit
        </div>
      ),
      maxWidth: "200px",
      cell: (row) => (
        <div className="text-sm ml-4">
          <span className="text-green-600">
            {row.submittedOn ? "Submitted" : "Pending"}
          </span>
        </div>
      ),
    },
    // {
    //   name: (
    //     <div className="text-sm font-medium text-black ml-4">
    //       Manager Approval
    //     </div>
    //   ),
    //   maxWidth: "200px",
    //   cell: (row) => (
    //     <div className="text-sm ml-4">
    //       {row.approvedOn ? (
    //         <span className="text-gray-600">Submitted</span>
    //       ) : (
    //         <span
    //           className="text-red-500 underline cursor-pointer"
    //           onClick={() =>
    //             navigate("/managerView", {
    //               state: { empCode: row },
    //             })
    //           }
    //         >
    //           Pending
    //         </span>
    //       )}
    //     </div>
    //   ),
    // },

    {
      name: (
        <div className="text-sm font-medium text-black ml-4">
          Manager Approval
        </div>
      ),
      maxWidth: "200px",
      cell: (row) => {
        const handlePendingClick = (row) => {
          // Print the row data
          console.log(row, "Row Info");

          // Navigate with the row data
          navigate("/managerView", {
            state: { empCode: { ...row, ...pendingScreenInfo } },
          });
        };

        return (
          <div className="text-sm ml-4">
            {row.approvedOn ? (
              <span className="text-gray-600">Submitted</span>
            ) : (
              <span
                className="text-blue-600 underline cursor-pointer"
                onClick={() => {
                  handlePendingClick(row);
                  handlePendingClick1(row);
                }}
              >
                Pending
              </span>
            )}
          </div>
        );
      },
    },
    {
      name: (
        <div className="text-sm font-medium text-black ml-4">
          Self Assessment
        </div>
      ),
      maxWidth: "200px",
      cell: (row) => (
        <div className="text-sm ml-4">
          <span className="text-blue-600">
            {row.submittedOnn ? "Submitted" : "Pending"}
          </span>
        </div>
      ),
    },
    // {
    //   name: (
    //     <div className="text-sm font-medium text-black ml-4">
    //       Manager Assessment
    //     </div>
    //   ),
    //   maxWidth: "235px",
    //   cell: (row) => (
    //     <div className="text-sm ml-4">
    //       <span className="text-gray-600">
    //         {row.reviewedOnn ? "Submitted" : "Pending"}
    //       </span>
    //     </div>
    //   ),
    // },

    {
      name: (
        <div className="text-sm font-medium text-black ml-4">
          Manager Assessment
        </div>
      ),
      maxWidth: "235px",
      cell: (row) => (
        <div className="text-sm ml-4">
          {row.reviewedOnn ? (
            <span className="text-green-600">Submitted</span>
          ) : (
            <span
              className="text-blue-600 underline cursor-pointer"
              onClick={() =>
                navigate("/ManagerScreen", {
                  state: { empCode: { ...row, ...pendingScreenInfo } },
                })
              }
            >
              Pending
            </span>
          )}
        </div>
      ),
    },
  ];

  const handleNavigate = (row) => {
    navigate("/managerView", { state: { data: row } });
  };

  const handleClick = () => setFilter(!filter);
  let roles = [];
  try {
    roles = JSON.parse(sessionStorage.getItem('role')) || [];
  } catch (e) {
    console.error('Error parsing roles from sessionStorage:', e);
  }

  // Function to check if the user has a specific role
  const hasRole = (role) => roles.includes(role);

  return (
    
      <div className="">
      {hasRole('admin') && <AdminNavHead />} {/* Admin-specific navigation */}
      {hasRole('CMS Employee') && <HRNavHead />}       {/* HR-specific navigation */}
      {hasRole('employee') && <NavHead />} {/* Employee-specific navigation */}
      <div className="relative overflow-x-auto shadow-md sm:rounded-lg h-full w-full">
      <div className=" ml-44 bg-white-300 mt-5">
    <div className="w-full h-screen bg-gray-200">
      <div className="bg-white rounded-md relative top-12 ml-4 mr-3 border-[1px] border-blue-400 pb-24">
        <div className="flex justify-between">
          <h1 className="text-[22px] font-medium text-black pl-12 pt-12">
            Appraisee List
          </h1>
          <div className="mt-12 ml-[460px] text-4xl text-black mr-10">
            <CiFilter onClick={handleClick} />
          </div>
          {filter && (
            <div className="absolute ml-[1200px] mt-24 z-50">
              <div className="border-[2px] border-black bg-gray-100 -ml-24 pb-12 rounded-md">
                <MenuItem
                  value={1}
                  onClick={() => {
                    setFilterType(1);
                    setFilter(false);
                  }}
                >
                  All
                </MenuItem>
                <MenuItem
                  value={2}
                  onClick={() => {
                    setFilterType(2);
                    setFilter(false);
                  }}
                >
                  Pending with Employee
                </MenuItem>
                <MenuItem
                  value={3}
                  onClick={() => {
                    setFilterType(3);
                    setFilter(false);
                  }}
                >
                  Pending with Manager
                </MenuItem>
              </div>
            </div>
          )}
        </div>
        <DataTable
          className="mt-10"
          columns={columns}
          // data={getFilteredData()} // Pass filtered data
          data={completeEmployeeStatus?.length !==0 ? completeEmployeeStatus : []}
          selectableRows
          highlightOnHover
          striped
        />
      </div>
    </div>
    </div>
    </div>
    <ToastContainer />
    </div>
  );
}

export default ManagerScreen2;
