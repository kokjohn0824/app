/** @type {import('tailwindcss').Config} */
module.exports = {
  mode: process.env.NODE_ENV ? "jit" : undefined,
  purge: ["./src/**/*.html", "./src/**/*.js"],
  darkMode: false,
  content: [],
  theme: {
    extend: {},
  },
  plugins: [],
};
