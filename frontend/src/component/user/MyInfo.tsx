import { handleUpdateUser, userDeleteAction } from "@/api/userApi";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { userState } from "@/recoil/atoms";
import { useEffect, useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useRecoilState } from "recoil";

export const MyInfo = () => {
  const navigate = useNavigate();
  const [loginUser, setLoginUser] = useRecoilState(userState);
  const [sendUser, setSendUser] = useState({});
  const formRef = useRef();
  useEffect(() => {
    if (loginUser == "") {
      navigate("/login");
    } else {
      navigate("/mypage/myinfo");
    }
    setSendUser(loginUser);
  }, []);

  console.log(sendUser);
  console.log(loginUser);

  const passwordUpdate = (e) => {
    setSendUser((prevUser) => ({
      ...prevUser,
      [e.target.name]: e.target.value,
    }));
  };
  const updateUser = async (e) => {
    try {
      e.preventDefault();
      if (sendUser.password === sendUser.password2) {
        const updatedUser = await handleUpdateUser(sendUser);
        console.log("User updated successfully:", updatedUser);
        navigate("/");
      } else {
        toast.info("비밀번호와 비밀번호 확인이 일치하지 않습니다.", {
          className: "color: black",
        });
        return;
      }
    } catch (error) {
      if (error.response) {
        toast.info("기존비밀번호가 일치하지 않습니다");
        console.error("Error updating user:", error);
      }
    }
  };
  const deleteUser = async (e) => {
    const result = confirm("정말 탈퇴하시겠습니까?");
    if (result) {
      await userDeleteAction(sendUser);
      navigate("/");
      setLoginUser("");
    } else {
      return;
    }
  };

  const isHomeProvider = loginUser.provider === "HOME";

  console.log(sendUser);

  return (
    <div className="mt-[200px] mx-auto w-[1170px] h-[1000px]">
      <ToastContainer
        position="bottom-left"
        autoClose={1000}
        hideProgressBar={true}
        closeOnClick={true}
        pauseOnHover={false}
        limit={1}
      />
      <div className="mx-auto p-[32px] w-[750px] h-[200px]">
        <form ref={formRef}>
          <fieldset>
            <legend className="font-bold">내정보 관리</legend>

            <div>
              <label htmlFor="exampleInputEmail1" className="form-label mt-4">
                계정정보
              </label>
              <input
                type="text"
                readOnly={true}
                disabled
                className="form-control rounded-xl h-[45px] bg-gray-300 border-[1px] border-gray-900"
                id="staticEmail"
                value={sendUser.email}
              />
              <small id="emailHelp" className="form-text text-muted">
                이메일은 수정이 불가능 합니다.
              </small>
            </div>
            <div>
              <label
                htmlFor="exampleInputPassword1"
                className="form-label mt-4"
              >
                이름
              </label>
              <input
                type="text"
                readOnly={true}
                disabled
                className="form-control rounded-xl h-[45px] bg-gray-300 border-[1px] border-gray-900"
                id="name"
                value={sendUser.name}
              />
              <small id="emailHelp" className="form-text text-muted">
                이름은 수정이 불가능 합니다.
              </small>
            </div>
            <div>
              <label
                htmlFor="exampleInputPassword1"
                className="form-label mt-4"
              >
                전화번호
              </label>
              <input
                type="text"
                readOnly={true}
                disabled
                className="form-control rounded-xl h-[45px] bg-gray-300 border-[1px] border-gray-900"
                id="phone"
                value={
                  sendUser.phone
                    ? sendUser.phone.replace(
                        /^(\d{3})(\d{3,4})(\d{4})$/,
                        "$1-$2-$3"
                      )
                    : ""
                }
              />
              <small id="emailHelp" className="form-text text-muted">
                전화번호는 수정이 불가능 합니다.
              </small>
            </div>
            <div>
              <label
                htmlFor="exampleInputPassword1"
                className="form-label mt-4"
              >
                기존 비밀번호
              </label>
              <Input
                type="password"
                className="form-control h-[45px] rounded-xl border-[1px] focus:border-[2px] focus:border-gray-900 "
                id="oldPassword"
                placeholder="비밀번호"
                autoComplete="off"
                onChange={passwordUpdate}
                disabled={!isHomeProvider}
                name="oldPassword"
              />
            </div>
            {!isHomeProvider && (
              <small id="emailHelp" className="form-text text-muted">
                소셜 로그인한 회원은 수정이 불가능 합니다.
              </small>
            )}
            <div>
              <label
                htmlFor="exampleInputPassword1"
                className="form-label mt-4"
              >
                새로운 비밀번호
              </label>
              <Input
                type="password"
                className="form-control h-[45px] rounded-xl border-[1px] focus:border-[2px] focus:border-gray-900 "
                id="password"
                placeholder="비밀번호"
                autoComplete="off"
                onChange={passwordUpdate}
                disabled={!isHomeProvider}
                name="password"
              />
            </div>
            {!isHomeProvider && (
              <small id="emailHelp" className="form-text text-muted">
                소셜 로그인한 회원은 수정이 불가능 합니다.
              </small>
            )}
            <div>
              <label
                htmlFor="exampleInputPassword1"
                className="form-label mt-4"
              >
                새로운 비밀번호 확인
              </label>
              <Input
                type="password"
                className="form-control h-[45px] rounded-xl border-[1px] focus:border-[2px] focus:border-gray-900"
                id="passwordCheck"
                placeholder="비밀번호 확인"
                autoComplete="off"
                name="password2"
                disabled={!isHomeProvider}
                onChange={passwordUpdate}
              />
            </div>
            {!isHomeProvider && (
              <small id="emailHelp" className="form-text text-muted">
                소셜 로그인한 회원은 수정이 불가능 합니다.
              </small>
            )}
            <br></br>
            <br></br>
            <div className="row justify-content-center">
              <div className="col-auto">
                {!isHomeProvider ? (
                  ""
                ) : (
                  <Button
                    variant="default"
                    className="bg-gray-900 text-white rounded-xl font-semibold w-[100px] h-[40px] hover:bg-gray-900"
                    onClick={(e) => {
                      updateUser(e);
                    }}
                  >
                    변경하기
                  </Button>
                )}
              </div>
              <div className=" flex justify-center">
                <Link to={"/"}>
                  <Button
                    variant="outline"
                    className="bg-white  text-black rounded-xl font-semibold border-[1px] border-gray-600 w-[100px] h-[40px]"
                  >
                    취소하기
                  </Button>
                </Link>
              </div>
            </div>
            <div className="row justify-content-center mt-3">
              {" "}
              {/* Add a margin-top for spacing */}
              <div className="col-auto flex justify-center">
                <span
                  className="underline text-center text-gray-500 cursor-pointer"
                  onClick={deleteUser}
                >
                  회원탈퇴
                </span>
              </div>
            </div>
          </fieldset>
        </form>
      </div>
    </div>
  );
};
