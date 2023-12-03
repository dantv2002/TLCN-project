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
import Dashboard from './pages/Admin/Dashboard';
import DashboardDoctor from './pages/Doctor/DashboardDoctor';
import Account from './pages/Admin/Account';
import Diagnose from './pages/Admin/Diagnose';
import PatientRecordAdmin from './pages/Admin/PatientRecordAdmin';
import ReissuePassword from './components/Form/Admin/ReissuePassword';
import CreateAccountDoctor from './components/Form/Admin/CreateAccountDoctor';
import PatientRecordDetailAdminForm from './components/Form/Admin/PatientRecordDetailAdminForm';
import CreatePatientRecordAdmin from './components/Form/Admin/CreatePatientRecordAdmin';
import UpdatePatientRecordAdmin from './components/Form/Admin/UpdatePatientRecordAdmin';
import MedicalRecordAdmin from './pages/Admin/MedicalRecordAdmin';
import ChangePasswordAdminForm from './components/Form/Admin/ChangePasswordAdminForm';

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
      <Route path="/dashboard" element={<Dashboard/>}>
        <Route path='changepass' element={<ChangePasswordAdminForm/>}/>
        <Route path='account' element={<Account/>}/>
        <Route path='account/createdoctor' element={<CreateAccountDoctor/>}/>
        <Route path='account/reissuepass/:id' element={<ReissuePassword/>}/>
        <Route path='patient' element={<PatientRecordAdmin/>}/>
        <Route path='patient/read/:id' element={<PatientRecordDetailAdminForm/>}/>
        <Route path='patient/create' element={<CreatePatientRecordAdmin/>}/>
        <Route path='patient/update/:id' element={<UpdatePatientRecordAdmin/>}/>
        <Route path='medical' element={<MedicalRecordAdmin/>}/>
        <Route path='diagnose' element={<Diagnose/>}/>
      </Route>
      <Route path='*' element={<Pagenotfound/>}/>
    </Routes>
  );
}

export default App;
