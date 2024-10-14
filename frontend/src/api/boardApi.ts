import axios from "axios";

const api = axios.create({
    baseURL: "http://192.168.15.3:5173/api",
    withCredentials: true,
  });

// boardApi.ts

export const boardWrite = async (board) => {
    const response = await api.post("/board/createBoard",board);
    return response.data;
    
};

export const itemListByCategoryType = async(boardType:string)=>{
    const response = await api.get(`/items/category/type/${boardType}`);
    return response.data;
}

export const boardModify = async(board)=>{
    console.log("board >>>>> "+board);
    const response = await api.put("/board/updateBoard",board);
    console.log("response >>>>"+response)
    return response.data;
};

export const boardNoticeList = async()=>{
    const response = await api.get("/board/NoticeList");
    return response.data;
};

export const boardQnaList = async()=>{
    const response = await api.get("/board/QNAList");
    return response.data;
};

export const boardDetail = async(boardNo)=>{
    const response = await api.get(`/boardDetail/${boardNo}`);
    return response.data;
};

export const boardDelete = async(boardNo) => {
    const response = await api.delete(`/board/${boardNo}`)
    return response;

};

