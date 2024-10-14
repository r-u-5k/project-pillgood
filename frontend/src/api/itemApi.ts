import axios from "axios";

const api = axios.create({
    baseURL: "http://192.168.15.3:5173/api",
    withCredentials: true,
  });

export const itemListPilly = async()=>{
    const response = await api.get("/items/home");
    return response.data;
}

export const itemListAll = async()=>{
    const response = await api.get("/items/all");
    return response.data;
}

export const itemListByCategoryType = async(categoryType:string)=>{
    const response = await api.get(`/items/category/type/${categoryType}`);
    return response.data;
}

export const itemListByCategorySymptom = async(categorySymptom:string)=>{
    const response = await api.get(`/items/category/symptom/${categorySymptom}`);
    return response.data;
}

export const itemDetail = async(itemNo:string)=>{
    const response = await api.get(`/item/${itemNo}`);
    return response.data;
}