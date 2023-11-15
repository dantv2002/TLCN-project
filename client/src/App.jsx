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
import ChangePasswordPatient from './components/Form/User/ChangePasswordPatient';
import CreatePatientRecordForm from './components/Form/User/CreatePatientRecordForm';
import UpdatePatientRecordForm from './components/Form/User/UpdatePatientRecordForm';
import MedicalRecord from './pages/User/MedicalRecord';
import MedicalRecordDetail from './components/Form/User/MedicalRecordDetail';
import DashboardDoctor from './pages/Doctor/DashboardDoctor';
import DashboardAdmin from './pages/Admin/DashboardAdmin';
import PatientRecordAdmin from './pages/Admin/PatientRecordAdmin';
import DoctorAccountAdmin from './pages/Admin/DoctorAccountAdmin';
import MedicalRecordAdmin from './pages/Admin/MedicalRecordAdmin';
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
      <Route path='/user/changepassword' element={<ChangePasswordPatient/>}/>
      <Route path='/user/patientrecord' element={<PatientRecord/>}/>
      <Route path='/user/patientrecord/create' element={<CreatePatientRecordForm/>}/>
      <Route path='/user/patientrecord/update' element={<UpdatePatientRecordForm/>}/>
      <Route path='/user/medicalrecord' element={<MedicalRecord/>}/>
      <Route path='/user/medicalrecord/detail/:id' element={<MedicalRecordDetail/>}/>
      <Route path='/doctor/dashboard' element={<DashboardDoctor/>}/>  
      <Route path='/admin/dashboard' element={<DashboardAdmin/>}/>
      <Route path='/admin/patientrecord' element={<PatientRecordAdmin/>}/>
      <Route path='/admin/doctoraccount' element={<DoctorAccountAdmin/>}/>
      <Route path='/admin/medicalrecord' element={<MedicalRecordAdmin/>}/>
      <Route path='/admin/diagnosis' element={<DiagnosisAdmin/>}/>
      <Route path='*' element={<Pagenotfound/>}/>
    </Routes>
  );
}

export default App;
