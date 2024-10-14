import { Badge } from "@/components/ui/badge";
import star1 from "../../images/star1.png";
import starEmpty from "@/images/icon-star-off@3x.png";
import { useEffect, useState } from "react";
import * as reviewApi from "@/api/reviewApi";
import { Button } from "@/components/ui/button";
import { useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { userState } from "@/recoil/atoms";
import * as userApi from "@/api/userApi";
import { ReviewDetail } from "./ReviewDetail";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "../../style/customToast.css";
import { useMutation, useQueryClient } from "@tanstack/react-query";

export const ReviewContent = () => {
  const [reviews, setReviews] = useState([]);
  const navigate = useNavigate();
  const loginUser = useRecoilValue(userState);
  const queryClient = useQueryClient();
  const [isLoading, setIsLoding] = useState(true);

  const [isToastVisible, setIsToastVisible] = useState(false);

  const formatMillisecondsAsDate = (milliseconds) => {
    const date = new Date(milliseconds);
    const year = date.getUTCFullYear() - 1970; // 1970년을 빼서 나이를 구함

    return `${year}`;
  };

  const [cartItem, setCartItem] = useState({
    cartQty: 1,
    itemId: 0,
    userId: loginUser.id,
  });

  const { mutate } = useMutation({
    mutationFn: () => userApi.addCartItem(cartItem),
    onSuccess: () => {
      // queryClient.setQueryData(["cart"], (prev) => prev + 1);
      queryClient.invalidateQueries({ queryKey: ["cart"] });
    },
  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        const responseJsonObject = await reviewApi.findReviewAll();
        console.log(responseJsonObject);
        setReviews(responseJsonObject);
        setIsLoding(false);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  const addCartItem = async (cartItem) => {
    if (loginUser == "") {
      toast.info("로그인이 필요합니다.", {
        className: "color: black",
      });
      return;
    }
    toast.info("장바구니에 상품이 담겼습니다", {
      className: "color: black",
    });
    mutate(cartItem);
  };
  console.log(reviews);
  if (isLoading)
    return (
      <>
        <div className="w-full h-[1000px] bg-white"></div>
      </>
    );

  return (
    <>
      <ToastContainer
        position="bottom-left"
        autoClose={2000}
        hideProgressBar={true}
        closeOnClick={true}
        pauseOnHover={false}
        limit={10}
        newestOnTop={true}
      />
      <div className="w-[1280px] h-full px-[55px] pt-[221px] mx-auto mb-[100px]">
        <div className="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-3 gap-8">
          {reviews.map((review, i) => (
            <div
              className="bg-white h-[380px] w-[380px] px-[16px] py-[24px]  border-[1px] rounded-2xl flex flex-col"
              key={i}
            >
              <div className="flex flex-row justify-between">
                <div className="flex justify-start">
                  <Badge
                    className="border-blue-500 border-[1px] bg-blue-100"
                    variant="outline"
                  >
                    구매인증
                  </Badge>{" "}
                </div>
                <div className="flex justify-end">
                  {review.date && review.date.substring(0, 10)}
                </div>
              </div>
              <div className="mt-[24px] font-extrabold">
                {review.itemDto.brand} {review.itemDto.name}
              </div>
              <div className="mt-[20px]">
                {review.content && review.content.substring(0, 60)}
              </div>
              <ReviewDetail review={review} />
              <div className="flex flex-row justify-between h-[410px] w-full mt-[24px]">
                <div className="flex justify-start flex-col item pt-[25px]">
                  <div className="items-center flex text-gray-400">
                    {review.name && review.name.substring(0, 1)}**
                  </div>
                  <div className="text-gray-400">
                    {review.gender === "male" || review.gender === "M"
                      ? "남"
                      : review.gender === "female" || review.gender === "FM"
                      ? "여"
                      : review.gender}
                    성 ·{" "}
                    {formatMillisecondsAsDate(
                      new Date() - new Date(review.birthday)
                    )}{" "}
                    세
                  </div>
                  <div>
                    {[...Array(5)].map((_, i) => (
                      <img
                        key={i}
                        src={i + 1 <= review.rating ? star1 : starEmpty}
                        className="w-[15px] inline"
                        alt={`Star ${i + 1}`}
                      />
                    ))}
                  </div>
                </div>
                <div className="flex justify-end w-[100px] h-[100px]">
                  <img
                    src={review.itemDto.itemImageDto[0].url}
                    alt=""
                    className="bg-gray-200 rounded-xl hover:cursor-pointer"
                    onClick={() => {
                      navigate(`/store/productDetail/${review.itemDto.no}`);
                    }}
                  />
                </div>
              </div>
              <Button
                className="bg-orange-600 hover:bg-orange-600 rounded-xl text-white"
                onClick={async () => {
                  setCartItem((prevCartItem) => {
                    const updatedCartItem = {
                      ...prevCartItem,
                      itemId: review.itemDto.no,
                    };
                    addCartItem(updatedCartItem);
                    return updatedCartItem;
                  });
                }}
              >
                장바구니에 담기
              </Button>
            </div>
          ))}
        </div>
      </div>
    </>
  );
};
