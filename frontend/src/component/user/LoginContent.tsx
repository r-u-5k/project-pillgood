import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import googleIcon from "@/images/google_icon.png";
import kakaoIcon from "@/images/kakao_icon.png";
import naverIcon from "@/images/naver_icon.png";
import { userState } from "@/recoil/atoms.ts";
import React, { useEffect, useRef, useState } from "react";
import { useCookies } from "react-cookie";
import { FiUser } from "react-icons/fi";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useRecoilState } from "recoil";
import { Modal } from "semantic-ui-react";
import { v4 as uuidv4 } from 'uuid';
import * as userApi from "../../api/userApi.ts";
import { naverLoginUser } from "../../api/userApi.ts";

export const LoginContent = () => {
  const [loginUser, setLoginUser] = useRecoilState(userState);
  const formRef = useRef<HTMLFormElement | null>(null);
  const emailRef = useRef<HTMLInputElement | null>(null);
  const passwordRef = useRef<HTMLInputElement | null>(null);
  const [open, setOpen] = React.useState(false);
  const [user, setUser] = useState({
    email: "",
    password: "",
  });
  const [errors, setErrors] = useState({
    email: "",
    password: "",
  });

  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setUser({
      ...user,
      [name]: value,
    });
    setErrors({
      ...errors,
      [name]: value
        ? ""
        : `${name === "email" ? "이메일" : "비밀번호"}를 입력해주세요.`,
    });
  };

  useEffect(() => {
    if (errors.email) {
      emailRef.current?.focus();
    } else if (errors.password) {
      passwordRef.current?.focus();
    }
  }, [errors]);

  const userLoginAction = async (e: React.FormEvent) => {
    e.preventDefault();

    const newErrors = {
      email: user.email ? "" : "이메일을 입력해주세요.",
      password: user.password ? "" : "비밀번호를 입력해주세요.",
    };
    setErrors(newErrors);

    if (newErrors.email || newErrors.password) {
      return;
    }

    try {
      const responseObject = await userApi.userLoginActionToken(user);
      if (
        responseObject &&
        responseObject.loginUser &&
        responseObject.loginUser.token
      ) {
        setLoginUser(responseObject.loginUser);
        localStorage.setItem("jwtToken", responseObject.loginUser.token);
        setOpen(false);
        navigate("/");
      } else {
        toast.error("아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.");
      }
    } catch (error) {
      toast.error("아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.");
    }
  };

  const kakaoLoginAction = async (e: React.FormEvent) => {
    e.preventDefault();
    location.href =
      "https://kauth.kakao.com/oauth/authorize?client_id=d2d11f66bbfdfde21c72cb215e3985c0&redirect_uri=http://192.168.15.3:8080/kakao_login_action&response_type=code";
  };

  const naverLoginAction = async (e: React.FormEvent) => {
    e.preventDefault();
    const state = uuidv4();
    localStorage.setItem('naver_state', state);
    document.cookie = `naver_state=${state}; path=/`;
    location.href = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=BUKLnjV3aHCYUsy7MKOj&redirect_uri=http://192.168.15.3:8080/naver_login_action&state=${state}`;
  };

  const [cookies] = useCookies(['authorize_access_token']);

  useEffect(() => {
    const login = async () => {
      try {
        const naverToken = cookies.authorize_access_token;
        if (naverToken) {
          const response = await naverLoginUser(naverToken);
          setLoginUser(response);
          localStorage.setItem('jwtToken', response.token);
          console.log(response.token);
          navigate('/');
        }
      } catch (error) {
        console.error('Error during login:', error);
      }
    };

    login();
  }, [cookies, setLoginUser, navigate]);

  return (
    <Modal
      style={{
        position: "fixed",
        top: "50%",
        left: "50%",
        transform: "translate(-50%, -50%)",
      }}
      centered={true}
      open={open}
      onClose={() => setOpen(false)}
      onOpen={() => setOpen(true)}
      trigger={
        <span className="flex items-center font-Pretendard text-md text-left tracking-tight text-gray-700 cursor-pointer font-semibold">
          <FiUser
            size="27"
            className="ml-[10px] cursor-pointer font-bold hover:text-blue-600"
          />
        </span>
      }
    >
      <div className="h-[1000px] w-[460px] mx-auto py-[250px]">
        <ToastContainer
          position="top-center"
          autoClose={1000}
          hideProgressBar={true}
          closeOnClick={true}
          pauseOnHover={false}
          limit={1}
        />
        <div className="w-[460px] h-[350px] border-[1px] mb-[20px] border-gray-400 rounded-xl p-[13px]">
          <form ref={formRef} onSubmit={userLoginAction}>
            <p className="w-full h-[70px] border-b-[1px] text-center pt-[15px]">
              계정으로 로그인
            </p>
            <div className="mt-[10px]">
              <Input
                ref={emailRef}
                type="email"
                placeholder="이메일을 입력하세요."
                className="mt-[10px] rounded-xl"
                name="email"
                onChange={handleChange}
              />
              {errors.email && (
                <p className="text-red-500 text-xs rounded mt-[5px] ml-[5px]">
                  {errors.email}
                </p>
              )}
            </div>
            <div className="mt-[10px]">
              <Input
                ref={passwordRef}
                type="password"
                placeholder="비밀번호를 입력하세요."
                className="mt-[10px] rounded-xl"
                name="password"
                onChange={handleChange}
              />
              {errors.password && (
                <p className="text-red-500 text-xs mt-[5px] ml-[5px]">
                  {errors.password}
                </p>
              )}
            </div>
            <Button
              variant="default"
              className="w-full mt-[20px] rounded-xl bg-gray-900 h-[40px] text-white hover:bg-gray-900"
              onClick={userLoginAction}
              disabled={!!errors.email || !!errors.password}
            >
              로그인
            </Button>

        
              <Link to="/join">
                <Button
                  variant="outline"
                  className="w-full mt-[10px] border-gray-500 rounded-xl border-[1px] h-[40px]"
                  onClick={() => {
                    navigate("/join");
                  }}
                >
                  회원가입
                </Button>
              </Link>
              <nav className="flex flex-row pt-[20px]">
                <p className="w-[50%] text-[12px] flex justify-end pr-[5px]">
                  <Link to="/find/id">아이디 찾기</Link>
                </p>
                <p className="w-[50%] text-[12px] pl-[5px] pl-">
                  <Link to="/find/password">비밀번호 찾기</Link>
                </p>
              </nav>
            </form>
          </div>

          <div className="flex flex-row mb-[15px]">
            <span className="w-[45%] h-[10px] border-b-[1px] border-gray-400"></span>
            <p className="w-[10%] text-[13px] text-center text-gray-400">or</p>
            <span className="w-[45%] h-[10px] border-b-[1px] border-gray-400"></span>
          </div>

          <button className="w-[458px] h-[50px] bg-[#ffeb00] text-[#3c1e1e] rounded-xl flex justify-center items-center mb-[10px]" onClick={kakaoLoginAction}>
            <img src={kakaoIcon} alt="" className="w-[30px] pr-[10px]" />
            카카오로 로그인
          </button>
          <button className="w-[458px] h-[50px] bg-[#27d34a] text-[#ffffff] rounded-xl flex justify-center items-center mb-[10px]" onClick={naverLoginAction}>
            <img src={naverIcon} alt="" className="w-[30px] pr-[10px]" />
            네이버로 로그인
          </button>
          <button className="w-[458px] h-[50px] bg-[#ffffff] text-[#000000] rounded-xl flex justify-center items-center border-[1px] border-gray-400">
            <img src={googleIcon} alt="" className="w-[30px] pr-[10px]" />
            구글로 로그인
          </button>
        </div>

        
   
    </Modal>
  );
};
