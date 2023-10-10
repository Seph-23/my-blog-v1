import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import axios from "axios";

const Home = () => {
  const dispatch = useDispatch();
  const [testFlag, setTestFlag] = useState("");
  const accessToken = useSelector((state: any) => state.auth.accessToken);

  useEffect(() => {
    axios.get(
      "http://localhost:8080/api/home",
      {
        headers: {
          Authorization: `Bearer ${accessToken}`
        },
        withCredentials: true,
      }
    ).then((response) => {
      setTestFlag(response?.data);
      console.log(response);
    }).catch((error) => {
      console.error(error);
    });
  }, [testFlag, dispatch])

  return (
    <div id="container">
      <span>{testFlag}</span>
    </div>
  )
};

export default Home;