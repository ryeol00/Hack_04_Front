import CalendarImg from '../../assets/images/Calendar.svg';
import './MyCalendar.css';

function MyCalendar() {
    return(
        <div className='CalendarBox'>
            <img src={CalendarImg} />
        </div>
    )
}

export default MyCalendar;