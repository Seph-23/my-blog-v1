import { createSlice } from '@reduxjs/toolkit';

interface Member {
  email: string | null,
  nickname: string | null;
}

const initialState: Member = {
  email: null,
  nickname: null,
};

const memberSlice = createSlice({
  name: "member",
  initialState,
  reducers: {
    setMemberInfo: (state, action) => {
      state.email = action.payload.email;
      state.nickname = action.payload.nickname;  
    },
  },
});

export const { setMemberInfo } = memberSlice.actions;
export default memberSlice.reducer;
