import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { userState } from "@/recoil/atoms";
import axios from "axios";
import React, { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useRecoilState } from "recoil";
import { Modal, ModalActions, ModalContent, ModalDescription, ModalHeader } from "semantic-ui-react";
import blood from "../../images/icon-discomfort-blood.svg";
import bone from "../../images/icon-discomfort-bone.svg";
import digestion from "../../images/icon-discomfort-digestion.svg";
import eye from "../../images/icon-discomfort-eye.svg";
import fatigue from "../../images/icon-discomfort-fatigue.svg";
import hair from "../../images/icon-discomfort-hair.svg";
import immune from "../../images/icon-discomfort-immune.svg";
import woman from "../../images/여성건강.png";
import FetchingModal from "./FetchingModal";
type Inputs = {
  id: string;
};

export const SurveyContent = () => {
  const [loginUser, setLoginUser] = useRecoilState(userState);
  const [open, setOpen] = React.useState(false);
  const formRef = useRef<HTMLFormElement | null>(null);
  const [fetching, setFetching] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    setFetching(false);
  }, []);
  const [survey, setSurvey] = useState({
    userId: loginUser.id,
    key: "",
    weight: "",
    height: "",
    age: "",
    symptomType: "",
  });
  const handleChangeForm = (e: React.ChangeEvent) => {
    const { name, value } = e.target;
    setSurvey((prevSurvey) => ({
      ...prevSurvey,
      [name]: value,
    }));
  };
  const api = axios.create({
    baseURL: "http://192.168.15.3:8080/api",
    withCredentials: true,
  });
  const surveySubmit = async () => {
    if (!survey.age) {
      formRef.current.age.focus();
      toast.error("나이를 입력해주세요");
      return;
    }
    if (!survey.height) {
      formRef.current.height.focus();
      toast.error("키를 입력해주세요");
      return;
    }
    if (!survey.weight) {
      formRef.current.weight.focus();
      toast.error("몸무게를 입력해주세요");
      return;
    }
    if (!survey.symptomType) {
      formRef.current.age.focus();
      toast.error("증상을 선택해주세요");
      return;
    }

    const response = await api.post("/survey", survey);
    console.log(response);
    setFetching(true);
    setTimeout(() => {
      navigate("/survey/item", { state: { survey } });
    }, 2000);
    //setOpen(false);
  };

  const handleButtonClick = () => {
    console.log("로그인 상태:", loginUser);
    if (!loginUser) {
      alert("로그인이 필요합니다.");
      setOpen(false);
      return;
    } else {
      setOpen(true);
    }
  };
  return (
    <>
    {loginUser ? (
    <Modal
      size="small"
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
        <span className="flex items-center font-Pretendard text-md text-left tracking-tight text-white cursor-pointer font-semibold" onClick={handleButtonClick}>
          AI 영양제 추천
        </span>
      }
    >
      {fetching ? (
        <FetchingModal />
      ) : (
      <>
        <ToastContainer position="top-center" autoClose={1000} hideProgressBar={true} closeOnClick={true} pauseOnHover={false} limit={3} />
        <ModalHeader className="flex justify-center items-center">AI 영양제 추천</ModalHeader>
        <ModalContent>
          <ModalDescription>
            <form ref={formRef}>
              <div className="w-full max-w-3xl mx-auto px-4 py-12 md:px-6 md:py-16">
                <div className="space-y-6">
                  <div className="text-center">
                    <p className="text-gray-500 dark:text-gray-400 mt-2"></p>
                  </div>
                  <div className="space-y-8">
                    <div className="space-y-4">
                      <h2 className="text-xl font-semibold">나이를 알려주세요.</h2>
                      <Input
                        onChange={handleChangeForm}
                        style={{ height: 40 }}
                        name="age"
                        id="age"
                        type="text"
                        placeholder="나이"
                        className="rounded-xl block w-full border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500 dark:bg-gray-800 dark:border-gray-600 dark:focus:border-primary-600 dark:focus:ring-primary-600"
                      />
                    </div>
                    <div className="space-y-4">
                      <h2 className="text-xl font-semibold">키를 알려주세요.</h2>
                      <Input
                        onChange={handleChangeForm}
                        style={{ height: 40 }}
                        name="height"
                        id="height"
                        type="text"
                        placeholder="키"
                        className="block w-full rounded-xl border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500 dark:bg-gray-800 dark:border-gray-600 dark:focus:border-primary-600 dark:focus:ring-primary-600"
                      />
                    </div>
                    <div className="space-y-4">
                      <div className="space-y-4">
                        <h2 className="text-xl font-semibold">몸무게를 알려주세요.</h2>
                        <Input
                          onChange={handleChangeForm}
                          style={{ height: 40 }}
                          name="weight"
                          id="weight"
                          type="text"
                          placeholder="몸무게"
                          className="block w-full rounded-xl border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500 dark:bg-gray-800 dark:border-gray-600 dark:focus:border-primary-600 dark:focus:ring-primary-600"
                        />
                      </div>
                      <br></br>

                      <br></br>

                      <br></br>
                      <h2 className="text-xl font-semibold mb-[30px]">불편하거나 걱정되는 것을 선택하세요</h2>
                      <div className="w-full h-full flex flex-row justify-center">
                        <div
                          className={`w-[570px] h-[80px] bg-gray-100 rounded-xl flex flex-row ${survey.symptomType == "혈관" && "bg-orange-400 text-white"}`}
                          data-value="혈관"
                          onClick={(e) => {
                            if (survey.symptomType == "혈관") {
                              setSurvey({
                                ...survey,
                                symptomType: "",
                              });
                            } else {
                              setSurvey({
                                ...survey,
                                symptomType: e.currentTarget.dataset.value,
                              });
                            }
                          }}
                        >
                          <span className="w-[48px] h-[48px] my-auto ml-[15px]">
                            <img src={blood} alt="" className="flex justify-center inline" />
                          </span>
                          <span className={`ml-[15px] flex items-center text-xl text-[20px] font-normal hover: cursor-pointer `}>혈관 · 혈액순환</span>
                        </div>
                      </div>

                      <div className="w-full h-full flex flex-row justify-center">
                        <div
                          className={`w-[570px] h-[80px] bg-gray-100 rounded-xl flex flex-row ${survey.symptomType == "장" && "bg-orange-400 text-white"}`}
                          data-value="장"
                          onClick={(e) => {
                            if (survey.symptomType == "장건강") {
                              setSurvey({
                                ...survey,
                                symptomType: "",
                              });
                            } else {
                              setSurvey({
                                ...survey,
                                symptomType: e.currentTarget.dataset.value,
                              });
                            }
                          }}
                        >
                          <span className="w-[48px] h-[48px] my-auto ml-[15px]">
                            <img src={digestion} alt="" className="flex justify-center inline" />
                          </span>
                          <span className={`ml-[15px] flex items-center text-xl text-[20px] font-normal hover: cursor-pointer `}>장 건강</span>
                        </div>
                      </div>

                      <div className="w-full h-full flex flex-row justify-center">
                        <div
                          className={`w-[570px] h-[80px] bg-gray-100 rounded-xl flex flex-row ${survey.symptomType == "여성건강" && "bg-orange-400 text-white"}`}
                          data-value="여성건강"
                          onClick={(e) => {
                            if (survey.symptomType == "여성건강") {
                              setSurvey({
                                ...survey,
                                symptomType: "",
                              });
                            } else {
                              setSurvey({
                                ...survey,
                                symptomType: e.currentTarget.dataset.value,
                              });
                            }
                          }}
                        >
                          <span className="w-[48px] h-[48px] my-auto ml-[15px]">
                            <img src={woman} alt="" className="flex justify-center inline m-auto pt-[7px]" />
                          </span>
                          <span className={`ml-[15px] flex items-center text-xl text-[20px] font-normal hover: cursor-pointer `}>여성건강</span>
                        </div>
                      </div>

                      <div className="w-full h-full flex flex-row justify-center">
                        <div
                          className={`w-[570px] h-[80px] bg-gray-100 rounded-xl flex flex-row ${survey.symptomType == "눈" && "bg-orange-400 text-white"}`}
                          data-value="눈"
                          onClick={(e) => {
                            if (survey.symptomType == "눈") {
                              setSurvey({
                                ...survey,
                                symptomType: "",
                              });
                            } else {
                              setSurvey({
                                ...survey,
                                symptomType: e.currentTarget.dataset.value,
                              });
                            }
                          }}
                        >
                          <span className="w-[48px] h-[48px] my-auto ml-[15px]">
                            <img src={eye} alt="" className="flex justify-center inline" />
                          </span>
                          <span className={`ml-[15px] flex items-center text-xl text-[20px] font-normal hover: cursor-pointer `}>눈</span>
                        </div>
                      </div>

                      <div className="w-full h-full flex flex-row justify-center">
                        <div
                          className={`w-[570px] h-[80px] bg-gray-100 rounded-xl flex flex-row ${survey.symptomType == "모발" && "bg-orange-400 text-white"}`}
                          data-value="모발"
                          onClick={(e) => {
                            if (survey.symptomType == "모발") {
                              setSurvey({
                                ...survey,
                                symptomType: "",
                              });
                            } else {
                              setSurvey({
                                ...survey,
                                symptomType: e.currentTarget.dataset.value,
                              });
                            }
                          }}
                        >
                          <span className="w-[48px] h-[48px] my-auto ml-[15px]">
                            <img src={hair} alt="" className="flex justify-center inline" />
                          </span>
                          <span className={`ml-[15px] flex items-center text-xl text-[20px] font-normal hover: cursor-pointer `}>모발</span>
                        </div>
                      </div>

                      <div className="w-full h-full flex flex-row justify-center">
                        <div
                          className={`w-[570px] h-[80px] bg-gray-100 rounded-xl flex flex-row ${survey.symptomType == "피로" && "bg-orange-400 text-white"}`}
                          data-value="피로"
                          onClick={(e) => {
                            if (survey.symptomType == "피로") {
                              setSurvey({
                                ...survey,
                                symptomType: "",
                              });
                            } else {
                              setSurvey({
                                ...survey,
                                symptomType: e.currentTarget.dataset.value,
                              });
                            }
                          }}
                        >
                          <span className="w-[48px] h-[48px] my-auto ml-[15px]">
                            <img src={fatigue} alt="" className="flex justify-center inline" />
                          </span>
                          <span className={`ml-[15px] flex items-center text-xl text-[20px] font-normal hover: cursor-pointer `}>피로</span>
                        </div>
                      </div>

                      <div className="w-full h-full flex flex-row justify-center">
                        <div
                          className={`w-[570px] h-[80px] bg-gray-100 rounded-xl flex flex-row ${survey.symptomType == "관절" && "bg-orange-400 text-white"}`}
                          data-value="관절"
                          onClick={(e) => {
                            if (survey.symptomType == "관절") {
                              setSurvey({
                                ...survey,
                                symptomType: "",
                              });
                            } else {
                              setSurvey({
                                ...survey,
                                symptomType: e.currentTarget.dataset.value,
                              });
                            }
                          }}
                        >
                          <span className="w-[48px] h-[48px] my-auto ml-[15px]">
                            <img src={bone} alt="" className="flex justify-center inline" />
                          </span>
                          <span className={`ml-[15px] flex items-center text-xl text-[20px] font-normal hover: cursor-pointer `}>관절</span>
                        </div>
                      </div>

                      <div className="w-full h-full flex flex-row justify-center">
                        <div
                          className={`w-[570px] h-[80px] bg-gray-100 rounded-xl flex flex-row ${survey.symptomType == "면역력" && "bg-orange-400 text-white"}`}
                          data-value="면역력"
                          onClick={(e) => {
                            if (survey.symptomType == "면역력") {
                              setSurvey({
                                ...survey,
                                symptomType: "",
                              });
                            } else {
                              setSurvey({
                                ...survey,
                                symptomType: e.currentTarget.dataset.value,
                              });
                            }
                          }}
                        >
                          <span className="w-[48px] h-[48px] my-auto ml-[15px]">
                            <img src={immune} alt="" className="flex justify-center inline" />
                          </span>
                          <span className={`ml-[15px] flex items-center text-xl text-[20px] font-normal hover: cursor-pointer `}>면역력</span>
                        </div>
                      </div>
                      <div className="w-full h-[100px] flex justify-center">
                        <Button type="button" onClick={surveySubmit} className="rounded-2xl bg-gray-900 text-white font-semibold m-auto w-[150px] h-[50px] hover:bg-gray-900" variant="default">
                          추천받기
                        </Button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </ModalDescription>
        </ModalContent>
        <ModalActions className="flex justify-center"></ModalActions>
      </>
      )}
    </Modal>):( <span className="flex items-center font-Pretendard text-md text-left tracking-tight text-white cursor-pointer font-semibold" onClick={handleButtonClick}>
          AI 영양제 추천
        </span>)}
    </>
  );
};
