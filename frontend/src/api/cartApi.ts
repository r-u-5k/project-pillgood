import axios from "axios";



const api = axios.create({
  baseURL: "http://192.168.15.3:5173/api",
  withCredentials: true,
});

export const addCartItem = async (cartItem) =>{
  const response = await api.post('/cart',cartItem);
  console.log(response.data);
  return response.data;
}

export const removeCartItem = async (cartId)=>{
  const response = await api.delete(`/cart/${cartId}`)
  return response.data;
}

export const plusCartItemQty = async (cartId)=>{
  const response = await api.put(`/cart/plus/${cartId}`)
  return response;
}

export const minusCartItemQty = async (cartId)=>{
  const response = await api.put(`/cart/minus/${cartId}`)
  return response;
}

export const userCartQty = async (userId)=>{
  const repsonse = await api.get()
}

