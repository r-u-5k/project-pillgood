import { ProductDetailContent } from "@/component/product/ProductViewContent";
import { DefaultLayout } from "../component/layout/Layout";
import { useCartQty } from "@/api/Hooks/useCartQty";
import { useRecoilState } from "recoil";
import { userState } from "@/recoil/atoms";

export const ProductDetailPage = () => {
  return (
    <DefaultLayout>
      <ProductDetailContent />
    </DefaultLayout>
  );
};
