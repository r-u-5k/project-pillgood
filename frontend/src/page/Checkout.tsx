import { Button } from "@/components/ui/button";
import { ANONYMOUS, loadPaymentWidget } from "@tosspayments/payment-widget-sdk";
import { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const generateRandomString = () => window.btoa(Math.random()).slice(0, 20);

export function CheckoutPage() {
  const location = useLocation();
  const { order } = location.state;
  console.log(order);
  const paymentWidgetRef = useRef(null);
  const paymentMethodsWidgetRef = useRef(null);
  const agreementWidgetRef = useRef(null);
  const [price, setPrice] = useState();
  const navigate = useNavigate();
  useEffect(() => {
    setPrice(order.ordersPrice);
    (async () => {
      const paymentWidget = await loadPaymentWidget(
        "test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm",
        ANONYMOUS
      ); // 비회원 customerKey
      if (paymentWidgetRef.current == null) {
        paymentWidgetRef.current = paymentWidget;
      }
      /**
       * 결제창을 렌더링합니다.
       * @docs https://docs.tosspayments.com/reference/widget-sdk#renderpaymentmethods%EC%84%A0%ED%83%9D%EC%9E%90-%EA%B2%B0%EC%A0%9C-%EA%B8%88%EC%95%A1
       */
      const paymentMethodsWidget =
        paymentWidgetRef.current.renderPaymentMethods(
          "#payment-method",
          { value: order.ordersPrice },
          { variantKey: "DEFAULT" }
        );
      /**
       * 약관을 렌더링합니다.
       * @docs https://docs.tosspayments.com/reference/widget-sdk#renderagreement%EC%84%A0%ED%83%9D%EC%9E%90-%EC%98%B5%EC%85%98
       */
      agreementWidgetRef.current = paymentWidgetRef.current.renderAgreement(
        "#agreement",
        { variantKey: "DEFAULT" }
      );

      paymentMethodsWidgetRef.current = paymentMethodsWidget;
    })();
  }, []);

  const handlePayment = async () => {
    try {
      const paymentWidget = paymentWidgetRef.current;

      const response = await paymentWidget?.requestPayment({
        orderId: generateRandomString(),
        orderName: '영양제',
        customerName: order.ordersName,
        customerEmail: "x.backrupt@gmail.com",
        successUrl: `${window.location.origin}/sandbox/success`,
        failUrl: `${window.location.origin}/sandbox/fail`,
      });
    } catch (error) {
      // TODO: 에러 처리
      console.error("결제 오류:", error);
    }
  };
  return (
    <div className="wrapper w-[700px] mx-auto mt-[150px]">
      <div className="max-w-540 w-100">
        <div id="payment-method" className="w-100" />
        <div id="agreement" className="w-100" />
        <div className="btn-wrapper w-100">
          <Button
            className="w-[100%] text-[17px] bg-blue-500 rounded-xl text-white h-[50px] font-semibold mt-[20px] hover:bg-blue-500"
            onClick={handlePayment}
          >
            결제하기
          </Button>
        </div>
      </div>
    </div>
  );
}
