/** @type {import('tailwindcss').Config} */
// const withMT = require("@material-tailwind/react/utils/withMT");


module.exports = {
  rules: [
    // other rules...
    {
      test: /\.js$/,
      enforce: 'pre',
      exclude: /node_modules\/flowbite/,
      use: ['source-map-loader'],
    },
  ],

  content: [
    './src/**/*.{js,jsx,ts,tsx}',
    'node_modules/flowbite-react/**/*.{js,jsx,ts,tsx}',
    "path-to-your-node_modules/@material-tailwind/react/components/**/*.{js,ts,jsx,tsx}",
    "path-to-your-node_modules/@material-tailwind/react/theme/components/**/*.{js,ts,jsx,tsx}",
    "./app/**/*.{js,ts,jsx,tsx}",
    "./pages/**/*.{js,ts,jsx,tsx}",
    "./components/**/*.{js,ts,jsx,tsx}",
    "./node_modules/tw-elements-react/dist/js/**/*.js",
  
    
  ],
  theme: {
    extend: { 
      gridTemplateColumns: {
        sidebar: "300px auto", //for sidebar layout
        "sidebar-collapsed": "64px auto", //for collapsed sidebar layout
      },
      // gridTemplateColumns: {
      //   '40-60': '40% 60%',
      //   '3': 'repeat(2, minmax(0, 1fr))',
      // },
      colors:{
        current: "var(--text-color)"
      },
      textColor:{
        skin:{
          base: "var(--text-color)",
        },
      },
      backgroundColor:{
        skin:{
          fill: "var(--fill-color)",
          indicator: "var(--fill-color-indicator)",
        },
      },
      gradientColorStops:{
        skin:{
          hue:"var(--fill-color)",
        }
      },
    },
  },
  plugins: [
    require("tw-elements-react/dist/plugin.cjs"),
    require("tailwind-scrollbar"),
    // Add other plugins here if needed...
  ],
}
