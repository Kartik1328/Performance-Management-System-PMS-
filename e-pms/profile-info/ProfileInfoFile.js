// // import React, { useEffect, useState } from "react";
// // import Component from "./Component";
// // import axios from "axios";
// // import Service from "../Service";
// // import Swal from "sweetalert2";
// // import AdminNavHead from "../../navbar/AdminNavHead";
// // import HRNavHead from "../../navbar/HRNavHead";
// // import NavHead from "../../Component/NavHead";
// // import { useStoreManagerId } from "../GlobalStorage/ZustandStore";
// // // import { useStoreManagerId } from "../GlobalStorage/ZustandStore";

// // //Here we have written two dots"..Service" because we want to import the service file which is in the services folder.For fetching the nested or file within a sub folder we use double dots..

// // //http://43.205.24.208:9031/api/getByProfile/5
// // //this API will be used here.

// // function ProfileInfonfoFile() {
// //   const [profileInfo, setProfileInfo] = useState([]);
// //   const [fileUrl, setFileUrl] = useState("");
// //   const empId = localStorage.getItem("empId");
// //   const [empData, setEmpData] = useState({});
// //   const [userData, setUserData] = useState({});
// //   const email = localStorage.getItem("email");
// //   const fallbackImageUrl =
// //     "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEhbRExVYjkvKlgz03fnIy3QN6HzcGDmE_3w&s";

// //   useEffect(() => {
// //     Service.getByProfileById(empId).then((response) => {
// //       console.log("harsh kumar 1");
// //       getEmployeeData();
// //       setProfileInfo(response.data);
// //     });
// //   }, [empId]);

// //   const getEmployeeData = () => {
// //     console.log("harsh kumar 2");
// //     axios
// //       .get(`http://43.205.24.208:9020/employee/by/email/${email}`)
// //       .then((response) => {
// //         console.log(response?.data, "data...........................");

// //         console.log(
// //           response?.data?.fileAndObjectTypeBean?.empResDTO,
// //           "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeemmmmmpData===========================================000000000000000"
// //         );
// //         setEmpData(response?.data?.fileAndObjectTypeBean?.empResDTO);
// //         setUserData(
// //           response.data.userDTO,
// //           "fffffffffffffffffffffffffffffffffffffffff"
// //         );
// //         // const managerId = sessionStorage.setItem("ManagerId", profileInfo.fileAndObjectTypeBean?.empResDTO
// //         //   ?.reportTo);

// //         console.log(
// //           "manageriiiiyuyudddd",
// //           response?.data?.fileAndObjectTypeBean?.empResDTO?.empCode,
// //           "===================================&&&&&&&&&&&&&&&&&&&&&&&&&==================000000000000000"
// //         );
// //         console.log("skills info");
// //         console.log(response.data);
// //         const { file, contentType } =
// //           response.data.fileAndObjectTypeBean.fileAndContentTypeBean;

// //         // Decode base64 string
// //         const byteCharacters = atob(file);
// //         const byteNumbers = new Array(byteCharacters.length);
// //         for (let i = 0; i < byteCharacters.length; i++) {
// //           byteNumbers[i] = byteCharacters.charCodeAt(i);
// //         }
// //         const byteArray = new Uint8Array(byteNumbers);
// //         const blob = new Blob([byteArray], { type: contentType });

// //         const url = URL.createObjectURL(blob);
// //         setFileUrl(url);

// //         console.log(response.data);
// //       })
// //       .catch((error) => console.log(error));
// //   };

// //   let roles = [];
// //   try {
// //     roles = JSON.parse(sessionStorage.getItem("role")) || [];
// //   } catch (e) {
// //     console.error("Error parsing roles from sessionStorage:", e);
// //   }

// //   // Function to check if the user has a specific role
// //   const hasRole = (role) => roles.includes(role);

