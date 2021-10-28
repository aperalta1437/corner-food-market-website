import { createSlice } from "@reduxjs/toolkit";
import JSEncrypt from "jsencrypt";

const initialStateValue = {
  isAuthenticated: false,
  accessToken: "",
  email: "",
  rsaEncryptedPassword: "",
};

let rsaEncrypter = new JSEncrypt();

export const adminFirstFactorAuthenticationSlice = createSlice({
  name: "adminFirstFactorAuthentication",
  initialState: {
    value: initialStateValue,
  },
  reducers: {
    setFirstFactorAuthentication: (state, action) => {
      state.value.isAuthenticated = action.payload.isAuthenticated;
      state.value.accessToken = action.payload.accessToken;
      state.value.email = action.payload.email;
      rsaEncrypter.setPublicKey(action.payload.rsaPublicKey);
      state.value.rsaEncryptedPassword = rsaEncrypter.encrypt(
        action.payload.password
      );
    },
    resetFirstFactorAuthentication: (state) => {
      state.value = initialStateValue;
    },
  },
});

export const { setFirstFactorAuthentication, resetFirstFactorAuthentication } =
  adminFirstFactorAuthenticationSlice.actions;

export default adminFirstFactorAuthenticationSlice.reducer;
