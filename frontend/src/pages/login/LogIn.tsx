import React, { useCallback, useEffect, useState } from "react";
import { Button, Form, Header, Input, Label, LinkContainer, Error } from "../signup/SignUpStyles";
import { Link, useNavigate } from "react-router-dom";
import useInput from "../../hooks/useInputs";
import axios from "axios";
import { useDispatch } from "react-redux";
import { setAuthState, setError, clearError } from "../../redux/slice/AuthSlice";
import MemberInfoFetcher from "../../components/MemberInfoFetcher";
import { useSelector } from "react-redux";

const LogIn = () => {
  const dispatch = useDispatch();
  const accessToken = useSelector((state: any) => state.auth.accessToken);

  const [logInError, setLogInError] = useState(false);
  const [email, onChangeEmail] = useInput('');
  const [password, onChangePassword] = useInput('');

  const onSubmit = useCallback((e: any) => {
    e.preventDefault();

    axios.post(
      "http://localhost:8080/api/login",
      { 
        email, 
        password, 
        role: "USER",
        memberType: "BLOG", 
      },
      { withCredentials: true }
    )
    .then((response) => {
      console.log(response);
      const { 
        grantType, 
        accessToken, 
        accessTokenExpirationTime, 
        refreshToken, 
        refreshTokenExpirationTime 
      } = response.data;
      dispatch(setAuthState({ 
        grantType, 
        accessToken, 
        accessTokenExpirationTime, 
        refreshToken, 
        refreshTokenExpirationTime
      }));
    }).catch((error) => {
      console.log(error);
    });
  }, [email, password, dispatch]);

  return (
    <div id="container">
      {accessToken && <MemberInfoFetcher accessToken={accessToken} />}
      <Header>Sleact</Header>
      <Form onSubmit={onSubmit}>
        <Label id="email-label">
          <span>이메일 주소</span>
          <div>
            <Input type="email" id="email" name="email" value={email} onChange={onChangeEmail} />
          </div>
        </Label>
        <Label id="password-label">
          <span>비밀번호</span>
          <div>
            <Input type="password" id="password" name="password" value={password} onChange={onChangePassword} />
          </div>
          {logInError && <Error>이메일과 비밀번호 조합이 일치하지 않습니다.</Error>}
        </Label>
        <Button type="submit">로그인</Button>
      </Form>
      <LinkContainer>
        아직 회원이 아니신가요?&nbsp;
        <Link to="/signup">회원가입 하러가기</Link>
      </LinkContainer>
    </div>
  );
};

export default LogIn;