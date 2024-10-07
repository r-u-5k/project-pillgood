import { userState } from "@/recoil/atoms";
import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useRecoilState } from "recoil";
import "semantic-ui-css/semantic.min.css";
import { OrderListCarouselPlugin } from "../layout/OrderListCarouselPlugin";
export const OrderList = () => {
  const [activeIndex, setActiveIndex] = useState(0); //
  const [loginUser, setLoginUser] = useRecoilState(userState);
  const api = axios.create({
    baseURL: "http://192.168.15.3:8080/",
    withCredentials: true,
  });
  const [orderItems, setOrderItemList] = useState([]);
  const [orderList, setOrderList] = useState([
    /*
    {
      ordersPrice: 0,
      orderDate: "",
      orderId: "",
      orderItems: [
        {
          item: {
            no: "",
            brand: "",
            name: "",
            price: "",
            description: "",
            itemImageDto: [{ img: "", url: "", type: "" }],
          },
          orderId: 1,
          orderItemNo: 1,
          orderItemQty: 3,
        },
      ],
      ordersAddress: "",
      ordersAddressDetail: "",
      ordersName: "",
      ordersPhone: "",
      ordersZipcode: "",
      paymentId: "",
      userId: "",
    },
    */
  ]);
  console.log(orderList);
  console.log(orderItems);
  const reponseOrderList = async () => {
    const responseJsonObject = await api.get(`/order/user/${loginUser.id}`);
    console.log(responseJsonObject.data);
    const order = responseJsonObject.data;
    const orderItems = order[0].orderItems;
    console.log(orderItems);
    order.sort((a, b) => {
      return new Date(b.orderDate).getTime() - new Date(a.orderDate).getTime();
    });
    setOrderItemList(orderItems);
    setOrderList(order);
  };
  useEffect(() => {
    const intervalId = setInterval(() => {
      setActiveIndex((prevIndex) => (prevIndex + 1) % orderItems.length);
    }, 1000);
    return () => clearInterval(intervalId);
  }, []);
  useEffect(() => {
    reponseOrderList();
  }, []);
  return (
    <section className="w-[1170px] h-full mx-auto pt-[180px] my-[50px]">
      {orderList.length === 0 ? (
        <div>
           <h1 className="text-[50px] text-orange-500 text-center pt-[240px] pb-[300px]">아직 주문한 내역이 없어요!</h1>
        </div>
      ) : (
        orderList.map((order) => (
          <div key={order.orderId} className="border-[1px] border-gray-600 rounded-xl mb-[20px] p-[30px]">
            <div className="ui grid">
              <div className="three column row">
                <div
                  className="ml-[20px]"
                  style={{ fontWeight: 600, fontSize: "15pt" }}
                >
                  {order.orderDate!.substring(0, 10)}
                </div>
                <div
                  className="right floated column"
                  style={{
                    textAlign: "right",
                    fontWeight: 600,
                    fontSize: "15pt",
                  }}
                >
                  <Link to={`/mypage/orderdetail/${order.orderId}`}>
                    주문내역상세보기
                  </Link>
                </div>
              </div>
            </div>
            <div className="ui items">
              <div className="item">
                <div className="image ">
                     <OrderListCarouselPlugin orderItems={order.orderItems}> </OrderListCarouselPlugin> 
                 
                </div>
                <div className="ml-[50px]">
                  <a className="header">{}</a>
                  <br></br>
                  <br></br>
                  <div
                    className=""
                    style={{ fontWeight: 600, fontSize: "15pt" }}
                  >
                    {order.orderItems[0].item.name}{" "}
                    {order.orderItems.length > 1 &&
                      `외 ${order.orderItems.length - 1} 개`}
                  </div>
                  <div className="description"></div>
                  <div
                    className="extra"
                    style={{ fontWeight: 600, fontSize: "15pt" }}
                  >
                    총 주문 금액{" "}
                    {order.ordersPrice !== null
                      ? order.ordersPrice.toLocaleString()
                      : 0}
                    원 &nbsp;&nbsp;{" "}
                  </div>
                </div>
              </div>
            </div>
            <br/>
            <br/>
          </div>
        ))
      )}
    </section>
  );
};
