import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = { isLoading: false };

export const adminHttpResponseLoaderGlobalStateSlice = createSlice({
  name: "adminHttpResponseLoaderGlobalState",
  initialState: {
    value: initialStateValue,
  },
  reducers: {
    flipIsLoading: (state) => {
      state.value.isLoading = !state.value.isLoading;
    },
  },
});

export const { flipIsLoading } =
  adminHttpResponseLoaderGlobalStateSlice.actions;

export default adminHttpResponseLoaderGlobalStateSlice.reducer;
