import { JoinUser, LoginUser, UpdateUser } from "@/models/user_model";
import axios from "axios";
import exp from "constants";

const loadTokenFromCookie = (): string | undefined => {
  const cookies = document.cookie.split(";");
  for (let i = 0; i < cookies.length; i++) {
    const cookie = cookies[i].trim();
    if (cookie.startsWith("jwtToken=")) {
      return cookie.substring("jwtToken=".length);
    }
  }
  return undefined; // 토큰이 없는 경우
};

const api = axios.create({
  baseURL: "http://192.168.15.3:8080/api",
  withCredentials: true,
});

/*
api.interceptors.request.use((config)=>{
  console.log('실행돼따');
  const token = loadTokenFromCookie();
  console.log(token);
  if(token){
    config.headers.Authorization=`Bearer ${token}`;
  }else{
    return Promise.reject({
      response:{
        msg:'로그인이 필요합니다.'
      }
    });
  }
  return config;
});
*/

const saveTokenToCookie = (token: string) => {
  // 토큰 만료일자 추출 (Payload에서)
  console.log(token);
  const tokenPayload = JSON.parse(atob(token.split(".")[1]));
  const expiresAt = new Date(tokenPayload.exp * 1000);
  console.log("잘되고있는거지?");
  // 쿠키 설정
  document.cookie = `jwtToken=${token};expires=${expiresAt};path=/`;
};

const removeTokenFromCookie = () => {
  document.cookie = "jwtToken=;expires=Thu, 01 Jan 1970 00:00:01 GMT;path=/";
};

export const userLoginActionSession = async (user: LoginUser) => {
  const response = await api.post("/user/login/session", user);
  console.log(response);
  console.log(response.data);
  return response.data;
};

export const idDuplicatedCheck = async (id: string) => {
  const response = await api.get(`/user/check_id/${id}`);
  console.log(response);
  console.log(response.data);
  return response.data;
};

export const loginCheckSession = async () => {
  const response = await api.get("/user/session");
  console.log(response);
  console.log(response.data);
  return response.data;
};

export const loginCheckToken = async () => {
  const response = await api.get("/user/validate");
  console.log(response);
  console.log(response.data);
  return response.data;
};

export const findIdAction = async (phone) => {
  const response = await api.get(`/user/findid/${phone}`);
  console.log(response);
  console.log(response.data);
  return response.data;
};

export const findPasswordAction = async (email) => {
  const response = await api.get(`/user/findpassword/${email}`);
  return response.data;
};

export const userCartList = async (userId) => {
  const response = await api.get(`/cart/${userId}`);
  console.log(response.data);
  return response.data;
};

export const userJoinAction = async (user: JoinUser) => {
  const response = await api.post("/user/join", user);
  return response.data;
};

export const userLoginActionToken = async (user: LoginUser) => {
  const response = await api.post("/user/login/token", user);
  return response.data;
};
export const logoutAction = async () => {
  localStorage.removeItem("jwtToken");
};
export const handleUpdateUser = async (loginUser: UpdateUser) => {
  const response = await api.put(
    `/user/${loginUser.id}`,
    {
      id: loginUser.id,
      email: loginUser.email,
      password: loginUser.password,
      oldPassword: loginUser.oldPassword,
    },
    {
      headers: {
        Authorization: `Bearer ${loginUser.token}`,
      },
    }
  );
  console.log(response.data);
  return response.data;
};

export const userDeleteAction = async (user) => {
  const response = await api.delete(`/user/${user.id}`);
  return response;
};

export const addCartItem = async (cartItem) => {
  const response = await api.post("/cart", cartItem);
  console.log(response.data);
  return response.data;
};

export const removeCartItem = async (cartId) => {
  const response = await api.delete(`/cart/${cartId}`);
  return response.data;
};

export const plusCartItemQty = async (cartId) => {
  const response = await api.put(`/cart/plus/`);
};

export const kakaoLoginUser = async (kakaoToken) => {
  const response = await api.get("/kakao_userinfo_with_token", {
    params: {
      authorize_access_token: kakaoToken,
    },
  });
  console.log(kakaoToken);
  return response.data;
};


export const naverLoginUser = async (naverToken: string) => {
  console.log('Authorization Header:', `Bearer ${naverToken}`);
  console.log('API Request URL:', api.defaults.baseURL + '/naver_userinfo_with_token');
  const response = await api.get('/naver_userinfo_with_token', {
      headers: {
        'Authorization': `Bearer ${naverToken}`,
      },
      params: {
        authorize_access_token: naverToken,
      },
    });
    console.log(naverToken);
    return response.data;
};

export const userAddressList = async (userId) => {
  const response = await api.get(`/address/user/${userId}`);
  return response.data;
};

export const deleteAddress = async (addrId) => {
  const response = await api.delete(`/address/delete/${addrId}`);
  return response.data;
};
export const updateAddress = async (addr) => {
  const response = await api.put(`/address/update`,addr);
  return response.data;
};
export const createAddress = async (addr) => {
  const response = await api.post(`/address/create`,addr);
  return response.data;
};
