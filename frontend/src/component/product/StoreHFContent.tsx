import { Link } from "react-router-dom";
import img from "../../images/마그네슘.png";
import { Badge } from "@/components/ui/badge";
import star from "../../images/star.svg";
import { useEffect, useState } from "react";
import { itemListByCategoryType } from "@/api/itemApi";

export const StoreHFContent = () => {
  const [items, setItems] = useState([]);
  const [isLoading, setIsLoding] = useState(true);
  useEffect(() => {
    const fetchData = async (categoryType: string) => {
      try {
        const responseJsonObject = await itemListByCategoryType(categoryType);
        console.log(responseJsonObject);
        setItems(responseJsonObject);
        setIsLoding(false);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData("건강식품");
  }, []);

  if (isLoading)
    return (
      <>
        <div className="w-full h-[1000px] bg-white"></div>
      </>
    );

  return (
    <>
      <div className="w-[1280px] h-full px-[55px] pt-[221px] mx-auto mb-[100px]">
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
                      className="border-blue-600 text-blue-600 border-[1px] items-center pt-[3.8px]"
                    >
                      건강식품
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
