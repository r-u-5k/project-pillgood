import { FindIdPage } from "@/page/FindIdPage";
import { FindPasswordPage } from "@/page/FindPasswordPage";
import { LoginPage } from "@/page/LoginPage";
import { createBrowserRouter } from "react-router-dom";
import { CartPage } from "./page/CartPage";
import { CheckoutPage } from "./page/Checkout";

import { FailPage } from "./page/Fail";
import { JoinPage } from "./page/JoinPage";
import { MainPage } from "./page/MainPage";
import { NotFoundPage } from "./page/NotFoundPage";
import { OrderPage } from "./page/OrderPage";
import { ProductDetailPage } from "./page/ProductViewPage";
import { SNSJoinPage } from "./page/SNSJoinPage";
import { StoreHFFPage } from "./page/StoreHFFPage";
import { StoreHFPage } from "./page/StoreHFPage";
import { StoreHPPage } from "./page/StoreHPPage";

import { StoreProblemPage } from "./page/StoreProblemPage";
import { SuccessPage } from "./page/Success";
import { SurveyPage } from "./page/SurveyPage";
import { MyInfoPage } from "./page/MyInfoPage";
import { OrderListPage } from "./page/OrderListPage";
import { OrderDetailPage } from "./page/OrderDetailPage";
import { BoardWritePage } from "./page/BoardWritePage";
import { BoardDetailPage } from "./page/BoardDetailPage";
import { BoardModifyPage } from "./page/BoardModifyPage";
import { ReviewPage } from "./page/ReviewPage";
import { StoreHomePage } from "./page/StoreHomePage";
import { ItemRecomend } from "./component/user/ItemRecommend";
import { BoardNoticeListPage } from "./page/BoardNoticeListPage";
import { BoardQnAListPage } from "./page/BoardQnAListPage";
import { OrderCartPage } from "./page/OrderCartPage";
import { MyReviewPage } from "./page/MyReviewPage";
import { AddressPage } from "./page/AddressPage";
import ScrollToTop from "./component/ScrollToTop";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <MainPage />,
  },
  {
    path: "/join",
    element: <JoinPage />,
  },
  {
    path: "/join/sns",
    element: <SNSJoinPage />,
  },
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/find/id",
    element: <FindIdPage />,
  },
  {
    path: "/find/password",
    element: <FindPasswordPage />,
  },
  {
    path: "/store/home",
    element:
    <>
    <ScrollToTop />
    <StoreHomePage />,
    </>
  },
  {
    path: "/store/hff",
    element: <StoreHFFPage />,
  },
  {
    path: "/store/hf",
    element: <StoreHFPage />,
  },
  {
    path: "/store/hp",
    element: <StoreHPPage />,
  },
  {
    path: "/store/problem",
    element: <StoreProblemPage />,
  },
  {
    path: "/cart",
    element: 
    <>
    <ScrollToTop />
    <CartPage />,
    </>
  },

  {
    path: "*",
    element: <NotFoundPage />,
  },
  {
    path: "/survey",
    element: <SurveyPage />,
  },
  {
    path: "/survey/item",
    element: <ItemRecomend />,
  },
  {
    path: "/store/productDetail/:itemNo",
    element:
    <>
    <ScrollToTop />
     <ProductDetailPage />,
     </>
  },
  {
    path: "/sandbox",
    element: <CheckoutPage />,
  },
  {
    path: "/sandbox/success",
    element: <SuccessPage />,
  },
  {
    path: "/sandbox/fail",
    element: <FailPage />,
  },
  {
    path: "/mypage/myinfo",
    element: 
    <>
    <ScrollToTop />
    <MyInfoPage />,
    </>
  },
  {
    path: "/mypage/orderlist",
    element: <OrderListPage />,
  },
  {
    path: "/mypage/orderdetail/:orderId",
    element: <OrderDetailPage />,
  },
  {
    path: "/board/notice",
    element: <BoardNoticeListPage />,
  },
  {
    path: "/board/QNA",
    element: <BoardQnAListPage />,
  },
  {
    path: "/boardDetail/:boardNo",
    element: <BoardDetailPage />,
  },
  {
    path: "/boardWrite",
    element: (
      <>
        <ScrollToTop />
        <BoardWritePage />
      </>
    ),
  },
  {
    path: "/boardModify/:boardNo",
    element: <BoardModifyPage />,
  },
  {
    path: "/mypage/review",
    element: <MyReviewPage />,
  },
  {
    path: "/mypage/address",
    element: <AddressPage />,
  },
  {
    path: "/order",
    element: <OrderPage />,
  },
  {
    path: "/review",
    element: <ReviewPage />,
  },
  {
    path: "/order/cart",
    element: <OrderCartPage />,
  },
]);
