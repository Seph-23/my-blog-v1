import React, { useEffect } from "react";
import axios from "axios";
import { useDispatch } from "react-redux";
import { setMemberInfo } from "../redux/slice/MemberSlice";

interface MemberInfoFetcherProps {
  accessToken: string;
}

const MemberInfoFetcher: React.FC<MemberInfoFetcherProps> = ({ accessToken }) => {
  const dispatch = useDispatch();

  useEffect(() => {
    axios.get(
      "http://localhost:8080/api/member",
      {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        }
      }).then((response) => {
        console.log(response);
        dispatch(setMemberInfo(response.data));
      }).catch((error) => {
        console.log(error);
      });
  }, [accessToken, dispatch]);

  return null;
};

export default MemberInfoFetcher;