// //   return (
// //     <>
// //       <div className="">
// //         {hasRole("admin") && <AdminNavHead />} {/* Admin-specific navigation */}
// //         {hasRole("CMS Employee") && <HRNavHead />}{" "}
// //         {/* HR-specific navigation */}
// //         {hasRole("employee") && <NavHead />}{" "}
// //         {/* Employee-specific navigation */}
// //         <div className="relative overflow-x-auto shadow-md sm:rounded-lg h-full w-full">
// //           <div className=" ml-44 bg-white-300">
// //             <div className=" grid grid-cols-1 bg-white md:grid-cols-6 lg:grid-cols-10 pt-6 mt-6 shadow-lg border border-white py-6 rounded-md">
// //               <div className="flex items-center justify-center col-span-1 lg:col-span-2 ml-0 lg:ml-5">
// //                 <div className="col-span-1 lg:col-span-2 ">
// //                   <div className="profile ml-8">
// //                     <div className="img">
// //                       <div className="relative inline-block">
// //                         {/* <img src="profile.jpeg" className='rounded-full h-40 w-40 ml-4' alt="Profile"></img> */}
// //                         <img
// //                           src={fileUrl || fallbackImageUrl} // Use fallback image if no image is available
// //                           alt="Profile"
// //                           className="w-40 h-40 mr-3 rounded-full mt-1"
// //                         />
// //                         {/* inserting image from backend. */}
// //                         <div className="absolute bottom-2 right-5 h-6 w-6 bg-green-500 rounded-full flex items-center justify-center cursor-pointer">
// //                           <svg
// //                             className="h-4 w-4 text-green-500"
// //                             fill="none"
// //                             stroke="currentColor"
// //                             viewBox="0 0 24 24"
// //                             xmlns="http://www.w3.org/2000/svg"
// //                           >
// //                             <path
// //                               stroke-linecap="round"
// //                               stroke-linejoin="round"
// //                               stroke-width="2"
// //                               d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
// //                             ></path>
// //                             <path
// //                               stroke-linecap="round"
// //                               stroke-linejoin="round"
// //                               stroke-width="2"
// //                               d="M12 1a11 11 0 00-11 11c0 6.075 4.925 11 11 11s11-4.925 11-11A11 11 0 0012 1zm0 2a9 9 0 019 9c0 4.97-4.03 9-9 9s-9-4.03-9-9A9 9 0 0112 3z"
// //                             ></path>
// //                           </svg>
// //                         </div>
// //                       </div>
// //                     </div>
// //                     <div className="text">
// //                       <h2 className="font-semibold text-sm text-blue-900 mx-8 pt-3 w-56 flex-col -ml-[5px]">
// //                         {
// //                           profileInfo.fileAndObjectTypeBean?.empResDTO
// //                             ?.firstName
// //                         }{" "}
// //                       </h2>

// //                       <p className="font-normal">
// //                         {
// //                           profileInfo.fileAndObjectTypeBean?.empResDTO
// //                             ?.designationResDTO?.designationName
// //                         }
// //                       </p>
// //                     </div>
// //                   </div>
// //                 </div>
// //               </div>

// //               {/* The second container */}
// //               <div className="col-span-4 sm:-ml-5 relative left-8">
// //                 <div className="div pt-8">
// //                   <div className="div1  flex space-x-5 pl-10 p-7">
// //                     <div className="emp w-56 flex-col ">
// //                       <h3 className="font-normal text-normal">Employee ID</h3>
// //                       <h2 className="text-blue-900 font-semibold ">
// //                         {profileInfo.fileAndObjectTypeBean?.empResDTO?.empCode}{" "}
// //                       </h2>
// //                     </div>
// //                     <div className="relative right-10">
// //                       <h3 className="font-normal  ">Contact No.</h3>
// //                       <h2 className="text-blue-900 font-semibold ">
// //                         {
// //                           profileInfo.fileAndObjectTypeBean?.empResDTO
// //                             ?.primaryContactNo
// //                         }{" "}
// //                       </h2>
// //                     </div>
// //                   </div>
// //                   <div className="div2 flex space-x-5 pl-10 p-7 pb-10 ">
// //                     <div className="dept flex w-56 flex-col">
// //                       <h3 className="font-normal">Department</h3>
// //                       <p className="font-semibold overflow-hidden text-blue-900">
// //                         {
// //                           profileInfo.fileAndObjectTypeBean?.empResDTO
// //                             ?.mainDeptResDTO.mainDepartment
// //                         }{" "}
// //                       </p>
// //                     </div>
// //                     {/*  For Positioning an Element:"relative left-0" this property is used............... */}

// //                     <div className="relative right-10">
// //                       <h3 className="font-normal">Reporting To</h3>
// //                       <h2 className="text-blue-900 font-semibold">
// //                         {
// //                           profileInfo.fileAndObjectTypeBean?.empResDTO
// //                             ?.reportingManager
// //                         }{" "}
// //                       </h2>
// //                     </div>
// //                   </div>
// //                 </div>
// //               </div>

// //               {/* The third container */}
// //               <div className="col-span-4 -ml-10 lg:-ml-24 -mt-10 lg:-mt-0 sm:relative left-12">
// //                 <div className="div pt-8">
// //                   <div className="div1 flex space-x-5 pl-10 p-7">
// //                     <div className="emp flex  flex-col">
// //                       <h3 className="font-normal ">Email</h3>
// //                       <h2 className="text-blue-900 font-semibold break-words ">
// //                         {profileInfo.fileAndObjectTypeBean?.empResDTO?.emailId}{" "}
// //                       </h2>
// //                     </div>
// //                     <div className="relative left-4">
// //                       <h3 className="font-normal">Location</h3>
// //                       <h2 className="text-blue-900 font-semibold ">
// //                         {profileInfo.userDTO?.locationResDTO?.locationName}{" "}
// //                       </h2>
// //                     </div>
// //                   </div>
// //                   <div className="div2  flex space-x-52 pl-10 p-7 pb-10">
// //                     <div className="dept flex w-56 flex-col">
// //                       <h3 className="font-normal">Employment Status</h3>
// //                       <h2 className="text-green-500 font-semibold ">Active</h2>
// //                       <h2 className="text-green-500 font-semibold ">
// //                         {/* {
// //                     profileInfo.fileAndObjectTypeBean?.employmentStatus
// //                       .employmentStatus
// //                   } */}
// //                       </h2>
// //                     </div>
// //                   </div>
// //                 </div>
// //               </div>
// //             </div>

