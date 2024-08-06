import './Footer.css'
import footerlogo from '../assets/images/FooterLogo.svg';
import React from 'react';

function Footer() {
    return(
        <footer className="footer">
        <div className="footer-pages">
          <div className="page-section">
            <p className="blue-text">마이 페이지</p>
            <p className="black-text">회원정보</p>
            <p className="black-text">회원정보수정</p>
            <p className="black-text">글작성목록</p>
          </div>
          <div className="page-section">
            <p className="blue-text">정보</p>
            <p className="black-text">게시글리스트</p>
            <p className="black-text">게시글작성</p>
          </div>
          <div className="page-section">
            <p className="blue-text">챌린지</p>
            <p className="black-text">게시글리스트</p>
            <p className="black-text">게시글작성</p>
          </div>
        </div>
        <div className="footer-logo-container">
          <img src={footerlogo} alt="Stellenge Logo" className="footer-logo" />
        </div>
        <p className="footer-copyright">© 2024 STELLENGE. All Rights Reserved.</p>
      </footer>
    )
}

export default Footer;