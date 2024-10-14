import { Link, useLocation, useNavigate } from "react-router-dom";

import { logoutAction } from "@/api/userApi";
import { orderState, userState } from "@/recoil/atoms";
import { useCookies } from "react-cookie";
import { FiUser } from "react-icons/fi";
import { IoLogOutOutline } from "react-icons/io5";
import { PiBagSimple } from "react-icons/pi";
import { useRecoilState } from "recoil";
import logo from "../../images/logo-orange.png";
import { SurveyContent } from "../user/SurveyContent";
import { LoginContent } from "../user/LoginContent";
import { SurveyContent2 } from "../user/SurveyContent2";
import { toast } from "react-toastify";
import { useEffect, useState } from "react";
import * as userApi from "@/api/userApi";
import { useCartQty } from "@/api/Hooks/useCartQty";

export const Header = () => {
  const [cookies, setCookie, removeCookie] = useCookies([
    "authorize_access_token",
  ]);
  const [loginUser, setLoginUser] = useRecoilState(userState);
  const [order,setOrder] = useRecoilState(orderState);
  const location = useLocation();
  const navigate = useNavigate();
  const onClickMain = () => {
    navigate("/");
  };
  const logout = () => {
    logoutAction();
    setLoginUser("");
    setOrder("");
    removeCookie("authorize_access_token");
    removeCookie("naver_state");
    removeCookie("refresh_token");
    removeCookie("refresh_token_expires_in");
    navigate("/");
  };
  const a = [
    {
      name: "AI cncjs",
      url: "/api",
    },
  ];

  a.map((info) => (
    <Link to={info.url}>
      <span>{info.name}</span>
    </Link>
  ));
  const handleButtonClick = () => {
    <SurveyContent />;
  };
  const { data: cartQty } = useCartQty(loginUser);

  return (
    <>
      <header className="z-10 fixed top-0 flex w-full h-[111px] border-b-[1px] border-solid border-gray-300 bg-white">
        <div className="flex w-full max-w-[1280px] mx-auto h-full">
          <div className="left-0 flex flex-col sm:w-full w-[1280px] h-full px-[55px] mx-auto">
            <div className="w-full h-[50%] items-center justify-center flex">
              <img
                src={logo}
                alt="로고이미지"
                className="w-[80px] cursor-pointer"
                onClick={onClickMain}
              />
            </div>
            <div className="flex justify-between h-[50%]">
              <div className="flex justify-between">
                <span className="flex items-center pr-[40px] font-Pretendard text-md text-left tracking-tight text-gray-700 cursor-pointer font-semibold">
                  <SurveyContent2 />
                </span>

                <span
                  className="flex items-center pr-[40px] font-SUIT text-md text-left tracking-tight text-gray-700 cursor-pointer font-semibold"
                  onClick={() => {
                    navigate("/store/home");
                  }}
                >
                  <span
                    className={`w-full h-full flex items-center ${
                      location.pathname.startsWith("/store") &&
                      "border-b-[2px] border-orange-500 text-orange-500"
                    }`}
                  >
                    스토어
                  </span>
                </span>
                <span
                  className="flex items-center pr-[40px] font-SUIT text-md text-left tracking-tight text-gray-700 cursor-pointer font-semibold"
                  onClick={() => {
                    navigate("/review");
                  }}
                >
                  <span
                    className={`w-full h-full flex items-center ${
                      location.pathname.startsWith("/review") &&
                      "border-b-[2px] border-orange-500 text-orange-500"
                    }`}
                  >
                    필굿후기
                  </span>
                </span>

                <span
                  className="flex items-center pr-[40px] font-suit text-md text-left tracking-tight text-gray-700 cursor-pointer font-semibold"
                  onClick={() => {
                    navigate("/board/notice");
                  }}
                >
                  <span
                    className={`w-full h-full flex items-center ${
                      location.pathname.startsWith("/board") &&
                      "border-b-[2px] border-orange-500 text-orange-500"
                    }`}
                  >
                    고객센터
                  </span>
                </span>
              </div>
              <div className="w-[50%] flex justify-end items-center">
                <div
                  className="flex items-center relative w-[30px] h-[30px] cursor-pointer"
                  onClick={(e) => {
                    e.preventDefault();
                    if (loginUser == "") {
                      alert("로그인이 필요합니다.");
                      return;
                    }
                    navigate("/cart");
                  }}
                >
                  <PiBagSimple
                    size="30"
                    className="cursor-pointer mt-[5px] absolute"
                  />
                  {loginUser != "" && (
                    <span className="absolute top-7 left-3/4 transform -translate-x-1/2 -translate-y+6 text-[12px] text-white bg-orange-600 my-auto rounded-3xl h-[15px] flex w-[15px] items-center justify-center">
                      {cartQty && cartQty.length}
                    </span>
                  )}
                </div>

                <div
                  className="flex items-center relative w-[40px] h-[30px] cursor-pointer hover:no-hover hover:text-gray-900"
                  onClick={() => {
                    loginUser != "" && navigate("/mypage/myinfo");
                  }}
                >
                  {loginUser != "" ? (
                    <FiUser
                      size="27"
                      className="ml-[10px] cursor-pointer font-bold hover:text-gray-900"
                    />
                  ) : (
                    <LoginContent></LoginContent>
                  )}

                  {loginUser != "" && (
                    <span className="absolute top-7 left-7 transform -translate-x-1/2 -translate-y-1/100 text-[12px] text-white bg-orange-600 my-auto rounded-3xl h-[15px] flex w-[30px] items-center justify-center">
                      MY
                    </span>
                  )}
                </div>

                {loginUser != "" && (
                  <Link
                    to="/"
                    className="hover:text-gray-700"
                    role="button"
                    onClick={logout}
                  >
                    <IoLogOutOutline
                      size="30"
                      className="ml-[10px] hover:text-gray-700"
                    />
                  </Link>
                )}
                <span className="flex w-[150px] h-[37px] ml-[10px] items-center justify-center rounded-3xl font-semibold text-5 font-Pretendard text-white cursor-pointer bg-black">
                  <SurveyContent />
                </span>
              </div>
            </div>
          </div>
        </div>
      </header>

      {location.pathname.startsWith("/store") && (
        <div className="z-10 w-full h-[55px] border-b-[1px] mt-[111px] bg-gray-100 fixed flex justify-center">
          <div className="w-[1170px] h-full flex justify-start">
            <Link to="/store/home" className="flex items-center">
              <span
                className={`text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/store/home" && "text-orange-600"
                }`}
              >
                홈
              </span>
            </Link>
            <Link to="/store/problem" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/store/problem" && "text-orange-600"
                }`}
              >
                고민별
              </span>
            </Link>
            <Link to="/store/hff" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/store/hff" && "text-orange-600"
                }`}
              >
                건강기능식품
              </span>
            </Link>
            <Link to="/store/hf" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/store/hf" && "text-orange-600"
                }`}
              >
                건강식품
              </span>
            </Link>
            <Link to="/store/hp" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/store/hp" && "text-orange-600"
                }`}
              >
                건강용품
              </span>
            </Link>
          </div>
        </div>
      )}

      {location.pathname.startsWith("/review") && (
        <div className="z-10 w-full h-[55px] border-b-[1px] mt-[111px] bg-gray-100 fixed flex justify-center">
          <div className="w-[1170px] h-full flex justify-start">
            <Link to="/store/home" className="flex items-center">
              <span
                className={`text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/review" && "text-orange-600"
                }`}
              >
                제품별 후기
              </span>
            </Link>
          </div>
        </div>
      )}
      {location.pathname.startsWith("/story") && (
        <div className="z-10 w-full h-[55px] border-b-[1px] mt-[111px] bg-gray-100 fixed flex justify-center">
          <div className="w-[1170px] h-full flex justify-start">
            <Link to="/store/home" className="flex items-center">
              <span
                className={`text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/story/all" && "text-orange-600"
                }`}
              >
                ALL
              </span>
            </Link>
            <Link to="/store/problem" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/store/problem" && "text-orange-600"
                }`}
              >
                Life
              </span>
            </Link>
            <Link to="/store/hff" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/store/hff" && "text-orange-600"
                }`}
              >
                People
              </span>
            </Link>
            <Link to="/store/hf" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/store/hf" && "text-orange-600"
                }`}
              >
                Nutrition
              </span>
            </Link>
            <Link to="/store/hp" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/store/hp" && "text-orange-600"
                }`}
              >
                Review
              </span>
            </Link>
          </div>
        </div>
      )}

      {location.pathname.startsWith("/mypage") && (
        <div className="z-10 w-full h-[55px] border-b-[1px] mt-[111px] bg-gray-100 fixed flex justify-center">
          <div className="w-[1170px] h-full flex justify-start">
            <Link to="/mypage/myinfo" className="flex items-center">
              <span
                className={`text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/mypage/myinfo" && "text-orange-600"
                }`}
              >
                내정보
              </span>
            </Link>

            <Link to="/mypage/orderList" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/mypage/orderList" && "text-orange-600"
                }`}
              >
                주문목록
              </span>
            </Link>

            <Link to="/mypage/address" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/mypage/address" && "text-orange-600"
                }`}
              >
                배송지관리
              </span>
            </Link>

            <Link to="/mypage/review" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/mypage/review" && "text-orange-600"
                }`}
              >
                후기관리
              </span>
            </Link>
          </div>
        </div>
      )}

      {location.pathname.startsWith("/board") && (
        <div className="z-10 w-full h-[55px] border-b-[1px] mt-[111px] bg-gray-100 fixed flex justify-center">
          <div className="w-[1170px] h-full flex justify-start">
            <Link to="/mypage/myinfo" className="flex items-center">
              <span
                className={`text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/board/notice" && "text-orange-600"
                }`}
              >
                공지사항
              </span>
            </Link>

            <Link to="/mypage/survey" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/board/QNA" && "text-orange-600"
                }`}
              >
                자주묻는질문
              </span>
            </Link>
          </div>
        </div>
      )}

      {location.pathname.startsWith("/board") && (
        <div className="z-10 w-full h-[55px] border-b-[1px] mt-[111px] bg-gray-100 fixed flex justify-center">
          <div className="w-[1170px] h-full flex justify-start">
            <Link to="/board/notice" className="flex items-center">
              <span
                className={`text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/board/notice" && "text-orange-600"
                }`}
              >
                공지사항
              </span>
            </Link>

            <Link to="/board/qna" className="flex items-center">
              <span
                className={`flex items-center text-sm pr-[35px] font-extrabold ${
                  location.pathname == "/board/qna" && "text-orange-600"
                }`}
              >
                자주묻는질문
              </span>
            </Link>
          </div>
        </div>
      )}
    </>
  );
};