// //             {/* empcode behave as empId */}
// //             <Component empId={empId} />
// //           </div>
// //         </div>
// //       </div>
// //     </>
// //   );
// // }

// // export default ProfileInfonfoFile;

// import React, { useEffect, useState } from "react";
// import Component from "./Component";
// import axios from "axios";
// import Service from "../Service";
// import Swal from "sweetalert2";
// import AdminNavHead from "../../navbar/AdminNavHead";
// import HRNavHead from "../../navbar/HRNavHead";
// import NavHead from "../../Component/NavHead";
// import { useStoreManagerId } from "../GlobalStorage/ZustandStore";
// // import { useStoreManagerId } from "../GlobalStorage/ZustandStore";

// //Here we have written two dots"..Service" because we want to import the service file which is in the services folder.For fetching the nested or file within a sub folder we use double dots..

// //http://43.205.24.208:9031/api/getByProfile/5
// //this API will be used here.

// function ProfileInfonfoFile() {
//   const [profileInfo, setProfileInfo] = useState([]);
//   const [fileUrl, setFileUrl] = useState("");
//   const empId = localStorage.getItem("empId");
//   const [empData, setEmpData] = useState({});
//   const [userData, setUserData] = useState({});
//   const email = localStorage.getItem("email");
//   const fallbackImageUrl =
//     "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEhbRExVYjkvKlgz03fnIy3QN6HzcGDmE_3w&s";

//   useEffect(() => {
//     Service.getByProfileById(empId).then((response) => {
//       console.log("harsh kumar 1");
//       getEmployeeData();
//       setProfileInfo(response.data);
//     });
//   }, [empId]);

//   const getEmployeeData = () => {
//     console.log("harsh kumar 2");
//     axios
//       .get(`http://43.205.24.208:9020/employee/by/email/${email}`)
//       .then((response) => {
//         console.log(response?.data, "data...........................");

//         console.log(
//           response?.data?.fileAndObjectTypeBean?.empResDTO,
//           "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeemmmmmpData===========================================000000000000000"
//         );
//         setEmpData(response?.data?.fileAndObjectTypeBean?.empResDTO);
//         setUserData(
//           response.data.userDTO,
//           "fffffffffffffffffffffffffffffffffffffffff"
//         );
//         // const managerId = sessionStorage.setItem("ManagerId", profileInfo.fileAndObjectTypeBean?.empResDTO
//         //   ?.reportTo);

//         console.log(
//           "manageriiiiyuyudddd",
//           response?.data?.fileAndObjectTypeBean?.empResDTO?.empCode,
//           "===================================&&&&&&&&&&&&&&&&&&&&&&&&&==================000000000000000"
//         );
//         console.log("skills info");
//         console.log(response.data);
//         const { file, contentType } =
//           response.data.fileAndObjectTypeBean.fileAndContentTypeBean;

//         // Decode base64 string
//         const byteCharacters = atob(file);
//         const byteNumbers = new Array(byteCharacters.length);
//         for (let i = 0; i < byteCharacters.length; i++) {
//           byteNumbers[i] = byteCharacters.charCodeAt(i);
//         }
//         const byteArray = new Uint8Array(byteNumbers);
//         const blob = new Blob([byteArray], { type: contentType });

//         const url = URL.createObjectURL(blob);
//         setFileUrl(url);

//         console.log(response.data);
//       })
//       .catch((error) => console.log(error));
//   };

//   let roles = [];
//   try {
//     roles = JSON.parse(sessionStorage.getItem("role")) || [];
//   } catch (e) {
//     console.error("Error parsing roles from sessionStorage:", e);
//   }

//   // Function to check if the user has a specific role
//   const hasRole = (role) => roles.includes(role);

//   return (
//     <>
//       <div className="flex flex-row">
//         <div className="">
//           {hasRole("admin") && <AdminNavHead />}{" "}
//           {/* Admin-specific navigation */}
//           {hasRole("CMS Employee") && <HRNavHead />}{" "}
//           {/* HR-specific navigation */}
//           {hasRole("employee") && <NavHead />}{" "}
//           {/* Employee-specific navigation */}
//         </div>

