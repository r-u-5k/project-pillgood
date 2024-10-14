import React, { useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Select, Form, TextArea, FormField, Button } from "semantic-ui-react";
import * as Api from "@/api/boardApi";

const countryOptions = [
  { key: "PRODUCT", value: "PRODUCT", text: "상품" },
  { key: "MEMBERINFO", value: "MEMBERINFO", text: "회원정보" },
  { key: "ORDERPAY", value: "ORDERPAY", text: "주문/결제" },
  { key: "EXCHANGERETURN", value: "EXCHANGERETURN", text: "교환/반품" },
  { key: "DELIVERY", value: "DELIVERY", text: "배송" },
  { key: "PILLYINFO", value: "PILLYINFO", text: "필리케어" },
  { key: "NEWS", value: "NEWS", text: "소식" },
];

const BoardWrite = () => {
  const navigate = useNavigate();
  const formRef = useRef(null);
  const [board, setBoard] = useState({
    boardTitle: "",
    boardContent: "",
    boardType: "",
    boardMenu: "",
  });

  const onChangeBoardWriteForm = (e) => {
    if (e.target.value == "소식") {
      setBoard({
        ...board,
        [e.target.name]: e.target.value,
        boardMenu: "Notice",
      });
    } else {
      setBoard({
        ...board,
        [e.target.name]: e.target.value,
        boardMenu: "QNA",
      });
    }

    console.log(board);
  };

  const boardWriteAction = async () => {
    if (board.boardType === "") {
      alert("분류를 선택하세요.");
      return;
    }
    if (board.boardTitle === "") {
      alert("제목을 입력하세요.");
      formRef.current.boardTitle.focus();
      return;
    }
    if (board.boardContent === "") {
      alert("내용을 입력하세요.");
      formRef.current.boardContent.focus();
      return;
    }

    try {
      const response = await Api.boardWrite(board);
      console.log(response);
      navigate(`/boardDetail/${response.boardNo}`);
    } catch (error) {
      // 오류 처리
      console.error("Error occurred while creating board:", error);
      alert("게시판을 생성하는 데 문제가 발생했습니다.");
    }
  };

  return (
    <div style={{ padding: "220px", margin: "0px 350px" }}>
      <Select
        placeholder="분류"
        options={countryOptions}
        style={{ marginBottom: "40px" }}
        onChange={(e, { value }) =>
          setBoard({ ...board, boardType: String(value) })
        }
      />
      <Form ref={formRef}>
        <FormField>
          <label>제목</label>
          <input
            placeholder="제목"
            onChange={onChangeBoardWriteForm}
            type="text"
            style={{ width: 400 }}
            name="boardTitle"
            value={board.boardTitle}
          />
        </FormField>
        <FormField>
          <label>내용</label>
          <TextArea
            placeholder="내용"
            style={{ minHeight: 500 }}
            onChange={onChangeBoardWriteForm}
            name="boardContent"
            value={board.boardContent}
          />
        </FormField>
      </Form>
      <div style={{ textAlign: "center", marginTop: "20px" }}>
        <Button
          style={{ marginRight: "10px" }}
          onClick={() => {
            navigate(-1);
          }}
        >
          이전
        </Button>
        <Button onClick={boardWriteAction}>작성하기</Button>
      </div>
    </div>
  );
};

export default BoardWrite;
