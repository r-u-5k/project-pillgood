import { useEffect, useState } from "react";
import * as userApi from "../../api/userApi";
import { useRecoilState } from "recoil";
import { userState } from "@/recoil/atoms";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Button } from "@/components/ui/button";
import { Checkbox } from "semantic-ui-react";
import { AddressUpdateContent } from "./AddressUpdateContent";
import { AddressCreateContent } from "./AddressCreateContent";
import { set } from "lodash";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
export const AddressContent = () => {
  const [loginUser, setLoginUser] = useRecoilState(userState);
  const [address, setAddress] = useState([

  ]);
  const [selectedAddr, setSelectedAddr] = useState({
    address: "",
    addressDetail: "",
    addressNo: "",
    defaultAddress: "",
    name: "",
    phone: "",
    request: "",
    userId: "",
    zipcode: "",
  });
  useEffect(() => {
    const addressList = async () => {
      const responseData = await userApi.userAddressList(loginUser.id);
      setAddress(responseData);
    };
    addressList();
  }, [loginUser]);
  const handleCheckboxChange = (index) => {
    setAddress((prevAddresses) =>
      prevAddresses.map((addr, i) => ({
        ...addr,
        defaultAddress: i === index,
      }))
    );
    const selectedAddress = address.find((addr, i) => i === index);
    if (selectedAddress) {
      setSelectedAddr(selectedAddress);
    }
  };
  const deleteAddress = async (addressNo) => {
    console.log(addressNo);
    const responseData = await userApi.deleteAddress(addressNo);
    setAddress((prevAddresses) => prevAddresses.filter((addr) => addr.addressNo !== addressNo));
  };
  const refreshAddressList = async () => {
    const responseData = await userApi.userAddressList(loginUser.id);
    setAddress(responseData);
  };

  const basicAddress = async (selectedAddr) => {
    try {
      // 모든 주소의 defaultAddress를 false로 설정
      const updatedAddresses = address.map((addr) => ({
        ...addr,
        defaultAddress: addr.addressNo === selectedAddr.addressNo
      }));

      // 서버에 업데이트된 주소 정보를 전송
      await Promise.all(
        updatedAddresses.map((addr) => userApi.updateAddress(addr))
      );

      // 상태를 업데이트하여 렌더링 트리거
      refreshAddressList();
      toast.info("기본배송지로 설정되었습니다");
    } catch (error) {
      console.error("Error updating default address:", error);
    }
  };
  console.log(address);
  console.log(selectedAddr);
  return (
    
    <section className="w-[1170px] h-full mx-auto pt-[180px] pb-[260px]">
       <ToastContainer position="bottom-left" autoClose={1000} hideProgressBar={true} closeOnClick={true} pauseOnHover={false} limit={10} />
      <div className="bg-white dark:bg-gray-950 rounded-lg shadow-lg p-6">
        <div className="flex items-center justify-between mb-6">
          <h2 className="text-2xl font-bold text-gray-900 dark:text-gray-50">배송지목록</h2>
        </div>

        <div className="overflow-x-auto">
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead className="text-center text-gray-500 dark:text-gray-400 font-medium">기본배송지</TableHead>
                <TableHead className="text-center text-gray-500 dark:text-gray-400 font-medium">이름</TableHead>
                <TableHead className="text-center text-gray-500 dark:text-gray-400 font-medium">우편번호</TableHead>
                <TableHead className="text-center text-gray-500 dark:text-gray-400 font-medium">주소</TableHead>
                <TableHead className="text-center text-gray-500 dark:text-gray-400 font-medium">전화번호</TableHead>
                <TableHead className="text-center text-gray-500 dark:text-gray-400 font-medium">배송지 수정</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody className="border-b-[1px]">
              {address.map((addr, index) => (
                <TableRow key={index} className="hover:bg-gray-100 dark:hover:bg-gray-800 transition-colors">
                  <TableCell className="text-center text-gray-900 dark:text-gray-50 font-medium">
                    <div className="justify-center flex">
                      <Checkbox checked={!!addr.defaultAddress } onChange={() => handleCheckboxChange(index)} />
                    </div>
                  </TableCell>
                  <TableCell className="text-center text-gray-700 dark:text-gray-300">{addr.name}</TableCell>
                  <TableCell className="text-center text-gray-700 dark:text-gray-300">{addr.zipcode}</TableCell>
                  <TableCell className="text-center text-gray-700 dark:text-gray-300">
                    {addr.address}&nbsp;&nbsp;{addr.addressDetail}
                  </TableCell>
                  <TableCell className="text-center text-gray-700 dark:text-gray-300">{addr.phone.replace(
                  /^(\d{3})(\d{3,4})(\d{4})$/,
                  "$1-$2-$3"
                )}</TableCell>
                  <TableCell>
                    <div className="justify-center flex items-center gap-2">
                      <AddressUpdateContent userId={loginUser.id} address={addr} onAddressAdded={refreshAddressList} />
                      <Button
                        variant="outline"
                        size="icon"
                        className="text-center text-gray-500 dark:text-gray-400 hover:text-gray-900 dark:hover:text-gray-50"
                        onClick={() => {
                          deleteAddress(addr.addressNo);
                        }}
                      >
                        삭제
                      </Button>
                    </div>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
          <br></br>
          <div className="flex items-center justify-center mb-6">
            <AddressCreateContent userId={loginUser.id} onAddressAdded={refreshAddressList} />
            <Button
              size="sm"
              className="w-[150px] h-[50px] ml-[15px] rounded-3xl bg-orange-500 hover:bg-orange-500 text-white font-semibold"
              onClick={() => {
                basicAddress(selectedAddr);
              }}
            >
              기본배송지로 설정
            </Button>
          </div>
        </div>
      </div>
    </section>
  );
};