//         <div className=" shadow-md sm:rounded-lg h-full ">
//           <div className=" bg-white-300">
//             <div className=" grid grid-cols-1 bg-white md:grid-cols-6 lg:grid-cols-10 pt-6 mt-6 shadow-lg border border-white py-6 rounded-md">
//               <div className="flex items-center justify-center col-span-1 lg:col-span-2 ml-0 lg:ml-5">
//                 <div className="col-span-1 lg:col-span-2 ">
//                   <div className="profile ml-8">
//                     <div className="img">
//                       <div className=" inline-block">
//                         {/* <img src="profile.jpeg" className='rounded-full h-40 w-40 ml-4' alt="Profile"></img> */}
//                         <img
//                           src={fileUrl || fallbackImageUrl} // Use fallback image if no image is available
//                           alt="Profile"
//                           className="w-40 h-40 mr-3 rounded-full mt-1"
//                         />
//                         {/* inserting image from backend. */}
//                         <div className="absolute bottom-2 right-5 h-6 w-6 bg-green-500 rounded-full flex items-center justify-center cursor-pointer">
//                           <svg
//                             className="h-4 w-4 text-green-500"
//                             fill="none"
//                             stroke="currentColor"
//                             viewBox="0 0 24 24"
//                             xmlns="http://www.w3.org/2000/svg"
//                           >
//                             <path
//                               stroke-linecap="round"
//                               stroke-linejoin="round"
//                               stroke-width="2"
//                               d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
//                             ></path>
//                             <path
//                               stroke-linecap="round"
//                               stroke-linejoin="round"
//                               stroke-width="2"
//                               d="M12 1a11 11 0 00-11 11c0 6.075 4.925 11 11 11s11-4.925 11-11A11 11 0 0012 1zm0 2a9 9 0 019 9c0 4.97-4.03 9-9 9s-9-4.03-9-9A9 9 0 0112 3z"
//                             ></path>
//                           </svg>
//                         </div>
//                       </div>
//                     </div>
//                     <div className="text">
//                       <h2 className="font-semibold text-sm text-blue-900 mx-8 pt-3 w-56 flex-col -ml-[5px]">
//                         {
//                           profileInfo.fileAndObjectTypeBean?.empResDTO
//                             ?.firstName
//                         }{" "}
//                       </h2>

//                       <p className="font-normal">
//                         {
//                           profileInfo.fileAndObjectTypeBean?.empResDTO
//                             ?.designationResDTO?.designationName
//                         }
//                       </p>
//                     </div>
//                   </div>
//                 </div>
//               </div>

//               {/* The second container */}
//               <div className="col-span-4 sm:-ml-5  left-8">
//                 <div className="div pt-8">
//                   <div className="div1  flex space-x-5 pl-10 p-7">
//                     <div className="emp w-56 flex-col ">
//                       <h3 className="font-normal text-normal">Employee ID</h3>
//                       <h2 className="text-blue-900 font-semibold ">
//                         {profileInfo.fileAndObjectTypeBean?.empResDTO?.empCode}{" "}
//                       </h2>
//                     </div>
//                     <div className=" right-10">
//                       <h3 className="font-normal  ">Contact No.</h3>
//                       <h2 className="text-blue-900 font-semibold ">
//                         {
//                           profileInfo.fileAndObjectTypeBean?.empResDTO
//                             ?.primaryContactNo
//                         }{" "}
//                       </h2>
//                     </div>
//                   </div>
//                   <div className="div2 flex space-x-5 pl-10 p-7 pb-10 ">
//                     <div className="dept flex w-56 flex-col">
//                       <h3 className="font-normal">Department</h3>
//                       <p className="font-semibold overflow-hidden text-blue-900">
//                         {
//                           profileInfo.fileAndObjectTypeBean?.empResDTO
//                             ?.mainDeptResDTO.mainDepartment
//                         }{" "}
//                       </p>
//                     </div>
//                     {/*  For Positioning an Element:"relative left-0" this property is used............... */}

//                     <div className=" right-10">
//                       <h3 className="font-normal">Reporting To</h3>
//                       <h2 className="text-blue-900 font-semibold">
//                         {
//                           profileInfo.fileAndObjectTypeBean?.empResDTO
//                             ?.reportingManager
//                         }{" "}
//                       </h2>
//                     </div>
//                   </div>
//                 </div>
//               </div>

