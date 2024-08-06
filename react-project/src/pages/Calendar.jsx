import Calendarimg2 from '../assets/images/CalendarImg2.svg'
import React from 'react';
import './Calendar.css'

function Calendar() {
    return(
        <div className='Calendar'>
            <div className='CalendarTitle'>
                <h2 className='CalendarTitleHeader'>지속가능한 <span className='Blueteext'>일일챌린지</span></h2>
                <p className='CalendarTitleExplain'>일일챌린지를 통해 매일매일 꾸준하게!</p>
            </div>
            <div className='CalendarImgBox'>
                <img src={Calendarimg2} className='CalendarImg' />
            </div>
        </div>
    )
}

export default Calendar;