// /** @type {import('tailwindcss').Config} */
// export default {
//    content: ["./src/main/resources/**/*.{html,js}"],
    
//   theme: {
//     extend: {},
//   },
//   plugins: [],
//   darkMode:"selector",
// };

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './scm/src/main/resources/templates/**/*.html',
    './scm/src/main/resources/static/js/**/*.js',
  ],
  theme: {
    extend: {},
  },
  plugins: [
    require('flowbite/plugin')  // <--- required for Flowbite components
  ],
}


