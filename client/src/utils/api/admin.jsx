import { host } from "./host";

// Account Manager
export const readAccoutApi = (page) => `${host}/auth/account/reads?page=${page}`;
export const lockAccountApi = (id) => `${host}/auth/account/deactive/${id}`;
export const unlockAccountApi = (id) => `${host}/auth/account/active/${id}`;
export const reissuePassApi = (id) => `${host}/auth/account/password/reset/${id}`;
export const deleteAccountApi = (id) => `${host}/auth/account/delete/${id}`;
export const createAccountDoctorApi = `${host}/auth/account/create`;

//Patient Record Manager
export const readsPatientRecordAdminApi = `${host}/auth/patientrecord/reads`;
export const readPatientRecordAdminApi = (id) => `${host}/auth/patientrecord/read/${id}`;
export const createPatientRecordAdminApi = `${host}/auth/patientrecord/create`;
export const updatePatientRecordAdminApi = (id) => `${host}/auth/patientrecord/update/${id}`;
export const deletePatientRecordAdminApi = (id) => `${host}/auth/patientrecord/delete/${id}`;
export const searchPatientRecordAdminApi = (keyword, page) => `${host}/auth/patientrecord/search?keyword=${keyword}&page=${page}`;
export const searchAllPatientRecordAdminApi = (keyword) => `${host}/auth/patientrecord/search?keyword=${keyword}&size=100`;

//Medical Record Manager
export const readsMedicalRecordAdminApi = (id) => `${host}/auth/medical/reads/${id}`;
export const searchMedicalRecordAdminApi = (keyword, page) => `${host}/auth/medical/search?keyword=${keyword}&page=${page}`;
export const searchAllMedicalRecordAdminApi = (keyword) => `${host}/auth/medical/search?keyword=${keyword}&size=1000`;
export const readMedicalRecordDetailAdminApi = (id) => `${host}/auth/medical/read/detail/${id}`;
export const deleteMedicalRecordAdminApi = (id) => `${host}/auth/medical/delete/${id}`;
export const updateMedicalRecordAdminApi = (id) => `${host}/auth/medical/update/${id}`;
export const createMedicalRecordAdminApi = `${host}/auth/medical/create`;

//Diagnosis Manager
export const diagnosisImageApi = `${host}/auth/diagnosisimage/predict`;
export const saveDiagnosisImageApi = (id) => `${host}/auth/diagnosisimage/save/${id}`;
 
