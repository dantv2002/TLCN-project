import { host } from "./host";

//Change Password
export const changePasswordApi = `${host}/auth/changepass/me`;

//Patient Record
export const createPatienRecordApi = `${host}/auth/patientrecord/create/me`;
export const readPatientRecordApi = `${host}/auth/patientrecord/read/me`;
export const updatePatientRecordApi = `${host}/auth/patientrecord/update/me`;

//Medical Record
export const readMedicalRecordApi = (page) => `${host}/auth/medical/read/me?page=${page}`;
export const readMedicalRecordDetailApi = (id) => `${host}/auth/medical/read/detail/${id}`;