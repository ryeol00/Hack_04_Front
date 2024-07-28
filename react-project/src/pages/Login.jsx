import React from 'react';
import logo1 from '../assets/logo1.png';
import loginlogo from '../assets/loginlogo.png';
import './Login.css';
import { Link } from 'react-router-dom';

function LoginPage() {
    return (
        <div className='login-container'>
            <div className='left-container'>
                <Link to="/" >
                    <img className='logo1' src={logo1} alt="Logo1" />
                </Link>
                <h2 className='title'>LOGIN</h2>
                <div className='login-form'>
                    <div className='input-container'>
                        <input type='email' placeholder='Email Address' />
                        <input type='password' placeholder='Password' />
                    </div>
                    <div className='login-button-container'>
                        <Link to='/join'>
                            <button className='login-button'>회원가입</button>
                        </Link>
                        <button className='login-button' type='submit'>로그인</button>
                    </div>
                </div>
            </div>

            <img className='logo2' src={loginlogo} alt="loginlogo" />
        </div>
        
    )
}

export default LoginPage;