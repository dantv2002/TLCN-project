import { Routes, Route } from 'react-router-dom';
import LoginPage from './pages/Auth/LoginPage';
import Pagenotfound from './pages/User/PageNotFound';
import HomePage from './pages/User/HomePage';
import RegisterPage from './pages/Auth/RegisterPage';
import VerifyRegisterPage from './pages/Auth/VerifyRegisterPage';
import ResetPasswordPage from './pages/Auth/ResetPasswordPage';
import VerifyResetPasswordPage from './pages/Auth/VerifyResetPasswordPage';
import Legal from './pages/User/Legal';
import PatientRecord from './pages/User/PatientRecord';
import ChangePasswordPatientForm from './components/Form/User/ChangePasswordPatientForm';
import CreatePatientRecordForm from './components/Form/User/CreatePatientRecordForm';
import UpdatePatientRecordForm from './components/Form/User/UpdatePatientRecordForm';
import MedicalRecord from './pages/User/MedicalRecord';
import MedicalRecordDetailForm from './components/Form/User/MedicalRecordDetailForm';
import DashboardDoctor from './pages/Doctor/DashboardDoctor';
import DashboardAdmin from './pages/Admin/DashboardAdmin';
import ChangePasswordAdminForm from './components/Form/Admin/ChangePasswordAdminForm';
import PatientRecordAdmin from './pages/Admin/PatientRecordAdmin';
import CreatePatientRecordAdminForm from './components/Form/Admin/CreatePatientRecordAdminForm';
import PatientRecordDetailAdminForm from './components/Form/Admin/PatientRecordDetailAdminForm';
import UpdatePatientRecordAdminForm from './components/Form/Admin/UpdatePatientRecordAdminForm';
import AccountAdmin from './pages/Admin/AccountAdmin';
import ReissuePasswordForm from './components/Form/Admin/ReissuePasswordForm';
import CreateAccountDoctorForm from './components/Form/Admin/CreateAccountDoctorForm';
import MedicalRecordAdmin from './pages/Admin/MedicalRecordAdmin';
import MedicalRecordDetailAdminForm from './components/Form/Admin/MedicalRecordDetailAdminForm';
import CreateMedicalRecordAdminForm from './components/Form/Admin/CreateMedicalRecordAdminForm';
import UpdateMedicalRecordAdminForm from './components/Form/Admin/UpdateMedicalRecordAdminForm';
import DiagnosisAdmin from './pages/Admin/DiagnosisAdmin';

function App() {
  return (
    <Routes>
      <Route path='/' element={<HomePage/>}/>
      <Route path='/login' element={<LoginPage/>}/>
      <Route path='/register' element={<RegisterPage/>}/>
      <Route path='/verifyregister' element={<VerifyRegisterPage/>}/>
      <Route path='/resetpassword' element={<ResetPasswordPage/>}/>
      <Route path='/verifyresetpassword' element={<VerifyResetPasswordPage/>}/>
      <Route path='/legal' element={<Legal />} />
      <Route path='/user/changepassword' element={<ChangePasswordPatientForm/>}/>
      <Route path='/user/patientrecord' element={<PatientRecord/>}/>
      <Route path='/user/patientrecord/create' element={<CreatePatientRecordForm/>}/>
      <Route path='/user/patientrecord/update' element={<UpdatePatientRecordForm/>}/>
      <Route path='/user/medicalrecord' element={<MedicalRecord/>}/>
      <Route path='/user/medicalrecord/detail/:id' element={<MedicalRecordDetailForm/>}/>
      <Route path='/doctor/dashboard' element={<DashboardDoctor/>}/>  
      <Route path='/admin/dashboard' element={<DashboardAdmin/>}/>
      <Route path='/admin/changepassword' element={<ChangePasswordAdminForm/>}/>
      <Route path='/admin/dashboard/account' element={<AccountAdmin/>}/>
      <Route path='/admin/dashboard/account/createdoctor' element={<CreateAccountDoctorForm/>}/>
      <Route path='/admin/dashboard/account/repass/:id' element={<ReissuePasswordForm/>}/>
      <Route path='/admin/dashboard/patientrecord' element={<PatientRecordAdmin/>}/>
      <Route path='/admin/dashboard/patientrecord/create' element={<CreatePatientRecordAdminForm/>}/>
      <Route path='/admin/dashboard/patientrecord/update/:id' element={<UpdatePatientRecordAdminForm/>}/>
      <Route path='/admin/dashboard/patientrecord/read/:id' element={<PatientRecordDetailAdminForm/>}/>
      <Route path='/admin/dashboard/medicalrecord' element={<MedicalRecordAdmin/>}/>
      <Route path='/admin/dashboard/medicalrecord/detail/:id' element={<MedicalRecordDetailAdminForm/>}/>
      <Route path='/admin/dashboard/medicalrecord/create/:id' element={<CreateMedicalRecordAdminForm/>}/>
      <Route path='/admin/dashboard/medicalrecord/update/:id' element={<UpdateMedicalRecordAdminForm/>}/>
      <Route path='/admin/dashboard/diagnosis' element={<DiagnosisAdmin/>}/>
      <Route path='*' element={<Pagenotfound/>}/>
    </Routes>
  );
}

export default App;