//               {/* The third container */}
//               <div className="col-span-4 -ml-10 lg:-ml-24 -mt-10 lg:-mt-0  left-12">
//                 <div className="div pt-8">
//                   <div className="div1 flex space-x-5 pl-10 p-7">
//                     <div className="emp flex  flex-col">
//                       <h3 className="font-normal ">Email</h3>
//                       <h2 className="text-blue-900 font-semibold break-words ">
//                         {profileInfo.fileAndObjectTypeBean?.empResDTO?.emailId}{" "}
//                       </h2>
//                     </div>
//                     <div className=" left-4">
//                       <h3 className="font-normal">Location</h3>
//                       <h2 className="text-blue-900 font-semibold ">
//                         {profileInfo.userDTO?.locationResDTO?.locationName}{" "}
//                       </h2>
//                     </div>
//                   </div>
//                   <div className="div2  flex space-x-52 pl-10 p-7 pb-10">
//                     <div className="dept flex w-56 flex-col">
//                       <h3 className="font-normal">Employment Status</h3>
//                       <h2 className="text-green-500 font-semibold ">Active</h2>
//                       <h2 className="text-green-500 font-semibold ">
//                         {/* {
//                     profileInfo.fileAndObjectTypeBean?.employmentStatus
//                       .employmentStatus
//                   } */}
//                       </h2>
//                     </div>
//                   </div>
//                 </div>
//               </div>
//             </div>

//             {/* empcode behave as empId */}
//             <Component empId={empId} />
//           </div>
//         </div>
//       </div>
//     </>
//   );
// }

// export default ProfileInfonfoFile;

// import React, { useEffect, useState } from "react";
// import Component from "./Component";
// import axios from "axios";
// import Service from "../Service";
// import Swal from "sweetalert2";
// import AdminNavHead from "../../navbar/AdminNavHead";
// import HRNavHead from "../../navbar/HRNavHead";
// import NavHead from "../../Component/NavHead";
// import { useStoreManagerId } from "../GlobalStorage/ZustandStore";
// // import { useStoreManagerId } from "../GlobalStorage/ZustandStore";

// //Here we have written two dots"..Service" because we want to import the service file which is in the services folder.For fetching the nested or file within a sub folder we use double dots..

// //http://43.205.24.208:9031/api/getByProfile/5
// //this API will be used here.

// function ProfileInfonfoFile() {
//   const [profileInfo, setProfileInfo] = useState([]);
//   const [fileUrl, setFileUrl] = useState("");
//   const empId = localStorage.getItem("empId");
//   const [empData, setEmpData] = useState({});
//   const [userData, setUserData] = useState({});
//   const email = localStorage.getItem("email");
//   const fallbackImageUrl =
//     "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEhbRExVYjkvKlgz03fnIy3QN6HzcGDmE_3w&s";

//   useEffect(() => {
//     Service.getByProfileById(empId).then((response) => {
//       console.log("harsh kumar 1");
//       getEmployeeData();
//       setProfileInfo(response.data);
//     });
//   }, [empId]);

//   const getEmployeeData = () => {
//     console.log("harsh kumar 2");
//     axios
//       .get(`http://43.205.24.208:9020/employee/by/email/${email}`)
//       .then((response) => {
//         console.log(response?.data, "data...........................");

//         console.log(
//           response?.data?.fileAndObjectTypeBean?.empResDTO,
//           "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeemmmmmpData===========================================000000000000000"
//         );
//         setEmpData(response?.data?.fileAndObjectTypeBean?.empResDTO);
//         setUserData(
//           response.data.userDTO,
//           "fffffffffffffffffffffffffffffffffffffffff"
//         );
//         // const managerId = sessionStorage.setItem("ManagerId", profileInfo.fileAndObjectTypeBean?.empResDTO
//         //   ?.reportTo);

//         console.log(
//           "manageriiiiyuyudddd",
//           response?.data?.fileAndObjectTypeBean?.empResDTO?.empCode,
//           "===================================&&&&&&&&&&&&&&&&&&&&&&&&&==================000000000000000"
//         );
//         console.log("skills info");
//         console.log(response.data);
//         const { file, contentType } =
//           response.data.fileAndObjectTypeBean.fileAndContentTypeBean;

//         // Decode base64 string
//         const byteCharacters = atob(file);
//         const byteNumbers = new Array(byteCharacters.length);
//         for (let i = 0; i < byteCharacters.length; i++) {
//           byteNumbers[i] = byteCharacters.charCodeAt(i);
//         }
//         const byteArray = new Uint8Array(byteNumbers);
//         const blob = new Blob([byteArray], { type: contentType });

//         const url = URL.createObjectURL(blob);
//         setFileUrl(url);

//         console.log(response.data);
//       })
//       .catch((error) => console.log(error));
//   };

//   let roles = [];
//   try {
//     roles = JSON.parse(sessionStorage.getItem("role")) || [];
//   } catch (e) {
//     console.error("Error parsing roles from sessionStorage:", e);
//   }

//   // Function to check if the user has a specific role
//   const hasRole = (role) => roles.includes(role);

