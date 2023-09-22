import { createSlice } from "@reduxjs/toolkit";

interface AuthState {
  grantType: string | null,
  accessToken: string | null,
  accessTokenExpirationTime: Date | null,
  refreshToken: string | null,
  refreshTokenExpirationTime: Date | null,
  errorMessage: string | null;
}

const initialState: AuthState = {
  grantType: null,
  accessToken: null,
  accessTokenExpirationTime: null,
  refreshToken:null,
  refreshTokenExpirationTime: null,
  errorMessage: null,
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    setAuthState: (state, action) => {
      state.grantType = action.payload.grantType;
      state.accessToken = action.payload.accessToken;
      state.accessTokenExpirationTime = action.payload.accessTokenExpirationTime;
      state.refreshToken = action.payload.refreshToken;
      state.refreshTokenExpirationTime = action.payload.refreshTokenExpirationTime;
      state.errorMessage = null;
    },
    setError: (state, action) => {
      state.grantType = null;
      state.accessToken = null;
      state.accessTokenExpirationTime = null;
      state.refreshToken = null;
      state.refreshTokenExpirationTime = action.payload.errorMessage;
    },
    clearError: (state) => {
      state.errorMessage = null;
    },
  },
});

export const { setAuthState, setError, clearError } = authSlice.actions;
export default authSlice.reducer;
