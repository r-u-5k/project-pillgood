import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useState } from "react";
import { Label, Modal } from "semantic-ui-react";
import { Postcode } from "../Postcode";
import * as userApi from "../../api/userApi";
//import {Modal} from "react-modal";
export const AddressCreateContent = ({ userId ,onAddressAdded}) => {
  const [open, setOpen] = useState(false);
  const [addr, setAddr] = useState({
    address: "",
    addressDetail: "",
    addressNo: "",
    defaultAddress: false,
    name: "",
    phone: "",
    request: "",
    userId: userId,
    zipcode: "",
  });

  const handleChangeForm = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setAddr({
      ...addr,
      [name]: value,
    });
    console.log(addr);
  };

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
    setAddr({
      ...addr,
      address: fullAddress,
      zipcode: zoneCode,
    });
    console.log(addr);
    return;
  };

  const createAddress = async () => {
    const responseData = await userApi.createAddress(addr);
    console.log(responseData);
    setAddr({
      address: "",
      addressDetail: "",
      addressNo: "",
      defaultAddress: false,
      name: "",
      phone: "",
      request: "",
      userId: userId,
      zipcode: "",
    });
    setOpen(false);
    if (onAddressAdded) {
      onAddressAdded();
    }
  };
  return (
    <Modal
      style={{ position: "fixed", top: "50%", left: "50%", transform: "translate(-50%, -50%)" }}
      centered={true}
      open={open}
      onClose={() => setOpen(false)}
      onOpen={() => setOpen(true)}
      trigger={
        <Button size="sm" className="h-[50px] w-[150px] rounded-3xl bg-orange-500 text-white hover:bg-orange-500 font-semibold">
          새로운 배송지 추가
        </Button>
      }
    >
      {/* 모달 내용 */}
      <Modal.Header>새로운 배송지 추가</Modal.Header>
      <Modal.Content>
        <Modal.Description>
          <h2 className="text-[20px] mt-[30px]">배송 정보</h2>
          <div className="mt-[30px]">
            <Label htmlFor="name">이름</Label>
            <Input type="text" placeholder="수령인 이름을 입력해 주세요." className="mt-[10px] h-[45px] border-[1px] border-gray-900 rounded-xl focus:border-[2px]" name="name" value={addr.name} onChange={handleChangeForm} />
          </div>
          <div className="mt-[30px]">
            <Label htmlFor="phone">연락처</Label>
            <Input type="text" placeholder="수령인 연락처를 입력해 주세요." className="mt-[10px] h-[45px] border-[1px] border-gray-900 rounded-xl focus:border-[2px]" name="phone" value={addr.phone} onChange={handleChangeForm} />
          </div>
          <div className="mt-[30px]">
            <Label htmlFor="postcode">우편번호</Label>
            <div className="flex flex-row w-full mt-[10px]">
              <Input
                type="text"
                placeholder="우편번호"
                disabled
                className="w-[70%] mr-[10px] bg-gray-300 border-[1px] border-gray-900 h-[45px]"
                name="postcode"
                value={addr.zipcode}
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
              className="mt-[10px] bg-gray-300 border-[1px] h-[45px] border-gray-900"
              name="address"
              value={addr.address}
              onChange={handleChangeForm}
            />
          </div>
          <div className="mt-[30px]">
            <Label htmlFor="address">나머지 주소</Label>
            <Input type="text" placeholder="나머지 주소를 입력해주세요." className="mt-[10px] h-[45px] border-[1px] border-gray-900 rounded-xl focus:border-[2px]" name="addressDetail" value={addr.addressDetail} onChange={handleChangeForm} />
          </div>
        </Modal.Description>
      </Modal.Content>
      {/* 모달 액션 버튼 */}
      <Modal.Actions className="justify-center flex">
        <Button className="text-white rounded-xl mr-[20px] bg-gray-900 hover:bg-gray-900 h-[40px]" onClick={createAddress}>
          추가하기
        </Button>
        <Button className="text-white rounded-xl bg-gray-900 hover:bg-gray-900 h-[40px]" onClick={() => setOpen(false)}>
          취소하기
        </Button>
        {/* 다른 액션 버튼 추가 */}
      </Modal.Actions>
    </Modal>
  );
};
