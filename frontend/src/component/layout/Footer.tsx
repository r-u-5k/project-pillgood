import { Button } from "@/components/ui/button";
import { BuildingIcon, ClockIcon } from "lucide-react";
import { useNavigate } from "react-router-dom";

export const Footer = () => {

  const chatting = () => {
    window.open("http://pf.kakao.com/_gFXcG", "", "width=800,height=800");
  };
  return (

    /*   <footer className="w-full h-[250px] bg-zinc-800 text-white p-8"> */
    <footer className="bg-zinc-800 text-white p-8 h-[300px]">
      <div className="max-w-6xl mx-auto grid grid-cols-3 gap-8">
        <div>
          <h2 className="font-bold text-lg mb-4">내일의 나를 만드는 [필굿!] </h2>
          <p className="text-sm mb-2">(주)필굿 사업자 정보</p>
          <p className="text-xs mb-2">
            서울특별시 강남구 테헤란로 124 4층 (아이티윌)
            <br />
            우편번호: 서울특별시 강남구 테헤란로 124 4층 (아이티윌)
          </p>
          <p className="text-xs mb-2">
            대표: 김수환, 사업자번호: 000-00-00000, 전자기기판매업신고번호: 제 0000-000000 &amp; 통신판매업신고번호: 제 0000-서울강남-00000 호 사업자정보확인, 통신판매업신고번호: 제 0000-00000 호
          </p>
          <p className="text-xs mb-2">
            케어커리어컨설팅부: 케어커리어컨설팅부, 케어커리어인재상담부: 김수환
            <br />
            고객센터: 02-xxxx-xxxx, tnghks7915@naver.com
            <br />
            제휴문의: tnghks7915@naver.com
          </p>
          <p className="text-xs">이용약관 · 개인정보처리방침</p>
        </div>
        <div className="flex flex-col items-center justify-center bg-gray-800 p-4">
          <ClockIcon className="h-6 w-6 text-white mb-4" />
          <h3 className="font-bold text-lg mb-2">카카오톡 채널 '필굿'</h3>
          <p className="text-xs mb-4">
            평일 오전 10:00 ~ 오후 17:00
            <br />
            (점심시간 12:00~14:00)
          </p>
          <Button className="text-white border border-white bg-slate-900" onClick={chatting}>
            문의하기
          </Button>
        </div>
        <div className="flex flex-col items-center justify-center bg-gray-800 p-4">
          <BuildingIcon className="h-6 w-6 text-white mb-4" />
          <h3 className="font-bold text-lg mb-2">맞춤형업체 가입문자</h3>
          <p className="text-xs mb-4">
            AI 영양제 추천
            <br />
            영양제의 맞춤형업체, 필굿
          </p>
          <Button className="text-white border border-white bg-slate-900">문의하기</Button>
        </div>
      </div>
      <div className="text-xs text-center mt-8">© Pillgood Inc. All Rights Reserved.</div>
    </footer>
  );
};
