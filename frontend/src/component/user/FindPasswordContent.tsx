import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import * as userApi from "@/api/userApi"
import { useState } from "react";

export const FindPasswordContent = () => {

  const [email, setEmail] = useState('');

  const updatePasswordAction = async(email)=>{
    const response = await userApi.findPasswordAction(email);
    console.log(response);
  }

  const handleUpdateForm = (e)=>{
    setEmail(e.target.value);
    console.log(email);
  }
  return (
    <>
      <section className="w-[668px] h-full mx-auto pt-[240px] pb-[260px]">
        <div className="w-full h-full">
          <form>
            <h2 className="text-[28px]">비밀번호 찾기</h2>
            <div className="mt-[30px]">
              <Label htmlFor="email" className="text-[12px]">
                <p className="text-gray-500">
                  가입한 아이디(이메일)를 입력하세요.
                  <br />
                  새로운 비밀번호를 보내드립니다.
                </p>
              </Label>
              <Input
                type="email"
                placeholder="아이디(이메일)를 입력해 주세요."
                className="mt-[10px] border-[1px] border-gray-900 rounded-xl h-[45px] focus:border-[2px]"
                onChange={(e)=>{handleUpdateForm(e)}}
              />
            </div>
            <Button variant="default" className="w-full mt-[20px] text-white bg-gray-900 rounded-xl font-semibold h-[45px] hover:bg-gray-900"
            onClick={(e)=>{
              e.preventDefault();
              updatePasswordAction(email)}}
            >
              임시 비밀번호 발송
            </Button>
          </form>
        </div>
      </section>
    </>
  );
};
