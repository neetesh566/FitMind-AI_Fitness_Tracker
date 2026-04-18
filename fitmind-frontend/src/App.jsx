
// import './App.css'
import { Box, Button } from "@mui/material"
import { useContext, useEffect, useState } from "react"
import { AuthContext } from "react-oauth2-code-pkce"
import { useDispatch } from "react-redux";
import { BrowserRouter as Router, Navigate, Route, Routes, useLocation } from "react-router"
import { logout, setCredentials } from "./store/authSlice";
import ActivityForm from "./components/ActivityForm";
import ActivityList from "./components/ActivityList";
import ActivityDetail from "./components/ActivityDetail";

import { BrowserRouter as Router,Navigate,Routes,Route,useLocation } from "react-router"
function App() {
  return (
    <Router>
      {!token ? (
        <Button variant="contained"
          onClick={() => {logIn();}}>
          LOGIN
        </Button>
      ) : (
        <div>
         <Box component="section" sx={{ p: 2, border: '1px dashed grey' }}>
          <Button variant="contained" onClick={logout} >
            LOGOUT  
          </Button>
          <Routes>
            <Route path="/activities" element={<ActivitiesPage />}/>
            <Route path="/activities/:id" element={<ActivityDetail />}/>
            <Route path="/" element={token ? <Navigate to="/activities" replace/> :
                                  <div>Welcome! Please login</div>}/>
          </Routes>
        </Box>
        </div>
      )}
    </Router>
  )
}

export default App
