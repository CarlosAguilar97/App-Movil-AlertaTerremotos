module.exports = {
  root: true,
  env: {
    es6: true,
    node: true,
  },
  extends: [
    "eslint:recommended",
    "google",
    "plugin:prettier/recommended",
  ],
  rules: {
    quotes: ["error", "double"],
  },
};
