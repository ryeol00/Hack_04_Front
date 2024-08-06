import React from 'react';
import logo1 from '../assets/images/logo1.png';
import './Home.css';
import logo2 from '../assets/images/logo2.png';
import { useNavigate } from 'react-router-dom';
import homeIcon from '../assets/images/HomeIcon.png';
import loginIcon from '../assets/images/LoginIcon.png';
import { Link } from 'react-router-dom';
import Header from '../components/Header';


function HomePage() {
    const navigate = useNavigate();

    const goToHome = () => {
        navigate('/');
    };

    const goToLogin = () => {
        navigate('/login');
    };
    return (
        <div className='container'>
            <div className='home-header-wrapper'>
                <Header />
            </div>
            <div className='button-container'>
                <div className='button-wrapper' onClick={goToHome}>
                    <button className='button'>
                        <img src={homeIcon} alt="Home Icon" />
                        HOME
                    </button>
                </div>
                <div className='button-wrapper' onClick={goToLogin}>
                    <button className='button'>
                        <img src={loginIcon} alt="Login Icon" />
                        LOGIN
                    </button>
                </div>
            </div>
            <div className='main-box'>
                <div className='main-content'> 
                    <h2 className='main-title'>
                        건강을 향한 즐거운 도전, <span className='highlight'>스텔린지</span>
                    </h2>
                    <p className='main-detail'>
                        금연, 금주, 야식 끊기 등 건강을 위한 도전을 결심 했지만 항상 작심삼일이라면?<br />
                        스텔린지만의 즐겁고 지속적인 챌린지를 통해<br />
                        다른 사람들과 함께 한 발자국씩 건강한 삶으로 나아가 보세요!
                    </p>
                </div>
                <img className='homelogo2' src={logo2} alt="Logo2" />
            </div>
            
        </div>
    );
}

export default HomePage;
