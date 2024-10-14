import { Button } from "@/components/ui/button";
import { useEffect, useState } from "react";
import { Modal } from "semantic-ui-react";
//import {Modal} from "react-modal";
import { Input } from "@/components/ui/input";
import { Label } from "semantic-ui-react";
import * as userApi from "../../api/userApi";
import { Postcode } from "../Postcode";
//import {Modal} from "react-modal";
export const AddressUpdateContent = ({ userId, address,onAddressAdded }) => {
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
  useEffect(() => {
    setAddr(address);
  }, [address]);
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

  const updateAddress = async () => {
    const responseData = await userApi.updateAddress(addr);
    console.log(responseData);
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
        <Button variant="outline" size="icon" className="text-center text-gray-500 dark:text-gray-400 hover:text-gray-900 dark:hover:text-gray-50">
          수정
        </Button>
      }
    >
      {/* 모달 내용 */}
      <Modal.Header>배송지 수정</Modal.Header>
      <Modal.Content>
        <Modal.Description>
          <h2 className="text-[20px] mt-[30px]">배송 정보</h2>
          <div className="mt-[30px]">
            <Label htmlFor="name">이름</Label>
            <Input type="text" placeholder="수령인 이름을 입력해 주세요." className="mt-[10px]" name="name" value={addr.name} onChange={handleChangeForm} />
          </div>
          <div className="mt-[30px]">
            <Label htmlFor="phone">연락처</Label>
            <Input type="text" placeholder="수령인 연락처를 입력해 주세요." className="mt-[10px]" name="phone" value={addr.phone} onChange={handleChangeForm} />
          </div>
          <div className="mt-[30px]">
            <Label htmlFor="postcode">우편번호</Label>
            <div className="flex flex-row w-full mt-[10px]">
              <Input
                type="text"
                placeholder="우편번호"
                disabled
                className="w-[70%] mr-[10px] bg-gray-300 border-[1px] border-gray-900"
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
              className="mt-[10px] bg-gray-300 border-[1px] border-gray-900"
              name="address"
              value={addr.address}
              onChange={handleChangeForm}
            />
          </div>
          <div className="mt-[30px]">
            <Label htmlFor="address">나머지 주소</Label>
            <Input type="text" placeholder="나머지 주소를 입력해주세요." className="mt-[10px]" name="addressDetail" value={addr.addressDetail} onChange={handleChangeForm} />
          </div>
        </Modal.Description>
      </Modal.Content>
      {/* 모달 액션 버튼 */}
      <Modal.Actions>
        <Button color="black" onClick={updateAddress}>
          수정하기
        </Button>
        <Button color="black" onClick={() => setOpen(false)}>
          취소하기
        </Button>
        {/* 다른 액션 버튼 추가 */}
      </Modal.Actions>
    </Modal>
  );
};
