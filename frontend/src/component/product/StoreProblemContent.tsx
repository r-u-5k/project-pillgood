import { Link } from "react-router-dom";
import { Badge } from "@/components/ui/badge";
import star from "../../images/star.svg";

import all from "../../images/전체.png";
import blood from "../../images/icon-discomfort-blood.svg";
import digestion from "../../images/icon-discomfort-digestion.svg";
import skin from "../../images/icon-discomfort-skin.svg";
import eye from "../../images/icon-discomfort-eye.svg";
import fatigue from "../../images/icon-discomfort-fatigue.svg";
import bone from "../../images/icon-discomfort-bone.svg";
import immune from "../../images/icon-discomfort-immune.svg";
import hair from "../../images/icon-discomfort-hair.svg";
import woman from "../../images/여성건강.png";
import man from "../../images/남성건강.png";
import { useEffect, useState } from "react";
import { itemListAll, itemListByCategorySymptom } from "@/api/itemApi";

export const StoreProblemContent = () => {
  const [symptom, setSymptom] = useState("전체");
  const [items, setItems] = useState([]);
  const [isLoading, setIsLoding] = useState(true);

  useEffect(() => {
    const fetchData = async (categorySymptom: string) => {
      try {
        const responseJsonObject =
          symptom === "전체"
            ? await itemListAll()
            : await itemListByCategorySymptom(symptom);
        console.log(responseJsonObject);
        setItems(responseJsonObject);
        setIsLoding(false);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData("모발");
  }, [symptom]);

  const changeSymptom = (e) => {
    setSymptom(e.target.alt);
    console.log(symptom);
  };

  if (isLoading)
    return (
      <>
        <div className="w-full h-[1000px] bg-white"></div>
      </>
    );

  return (
    <>
      <div className="w-[1280px] h-full px-[55px] pt-[221px] mx-auto mb-[100px]">
        <div className="w-full h-[70px] text-xl font-bold">
          고민되는 카테고리를 선택하세요.
        </div>
        <div className="w-full h-[150px] flex">
          <div className="w-[72px] h-full mr-[23px]">
            <div
              className={`w-[72px] h-[72px] flex items-center justify-center rounded-2xl mr-[23px] cursor-pointer ${
                symptom === "전체" ? "bg-orange-400" : "bg-gray-100"
              }`}
            >
              <img src={all} alt="전체" onClick={changeSymptom} />
            </div>
            <div className="text-xs text-gray-900 flex justify-center pt-[5px] font-semibold">
              전체
            </div>
          </div>
          <div className="w-[72px] h-full mr-[23px]">
            <div
              className={`w-[72px] h-[72px] flex items-center justify-center rounded-2xl mr-[23px] cursor-pointer ${
                symptom === "혈관" ? "bg-orange-400" : "bg-gray-100"
              }`}
            >
              <img src={blood} alt="혈관" onClick={changeSymptom} />
            </div>
            <div className="text-xs text-gray-900 flex justify-center pt-[5px] font-semibold">
              혈관
            </div>
          </div>
          <div className="w-[72px] h-full mr-[23px]">
            <div
              className={`w-[72px] h-[72px] flex items-center justify-center rounded-2xl mr-[23px] cursor-pointer ${
                symptom === "장" ? "bg-orange-400" : "bg-gray-100"
              }`}
            >
              <img src={digestion} alt="장" onClick={changeSymptom} />
            </div>
            <div className="text-xs text-gray-900 flex justify-center pt-[5px] font-semibold">
              장 건강
            </div>
          </div>
          <div className="w-[72px] h-full mr-[23px]">
            <div
              className={`w-[72px] h-[72px] flex items-center justify-center rounded-2xl mr-[23px] cursor-pointer ${
                symptom === "피부" ? "bg-orange-400" : "bg-gray-100"
              }`}
            >
              <img src={skin} alt="피부" onClick={changeSymptom} />
            </div>
            <div className="text-xs text-gray-900 flex justify-center pt-[5px] font-semibold">
              피부
            </div>
          </div>
          <div className="w-[72px] h-full mr-[23px]">
            <div
              className={`w-[72px] h-[72px] flex items-center justify-center rounded-2xl mr-[23px] cursor-pointer ${
                symptom === "눈" ? "bg-orange-400" : "bg-gray-100"
              }`}
            >
              <img src={eye} alt="눈" onClick={changeSymptom} />
            </div>
            <div className="text-xs text-gray-900 flex justify-center pt-[5px] font-semibold">
              눈 건강
            </div>
          </div>
          <div className="w-[72px] h-full mr-[23px]">
            <div
              className={`w-[72px] h-[72px] flex items-center justify-center rounded-2xl mr-[23px] cursor-pointer ${
                symptom === "피로" ? "bg-orange-400" : "bg-gray-100"
              }`}
            >
              <img src={fatigue} alt="피로" onClick={changeSymptom} />
            </div>
            <div className="text-xs text-gray-900 flex justify-center pt-[5px] font-semibold">
              피로감
            </div>
          </div>
          <div className="w-[72px] h-full mr-[23px]">
            <div
              className={`w-[72px] h-[72px] flex items-center justify-center rounded-2xl mr-[23px] cursor-pointer ${
                symptom === "관절" ? "bg-orange-400" : "bg-gray-100"
              }`}
            >
              <img src={bone} alt="관절" onClick={changeSymptom} />
            </div>
            <div className="text-xs text-gray-900 flex justify-center pt-[5px] font-semibold">
              관절/뼈
            </div>
          </div>
          <div className="w-[72px] h-full mr-[23px]">
            <div
              className={`w-[72px] h-[72px] flex items-center justify-center rounded-2xl mr-[23px] cursor-pointer ${
                symptom === "면역력" ? "bg-orange-400" : "bg-gray-100"
              }`}
            >
              <img src={immune} alt="면역력" onClick={changeSymptom} />
            </div>
            <div className="text-xs text-gray-900 flex justify-center pt-[5px] font-semibold">
              면역력
            </div>
          </div>
          <div className="w-[72px] h-full mr-[23px]">
            <div
              className={`w-[72px] h-[72px] flex items-center justify-center rounded-2xl mr-[23px] cursor-pointer ${
                symptom === "모발" ? "bg-orange-400" : "bg-gray-100"
              }`}
            >
              <img src={hair} alt="모발" onClick={changeSymptom} />
            </div>
            <div className="text-xs text-gray-900 flex justify-center pt-[5px] font-semibold">
              모발
            </div>
          </div>
          <div className="w-[72px] h-full mr-[23px]">
            <div
              className={`w-[72px] h-[72px] flex items-center justify-center rounded-2xl mr-[23px] cursor-pointer ${
                symptom === "여성건강" ? "bg-orange-400" : "bg-gray-100"
              }`}
            >
              <img src={woman} alt="여성건강" onClick={changeSymptom} />
            </div>
            <div className="text-xs text-gray-900 flex justify-center pt-[5px] font-semibold">
              여성건강
            </div>
          </div>
          <div className="w-[72px] h-full mr-[23px]">
            <div
              className={`w-[72px] h-[72px] flex items-center justify-center rounded-2xl mr-[23px] cursor-pointer ${
                symptom === "남성건강" ? "bg-orange-400" : "bg-gray-100"
              }`}
            >
              <img src={man} alt="남성건강" onClick={changeSymptom} />
            </div>
            <div className="text-xs text-gray-900 flex justify-center pt-[5px] font-semibold">
              남성건강
            </div>
          </div>
          <div className="w-[72px] h-full mr-[23px]">
            <div
              className={`w-[72px] h-[72px] flex items-center justify-center rounded-2xl mr-[23px] cursor-pointer ${
                symptom === "구강관리" ? "bg-orange-400" : "bg-gray-100"
              }`}
            >
              <img
                src="https://pilly.kr/images/store/concern/icon-oral_cavity_off.png"
                alt="구강관리"
                onClick={changeSymptom}
              />
            </div>
            <div className="text-xs text-gray-900 flex justify-center pt-[5px] font-semibold">
              구강관리
            </div>
          </div>
        </div>

        <div className="w-full h-[70px] flex justify-between">
          <div className="w-[50%] h-full flex justify-start items-center font-semibold text-lg">
            총 {items.length}개
          </div>
        </div>
        <div className="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-4 gap-8">
          {items.length > 0 &&
            items.map((item) => (
              <Link key={item.no} to={`/store/productDetail/${item.no}`}>
                <div className="bg-white h-full">
                  <img
                    src={item.itemImageDto[0].url}
                    className="bg-gray-100 rounded-2xl"
                    alt={item.name}
                  />
                  <div className="mt-[15px] ml-[10px] text-left">
                    <Badge
                      variant="outline"
                      className={`border-[1px] pt-[3.8px] ${
                        item.categoryType === "건강기능식품"
                          ? "border-orange-600 text-orange-600"
                          : item.categoryType === "건강식품"
                          ? "border-blue-600 text-blue-600"
                          : item.categoryType === "건강용품"
                          ? "border-gray-600 text-gray-600"
                          : ""
                      }`}
                    >
                      {item.categoryType}
                    </Badge>

                    <h3 className="tracking-tighter  text-gray-700 text-sm font-extrabold mt-[5px]">
                      {item.brand}
                      <span className="font-medium pl-[10px]">{item.name}</span>
                    </h3>
                    <span className="font-semibold text-gray-700 mt-[10px]">
                      {item.price.toLocaleString()}원
                    </span>
                    <div>
                      <img src={star} className="inline pr-[3px]" alt="Star" />
                      <span className="text-gray-600 text-sm pl-[3px]">
                        {item.reviewAvg && item.reviewAvg.toFixed(1)}{" "}
                      </span>
                      <span className="pl-[5px]">(후기 {item.reviewQty})</span>
                    </div>
                  </div>
                </div>
              </Link>
            ))}
        </div>
      </div>
    </>
  );
};
