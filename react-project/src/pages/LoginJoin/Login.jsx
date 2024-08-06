import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import logo1 from '../../assets/images/logo1.png';
import loginlogo from '../../assets/images/loginlogo.png';
import './Login.css';

function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(''); // 성공 메시지 상태 추가
    const navigate = useNavigate();

    const handleLogin = async (event) => {
        event.preventDefault();
        setError('');
    
        try {
            const formData = new FormData();
            formData.append('email', email);
            formData.append('password', password);
    
            const response = await fetch('http://localhost:8080/user/login', {
                method: 'POST',
                body: formData,
                credentials: 'include', // 세션 쿠키를 포함하도록 설정
            });
    
            if (response.status === 401) {
                // 401 에러인 경우 로그인 실패
                const errorData = await response.json();
                setError(errorData.message || '로그인에 실패했습니다.');
            } else {
                // 401 에러를 제외한 모든 경우 로그인 성공
                alert('로그인에 성공했습니다.');
                navigate('/'); // 로그인 성공 후 리다이렉션
            }
        } catch (error) {
            setError('서버와 통신 중 오류가 발생했습니다.');
        }
    };
    return (
        <div className='login-container'>
            <div className='left-container'>
                <a href="/">
                    <img className='logo1' src={logo1} alt="Logo1" />
                </a>
                <h2 className='title'>LOGIN</h2>
                <form className='login-form' onSubmit={handleLogin}>
                    <div className='input-container'>
                        <input
                            type='email'
                            placeholder='Email Address'
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                        <input
                            type='password'
                            placeholder='Password'
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <div className='login-button-container'>
                        <a href='/join'>
                            <button type='button' className='login-button'>회원가입</button>
                        </a>
                        <button className='login-button' type='submit'>로그인</button>
                    </div>
                    {error && <p className='error-message'>{error}</p>}
                    {success && <p className='success-message'>{success}</p>} {/* 성공 메시지 표시 */}
                </form>
            </div>
            <img className='logo2' src={loginlogo} alt="loginlogo" />
        </div>
    );
}

export default LoginPage;
