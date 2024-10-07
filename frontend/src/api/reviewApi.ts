import axios from "axios";



const api = axios.create({
  baseURL: "http://192.168.15.3:5173/api",
  withCredentials: true,
});

export const createReview = async(review)=>{
    const response = await api.post('/review',review);
    console.log(response.data); 
    return response.data;
}

export const findReviewByItem = async(itemNo) =>{
    const response = await api.get(`/review/${itemNo}`);
    console.log(response.data);
    return response.data;
}

export const findReviewAll = async()=>{
  const response = await api.get('/review');
  console.log(response.data);
  return response.data;
}
export const findReviewByUser = async(userId)=>{
  const response = await api.get(`/review/user/${userId}`)
  return response.data;
}