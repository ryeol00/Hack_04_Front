import PointImg from '../assets/images/PointIllust.svg'
import './ChallengePoint.css'
import React from 'react';

function ChallengePoint() {
    return(
        <div className='ChallengePoint'>
            <div className='ChallengePointTitle'>
            <h2 className='PointTitle'><span className='Bluetext'>챌린지 포인트</span>로 상품 구매</h2>
            <p className='PointExplain'>매일 챌린지에 성공하고 할인된 가격으로 상품을 구매해보세요!</p>
            </div>
            <img src={PointImg} className='PointImage'/>
        </div>
    )
}

export default ChallengePoint;