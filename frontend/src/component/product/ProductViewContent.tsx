import { useCartQty } from "@/api/Hooks/useCartQty";
import { itemDetail } from "@/api/itemApi";
import * as reviewApi from "@/api/reviewApi";
import * as userApi from "@/api/userApi";
import { Avatar } from "@/components/ui/avatar";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { userState } from "@/recoil/atoms";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { UserIcon } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useRecoilValue } from "recoil";
import starEmpty from "../../images/icon-star-off@3x.png";
import star1 from "../../images/star1.png";
import starhalf from "../../images/star_half.png";
import "../../style/customToast.css";
import "../../style/sticky.css";
import { CarouselPlugin } from "../layout/CarouselPlugin";

export const ProductDetailContent = () => {
  const location = useLocation();
  const { state } = location;
  if (state == "review") {
    const reviewSection = document.getElementById("review-section");
    if (reviewSection) {
      const topOffset = reviewSection.offsetTop;
      window.scrollTo({ top: topOffset - 200, behavior: "smooth" });
    }
  }
  const reviewTitleRef = useRef("");
  const reviewContentRef = useRef("");
  const loginUser = useRecoilValue(userState);
  const navigate = useNavigate();
  const { itemNo } = useParams();
  const { isLoading, error, data: cart } = useCartQty(loginUser);
  const queryClient = useQueryClient();

  const { mutate } = useMutation({
    mutationFn: () => userApi.addCartItem(cartItem),
    onSuccess: () => {
      // queryClient.setQueryData(["cart"], (prev) => prev + 1);
      queryClient.invalidateQueries({ queryKey: ["cart"] });
    },
  });

  const [item, setItem] = useState({
    no: 0,
    itemImageDto: [],
    brand: "",
    name: "",
    price: "",
    description: "",
  });

  const [hoveredStar, setHoveredStar] = useState(-1);
  const [selectedStar, setSelectedStar] = useState(-1);

  const handleStarClick = (index) => {
    setReview({
      ...review,
      rating: index + 1,
    });
    setSelectedStar(index);
  };

  const handleStarHover = (index) => {
    setHoveredStar(index); // 호버된 별점의 인덱스 설정
  };
  const handleStarLeave = () => {
    setHoveredStar(-1);
  };

  const fullStars = Math.floor(item.reviewAvg);
  const halfStar = item.reviewAvg % 1 !== 0;
  const initialReviewState = {
    no: 0,
    title: "",
    name: "",
    content: "",
    rating: 0,
    userNo: loginUser.id,
    itemNo: item.no,
  };

  const [cartItem, setCartItem] = useState({
    cartQty: 1,
    itemId: 0,
    userId: loginUser.id,
  });

  const [review, setReview] = useState(initialReviewState);

  const [reviewList, setReviewList] = useState([]);

  const handleReviewForm = (e) => {
    setReview({
      ...review,
      [e.target.name]: e.target.value,
    });
    console.log(review);
  };

  const changCartQty = (e) => {
    if (e.target.getAttribute("name") === "plus") {
      setCartItem({
        ...cartItem,
        cartQty: cartItem.cartQty + 1,
      });
    }
    if (cartItem.cartQty > 1 && e.target.getAttribute("name") === "minus") {
      setCartItem({
        ...cartItem,
        cartQty: cartItem.cartQty - 1,
      });
    }
  };

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
    //
    console.log("called");
    mutate(cartItem);
  };

  const findReviewList = async (itemId) => {
    const response = await reviewApi.findReviewByItem(itemId);
    setReviewList(response);
  };

  useEffect(() => {
    const fetchData = async (itemNo) => {
      try {
        const responseJsonObject = await itemDetail(itemNo);
        setItem(responseJsonObject);
        console.log(responseJsonObject);
        setCartItem({
          ...cartItem,
          itemId: Number(itemNo),
        });
        setReview((prevReview) => ({
          ...prevReview,
          itemNo: responseJsonObject.no,
        }));

        const response = await findReviewList(itemNo);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    if (itemNo) {
      fetchData(itemNo);
    }
  }, [itemNo]);

  const handleClick = () => {
    if (loginUser == "") {
      toast.info("로그인이 필요합니다.", {
        className: "color: black",
      });
      return;
    }
    navigate(`/order`, { state: { cartItem } });
  };

  const createReview = async (e, review) => {
    e.preventDefault();
    if (loginUser == "") {
      toast.info("로그인이 필요합니다.", {
        className: "color: black",
      });
      return;
    }
    console.log(review);
    if (review.rating == 0) {
      toast.info("별점을 선택해주세요.", {
        className: "color: black",
      });
      return;
    }
    const response = await reviewApi.createReview(review);
    setReview(initialReviewState);
    findReviewList(review.itemNo); // Use review.itemNo instead of itemNo
    setSelectedStar(-1);
    reviewTitleRef.current.value = "";
    reviewContentRef.current.value = "";
  };

  return (
    <>
      <ToastContainer
        position="bottom-left"
        autoClose={1000}
        hideProgressBar={true}
        closeOnClick={true}
        pauseOnHover={false}
        limit={10}
        newestOnTop={true}
      />
      <div className="w-[1280px] h-full px-[55px] pt-[221px] mx-auto mb-[100px]">
        <div className="w-full h-[770px] flex flex-row mb-[50px]">
          <CarouselPlugin itemImage={item.itemImageDto}></CarouselPlugin>
          <div className="w-[400px] h-[550px] p-[15px] fixed left-[1150px] bg-white rounded-3xl border-[1px] border-gray-400">
            <Badge
              variant="outline"
              className="text-gray-600 rounded-sm border-gray-500"
            >
              BRAND NAME
            </Badge>
            <span className="text-sm pl-[10px] text-gray-600 font-semibold">
              {item.brand}
            </span>
            <h1 className="mt-[20px] text-2xl text-gray-700 font-semibold tracking-tighter">
              {item.name}
            </h1>
            <div className="mt-[24px] text-xl text-gray-700">
              {item.price.toLocaleString()}원
            </div>
            <div className="flex items-center justify-start gap-2 mt-[10px] font-bold">
              수량 :
              <Button
                size="icon"
                variant="outline"
                name="minus"
                onClick={changCartQty}
              >
                <span
                  className="text-[30px] font-light flex justify-center items-center pb-[4px]"
                  name="minus"
                >
                  -
                </span>
              </Button>
              <span>{cartItem.cartQty}</span>
              <Button
                size="icon"
                variant="outline"
                name="plus"
                onClick={changCartQty}
              >
                <span className="text-[20px]" name="plus">
                  +
                </span>
              </Button>
            </div>
            <div className="mt-[27px]">
              {[...Array(5)].map((_, i) => (
                <img
                  key={i}
                  src={
                    i + 1 <= fullStars
                      ? star1
                      : i === fullStars && halfStar
                      ? starhalf
                      : starEmpty
                  }
                  className="w-[15px] inline"
                  alt={`Star ${i + 1}`}
                />
              ))}
              <span className="ml-[5px]">
                {item.reviewAvg && item.reviewAvg.toFixed(1)}
              </span>
              <span className="pl-[10px]">후기 {item.reviewQty}</span>
            </div>
            <div className="mt-[27px] py-[24px] border-y-[1px] border-gray-400 text-gray-700">
              {item.description}
            </div>
            <Button
              variant="default"
              className="bg-orange-600 mt-[25px] w-[360px] h-[50px] hover:bg-orange-600 text-white rounded-xl font-semibold text-lg"
              onClick={(e) => {
                addCartItem(cartItem);
              }}
            >
              장바구니에 담기
            </Button>

            <Button
              variant="default"
              className="bg-orange-600 mt-[15px] w-[360px] h-[50px] font-semibold text-lg hover:bg-orange-600 rounded-xl text-white"
              onClick={handleClick}
            >
              구매하기
            </Button>
          </div>
        </div>
        <div></div>{" "}
        <div className="w-[770px] h-full">
          {item.itemImageDto.map(
            (img) =>
              img.type === "상품설명" && (
                <img src={img.url} key={img.url} className="w-[770px]" />
              )
          )}
        </div>
        <div className="w-[770px] h-full flex flex-col">
          <div className="w-full max-w-[770px] mx-auto grid gap-8 py-12 px-8 md:px-12">
            <div id="review-section">
              <h2 className="text-2xl font-bold mb-4">제품후기 작성하기</h2>
              <form
                className="grid gap-4"
                onSubmit={(e) => {
                  createReview(e, review);
                }}
              >
                <div className="grid grid-cols-2 gap-4">
                  <div className="grid gap-2">
                    <label htmlFor="rating" className="text-sm font-medium">
                      Rating
                    </label>
                    <div className="flex items-center gap-2">
                      {[...Array(5)].map((_, index) => (
                        <img
                          key={index}
                          src={
                            index <=
                            (selectedStar !== -1 ? selectedStar : hoveredStar)
                              ? star1
                              : starEmpty
                          }
                          className="w-[20px] h-auto cursor-pointer"
                          onMouseEnter={() => handleStarHover(index)}
                          onMouseLeave={handleStarLeave}
                          onClick={() => handleStarClick(index)}
                          alt={`star-${index}`}
                        />
                      ))}
                    </div>
                  </div>
                  <div className="grid gap-2">
                    <Label htmlFor="title" className="text-sm font-medium">
                      Title
                    </Label>
                    <Input
                      ref={reviewTitleRef}
                      id="title"
                      placeholder="Enter a title"
                      name="title"
                      onChange={handleReviewForm}
                    />
                  </div>
                </div>
                <div className="grid gap-2">
                  <Label htmlFor="content" className="text-sm font-medium">
                    Review
                  </Label>
                  <Textarea
                    ref={reviewContentRef}
                    id="content"
                    placeholder="Write your review"
                    rows={4}
                    name="content"
                    onChange={handleReviewForm}
                  />
                </div>
                <Button
                  variant="default"
                  className="bg-orange-600 mt-[25px] w-full h-[50px] hover:bg-orange-600 rounded-xl text-white text-lg font-semibold"
                >
                  작성하기
                </Button>
              </form>
            </div>
            <div>
              {reviewList &&
                reviewList.map((review, i) => (
                  <div className="grid gap-6" key={i}>
                    <div className="grid gap-4 mt-[10px]">
                      <div className="flex items-center gap-4 flex-row justify-between">
                        <div className="flex justify-start">
                          <Avatar className="w-10 h-10 border flex justify-center items-center mr-[10px]">
                            <UserIcon />
                          </Avatar>
                          <div className="grid gap-1">
                            <div className="font-medium">{review.name}</div>
                            <div className="flex items-center gap-1 text-sm">
                              {[...Array(5)].map((_, i) => (
                                <img
                                  key={i}
                                  src={
                                    i + 1 <= review.rating ? star1 : starEmpty
                                  }
                                  className="w-[15px] inline"
                                  alt={`Star ${i + 1}`}
                                />
                              ))}
                            </div>
                          </div>
                        </div>
                        <div className="flex justify-end">
                          {review.date.substring(0, 10)}
                        </div>
                      </div>
                      <div>
                        <h3 className="font-medium font-semibold">
                          {review.title}
                        </h3>
                        <p className="text-gray-500 dark:text-gray-400 mt-[10px]">
                          {review.content}
                        </p>
                      </div>
                    </div>
                    <hr />
                  </div>
                ))}
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default ProductDetailContent;
