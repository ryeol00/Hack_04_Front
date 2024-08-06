import React from "react";
import Header from "../../components/Header";
import ProfileInfo from "./ProfileInfo";
import ChallengeSuccess from "./ChallengeSuccess";
import MyCalendar from "./MyCalendar";
import Footer from "../Footer";
import './Mypage.css';

function Mypage() {
    return(
        <div className="MyPage">
            <div className="header-wrapper">
                <Header />
            </div>
            <main className="main-content">
                <ProfileInfo />
                <ChallengeSuccess />
                <MyCalendar />
            </main>
            <Footer />
        </div>
    )
}

export default Mypage;