//   return (
//     <>
//       <div className="">
//         {hasRole("admin") && <AdminNavHead />} {/* Admin-specific navigation */}
//         {hasRole("CMS Employee") && <HRNavHead />}{" "}
//         {/* HR-specific navigation */}
//         {hasRole("employee") && <NavHead />}{" "}
//         {/* Employee-specific navigation */}
//         <div className="relative overflow-x-auto shadow-md sm:rounded-lg h-full w-full">
//           <div className=" ml-44 bg-white-300">
//             <div className=" grid grid-cols-1 bg-white md:grid-cols-6 lg:grid-cols-10 pt-6 mt-6 shadow-lg border border-white py-6 rounded-md">
//               <div className="flex items-center justify-center col-span-1 lg:col-span-2 ml-0 lg:ml-5">
//                 <div className="col-span-1 lg:col-span-2 ">
//                   <div className="profile ml-8">
//                     <div className="img">
//                       <div className="relative inline-block">
//                         {/* <img src="profile.jpeg" className='rounded-full h-40 w-40 ml-4' alt="Profile"></img> */}
//                         <img
//                           src={fileUrl || fallbackImageUrl} // Use fallback image if no image is available
//                           alt="Profile"
//                           className="w-40 h-40 mr-3 rounded-full mt-1"
//                         />
//                         {/* inserting image from backend. */}
//                         <div className="absolute bottom-2 right-5 h-6 w-6 bg-green-500 rounded-full flex items-center justify-center cursor-pointer">
//                           <svg
//                             className="h-4 w-4 text-green-500"
//                             fill="none"
//                             stroke="currentColor"
//                             viewBox="0 0 24 24"
//                             xmlns="http://www.w3.org/2000/svg"
//                           >
//                             <path
//                               stroke-linecap="round"
//                               stroke-linejoin="round"
//                               stroke-width="2"
//                               d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
//                             ></path>
//                             <path
//                               stroke-linecap="round"
//                               stroke-linejoin="round"
//                               stroke-width="2"
//                               d="M12 1a11 11 0 00-11 11c0 6.075 4.925 11 11 11s11-4.925 11-11A11 11 0 0012 1zm0 2a9 9 0 019 9c0 4.97-4.03 9-9 9s-9-4.03-9-9A9 9 0 0112 3z"
//                             ></path>
//                           </svg>
//                         </div>
//                       </div>
//                     </div>
//                     <div className="text">
//                       <h2 className="font-semibold text-sm text-blue-900 mx-8 pt-3 w-56 flex-col -ml-[5px]">
//                         {
//                           profileInfo.fileAndObjectTypeBean?.empResDTO
//                             ?.firstName
//                         }{" "}
//                       </h2>

//                       <p className="font-normal">
//                         {
//                           profileInfo.fileAndObjectTypeBean?.empResDTO
//                             ?.designationResDTO?.designationName
//                         }
//                       </p>
//                     </div>
//                   </div>
//                 </div>
//               </div>

//               {/* The second container */}
//               <div className="col-span-4 sm:-ml-5 relative left-8">
//                 <div className="div pt-8">
//                   <div className="div1  flex space-x-5 pl-10 p-7">
//                     <div className="emp w-56 flex-col ">
//                       <h3 className="font-normal text-normal">Employee ID</h3>
//                       <h2 className="text-blue-900 font-semibold ">
//                         {profileInfo.fileAndObjectTypeBean?.empResDTO?.empCode}{" "}
//                       </h2>
//                     </div>
//                     <div className="relative right-10">
//                       <h3 className="font-normal  ">Contact No.</h3>
//                       <h2 className="text-blue-900 font-semibold ">
//                         {
//                           profileInfo.fileAndObjectTypeBean?.empResDTO
//                             ?.primaryContactNo
//                         }{" "}
//                       </h2>
//                     </div>
//                   </div>
//                   <div className="div2 flex space-x-5 pl-10 p-7 pb-10 ">
//                     <div className="dept flex w-56 flex-col">
//                       <h3 className="font-normal">Department</h3>
//                       <p className="font-semibold overflow-hidden text-blue-900">
//                         {
//                           profileInfo.fileAndObjectTypeBean?.empResDTO
//                             ?.mainDeptResDTO.mainDepartment
//                         }{" "}
//                       </p>
//                     </div>
//                     {/*  For Positioning an Element:"relative left-0" this property is used............... */}

//                     <div className="relative right-10">
//                       <h3 className="font-normal">Reporting To</h3>
//                       <h2 className="text-blue-900 font-semibold">
//                         {
//                           profileInfo.fileAndObjectTypeBean?.empResDTO
//                             ?.reportingManager
//                         }{" "}
//                       </h2>
//                     </div>
//                   </div>
//                 </div>
//               </div>

