import { host } from "./host";

export const loginApi = `${host}/login`;
export const registerApi = `${host}/sendemailsignup`;
export const confirmRegister = `${host}/signup`;
export const resetpassApi = `${host}/sendemailresetpass`;
export const confirmReset = `${host}/resetpass`;
export const logoutApi = `${host}/auth/logout`;