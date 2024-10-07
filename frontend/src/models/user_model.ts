export interface LoginUser {
    id: string;
    password: string;
}

export interface JoinUser {
    email: string;
    password: string;
    name: string;
    birthday: string;
	gender: string;
	phone: string;
}

export interface UpdateUser {
    id: string;
    email: string;
    password: string;
    oldPassword: string;
    token: string;
}