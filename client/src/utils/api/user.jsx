import { host } from "./host";

//Change Password
export const changePasswordApi = `${host}/auth/changepass/me`;

//Patient Record
export const createPatienRecordApi = `${host}/auth/patientrecord/create/me`;
export const readPatientRecordApi = `${host}/auth/patientrecord/read/me`;
export const updatePatientRecordApi = `${host}/auth/patientrecord/update/me`;

//Medical Record
export const readMedicalRecordDetailApi = (id) => `${host}/auth/medical/read/detail/${id}`;
export const searchMedicalRecordApi = (keyword, page) => `${host}/auth/medical/search/me?keyword=${keyword}&page=${page}`