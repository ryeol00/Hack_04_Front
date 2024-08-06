import ProfileImg from '../../assets/images/ProfileImg.svg';
import './ProfileInfo.css';
import Purchase from '../../assets/images/ProductPurchase.svg';
import Fillin from '../../assets/images/Fillin.svg';

function ProfileInfo() {
    return (
        <div className='ProfileBox'>
            <div className='ProfileImg'>
                <img src={ProfileImg} />
            </div>
            <div className='Name'>
                <div><h2>김멋사 <span className='nim'>님</span></h2></div>
            </div>
            <div className='Point'>
                <div><h2>100 <span className='P'>P</span></h2></div>
            </div>
            <div className='Buttons'>
                <button className='buttons'>
                    상품 구매
                </button>
                <button className='buttons'>
                    충전하기
                </button>
            </div>
        </div>
    )
}

export default ProfileInfo;