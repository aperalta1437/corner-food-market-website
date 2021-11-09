import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = { isAuthenticated: false, accessToken: "" };

export const authenticationSlice = createSlice({
  name: "authentication",
  initialState: {
    value: initialStateValue,
  },
  reducers: {
    setAuthentication: (state, action) => {
      state.value = action.payload;
    },
    resetAuthentication: (state) => {
      state.value = initialStateValue;
    },
  },
});

export const { setAuthentication, resetAuthentication } =
  authenticationSlice.actions;

export default authenticationSlice.reducer;
