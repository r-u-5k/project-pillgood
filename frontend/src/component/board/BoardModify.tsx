import React, { useRef, useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Form, TextArea, FormField, Button, Grid } from 'semantic-ui-react';
import * as Api from '@/api/boardApi';


// const countryOptions = [
//     { key: 'PRODUCT', value: 'PRODUCT', text: '상품' },
//     { key: 'MEMBERINFO', value: 'MEMBERINFO', text: '회원정보' },
//     { key: 'ORDERPAY', value: 'ORDERPAY', text: '주문/결제' },
//     { key: 'EXCHANGERETURN', value: 'EXCHANGERETURN', text: '교환/반품' },
//     { key: 'DELIVERY', value: 'DELIVERY', text: '배송' },
//     { key: 'PILLYINFO', value: 'PILLYINFO', text: '필리케어' },
//     { key: 'NEWS', value: 'NEWS', text: '소식' },
// ];

const BoardModify = () => {
    const navigate = useNavigate();
    const { boardNo } = useParams();
    const formRef = useRef(null);
    const [board, setBoard] = useState({
        boardNo:boardNo,
        boardTitle: '',
        boardContent: '',
        boardType:  '',
        boardMenu: '',
        boardDate: '',
        adminNo:2,
    });
    
    useEffect(() => {
        const fetchBoardDetails = async () => {
            try {
                const boardDetails = await Api.boardDetail(boardNo);
                setBoard(boardDetails);
            } catch (error) {
                console.error('Failed to fetch board details', error);
            }
        };
        fetchBoardDetails(); // 컴포넌트가 마운트될 때 한 번 실행하여 게시판 상세 정보를 가져옴
    
    }, []);

    const onChangeBoardModifyForm = (e) => {
            setBoard({
                ...board,
                [e.target.name]: e.target.value,
            });
            console.log(board);
        };
        
        console.log("board11 >>>>"+board);
    

    const  boardModifyAction = async () => {
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
            const response = await Api.boardModify(board);
            console.log(response);
            navigate(`/boardDetail/${response.boardNo}`);
        } catch (error) {
            // 오류 처리
            console.error("게시판 수정실패", error);
            alert("게시판 수정실패");
        }
    };

    return (
        <Grid centered style={{ padding: '220px' }}>
        <Grid.Column style={{ maxWidth: '800px', width: '100%' }}>
        {/* <div style={{ padding: '220px', margin: '0px 350px' }}> */}
          
            <Form ref={formRef}>
                <FormField>
                    <label>제목</label>
                    <input
                        placeholder='제목'
                        onChange={ onChangeBoardModifyForm}
                        type="text"
                        style={{ width: 400 }}
                        name="boardTitle"
                        value={board.boardTitle}
                    />
                </FormField>
                <FormField>
                    <label>내용</label>
                    <TextArea
                        placeholder='내용'
                        style={{ minHeight: 500 }}
                        onChange={ onChangeBoardModifyForm}
                        name="boardContent"
                        value={board.boardContent}
                    />
                </FormField>
            </Form>
            <div style={{ textAlign: 'center', marginTop: '20px' }}>
                <Link to={`/boardDetail/${boardNo}`}>
                    <Button style={{ marginRight: '10px' }}>이전</Button>
                </Link>
                <Button onClick={ boardModifyAction}>수정하기</Button>
            </div>
        </Grid.Column>
    </Grid>
    );
};

export default BoardModify;
 