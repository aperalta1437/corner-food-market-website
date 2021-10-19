import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = { isAuthenticated: false, accessToken: "" };

export const adminAuthenticationSlice = createSlice({
  name: "adminAuthentication",
  initialState: {
    value: initialStateValue,
  },
  reducers: {
    setAuthentication: (state, action) => {
      state.value = action.payload;
    },
    resetAuthentication: (state) => {
      state.value = initialStateValue;
    }
  },
});

export const { setAuthentication, resetAuthentication } = adminAuthenticationSlice.actions;

export default adminAuthenticationSlice.reducer;
