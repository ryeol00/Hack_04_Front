import MainChallenge from "./pages/MainChallenge.jsx";
import Calendar from "./pages/Calendar.jsx";
import ChallengePoint from "./pages/ChallengePoint.jsx";
import InfoShare from './pages/InfoShare.jsx';
import Footer from './pages/Footer.jsx';
import HomePage from "./pages/Home.jsx";
import './Mainpage.css';
import React from "react";


function Mainpage() {
    return(
        
        <div className="MainPage">
            <HomePage />
            <MainChallenge />
            <Calendar />
            <ChallengePoint />
            <InfoShare />
            <Footer />
        </div>
    )
}

export default Mainpage;