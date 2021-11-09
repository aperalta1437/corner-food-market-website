import { createSlice } from "@reduxjs/toolkit";
import JSEncrypt from "jsencrypt";

const initialStateValue = {
  isAuthenticated: false,
  accessToken: "",
  base64RsaEncryptedPassword: "",
};

let rsaEncrypter = new JSEncrypt();

export const firstFactorAuthenticationSlice = createSlice({
  name: "firstFactorAuthentication",
  initialState: {
    value: initialStateValue,
  },
  reducers: {
    setFirstFactorAuthentication: (state, action) => {
      state.value.isAuthenticated = action.payload.isAuthenticated;
      state.value.accessToken = action.payload.accessToken;

      rsaEncrypter.setPublicKey(action.payload.base64RsaPublicKey);
      state.value.base64RsaEncryptedPassword = rsaEncrypter.encrypt(
        action.payload.password
      );
    },
    resetFirstFactorAuthentication: (state) => {
      state.value = initialStateValue;
    },
  },
});

export const { setFirstFactorAuthentication, resetFirstFactorAuthentication } =
  firstFactorAuthenticationSlice.actions;

export default firstFactorAuthenticationSlice.reducer;
