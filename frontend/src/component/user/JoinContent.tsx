import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import DatePicker from "./DatePicker";
import * as userApi from "../../api/userApi";
type Inputs = {
  id: string;
};

export const JoinContent = () => {
  const [selectedDate, setSelectedDate] = useState("");
  const formRef = useRef<HTMLFormElement | null>(null);
  const navigate = useNavigate();
  const [user, setUser] = useState({
    email: "",
    password: "",
    name: "",
    birthday: "",
    gender: "",
    phone: "",
  });
  const [checkboxState, setCheckboxState] = useState({
    isCheckedAll: false,
    isCheckedAge: false,
    isCheckedTerms: false,
    isCheckedPolicy: false,
    isCheckedMarketing: false,
  });
  const handleChangeForm = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setUser({
      ...user,
      [name]: value,
    });
  };

  const handleGenderChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const target = e.target;
    setUser({
      ...user,
      gender: target.value,
    });
  };
  const [errors, setErrors] = useState({
    email: "",
    password: "",
    name: "",
  });

  const handleDateSelect = (selectedDates, birthday) => {
    setSelectedDate(birthday);
    setUser({
      ...user,
      birthday: birthday,
    });
  };

  // "모두 동의하기" 체크박스의 변경 이벤트 핸들러
  const handleCheckAll = () => {
    const newCheckboxState = {
      ...checkboxState,
      isCheckedAll: !checkboxState.isCheckedAll,
      isCheckedAge: !checkboxState.isCheckedAll,
      isCheckedTerms: !checkboxState.isCheckedAll,
      isCheckedPolicy: !checkboxState.isCheckedAll,
      isCheckedMarketing: !checkboxState.isCheckedAll,
    };
    setCheckboxState(newCheckboxState);
  };

  // 각 체크박스의 변경 이벤트 핸들러
  const handleCheckboxChange = (checkboxName) => {
    setCheckboxState((prevState) => ({
      ...prevState,
      [checkboxName]: !checkboxState[checkboxName],
    }));
  };
  console.log(user.name.length);
  const userJoinAction = async (e) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const phoneRegex = /^\d*$/;
    e.preventDefault();
    if (!user.email) {
      toast.error("이메일을입력해주세요.");
      formRef.current.querySelector('input[name="email"]').focus();
      return;
    }
    if (!emailRegex.test(user.email)) {
      toast.error("유효한 이메일이 아닙니다.");
      formRef.current.email.focus();
      return;
    }
    if (!user.password) {
      toast.error("비밀번호를 입력해주세요");
      formRef.current.querySelector('input[name="password"]').focus();
      return;
    }
    if (
      formRef.current.querySelector('input[name="password2"]').value !=
      formRef.current.querySelector('input[name="password"]').value
    ) {
      toast.error("비밀번호가 일치하지 않습니다");
      formRef.current.querySelector('input[name="password"]').focus();
      return;
    }
    if (!user.name) {
      formRef.current.querySelector('input[name="name"]').focus();
      return;
    }
    if (user.name.length > 3) {
      toast.error("유효한 이름이 아닙니다.");
      formRef.current.name.focus();
      return;
    }
    if (!user.phone) {
      toast.error("전화번호를 입력 해주세요.");
      formRef.current.querySelector('input[name="phone"]').focus();
      return;
    }
    if (!phoneRegex.test(user.phone)) {
      toast.error("숫자만 입력 해주세요.");
      formRef.current.querySelector('input[name="phone"]').focus();
      return;
    }

    if (!user.birthday) {
      toast.error("생년월일을 선택해주세요.");
      return;
    }
    if (!user.gender) {
      toast.error("성별을 선택해주세요.");
      return;
    }
    if (
      !checkboxState.isCheckedAge ||
      !checkboxState.isCheckedPolicy ||
      !checkboxState.isCheckedTerms
    ) {
      toast.error("이용약관을 동의해주세요");
      return;
    }
    try {
      const response = await userApi.userJoinAction(user);
      navigate("/", { replace: true });
    } catch (error) {
      if (error.response.status === 409) {
        toast.error("이미 존재하는 회원 아이디입니다.");
        formRef.current.querySelector('input[name="email"]').focus();
      } else {
        toast.error("회원가입에 실패하였습니다. 다시 시도해주세요.");
      }
    }
  };
  return (
    <>
      <ToastContainer
        position="bottom-left"
        autoClose={1000}
        hideProgressBar={true}
        closeOnClick={true}
        pauseOnHover={false}
        limit={1}
      />
      <section className="w-[668px] h-[1400px] mx-auto pt-[240px] pb-[260px]">
        <div className="w-full h-full">
          <form ref={formRef}>
            <div>
              <h2 className="text-[28px]">회원가입</h2>
              <div className="mt-[30px]">
                <Label htmlFor="email">아이디(이메일)</Label>
                <Input
                  type="email"
                  placeholder="아이디(이메일)을 입력해 주세요."
                  className="mt-[10px] h-[45px] rounded-xl border-[1px] focus:border-[2px]"
                  id="email"
                  name="email"
                  onChange={handleChangeForm}
                />
                {errors.email && (
                  <p className="text-red-500 text-sm">{errors.email}</p>
                )}
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="password">비밀번호</Label>
                <Input
                  type="password"
                  placeholder="비밀번호를 입력해 주세요."
                  className="mt-[10px] h-[45px] rounded-xl border-[1px] focus:border-[2px]"
                  name="password"
                  onChange={handleChangeForm}
                />
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="password">비밀번호 확인</Label>
                <Input
                  type="password"
                  placeholder="비밀번호를 다시 입력해 주세요."
                  className="mt-[10px] h-[45px] rounded-xl border-[1px] focus:border-[2px]"
                  name="password2"
                  onChange={handleChangeForm}
                />
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="name">이름</Label>
                <Input
                  type="name"
                  placeholder="이름을 입력해 주세요."
                  className="mt-[10px] h-[45px] rounded-xl border-[1px] focus:border-[2px]"
                  name="name"
                  onChange={handleChangeForm}
                />
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="phone">연락처</Label>
                <Input
                  type="phone"
                  placeholder="연락처('-' 제외)를 입력해 주세요."
                  maxLength="11"
                  className="mt-[10px] h-[45px] rounded-xl border-[1px] focus:border-[2px]"
                  name="phone"
                  onChange={handleChangeForm}
                />
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="birthday">생년월일</Label>
                <div>
                  <Button
                    className="w-full h-[45px] p-0 bg-white border-gray-900 rounded-xl focus:border[2px] inline"
                    onClick={(e) => {
                      e.preventDefault();
                    }}
                  >
                    <DatePicker onSelect={handleDateSelect} />
                  </Button>
                </div>
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="gender">성별</Label>
                <RadioGroup
                  defaultValue="comfortable"
                  className="flex flex-row mt-[10px]"
                  onChange={handleGenderChange}
                >
                  <RadioGroupItem
                    className="justify-center flex items-center size-[18px] border-gray-900 border-[1px] text-gray-900"
                    value="남"
                  ></RadioGroupItem>
                  <Label htmlFor="text" className="mr-[300px]">
                    남성
                  </Label>
                  <RadioGroupItem
                    className="justify-center flex items-center size-[18px] border-gray-900 border-[1px] text-gray-900"
                    value="여"
                  ></RadioGroupItem>
                  <Label htmlFor="text">여성</Label>
                </RadioGroup>
              </div>
              <div className="w-full h-[50px] mt-[50px] border-b-[1px] border-gray-300">
                <Checkbox
                  className="mr-[20px] border-gray-900 border-[1px]"
                  name="isCheckedTerms"
                  checked={checkboxState.isCheckedAll}
                  onClick={handleCheckAll}
                />
                <span className="text-[13px] text-center items-center">
                  모두 동의하기
                </span>
              </div>
              <div className="w-full h-[200px] mt-[30px]">
                <div className="mb-[10px]">
                  <Checkbox
                    className="mr-[20px] border-gray-900 border-[1px]"
                    name="isCheckedTerms"
                    checked={checkboxState.isCheckedAge}
                    onClick={() => handleCheckboxChange("isCheckedAge")}
                  />
                  <span className="text-[13px] text-center items-center">
                    만 14세 이상입니다.
                  </span>
                </div>
                <div className="mb-[10px]">
                  <Checkbox
                    className="mr-[20px] border-gray-900 border-[1px]"
                    checked={checkboxState.isCheckedTerms}
                    onClick={() => handleCheckboxChange("isCheckedTerms")}
                  />
                  <span className="text-[13px] text-center items-center">
                    이용 약관 동의
                  </span>
                </div>
                <div className="mb-[10px]">
                  <Checkbox
                    className="mr-[20px] border-gray-900 border-[1px]"
                    name=""
                    checked={checkboxState.isCheckedPolicy}
                    onClick={() => handleCheckboxChange("isCheckedPolicy")}
                  />
                  <span className="text-[13px] text-center items-center">
                    개인정보처리방침 동의
                  </span>
                </div>
                <div className="mb-[10px]">
                  <Checkbox
                    className="mr-[20px] border-gray-900 border-[1px]"
                    checked={checkboxState.isCheckedMarketing}
                    onClick={() => handleCheckboxChange("isCheckedMarketing")}
                  />
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
              <Button
                variant="default"
                className="w-full h-[45px] bg-gray-900 text-white rounded-xl font-semibold text-[15px] hover:bg-gray-900"
                type="button"
                onClick={userJoinAction}
              >
                회원가입
              </Button>
            </div>
          </form>
        </div>
      </section>
    </>
  );
};
