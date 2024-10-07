import React, { useEffect, useState } from "react";
import { Table, Modal, Grid } from "semantic-ui-react";
import { boardDetail, boardDelete } from "@/api/boardApi"; // API 함수 임포트
import { useParams, Link, useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { userState } from "@/recoil/atoms";
import { Button } from "@/components/ui/button";

// 게시판 상세 정보를 보여주는 컴포넌트
export const BoardDetail = () => {
  const navigate = useNavigate();
  const { boardNo } = useParams(); // URL의 파라미터(boardNo) 값을 가져오기
  const loginUser = useRecoilValue(userState);
  const [board, setBoard] = useState({
    boardNo: "", // 게시글 번호
    boardTitle: "", // 게시글 제목
    boardContent: "", // 게시글 내용
    boardType: "", // 게시글 분류
    boardMenu: "", // 게시글 메뉴
    boardDate: "", // 게시글 작성일
    adminNo: "", // 관리자 번호
  });
  const [open, setOpen] = useState(false); // 모달 열림 상태 관리

  // 컴포넌트가 마운트될 때 데이터를 가져오는 useEffect
  useEffect(() => {
    const fetchBoardDetail = async () => {
      // 게시판 상세 정보를 가져오는 비동기 함수
      try {
        const responseJsonObject = await boardDetail(boardNo); // API를 호출하여 게시판 상세 정보를 가져오기
        console.log(responseJsonObject);
        setBoard(responseJsonObject); // 가져온 데이터를 상태로 설정
      } catch (error) {
        console.error("Error fetching data:", error); // 오류 발생 시 콘솔에 오류 메시지 출력
      }
    };
    fetchBoardDetail(); // 컴포넌트가 마운트될 때 한 번 실행하여 게시판 상세 정보를 가져옴
  }, [boardNo]); // boardNo가 변경될 때마다 실행

  // 게시글 삭제 함수
  const boardDeleteAction = async () => {
    try {
      const response = await boardDelete(boardNo);
      console.log(response);
      navigate(-1);
    } catch (error) {
      console.error("Error deleting board:", error); // 오류 발생 시 콘솔에 오류 메시지 출력
    }
  };

  const typeMapping = {
    PRODUCT: "상품",
    MEMBERINFO: "회원정보",
    ORDERPAY: "주문/결제",
    EXCHANGERETURN: "교환/반품",
    DELIVERY: "배송",
    PILLYINFO: "필리케어",
    NEWS: "소식",
  };

  return (
    <Grid centered style={{ padding: "220px" }}>
      <Grid.Column style={{ maxWidth: "800px", width: "100%" }}>
        {/*  <div style={{ padding: '220px', margin: '0px 350px' }}> */}
        <Table celled>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell width={2} textAlign="center">
                분류
              </Table.HeaderCell>
              <Table.HeaderCell textAlign="center">제목</Table.HeaderCell>
              <Table.HeaderCell width={3} textAlign="center">
                날짜
              </Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            <Table.Row>
              <Table.Cell
                style={{ color: "orangered", fontWeight: "bold" }}
                textAlign="center"
              >
                {typeMapping[board.boardType]}
              </Table.Cell>
              <Table.Cell textAlign="center">{board.boardTitle}</Table.Cell>
              <Table.Cell textAlign="center">
                {board.boardDate.substring(0, 10)}
              </Table.Cell>
            </Table.Row>
          </Table.Body>
        </Table>
        <Table celled style={{ borderTop: "white" }}>
          <Table.Body>
            <Table.Row>
              <Table.Cell
                style={{
                  borderColor: "white",
                  fontSize: "16px",
                  lineHeight: "1.6",
                  whiteSpace: "pre-wrap",
                }}
              >
                {board.boardContent}
              </Table.Cell>
            </Table.Row>
          </Table.Body>
        </Table>
        <div style={{ textAlign: "center", marginTop: "20px" }}>
          <Link to={"/board/notice"}>
            <Button className="bg-gray-900 text-white font-semibold rounded-xl hover:bg-gray-900 mr-[10px]">
              목록
            </Button>
          </Link>
          {loginUser.role == "ADMIN" && (
            <Link to={`/boardModify/${boardNo}`}>
              <Button className="bg-gray-900 text-white font-semibold rounded-xl hover:bg-gray-900 mr-[10px]">
                수정하기
              </Button>
            </Link>
          )}
          {loginUser.role == "ADMIN" && (
            <Button
              className="bg-gray-900 text-white font-semibold rounded-xl hover:bg-gray-900"
              onClick={() => setOpen(true)}
            >
              삭제하기
            </Button>
          )}
        </div>

        <Modal
          open={open}
          onClose={() => setOpen(false)}
          size="mini"
          style={{
            top: "20%",
            left: "50%",
            transform: "translate(-50%, 50%)",
            width: "25%",
            height: "20%",
          }}
        >
          <Modal.Header>삭제 확인</Modal.Header>
          <Modal.Content>
            <p>이 게시글을 삭제하시겠습니까?</p>
          </Modal.Content>
          <Modal.Actions>
            <Button
              className="bg-gray-900 text-white font-semibold rounded-xl hover:bg-gray-900 mr-[10px]"
              onClick={() => {
                setOpen(false);
                boardDeleteAction();
              }}
            >
              삭제
            </Button>
            <Button
              className="bg-white text-gray-900 font-semibold rounded-xl hover:bg-white mr-[10px] border-[1px] border-gray-800"
              onClick={() => setOpen(false)}
            >
              취소
            </Button>
          </Modal.Actions>
        </Modal>
      </Grid.Column>
    </Grid>
  );
};

export default BoardDetail;
