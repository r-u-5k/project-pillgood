import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import * as userApi from '@/api/userApi'
import { useState } from "react";

export const FindIdContent = () => {
  const [phone, setPhone] = useState('');

  const findIdAction = async (e) => {
    e.preventDefault();
    try {
      const response = await userApi.findIdAction(phone);
      alert('아이디 : '+response);
    } catch (error) {
      console.error('Error finding ID:', error);
      // 오류 처리: 필요에 따라 추가적인 오류 메시지를 표시할 수도 있음
    }
  }

  const handleChangeForm = (e) => {
    setPhone(e.target.value); // 전화번호 입력 값 변경
  }

  return (
    <>
      <section className="w-[668px] h-full mx-auto pt-[240px] pb-[260px]">
        <div className="w-full h-full">
          <form onSubmit={findIdAction}>
            <h2 className="text-[28px]">아이디 찾기</h2>
            <div className="mt-[30px]">
              <Label htmlFor="email" className="text-[12px]">
                <p className="text-gray-500">가입한 전화번호를 입력하세요.</p>
              </Label>
              <Input
                type="phone"
                placeholder="전화번호를 입력해 주세요."
                className="mt-[10px] h-[45px] rounded-xl border-gray-900 border-[1px] focus:border-[2px]"
                onChange={handleChangeForm}
                value={phone} // value 추가
              />
            </div>
            <Button variant="default" className="w-full mt-[20px] text-white bg-gray-900 rounded-xl font-semibold h-[45px] hover:bg-gray-900">
              아이디 찾기
            </Button>

      
          </form>
      
      
        </div>
      </section>
      
    </>
  );
};
