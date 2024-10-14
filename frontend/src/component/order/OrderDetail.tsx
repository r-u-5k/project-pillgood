import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import axios from "axios";
import { TrashIcon } from "lucide-react";
import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export const OrderDetail = () => {
  const navigate = useNavigate();
  const { orderId } = useParams();
  const api = axios.create({
    baseURL: "http://192.168.15.3:8080/",
    withCredentials: true,
  });
  useEffect(() => {
    reponseOrderDetailList();
  }, []);

  const [orderItemList, setItemList] = useState([
    {
      item: {
        brand: "",
        categorySymptom: "",
        categoryType: "",
        description: "",
        itemImageDto: [{ img: "", url: "", type: "" }],
        name: "",
        no: "",
        price: "",
      },
      orderId: "",
      orderItemNo: "",
      orderItemQty: "",
    },
  ]);

  const reponseOrderDetailList = async () => {
    const responseJsonObject = await api.get(`/order/${orderId}`);
    const orderItemList = responseJsonObject.data.orderItems;
    setItemList(orderItemList);
  };

  return (
    <section className="w-[1170px] h-full mx-auto pt-[180px] pb-[260px]">
      <div className="flex flex-col md:grid md:grid-cols-4 gap-6">
        <div className="md:col-span-4 lg:col-span-3 xl:col-span-4 flex flex-col gap-6">
          <Card className="shadow-lg rounded-lg">
            <CardHeader>
              <CardTitle className="text-2xl font-bold text-center">주문 상세 내역</CardTitle>
            </CardHeader>
            <CardContent>
              <Table className="min-w-full divide-y divide-gray-200">
                <TableHeader className="bg-gray-50">
                  <TableRow>
                    <TableHead className="w-[150px] py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">제품사진</TableHead>
                    <TableHead className="py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">이름</TableHead>
                    <TableHead className="py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">수량</TableHead>
                    <TableHead className="py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">가격</TableHead>
                    <TableHead className="py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">총 가격</TableHead>
                    <TableHead className="py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider"></TableHead>
                  </TableRow>
                </TableHeader>
                <TableBody className="bg-white divide-y divide-gray-200">
                  {orderItemList.map((item) => (
                    <TableRow key={item.orderItemNo} className="hover:bg-gray-50">
                      <TableCell className="w-[150px] h-[150px] text-center">
                        <img
                          alt="Product image"
                          className="w-[140px] h-[140px] aspect-square rounded-md object-cover mx-auto"
                          src={item.item.itemImageDto[0].url}
                        />
                      </TableCell>
                      <TableCell className="text-center text-sm font-medium text-gray-900" style={{ fontSize: 20 }}>
                        {item.item.name}
                      </TableCell>
                      <TableCell className="text-center text-sm text-gray-500" style={{ fontSize: 20 }}>
                        {item.orderItemQty}
                      </TableCell>
                      <TableCell className="text-center text-sm text-gray-500" style={{ fontSize: 20 }}>
                        {item.item.price.toLocaleString()}
                      </TableCell>
                      <TableCell className="text-center text-sm text-gray-500" style={{ fontSize: 20 }}>
                        {(Number(item.item.price) * Number(item.orderItemQty)).toLocaleString()}
                      </TableCell>
                      <TableCell className="text-center">
                        <Button type="button" variant="outline" className="bg-orange-600 w-[70px] h-[30px] hover:bg-orange-600 text-white rounded-xl"
                        onClick={()=>{
                          navigate(`/store/productDetail/${item.item.no}`,{state:'review'});
                        }}>
                        리뷰쓰기
                        </Button>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </CardContent>
          </Card>
        </div>
      </div>
    </section>
  );
};
