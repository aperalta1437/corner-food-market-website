import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = { isLoading: true };

export const httpResponseLoaderGlobalStateSlice = createSlice({
  name: "httpResponseLoaderGlobalState",
  initialState: {
    value: initialStateValue,
  },
  reducers: {
    flipIsLoading: (state) => {
      state.value.isLoading = !state.value.isLoading;
    },
  },
});

export const { flipIsLoading } = httpResponseLoaderGlobalStateSlice.actions;

export default httpResponseLoaderGlobalStateSlice.reducer;
