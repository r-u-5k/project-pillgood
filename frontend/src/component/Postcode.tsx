import { Button } from "@/components/ui/button";
import React from "react";
import { useDaumPostcodePopup } from "react-daum-postcode";

const scriptUrl =
  "https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js";

export const Postcode = ({ handleComplete }) => {
  const open = useDaumPostcodePopup(scriptUrl);

  const handleClick = () => {
    open({ onComplete: handleComplete });
  };

  return (
    <Button
      type="button"
      onClick={handleClick}
      className="w-[30%] text-white bg-gray-900 rounded-xl font-semibold hover:bg-gray-900 h-[45px]"
    >
      우편번호 검색
    </Button>
  );
};
