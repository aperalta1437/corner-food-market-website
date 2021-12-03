export const LoginProcessIssueEnum = Object.freeze({
  EXPIRED_SESSION: {
    name: "EXPIRED_SESSION",
    message: "Your previous session expired",
  },
  REDIRECTED: {
    name: "REDIRECTED",
    message: "You MUST log in to access the account",
  },
  FAILED_LOGIN: {
    name: "FAILED_LOGIN",
    message:
      "The email and/or password you provided don't match any existing account",
  },
});
