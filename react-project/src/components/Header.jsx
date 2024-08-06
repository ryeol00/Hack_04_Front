import React from "react";
import { Link } from "react-router-dom";
import logo1 from '../assets/images/logo1.png';
import './Header.css';

function Header() {
    return (
        <header className="list-header">
            <img className="header-logo" src={logo1} />
                <h3 className="list-category">
                    <Link to='/posts' className="list-link">CHALLENGE</Link>
                    <Link to='/' className="list-link">HOME</Link>
                    <Link to='/mypage' className="list-link">MYPAGE</Link>
                 </h3>
        </header>
    )
}

export default Header;