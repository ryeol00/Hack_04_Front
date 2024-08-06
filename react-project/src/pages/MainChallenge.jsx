import Logo from '../assets/images/MainchallengeLogo.svg'
import NoSmokingImg from '../assets/images/NoSmokingCard.svg'
import NoAlcohol from '../assets/images/NoAlcoholCard.svg'
import NoMidnightmeal from '../assets/images/NoMidnightmealCard.svg'
import './MainChallenge.css'
import React from 'react'

function MainChallenge() {
    return(
        <div className='MainChallenge'>
            <div className='Title'>
                <img src={Logo} className='TitlStellengeLogo' />
                <h2 className='TitleMainchallenge'><span className='BlueText'>주요</span> 챌린지</h2>
            </div>
            <div className='ChallengeContents'>
                <img src={NoSmokingImg} />
                <img src={NoAlcohol} />
                <img src={NoMidnightmeal} />
            </div>
        </div>
    )
}

export default MainChallenge;