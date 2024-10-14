import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";

export const SNSJoinContent = () => {
  return (
    <>
      <section className="w-[668px] h-[1400px] mx-auto pt-[240px] pb-[260px]">
        <div className="w-full h-full">
          <form>
            <div>
              <h2 className="text-[28px]">추가 정보 입력</h2>
              <div className="mt-[30px]">
                <Label htmlFor="email">이름</Label>
                <Input
                  type="email"
                  placeholder="이름을 입력해 주세요."
                  className="mt-[10px]"
                />
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="email">연락처</Label>
                <Input
                  type="email"
                  placeholder="연락처('-' 제외)를 입력해 주세요."
                  className="mt-[10px]"
                />
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="email">성별</Label>
                <RadioGroup className="flex flex-row mt-[10px]">
                  <RadioGroupItem value="남"></RadioGroupItem>
                  <Label htmlFor="email" className="mr-[300px]">
                    남성
                  </Label>
                  <RadioGroupItem value="여"></RadioGroupItem>
                  <Label htmlFor="email">여성</Label>
                </RadioGroup>
              </div>
              <div className="w-full h-[50px] mt-[50px] border-b-[1px] border-gray-300">
                <Checkbox className="mr-[20px]" /> 모두 동의하기
              </div>
              <div className="w-full h-[200px] mt-[30px]">
                <div className="mb-[10px]">
                  <Checkbox className="mr-[20px]" />{" "}
                  <span className="text-[13px] text-center items-center">
                    만 14세 이상입니다.
                  </span>
                </div>
                <div className="mb-[10px]">
                  <Checkbox className="mr-[20px]" />{" "}
                  <span className="text-[13px] text-center items-center">
                    이용 약관 동의
                  </span>
                </div>
                <div className="mb-[10px]">
                  <Checkbox className="mr-[20px]" />{" "}
                  <span className="text-[13px] text-center items-center">
                    개인정보처리방침 동의
                  </span>
                </div>
                <div className="mb-[10px]">
                  <Checkbox className="mr-[20px]" />{" "}
                  <span className="text-[13px] text-center items-center">
                    마케팅 수신 동의{" "}
                    <span className="text-gray-400">(선택)</span>
                    <br />
                    <span className="pl-[40px]">
                      동의 후 가입하면 즉시 쿠폰 지급!
                    </span>
                  </span>
                </div>
              </div>
              <Button variant="default" className="w-full">
                회원가입
              </Button>
            </div>
          </form>
        </div>
      </section>
    </>
  );
};