//               {/* The third container */}
//               <div className="col-span-4 -ml-10 lg:-ml-24 -mt-10 lg:-mt-0 sm:relative left-12">
//                 <div className="div pt-8">
//                   <div className="div1 flex space-x-5 pl-10 p-7">
//                     <div className="emp flex  flex-col">
//                       <h3 className="font-normal ">Email</h3>
//                       <h2 className="text-blue-900 font-semibold break-words ">
//                         {profileInfo.fileAndObjectTypeBean?.empResDTO?.emailId}{" "}
//                       </h2>
//                     </div>
//                     <div className="relative left-4">
//                       <h3 className="font-normal">Location</h3>
//                       <h2 className="text-blue-900 font-semibold ">
//                         {profileInfo.userDTO?.locationResDTO?.locationName}{" "}
//                       </h2>
//                     </div>
//                   </div>
//                   <div className="div2  flex space-x-52 pl-10 p-7 pb-10">
//                     <div className="dept flex w-56 flex-col">
//                       <h3 className="font-normal">Employment Status</h3>
//                       <h2 className="text-green-500 font-semibold ">Active</h2>
//                       <h2 className="text-green-500 font-semibold ">
//                         {/* {
//                     profileInfo.fileAndObjectTypeBean?.employmentStatus
//                       .employmentStatus
//                   } */}
//                       </h2>
//                     </div>
//                   </div>
//                 </div>
//               </div>
//             </div>

//             {/* empcode behave as empId */}
//             <Component empId={empId} />
//           </div>
//         </div>
//       </div>
//     </>
//   );
// }

// export default ProfileInfonfoFile;

import React, { useEffect, useState } from "react";
import Component from "./Component";
import axios from "axios";
import Service from "../Service";
import Swal from "sweetalert2";
import AdminNavHead from "../../navbar/AdminNavHead";
import HRNavHead from "../../navbar/HRNavHead";
import NavHead from "../../Component/NavHead";
import { useStoreManagerId } from "../GlobalStorage/ZustandStore";
// import { useStoreManagerId } from "../GlobalStorage/ZustandStore";

//Here we have written two dots"..Service" because we want to import the service file which is in the services folder.For fetching the nested or file within a sub folder we use double dots..

//http://43.205.24.208:9031/api/getByProfile/5
//this API will be used here.

