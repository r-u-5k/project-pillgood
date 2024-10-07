import * as userApi from "@/api/userApi";
import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { userState } from "@/recoil/atoms";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import {
  Item,
  ItemContent,
  ItemDescription,
  ItemHeader,
  Modal,
  ModalActions,
  ModalContent,
  ModalDescription,
  ModalHeader
} from "semantic-ui-react";
import * as itemApi from "../../api/itemApi";

export const ItemRecomend = () => {
  const navigate = useNavigate();
  const [loginUser, setLoginUser] = useRecoilState(userState);
  const location = useLocation();
  const { survey } = location.state;
  const [open, setOpen] = useState(false);
  const [items, setItems] = useState([]);
  const [selectedItems, setSelectedItems] = useState([]);

  useEffect(() => {
    recomendItem();
    setOpen(true); // 페이지가 로드될 때 모달을 열기
  }, []);

  const recomendItem = async () => {
    const response = await itemApi.itemListByCategorySymptom(survey.symptomType);
    setItems(response);
    console.log(response);
  };

  const handleChange = (e) => {
    const value = e.target.value;
    console.log(`Checkbox clicked with value: ${value}`);
    console.log(value)
    setSelectedItems((prevSelectedItems) => {
      if (prevSelectedItems.includes(value)) {
        return prevSelectedItems.filter((item) => item !== value);
      } else {
        return [...prevSelectedItems, value];
      }
    });
  };

  const addCartItems = async () => {
    console.log("Selected Items:", selectedItems);
    const cartPromises = selectedItems.map((itemId) => {
      const cartItem = {
        cartQty: 1,
        itemId: itemId,
        userId: loginUser.id,
      };
      console.log("Adding cart item:", cartItem);
      return userApi.addCartItem(cartItem);
    });
    console.log('>>>');
    console.log(cartPromises);
    const responses = await Promise.all(cartPromises);
    console.log("Add to cart responses:", responses);
    setOpen(false); // 모달 닫기
    navigate("/cart");
  };

  return (
    <Modal
      size="small"
      style={{
        position: "fixed",
        top: "50%",
        left: "50%",
        transform: "translate(-50%, -50%)",
      }}
      onClose={() => {
        setOpen(false);
        navigate(-1);
      }}
      onOpen={() => setOpen(true)}
      open={open}
    >
      <ModalHeader>AI 추천 영양제</ModalHeader>
      <p className='my-[30px]' style={{ textAlign: "center", fontSize: "20pt" }}>
        추천영양성분
        <span className="text-orange-500 ml-[10px]">{items.length}</span>
      </p>
      {items.map((item, index) => (
        <ModalContent
          image
          key={index}
          style={{ width: "95%" }}
          className={`border-[1px] rounded-2xl hover:bg-orange-300 m-[15px] w-[95%]`}
        >
          <Checkbox
            name="no"
            value={item.no}
            onClick={handleChange}
            className="my-auto border-black"
          />
          <img src={item.itemImageDto[0].url} alt="" className="w-[82px] h-[82px] bg-gray-100 rounded-xl mx-[15px]" />
          <ModalDescription className="w-[80%]">
            <Item>
              <ItemContent>
                <ItemHeader as="a" className="font-semibold">{item.name}</ItemHeader>
                <ItemDescription className="mt-[5px]">
                  <p>{item.description}</p>
                  <p className="mt-[5px]">상품가격: {item.price.toLocaleString()}</p>
                </ItemDescription>
              </ItemContent>
            </Item>
          </ModalDescription>
        </ModalContent>
      ))}

      <ModalActions className="flex justify-center">
        <Button variant="outline" className="border-[1px] rounded-xl mr-[10px]"
          onClick={() => {
            setOpen(false);
            navigate(-1);
          }}
        >
          취소하기
        </Button>
        <Button variant="default" className="bg-gray-900 text-white rounded-xl font-semibold hover:bg-gray-900" onClick={addCartItems}>
          장바구니 담기
        </Button>
      </ModalActions>
    </Modal>
  );
};
