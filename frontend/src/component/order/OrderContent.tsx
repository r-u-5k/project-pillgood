import { itemDetail } from "@/api/itemApi";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { orderState, userState } from "@/recoil/atoms";
import React, { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useRecoilState } from "recoil";
import * as userApi from "../../api/userApi"; // 기본 배송지 정보를 불러오는 API
import { Postcode } from "../Postcode";

export const OrderContent = () => {
  const location = useLocation();
  const { cartItem } = location.state;
  const formRef = useRef<HTMLFormElement | null>(null);
  const navigate = useNavigate();
  const [createOrder, setCreateOrder] = useRecoilState(orderState);
  const [loginUser, setLoginUser] = useRecoilState(userState);

  const [itemData, setItemData] = useState({
    categoryType: "",
    name: "",
    brand: "",
    no: "",
    categorySymptom: "",
    description: "",
    itemImageDto: [
      {
        url: "",
      },
    ],
    price: "",
  });

  const [order, setOrder] = useState({
    cartItems: [],
    ordersName: "",
    ordersPhone: "",
    ordersZipcode: "",
    ordersAddress: "",
    ordersAddressDetail: "",
    ordersPrice: "",
    userId: loginUser.id,
    deliveryRequest: "",
    orderItems: [
      {
        orderItemNo: "",
        orderItemQty: cartItem.cartQty,
        item: {
          no: cartItem.itemId,
        }, // 상품 객체
        orderId: "", // 주문 아이디
      },
    ],
    paymentId: "", // 나중에 결제 추가시 사용
  });

  const [defaultAddress, setDefaultAddress] = useState(null);

  const handleChangeForm = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setOrder({
      ...order,
      [name]: value,
      ordersPrice: String(Number(cartItem.cartQty) * Number(itemData.price) + 3000),
    });
    setCreateOrder(order);
  };

  useEffect(() => {
    const fetchItemData = async () => {
      const data = await itemDetail(cartItem.itemId);
      setItemData(data);
    };
    fetchItemData();
  }, [cartItem.itemId]);

  useEffect(() => {
    const fetchDefaultAddress = async () => {
      const addresses = await userApi.userAddressList(loginUser.id);
      const defaultAddr = addresses.find((addr) => addr.defaultAddress);
      setDefaultAddress(defaultAddr);
    };
    fetchDefaultAddress();
  }, [loginUser.id]);

  const handleComplete = (data) => {
    let fullAddress = data.address;
    const zoneCode = data.zonecode;
    let extraAddress = "";
    if (data.addressType === "R") {
      if (data.bname !== "") {
        extraAddress += data.bname;
      }
      if (data.buildingName !== "") {
        extraAddress += extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
    }
    setOrder({
      ...order,
      ordersAddress: fullAddress,
      ordersZipcode: zoneCode,
    });
  };

  const fillDefaultAddress = () => {
    if (defaultAddress) {
      setOrder({
        ...order,
        ordersName: defaultAddress.name,
        ordersPhone: defaultAddress.phone,
        ordersZipcode: defaultAddress.zipcode,
        ordersAddress: defaultAddress.address,
        ordersAddressDetail: defaultAddress.addressDetail,
      });
    }
  };
  const payButton = () => {
    const phoneRegex = /^\d*$/;
    if (!order.ordersName) {
      formRef.current.ordersName.focus();
      toast.error("수령인 이름을 입력해 주세요");
      return;
    }
    if (!order.ordersPhone) {
      formRef.current.ordersPhone.focus();
      toast.error("전화번호를 입력해 주세요");
      return;
    }
    if (!order.ordersZipcode) {
      formRef.current.postcode.focus();
      toast.error("우편번호를 입력해 주세요");
      return;
    }
    if (!order.ordersAddressDetail) {
      formRef.current.ordersAddressDetail.focus();
      toast.error("상세주소를 입력해 주세요");
      return;
    }
 
    /* 
  ordersName: "",
    ordersPhone: "",
    ordersZipcode: "",
    ordersAddress: "",
    ordersAddressDetail: "",
    ordersPrice: "",
    userId: loginUser.id,
    deliveryRequest: "",
   */
    navigate("/sandbox", { state: { order } });
  };

  return (
    <>
      <section className="w-[668px] h-full mx-auto pt-[180px] pb-[260px]">
        <ToastContainer position="bottom-left" autoClose={1000} hideProgressBar={true} closeOnClick={true} pauseOnHover={false} limit={10} />
        <div className="w-full h-full">
          <form
            ref={formRef}
            onSubmit={(e) => {
              e.preventDefault();
            }}
          >
            <div className="w-full h-full">
              <h2 className="text-[28px]">주문서 작성</h2>
              <h2 className="text-[20px] mt-[30px]">제품 정보</h2>
              <div className="w-full h-[400px] flex-col mt-[30px]">
                {cartItem && (
                  <div className="w-full h-[65px] mb-[20px] flex flex-row">
                    <img src={itemData.itemImageDto[0].url} className="w-[65px] bg-gray-200 rounded-xl" />
                    <div className="flex flex-col ml-[15px]">
                      <div>
                        <Badge variant="outline" className="border-orange-600 text-orange-600">
                          {itemData.categoryType}
                        </Badge>
                      </div>
                      <span>{`[${itemData.brand}] ${itemData.name}`} </span>
                      <span>
                        {cartItem.cartQty} 개 {(Number(cartItem.cartQty) * Number(itemData.price)).toLocaleString()}원
                      </span>
                    </div>
                  </div>
                )}
                <div className="w-full h-[130px] border-[1px] border-gray-600 rounded-xl py-[5px] px-[25px] flex flex-col">
                  <div className="w-full h-[40px] flex justify-between">
                    <div className="flex justify-start items-center">제품합계금액</div>
                    <div className="flex justify-end items-center">{(Number(cartItem.cartQty) * Number(itemData.price)).toLocaleString()}원</div>
                  </div>
                  <div className="w-full h-[40px] flex justify-between">
                    <div className="flex justify-start items-center">기본 배송비</div>
                    <div className="flex justify-end items-center">3,000원</div>
                  </div>
                  <div className="w-full h-[40px] flex justify-between">
                    <div className="flex justify-start items-center text-orange-500">총 결제금액</div>
                    <div className="flex justify-end items-center">{(Number(cartItem.cartQty) * Number(itemData.price) + 3000).toLocaleString()}원</div>
                  </div>
                </div>
              </div>
              <div className="w-full h-[40px] flex justify-between">
                <h2 className="text-[20px] mt-[30px]">배송 정보</h2>
                <Button className="ml-[10px] rounded-xl bg-gray-900 hover:bg-gray-900 text-white font-semibold w-[130px] h-[45px]" onClick={fillDefaultAddress}>
                  기본배송지로 입력
                </Button>
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="name">수령인 이름</Label>
                <Input type="text" placeholder="수령인 이름을 입력해 주세요."  className="mt-[10px] h-[45px] rounded-xl border-[1px] focus:border-[2px]" name="ordersName" value={order.ordersName} onChange={handleChangeForm} />
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="phone">수령인 연락처</Label>
                <Input type="text" placeholder="수령인 연락처(-빼고 숫자만)를 입력해 주세요." maxLength={11}  className="mt-[10px] h-[45px] rounded-xl border-[1px] focus:border-[2px]" name="ordersPhone" value={order.ordersPhone} onChange={handleChangeForm} />
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="postcode">우편번호</Label>
                <div className="flex flex-row w-full mt-[10px]">
                  <Input
                    type="text"
                    placeholder="우편번호"
                    disabled
                    className="w-[70%] mr-[10px] bg-gray-300 border-[1px] border-gray-900 h-[45px] rounded-xl"
                    name="postcode"
                    value={order.ordersZipcode}
                    onChange={handleChangeForm}
                  />
                  <Postcode handleComplete={handleComplete} />
                </div>
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="address">도로명 주소</Label>
                <Input
                  type="text"
                  placeholder="도로명 주소를 입력해주세요."
                  disabled
                  className="mt-[10px] bg-gray-300 border-[1px] border-gray-900 h-[45px] rounded-xl"
                  name="address"
                  value={order.ordersAddress}
                  onChange={handleChangeForm}
                />
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="address">상세 주소</Label>
                <Input type="text" placeholder="상세 주소를 입력해주세요." 
                  className="mt-[10px] h-[45px] rounded-xl border-[1px] focus:border-[2px]"
                  name="ordersAddressDetail" value={order.ordersAddressDetail} onChange={handleChangeForm} />
              </div>
              <div className="mt-[30px]">
                <Label htmlFor="deliveryRequest">배송 요청사항</Label>
                <Input type="text" placeholder="배송 요청사항 입력해주세요."
                  className="mt-[10px] h-[45px] rounded-xl border-[1px] focus:border-[2px]"
                  name="deliveryRequest" value={order.deliveryRequest} onChange={handleChangeForm} />
              </div>

              <Button variant="default"
                className="w-full mt-[30px] h-[45px] bg-gray-900 text-white rounded-xl font-semibold hover:bg-gray-900"
                onClick={payButton}>
                결제하기
              </Button>
            </div>
          </form>
        </div>
      </section>
    </>
  );
};
