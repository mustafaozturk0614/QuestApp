import './App.css';
import { BrowserRouter, Routes, Route, Redirect, NavLink } from "react-router-dom";
import Navbar from './components/NavBar/NavBar';
import Home from './components/Home/Home';
import User from './components/User/User';
import Auth from './components/Auth/Auth';

function App() {
  const route = localStorage.getItem("currentUser") != null ? <Home /> : <Auth />

  return (
    <div className="App">
      <BrowserRouter>
        <Navbar></Navbar>
        <Routes>
          <Route exact path="/" element={<Home />}></Route>
          <Route exact path="/users/:userId" element={<User />}></Route>
          <Route exact path="/auth" element={route}>

          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
