import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './PostCreate.css';
import Header from '../Header';
import Footer from '../../pages/Footer';

const PostCreate = () => {
  const [title, setTitle] = useState('');
  const [date, setDate] = useState('');
  const [content, setContent] = useState('');
  const [category, setCategory] = useState('SMOKE');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const newPost = {
      title,
      date,
      content,
      category,
    };

    try {
      const response = await fetch('http://localhost:8080/post/save?postType=CHALLENGE', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          postType: category,
          
          ...newPost,
        }),
        credentials: 'include',  // Include credentials for session-based authentication
      });

      if (!response.ok) {
        if (response.status === 401) {
          alert('로그인이 필요합니다.');
          navigate('/login');
        } else {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return;
      }

      const postData = await response.json();
      console.log('Post created successfully:', postData);
      navigate('/posts'); // 목록 페이지로 이동
    } catch (error) {
      console.error('Error creating post:', error);
      alert('게시물 생성 중 오류가 발생했습니다.');
    }
  };

  return (
    <div className="create-post-container">
      <div className='content-wrapper'>
        <main className='post-list-main'>
          <div className='header-wrapper'>
            <Header />
          </div>
          <h2 className='create-post-name'>챌린지 만들기</h2>
          <form onSubmit={handleSubmit} className="create-post-form">
            <div className='category-form'>
              <p htmlFor="category" className='form-name'>해시태그</p>
              <select className='category-select'
                id="category"
                value={category}
                onChange={(e) => setCategory(e.target.value)}
                required
              >
                <option value="SMOKE">SMOKE(금연)</option>
                <option value="DRINK">DRINK(금주)</option>
                <option value="HEALTH">HEALTH(건강 관리)</option>
              </select>
            </div>
            <div className='title-form'>
              <p htmlFor="title" className='form-name'>제목</p>
              <input className='title-box'
                id="title"
                type="text"
                placeholder='제목을 입력하세요.'
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                required
              />
            </div>
            <div className='content-form'>
              <p htmlFor="content" className='form-name'>내용</p>
              <textarea className='content-box'
                id="content"
                placeholder='내용을 입력하세요.'
                value={content}
                onChange={(e) => setContent(e.target.value)}
                required
              />
            </div>
            <div className='date-form'>
              <p htmlFor="date" className='form-name'>일자(시작일)</p>
              <input className='date-box'
                id="date"
                type="date"
                value={date}
                onChange={(e) => setDate(e.target.value)}
                required
              />
            </div>
            <div className='button-form'>
              <Link to='/posts'>
                <button type="button" className='back-button'>돌아가기</button>
              </Link>
              <button type="submit" className='create-button'>게시하기</button>
            </div>
          </form>
          <Footer />
        </main>
      </div>
    </div>
  );
};

export default PostCreate;
