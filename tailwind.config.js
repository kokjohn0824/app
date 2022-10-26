/** @type {import('tailwindcss').Config} */
module.exports = {
  prefix: "tw-",
  mode: process.env.NODE_ENV ? "jit" : undefined,
  content: ["./src/**/*.html", "./src/**/*.js"],
  theme: {
    extend: {},
  },
  plugins: [],
};
