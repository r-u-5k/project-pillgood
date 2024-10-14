import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Table, Pagination } from "semantic-ui-react";
import { boardQnaList } from "@/api/boardApi";
import { Button } from "@/components/ui/button";
import { useRecoilValue } from "recoil";
import { userState } from "@/recoil/atoms";

export const BoardQnAList = () => {
  const [board, setBoard] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage] = useState(10);
  const loginUser = useRecoilValue(userState);

  useEffect(() => {
    const fetchData = async () => {
      const responseJsonObject = await boardQnaList();
      const sortedData = responseJsonObject.sort((a, b) => {
        return (
          new Date(b.boardDate as string).getTime() -
          new Date(a.boardDate as string).getTime()
        );
      });
      console.log(sortedData);
      setBoard(sortedData);
    };

    fetchData();
  }, []);

  // const countryOptions = [
  //   { key: 'NOTICE', value: 'NOTICE', text: '소식' },
  //   { key: 'QNA', value: 'QNA', text: '자주묻는질문' },
  // ];

  const typeMapping = {
    PRODUCT: "상품",
    MEMBERINFO: "회원정보",
    ORDERPAY: "주문/결제",
    EXCHANGERETURN: "교환/반품",
    DELIVERY: "배송",
    PILLYINFO: "필리케어",
    NEWS: "소식",
  };

  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = board.slice(indexOfFirstItem, indexOfLastItem);

  const handlePaginationChange = (e, { activePage }) =>
    setCurrentPage(activePage);

  return (
    <section className="w-[668px] h-full mx-auto pt-[240px] pb-[200px]">
      <div>
        <form>
          <div>
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
                {currentItems && currentItems.length > 0 ? (
                  currentItems.map((board) => {
                    return (
                      <>
                        {
                          <Table.Row key={board.boardNo}>
                            <Table.Cell
                              style={{ color: "orangered", fontWeight: "bold" }}
                              textAlign="center"
                            >
                              {typeMapping[board.boardType]}
                            </Table.Cell>
                            <Table.Cell textAlign="center">
                              <Link to={`/boardDetail/${board.boardNo}`}>
                                {board.boardTitle}
                              </Link>
                            </Table.Cell>
                            <Table.Cell textAlign="center">
                              {board.boardDate.substring(0, 10)}
                            </Table.Cell>
                          </Table.Row>
                        }
                      </>
                    );
                  })
                ) : (
                  <Table.Row>
                    <Table.Cell colSpan="3" textAlign="center">
                      No data available
                    </Table.Cell>
                  </Table.Row>
                )}
              </Table.Body>
            </Table>
            <div style={{ textAlign: "center", marginTop: "20px" }}>
              <Pagination
                activePage={currentPage}
                onPageChange={handlePaginationChange}
                totalPages={Math.ceil(board.length / itemsPerPage)}
              />
            </div>
            <div style={{ textAlign: "center", marginTop: "20px" }}>
              <Link to={"/boardWrite"}>
                {loginUser.role == "ADMIN" && (
                  <Button className="bg-gray-900 text-white font-semibold rounded-xl hover:bg-gray-900">
                    작성하기
                  </Button>
                )}
              </Link>
            </div>
          </div>
        </form>
      </div>
    </section>
  );
};
