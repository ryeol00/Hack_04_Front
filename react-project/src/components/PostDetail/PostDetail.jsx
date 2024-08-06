import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Crying from '../../assets/images/CryingIllust.svg';
import ProfileImg from '../../assets/images/ProfileImg.png';
import LikeButton from '../../assets/images/LikeButtonImg.svg';
import ClickedLikeButton from '../../assets/images/LikeButtonClickedImg.svg';
import LikeImogi from '../../assets/images/LikeImg.svg';
import ChatImogi from '../../assets/images/ChatImg.svg';
import SmokeImg from '../../assets/images/smoke.png';
import DrinkImg from '../../assets/images/drink.png';
import SubmitButton from '../../assets/images/SubmitButton.svg';
import CommentProfile from '../../assets/images/CommentProfile.svg';
import Header from '../Header';
import Footer from '../../pages/Footer';
import './PostDetail.css';

const getCurrentUser = () => {
  const user = JSON.parse(localStorage.getItem('currentUser'));
  return user;
};

const PostDetail = () => {
  const { id } = useParams();
  const [post, setPost] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [likes, setLikes] = useState(0);
  const [liked, setLiked] = useState(false);
  const [comments, setComments] = useState([]);
  const [newComment, setNewComment] = useState('');
  const [hashtagImage, setHashtagImage] = useState(null);
 

  useEffect(() => {
    const fetchPost = async () => {
      setLoading(true);
      setError(null);
      try {
        const response = await fetch(`http://localhost:8080/post/view?uuid=${id}`);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        setPost(data);
        setLikes(data.likes);
        setComments(data.comments || []);
        switch (data.category) {
          case 'SMOKE':
            setHashtagImage(SmokeImg);
            break;
          case 'DRINK':
            setHashtagImage(DrinkImg);
            break;
          default:
            setHashtagImage(null);
        }
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchPost();
  }, [id]);

  const handleLike = async () => {
    try {
      const response = await fetch(`http://localhost:8080/post/view/like?uuid=${id}`, {
        method: 'GET',
      });
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      setLikes(likes + 1);
      setLiked(true);
    } catch (error) {
      console.error('Error liking post:', error);
      setError(error.message);
    }
  };

  const handleCommentSubmit = async (e) => {
    e.preventDefault();
    const currentUser = getCurrentUser();
    const newCommentData = {
      author: currentUser ? currentUser.username : '익명',
      content: newComment,
      date: new Date().toISOString(),
    };

    try {
      const response = await fetch(`http://localhost:8080/post/view/answer?uuid=${id}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newCommentData),
      });
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      setComments([...comments, newCommentData]);
      setNewComment('');
    } catch (error) {
      console.error('Error adding comment:', error);
      setError(error.message);
    }
  };

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return (
      <div className='NetworkError'>
        <img src={Crying} alt='Error illustration' />
        <p>{error}</p>
      </div>
    );
  }

  if (!post) {
    return (
      <div className='PostNotFound'>
        <img src={Crying} alt='Deleted post illustration' />
        <p>삭제된 게시글입니다</p>
      </div>
    );
  }

  return (
    <div className='PostDetailBox'>
      <div className='content-wrapper'>
        <main className='post-list-main'>
          <div className='detail-header-wrapper'>
            <Header />
          </div>
          {post.category && (
            <div className='HashtagText'>
              <p className='Hashtag'>{post.category}</p>
            </div>
          )}
          <div className='HeadBox'>
            <div className='title-info'>
              <h1 className='PostTitle'>{post.title}</h1>
              <div className='PostInfo'>
                <p className='profile-info'>
                  <img src={ProfileImg} className='ProfileImg' alt='Profile' />
                  {post.author}
                </p>
                <p className='date-info'>{new Date(post.date).toLocaleDateString()}</p>
                <p className='like-chat-info'>
                  <img src={LikeImogi} className='like-chat-img' alt='Likes' /> {likes} <img src={ChatImogi} className='like-chat-img' alt='Comments' /> {comments.length}
                </p>
              </div>
            </div>
            {hashtagImage && <img src={hashtagImage} alt={post.category} className='category-img' />}
          </div>
          <div className='ContentAndButtons'>
            <div className='PostContent'>
              <p className='PostText'>{post.content}</p>
            </div>
            <div className='button-comment'>
              <div className='ButtonContainer'>
                <button className='LikeButton' onClick={handleLike} disabled={liked}>
                  <img src={liked ? ClickedLikeButton : LikeButton} alt='Like Button' />
                </button>
                <button className='PretendButton'>
                  <h2>참여하기</h2>
                </button>
              </div>
              <div className='CommentSection'>
                <form onSubmit={handleCommentSubmit} className='CommentForm'>
                  <input
                    type='text'
                    value={newComment}
                    onChange={(e) => setNewComment(e.target.value)}
                    placeholder='댓글을 작성하세요'
                    className='CommentInput'
                  />
                  <button type='submit' className='CommentSubmitButton'>
                    <img src={SubmitButton} alt='Submit Button' />
                  </button>
                </form>
                <div className='CommentList'>
                  {comments.map((comment) => (
                    <div key={comment.id} className='CommentItem'>
                      <p>
                        <img src={CommentProfile} alt='Comment Profile' />
                        <strong>{comment.author}</strong>
                      </p>
                      <p>{comment.content}</p>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          </div>
          <Footer />
        </main>
      </div>
    </div>
  );
};

export default PostDetail;
