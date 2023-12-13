export const host = "https://3.24.181.0/api";

//Login Register ResetPasswordx`
export const loginApi = `${host}/login`;
export const registerApi = `${host}/sendemailsignup`;
export const confirmRegister = `${host}/signup`;
export const resetpassApi = `${host}/sendemailresetpass`;
export const confirmReset = `${host}/resetpass`;
export const logoutApi = `${host}/auth/logout`;

//Admin
//Accout
export const readAccoutApi = (page) =>
	`${host}/auth/account/reads?page=${page}&size=5`;
export const lockAccountApi = (id) => `${host}/auth/account/deactive/${id}`;
export const unlockAccountApi = (id) => `${host}/auth/account/active/${id}`;
export const reissuePassApi = (id) =>
	`${host}/auth/account/password/reset/${id}`;
export const deleteAccountApi = (id) => `${host}/auth/account/delete/${id}`;
export const createAccountDoctorApi = `${host}/auth/account/create`;

//Patient
export const readsPatientRecordAdminApi = `${host}/auth/patientrecord/reads`;
export const readPatientRecordAdminApi = (id) =>
	`${host}/auth/patientrecord/read/${id}`;
export const createPatientRecordAdminApi = `${host}/auth/patientrecord/create`;
export const updatePatientRecordAdminApi = (id) =>
	`${host}/auth/patientrecord/update/${id}`;
export const deletePatientRecordAdminApi = (id) =>
	`${host}/auth/patientrecord/delete/${id}`;
export const searchPatientRecordAdminApi = (keyword, page) =>
	`${host}/auth/patientrecord/search?keyword=${keyword}&page=${page}`;
export const searchAllPatientRecordAdminApi = (keyword) =>
	`${host}/auth/patientrecord/search?keyword=${keyword}&size=100`;

//Medical Record Manager
export const readsMedicalRecordAdminApi = (id) =>
	`${host}/auth/medical/reads/${id}`;
export const readsMedicalPatientAdminApi = `${host}/auth/patientrecord/search?size=1000`;
export const readsMedicalDoctorAdminApi = `${host}/auth/doctor/reads?size=1000`;
export const readMedicalRecordByPatientDoctorAdminApi = (
	patientid,
	doctorid,
	page,
	isReadAll
) =>
	`${host}/auth/medical/search?patientId=${patientid}&doctorId=${doctorid}&page=${page}&isReadAll=${isReadAll}`;
export const readMedicalRecordDetailAdminApi = (id) =>
	`${host}/auth/medical/read/detail/${id}`;
export const deleteMedicalRecordAdminApi = (id) =>
	`${host}/auth/medical/delete/${id}`;
export const updateMedicalRecordAdminApi = (id) =>
	`${host}/auth/medical/update/${id}`;
export const createMedicalRecordAdminApi = `${host}/auth/medical/create`;

//Diagnosis Manager
export const diagnosisImageApi = `${host}/auth/diagnosisimage/predict`;
export const saveDiagnosisImageApi = (id) =>
	`${host}/auth/diagnosisimage/save/${id}`;

//User
//Change Password
export const changePasswordApi = `${host}/auth/changepass/me`;

//Patient Record
export const createPatienRecordApi = `${host}/auth/patientrecord/create/me`;
export const readPatientRecordApi = `${host}/auth/patientrecord/read/me`;
export const updatePatientRecordApi = `${host}/auth/patientrecord/update/me`;

//Medical Record
export const readMedicalRecordDetailApi = (id) =>
	`${host}/auth/medical/read/detail/${id}`;
export const searchMedicalRecordApi = (keyword, page) =>
	`${host}/auth/medical/search/me?keyword=${keyword}&page=${page}`;
