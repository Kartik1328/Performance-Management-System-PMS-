import React from 'react';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'flowbite';
import { ThemeProvider } from "@material-tailwind/react";
import {createRoot} from 'react-dom/client';
import Footer from "./Footer/Footer";

const container = document.getElementById('root');
const root = createRoot(container); // createRoot(container!) if you use TypeScript
root.render(
  <>
    <React.StrictMode>
      <ThemeProvider>
        <div className="flex flex-col min-h-screen">
          <App />
          <Footer className="mt-auto" /> {/* Footer at the bottom */}
        </div>
      </ThemeProvider>
    </React.StrictMode>
  </>
);

reportWebVitals();


// import React from 'react';
// import ReactDOM from 'react-dom';
// import App from './components/App';
// import { MsalProvider } from '@azure/msal-react';
// import { msalInstance } from './msl/authConfig.js';

// ReactDOM.render(
//   <React.StrictMode>
//     <MsalProvider instance={msalInstance}>
//       <App />
//     </MsalProvider>
//   </React.StrictMode>,
//   document.getElementById('root')
// );