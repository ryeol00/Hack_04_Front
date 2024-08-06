import './ChallengeSuccess.css';
import NoDrinkBtn from '../../assets/images/NoDrinkSuccess.svg';
import NoSmokeBtn from '../../assets/images/NoSmokeSuccess.svg';

function ChallengeSuccess() {
    return(
        <div className="ChallengeSuccessBox">
            <h3 className='Today'>Today</h3>
            <h2 className='Date'>8 / 7</h2>
            <h3 className='NoDrinkSmoke'>&lt; 금주 꼭 하기 &gt;
                 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt; 금연하기 &gt;</h3>
            <div className='Daybutton'>
                <button className='buttons2'>
                    <img src={NoDrinkBtn} />
                </button>
                <button className='buttons2'>
                    <img src={NoSmokeBtn} />
                </button>
            </div>
            <p className='Messege'>
                오늘 하루, 챌린지를 성공했다면 버튼을 눌러주세요!
            </p>
        </div>
    )
}

export default ChallengeSuccess;