function ProfileInfonfoFile() {
  const [profileInfo, setProfileInfo] = useState([]);
  const [fileUrl, setFileUrl] = useState("");
  const empId = localStorage.getItem("empId");
  const [empData, setEmpData] = useState({});
  const [userData, setUserData] = useState({});
  const email = localStorage.getItem("email");
  const fallbackImageUrl =
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEhbRExVYjkvKlgz03fnIy3QN6HzcGDmE_3w&s";

  useEffect(() => {
    Service.getByProfileById(empId).then((response) => {
      console.log("harsh kumar 1");
      getEmployeeData();
      setProfileInfo(response.data);
    });
  }, [empId]);

  const getEmployeeData = () => {
    console.log("harsh kumar 2");
    axios
      .get(`http://43.205.24.208:9020/employee/by/email/${email}`)
      .then((response) => {
        console.log(response?.data, "data...........................");

        console.log(
          response?.data?.fileAndObjectTypeBean?.empResDTO,
          "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeemmmmmpData===========================================000000000000000"
        );
        setEmpData(response?.data?.fileAndObjectTypeBean?.empResDTO);
        setUserData(
          response.data.userDTO,
          "fffffffffffffffffffffffffffffffffffffffff"
        );
        // const managerId = sessionStorage.setItem("ManagerId", profileInfo.fileAndObjectTypeBean?.empResDTO
        //   ?.reportTo);

        console.log(
          "manageriiiiyuyudddd",
          response?.data?.fileAndObjectTypeBean?.empResDTO?.empCode,
          "===================================&&&&&&&&&&&&&&&&&&&&&&&&&==================000000000000000"
        );
        console.log("skills info");
        console.log(response.data);
        const { file, contentType } =
          response.data.fileAndObjectTypeBean.fileAndContentTypeBean;

        // Decode base64 string
        const byteCharacters = atob(file);
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
          byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], { type: contentType });

        const url = URL.createObjectURL(blob);
        setFileUrl(url);

        console.log(response.data);
      })
      .catch((error) => console.log(error));
  };

  let roles = [];
  try {
    roles = JSON.parse(sessionStorage.getItem("role")) || [];
  } catch (e) {
    console.error("Error parsing roles from sessionStorage:", e);
  }

  // Function to check if the user has a specific role
  const hasRole = (role) => roles.includes(role);

  return (
    <>
      <div className="flex flex-row">
        <div className="">
          {hasRole("admin") && <AdminNavHead />}{" "}
          {/* Admin-specific navigation */}
          {hasRole("CMS Employee") && <HRNavHead />}{" "}
          {/* HR-specific navigation */}
          {hasRole("employee") && <NavHead />}{" "}
          {/* Employee-specific navigation */}
        </div>

        <div className="">
          <div className="bg-white p-5">
            <div className="grid md:grid-cols-12 mt-7 pt-10 gap-10">
              <div className="col-span-3">
                <div className="">
                  <div className="img">
                    <div className=" inline-block">
                      <img
                        src={fileUrl || fallbackImageUrl} // Use fallback image if no image is available
                        alt="Profile"
                        className="w-40 h-40 mr-3 rounded-full mt-1"
                      />
                      {/* inserting image from backend. */}
                      <div className=" h-6 w-6 bg-green-500 rounded-full flex items-center justify-center cursor-pointer">
                        <svg
                          className="h-4 w-4 text-green-500"
                          fill="none"
                          stroke="currentColor"
                          viewBox="0 0 24 24"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <path
                            stroke-linecap="round"
                            stroke-linejoin="round"
                            stroke-width="2"
                            d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
                          ></path>
                          <path
                            stroke-linecap="round"
                            stroke-linejoin="round"
                            stroke-width="2"
                            d="M12 1a11 11 0 00-11 11c0 6.075 4.925 11 11 11s11-4.925 11-11A11 11 0 0012 1zm0 2a9 9 0 019 9c0 4.97-4.03 9-9 9s-9-4.03-9-9A9 9 0 0112 3z"
                          ></path>
                        </svg>
                      </div>
                    </div>
                  </div>
                  <div className="text">
                    <h2 className="font-semibold text-sm text-blue-900 mx-8 pt-3 w-56 flex-col -ml-[5px]">
                      {profileInfo.fileAndObjectTypeBean?.empResDTO?.firstName}{" "}
                    </h2>

                    <p className="font-normal">
                      {
                        profileInfo.fileAndObjectTypeBean?.empResDTO
                          ?.designationResDTO?.designationName
                      }
                    </p>
                  </div>
                </div>
              </div>

              {/* The second container */}
              <div className="col-span-4 mt-10">
                <div className="">
                  <div className="flex space-x-[120px]">
                    <div className="flex flex-col ">
                      <h3 className="font-normal text-normal">Employee ID</h3>
                      <h2 className="text-blue-900 font-semibold ">
                        {profileInfo.fileAndObjectTypeBean?.empResDTO?.empCode}{" "}
                      </h2>
                    </div>
                    <div className="">
                      <h3 className="font-normal flex flex-col ">
                        Contact No.
                      </h3>
                      <h2 className="text-blue-900 font-semibold ">
                        {
                          profileInfo.fileAndObjectTypeBean?.empResDTO
                            ?.primaryContactNo
                        }{" "}
                      </h2>
                    </div>
                  </div>
                  <div className="div2 flex space-x-[120px] py-5">
                    <div className="flex flex-col">
                      <h3 className="font-normal">Department</h3>
                      <p className="font-semibold overflow-hidden text-blue-900">
                        {
                          profileInfo.fileAndObjectTypeBean?.empResDTO
                            ?.mainDeptResDTO.mainDepartment
                        }{" "}
                      </p>
                    </div>
                    {/*  For Positioning an Element:"relative left-0" this property is used............... */}

                    <div className="">
                      <h3 className="font-normal">Reporting To</h3>
                      <h2 className="text-blue-900 font-semibold overflow-hidden">
                        {
                          profileInfo.fileAndObjectTypeBean?.empResDTO
                            ?.reportingManager
                        }{" "}
                      </h2>
                    </div>
                  </div>
                </div>
              </div>

              {/* The third container */}
              <div className="col-span-4 mt-10">
                <div className="div">
                  <div className="div1 flex space-x-5 ">
                    <div className="emp flex  flex-col">
                      <h3 className="font-normal ">Email</h3>
                      <h2 className="text-blue-900 font-semibold break-words ">
                        {profileInfo.fileAndObjectTypeBean?.empResDTO?.emailId}{" "}
                      </h2>
                    </div>
                    <div className=" left-4">
                      <h3 className="font-normal flex flex-col">Location</h3>
                      <h2 className="text-blue-900 font-semibold ">
                        {profileInfo.userDTO?.locationResDTO?.locationName}{" "}
                      </h2>
                    </div>
                  </div>
                  <div className="div2  flex space-x-52 py-5">
                    <div className="dept flex w-56 flex-col">
                      <h3 className="font-normal flex flex-col">
                        Employment Status
                      </h3>
                      <h2 className="text-green-500 font-semibold ">Active</h2>
                      <h2 className="text-green-500 font-semibold ">
                        {/* {
                    profileInfo.fileAndObjectTypeBean?.employmentStatus
                      .employmentStatus
                  } */}
                      </h2>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            {/* empcode behave as empId */}
            <Component empId={empId} />
          </div>
        </div>
      </div>
    </>
  );
}

export default ProfileInfonfoFile;
