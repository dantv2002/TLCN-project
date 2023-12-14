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
import DashboardAdmin from './pages/Admin/DashboardAdmin';
import DashboardDoctor from './pages/Doctor/DashboardDoctor';
import Account from './pages/Admin/Account';
import PatientRecordAdmin from './pages/Admin/PatientRecordAdmin';
import MedicalRecordAdmin from './pages/Admin/MedicalRecordAdmin';
import ChangePasswordAdminForm from './components/Form/Admin/ChangePasswordAdminForm';
import ReissuePassword from './components/Form/Admin/Account/ReissuePassword';
import CreateAccountDoctor from './components/Form/Admin/Account/CreateAccountDoctor';
import PatientRecordDetailAdmin from './components/Form/Admin/PatientRecord/PatientRecordDetailAdmin';
import CreatePatientRecordAdmin from './components/Form/Admin/PatientRecord/CreatePatientRecordAdmin';
import UpdatePatientRecordAdmin from './components/Form/Admin/PatientRecord/UpdatePatientRecordAdmin';
import MedicalRecordDetailAdmin from './components/Form/Admin/MedicalRecord/MedicalRecordDetailAdmin';
import UpdateMedicalRecordAdmin from './components/Form/Admin/MedicalRecord/UpdateMedicalRecordAdmin';
import CreateMedicalRecordAdmin from './components/Form/Admin/MedicalRecord/CreateMedicalRecordAdmin';
import Doctor from './pages/Admin/Doctor';
import DoctorDetail from './components/Form/Admin/Doctor/DoctorDetail';
import UpdateDoctor from './components/Form/Admin/Doctor/UpdateDoctor';
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
      <Route path="/dashboard" element={<DashboardAdmin/>}>
        <Route path='changepass' element={<ChangePasswordAdminForm/>}/>
        <Route path='account' element={<Account/>}/>
        <Route path='account/createdoctor' element={<CreateAccountDoctor/>}/>
        <Route path='account/reissuepass/:id' element={<ReissuePassword/>}/>
        <Route path='doctor' element={<Doctor/>}/>
        <Route path='doctor/detail/:id' element={<DoctorDetail/>}/>
        <Route path='doctor/update/:id' element={<UpdateDoctor/>}/>
        <Route path='patient' element={<PatientRecordAdmin/>}/>
        <Route path='patient/read/:id' element={<PatientRecordDetailAdmin/>}/>
        <Route path='patient/create' element={<CreatePatientRecordAdmin/>}/>
        <Route path='patient/update/:id' element={<UpdatePatientRecordAdmin/>}/>
        <Route path='medical' element={<MedicalRecordAdmin/>}/>
        <Route path='medical/create' element={<CreateMedicalRecordAdmin/>}/>
        <Route path='medical/detail/:id' element={<MedicalRecordDetailAdmin/>}/>
        <Route path='medical/update/:id' element={<UpdateMedicalRecordAdmin/>}/>
        <Route path='diagnose' element={<DiagnosisAdmin/>}/>
      </Route>
      <Route path='*' element={<Pagenotfound/>}/>
    </Routes>
  );
}

export default App;
