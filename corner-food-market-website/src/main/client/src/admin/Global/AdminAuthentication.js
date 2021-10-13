import { createSlice } from "@reduxjs/toolkit";

export const adminAuthenticationSlice = createSlice({
  name: "adminAuthentication",
  initialState: {
    value: { isAuthenticated: false, accessToken: "" },
  },
  reducers: {
    setAuthentication: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { setAuthentication } = adminAuthenticationSlice.actions;

export default adminAuthenticationSlice.reducer;
