import { Button } from "@/components/ui/button";
import video from "../images/main.mp4";
import main1 from "../images/main1.png";
import main2 from "../images/main2.png";
import main3 from "../images/main3.png";
import { useEffect } from "react";
import { useCookies } from "react-cookie";
import * as userApi from "../api/userApi.ts";
import { useRecoilState } from "recoil";
import { userState } from "@/recoil/atoms.ts";
import { SurveyContent } from "./user/SurveyContent.tsx";

export const MainContent = () => {
  const [loginUser, setLoginUser] = useRecoilState(userState);
  const [cookies, setCookie, removeCookie] = useCookies([
    "authorize_access_token",
  ]);
  useEffect(() => {
    const login = async () => {
      try {
        const kakaoToken = cookies.authorize_access_token;
        if (kakaoToken) {
          const response = await userApi.kakaoLoginUser(kakaoToken);
          console.log(response);
          setLoginUser(response);
          localStorage.setItem("jwtToken", response.token);
          console.log(response.token);
          return; // Kakao 로그인 성공 시 Naver 로그인 로직을 건너뜁니다.
        }

        const naverToken = cookies.authorize_access_token;
        if (naverToken) {
          const response = await userApi.naverLoginUser(naverToken);
          console.log(response);
          setLoginUser(response);
          localStorage.setItem("jwtToken", response.token);
          console.log(response.token);
        }
      } catch (error) {
        console.error("Error during login:", error);
      }
    };

    login();
  }, [cookies, setLoginUser]);

  return (
      <main className="w-full">
        <div className="relative">
          <video
              src={video}
              autoPlay
              muted
              loop
              className="w-full h-[700px] object-cover"
          ></video>
          <h1 className="text-white absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 text-center text-[32px]">
            맞춤 영양제 <br />
            스마트하게 시작하세요!
          </h1>
          <span className="mt-[100px] flex absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-[180px] h-[45px] ml-[10px] items-center justify-center rounded-3xl font-semibold text-5 font-Pretendard text-white cursor-pointer bg-black">
          <SurveyContent />
        </span>
        </div>

        <img src={main1} alt="" />
        <img src={main2} alt="" />
        <img src={main3} alt="" />
      </main>
  );
};
