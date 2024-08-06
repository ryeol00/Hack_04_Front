// src/pages/LoginJoin/Join.jsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import logo1 from '../../assets/images/logo1.png';
import logo2 from '../../assets/images/profilelogo.png';
import './Join.css';

const JoinPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [username, setUsername] = useState('');
  const navigate = useNavigate();

  const handleSignUp = async (event) => {
    event.preventDefault();
    const response = await fetch('http://localhost:8080/user/signUp', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email, password, name: username }),
    });

    if (response.ok) {
      alert('회원가입 성공!');
      navigate('/login');
    } else {
      alert('회원가입 실패.');
    }
  };

  return (
    <div className="join-container">
      <img src={logo1} className="join-logo1" />
      <img src={logo2} className="join-logo2" />
      <form onSubmit={handleSignUp} className='join-input-container'>
        <input type='email' placeholder='Email' value={email} onChange={(e) => setEmail(e.target.value)} required />
        <input type='password' placeholder='Password' value={password} onChange={(e) => setPassword(e.target.value)} required />
        <input type='text' placeholder='Username' value={username} onChange={(e) => setUsername(e.target.value)} required />
        <div className='join-button-container'>
          <button className='join-button' type='button' onClick={() => navigate('/')}>돌아가기</button>
          <button className='join-button' type='submit'>가입하기</button>
        </div>
      </form>
    </div>
  );
};

export default JoinPage;
