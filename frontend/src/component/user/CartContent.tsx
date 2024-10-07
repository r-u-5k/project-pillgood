import * as cartApi from "@/api/cartApi";
import * as userApi from "@/api/userApi";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { userState } from "@/recoil/atoms";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { MinusIcon, NavigationIcon, PlusIcon, TrashIcon } from "lucide-react";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";

export const CartContent = () => {
  const navigate = useNavigate();
  const loginUser = useRecoilValue(userState);
  console.log(loginUser);
  const queryClient = useQueryClient();
  const [cart, setCart] = useState([
    {
      cartId: "",
      cartQty: "",
      itemId: "",
      userId: "",
      selected: true,
      itemDto: {
        no: "",
        brand: "",
        name: "",
        price: "",
        description: "",
        categorySymptom: "",
        categoryType: "",
        itemImageDto: [
          {
            url: "https://previews.123rf.com/images/estherpoon/estherpoon1706/estherpoon170600060/80394133-%EB%A1%9C%EB%94%A9-%EC%95%84%EC%9D%B4%EC%BD%98.jpg",
          },
        ],
      },
    },
  ]);

  const [isAllSelected, setIsAllSelected] = useState(true);

  const { mutate } = useMutation({
    mutationFn: (cartId) => userApi.removeCartItem(cartId),
    onSuccess: () => {
      // queryClient.setQueryData(["cart"], (prev) => prev + 1);
      queryClient.invalidateQueries({ queryKey: ["cart"] });
    },
  });

  const handleToggleSelectAll = () => {
    const newSelectionState = !isAllSelected;
    setIsAllSelected(newSelectionState);
    setCart((prevCart) =>
      prevCart.map((item) => ({ ...item, selected: newSelectionState }))
    );
  };

  const handleToggleSelect = (cartId) => {
    setCart((prevCart) =>
      prevCart.map((item) =>
        item.cartId === cartId ? { ...item, selected: !item.selected } : item
      )
    );

    console.log(cart);
  };

  useEffect(() => {
    // 모든 항목이 선택되었는지 확인하여 전체 선택 상태 업데이트
    const allSelected = cart.every((item) => item.selected);
    setIsAllSelected(allSelected);
  }, [cart]);

  useEffect(() => {
    const fetchData = async (userNo) => {
      console.log(userNo);
      try {
        const responseJsonObject = await userApi.userCartList(userNo);
        const updatedCart = responseJsonObject.map((item) => ({
          ...item,
          selected: true, // 여기서 selected 속성 추가
        }));
        setCart(updatedCart);
        console.log(cart);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData(loginUser.id);
  }, []);

  const handleRemoveItem = async (cartId) => {
    try {
      mutate(cartId);
      setCart((prevCart) => prevCart.filter((item) => item.cartId !== cartId)); // 상태 업데이트
    } catch (error) {
      console.error("Error removing item:", error);
    }
  };

  const plusCartItemQty = async (cartId) => {
    try {
      await cartApi.plusCartItemQty(cartId);
      setCart((prevCart) =>
        prevCart.map((item) =>
          item.cartId === cartId ? { ...item, cartQty: item.cartQty + 1 } : item
        )
      );
    } catch (error) {
      console.error("Error increasing item quantity:", error);
    }
  };

  const minusCartItemQty = async (cartId, cartQty) => {
    if (cartQty > 1) {
      try {
        await cartApi.minusCartItemQty(cartId);
        setCart((prevCart) =>
          prevCart.map((item) =>
            item.cartId === cartId
              ? { ...item, cartQty: item.cartQty - 1 }
              : item
          )
        );
      } catch (error) {
        console.error("Error decreasing item quantity:", error);
      }
    }
  };

  const handleSelectedItemsOrder = () => {
    const selectedCart = cart.filter((item) => item.selected); // selected가 true인 아이템들만 필터링
    const selectedCartIds = selectedCart.map((item) => item.cartId);
    const selectedItems = selectedCart;

    // 선택된 상품들의 데이터를 주문하기 페이지로 전달
    navigate("/order/cart", { state: { selectedItems } });
  };
  //전체상품주문하기 버튼 클릭시 선택안된상품들도 전체선택한뒤 전달 하는 펑션
  const [navigateReady, setNavigateReady] = useState(false);
  useEffect(() => {
    if (navigateReady) {
      const selectedCart = cart.filter((item) => item.selected);
      const selectedItems = selectedCart;
      navigate("/order/cart", { state: { selectedItems } });
      setNavigateReady(false); // Reset the flag
    }
  }, [navigateReady, cart, navigate]);

  const handleAllItemsOrder = () => {
    cart.forEach((item) => item.selected = true);
    setNavigateReady(true); // Set the flag to trigger useEffect
  };

  const totalOrderPrice = cart.reduce((total, item) => {
    if (item.selected) {
      return total + item.cartQty * item.itemDto.price;
    }
    return total; // 항목이 선택되지 않은 경우 현재의 total 값을 반환
  }, 0);
  return (
    <>
      <div className="w-[1170px] h-full mx-auto my-[200px]">
        <div className="flex flex-col gap-8">
          <div>
            <h1 className="text-2xl font-bold mb-4">장바구니</h1>
            <div className="border rounded-lg overflow-hidden">
              <table className="w-full">
                <thead className="bg-gray-100 dark:bg-gray-800">
                  <tr>
                    <th className="px-4 py-3 text-left">
                      <div className="flex items-center gap-2">
                        <Checkbox
                          id="select-all"
                          checked={isAllSelected}
                          onClick={handleToggleSelectAll}
                        />
                      </div>
                    </th>
                    <th className="px-4 py-3 text-center">제품</th>
                    <th className="px-4 py-3 text-right">가격</th>
                    <th className="px-4 py-3 text-center">수량</th>
                    <th className="px-4 py-3 text-right">합계</th>
                    <th className="px-4 py-3 text-right">삭제</th>
                  </tr>
                </thead>
                <tbody>
                  {cart.map((cartItem) => (
                    <tr className="border-b dark:border-gray-700">
                      <td className="px-4 py-5">
                        <Checkbox
                          id="product1"
                          checked={cartItem.selected}
                          onClick={() => handleToggleSelect(cartItem.cartId)}
                        />
                      </td>
                      <td className="flex items-center gap-4 px-4 py-5">
                        <img
                          alt="Product Image"
                          className="rounded-xl bg-gray-100"
                          height={80}
                          src={cartItem.itemDto.itemImageDto[0].url}
                          style={{
                            aspectRatio: "80/80",
                            objectFit: "cover",
                          }}
                          width={80}
                        />
                        <div>
                          <Badge
                            variant="outline"
                            className={`border-[1px] pt-[3.8px] ${
                              cartItem.itemDto.categoryType === "건강기능식품"
                                ? "border-orange-600 text-orange-600"
                                : cartItem.itemDto.categoryType === "건강식품"
                                ? "border-blue-600 text-blue-600"
                                : cartItem.itemDto.categoryType === "건강용품"
                                ? "border-gray-600 text-gray-600"
                                : ""
                            }`}
                          >
                            {cartItem.itemDto.categoryType}
                          </Badge>
                          <h3 className="font-medium mt-[10px]">
                            <span className="font-semibold mr-[10px]">
                              {cartItem.itemDto.brand}
                            </span>
                            {cartItem.itemDto.name}
                          </h3>
                        </div>
                      </td>
                      <td className="px-4 py-5 text-right font-medium">
                        {cartItem.itemDto.price.toLocaleString()}원
                      </td>
                      <td className="px-4 py-5 text-center">
                        <div className="flex items-center justify-center gap-2">
                          <Button
                            size="icon"
                            variant="outline"
                            onClick={(e) => {
                              minusCartItemQty(
                                cartItem.cartId,
                                cartItem.cartQty
                              );
                            }}
                          >
                            <MinusIcon className="w-4 h-4" />
                          </Button>
                          <span>{cartItem.cartQty}</span>
                          <Button
                            size="icon"
                            variant="outline"
                            onClick={(e) => {
                              plusCartItemQty(cartItem.cartId);
                            }}
                          >
                            <PlusIcon className="w-4 h-4" />
                          </Button>
                        </div>
                      </td>
                      <td className="px-4 py-5 text-right font-medium">
                        {(
                          cartItem.cartQty * cartItem.itemDto.price
                        ).toLocaleString()}
                        원
                      </td>
                      <td className="px-4 py-5 text-right">
                        <Button size="icon" variant="outline">
                          <TrashIcon
                            className="w-4 h-4"
                            onClick={(e) => {
                              handleRemoveItem(cartItem.cartId);
                            }}
                          />
                        </Button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
          <div className="grid gap-8">
            <div className="bg-gray-100 dark:bg-gray-800 rounded-lg p-6">
              <div className="grid gap-2">
                <div className="flex justify-between">
                  <span>상품 합계</span>
                  <span className="font-medium">
                    {totalOrderPrice ? totalOrderPrice.toLocaleString() : "0"}원
                  </span>
                </div>
                <div className="flex justify-between">
                  <span>배송비</span>
                  <span className="font-medium">
                    {totalOrderPrice ? "3,000" : "0"}원
                  </span>
                </div>
                <div className="flex justify-between items-center">
                  <span className="text-lg font-bold">총 주문금액</span>
                  <span className="text-lg font-bold">
                    {totalOrderPrice
                      ? (totalOrderPrice + 3000).toLocaleString()
                      : "0"}
                    원{" "}
                  </span>
                </div>
              </div>
            </div>
            <div className="flex flex-row">
              <div className="flex flex-row justify-start w-[50%]">
                <Button
                  size="lg"
                  className="w-[150px] bg-white text-gray-950 border-[1px] border-gray-800 rounded-3xl hover:bg-white"
                  onClick={(e) => {
                    navigate("/store/home");
                  }}
                >
                  쇼핑 계속하기
                </Button>
              </div>
              <div className="flex flex-row justify-end w-[50%]">
                <Button
                  size="lg"
                  className="w-[150px] bg-white text-gray-950 border-[1px] border-gray-800 rounded-3xl hover:bg-white"
                  onClick={handleSelectedItemsOrder}
                >
                  선택 상품 주문
                </Button>
                <Button
                  size="lg"
                  className="w-[150px] bg-orange-600 ml-[10px] rounded-3xl hover:bg-orange-600 text-white font-semibold"
                  onClick={handleAllItemsOrder}
                >
                  전체 상품 주문
                </Button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
