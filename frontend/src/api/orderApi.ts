import axios from "axios";


const api = axios.create({
    baseURL: "http://192.168.15.3:8080",
    withCredentials: true,
  });
  export const createOrder = async(order:string)=>{
    const response = await api.post(`/order`,order,
{
    headers: {
        Authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
      },
}
    );
    console.log(order);
    return response.data;
}

export const createCartOrder = async(order:string)=>{
  const response = await api.post(`/order/cart`,order,
{
  headers: {
      Authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
    },
}
  );
  console.log(order);
  return response.data;
}