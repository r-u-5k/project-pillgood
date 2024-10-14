import * as userApi from "@/api/userApi";
import { userState } from "@/recoil/atoms";
import { ClipboardIcon, CreditCardIcon, GiftIcon, StarIcon, UserIcon } from "lucide-react";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";

export const MyPageContent = () => {
  const [loginUser, setLoginUser] = useRecoilState(userState);
  const navigate = useNavigate();
  const [user, setUser] = useState({
    id: "",
    password: "",
    name: "",
    birthday: "",
    gender: "",
    phone: "",
  });

  

 
  

  return (
    <div className="mt-[200px] mx-auto w-[1170px] h-[1000px]">
      <div className="mx-auto p-[32px] w-[1023px] h-[200px]">
        <div className="w-full h-[50px]">{loginUser.name} 님의 필굿</div>
        <div className="w-full h-[50px]">{user.id}</div>
      </div>

      <div className="mx-auto max-w-5xl px-4 py-8 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
          <Link
            to={""}
            className="flex flex-col items-center justify-center rounded-lg bg-white p-6 shadow-md transition-all hover:scale-105 hover:shadow-lg dark:bg-gray-800 border-[1px] border-gray-300"
          >
            <ClipboardIcon className="mb-4 h-8 w-8 text-primary" />
            <h3 className="text-lg font-medium">건강설문관리</h3>
          </Link>
          <Link
            to={""}
            className="flex flex-col items-center justify-center rounded-lg bg-white p-6 shadow-md transition-all hover:scale-105 hover:shadow-lg dark:bg-gray-800 border-[1px] border-gray-300"
          >
            <CreditCardIcon className="mb-4 h-8 w-8 text-primary" />
            <h3 className="text-lg font-medium">결제관리</h3>
          </Link>
          <Link
            to={""}
            className="flex flex-col items-center justify-center rounded-lg bg-white p-6 shadow-md transition-all hover:scale-105 hover:shadow-lg dark:bg-gray-800 border-[1px] border-gray-300"
          >
            <GiftIcon className="mb-4 h-8 w-8 text-primary" />
            <h3 className="text-lg font-medium">쿠폰함</h3>
          </Link>
          <Link
            to={""}
            className="flex flex-col items-center justify-center rounded-lg bg-white p-6 shadow-md transition-all hover:scale-105 hover:shadow-lg dark:bg-gray-800 border-[1px] border-gray-300"
          >
            <StarIcon className="mb-4 h-8 w-8 text-primary" />
            <h3 className="text-lg font-medium">필굿후기관리</h3>
          </Link>
          <Link
            to={""}
            className="flex flex-col items-center justify-center rounded-lg bg-white p-6 shadow-md transition-all hover:scale-105 hover:shadow-lg dark:bg-gray-800 border-[1px] border-gray-300"
          >
            <UserIcon className="mb-4 h-8 w-8 text-primary" />
            <h3 className="text-lg font-medium">내정보관리</h3>
          </Link>
        </div>
      </div>
    </div>
  );
};
