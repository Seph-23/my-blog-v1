import { configureStore } from "@reduxjs/toolkit";
import memberReducer from "./slice/MemberSlice";
import authReducer from "./slice/AuthSlice";

const store = configureStore({
  reducer: {
    member: memberReducer,
    auth: authReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export default store;
