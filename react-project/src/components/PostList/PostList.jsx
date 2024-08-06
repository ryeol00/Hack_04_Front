import React, { useState, useEffect } from 'react';
import DivImg from '../../assets/images/listadv.png';
import LikesImg from '../../assets/images/LikesImg.png';
import PostLink from '../../assets/images/PostLink.png';
import { Link } from 'react-router-dom';
import Header from '../Header.jsx';
import Footer from '../../pages/Footer.jsx';
import CategoryList from './CategoryList.jsx';
import SearchBar from './SearchBar.jsx';
import './PostList.css';

const PostList = () => {
  const [posts, setPosts] = useState([]);
  const [categories, setCategories] = useState(['ALL', 'SMOKE', 'DRINK', 'HEALTH']);
  const [selectedCategory, setSelectedCategory] = useState('ALL');
  const [searchTerm, setSearchTerm] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchPosts = async () => {
      setLoading(true); 
      setError(null);  

      try {
        console.log(`Fetching posts for category: ${selectedCategory}`);
        let response;
        if (selectedCategory === 'ALL') {
          response = await fetch('http://localhost:8080/post/all?postType=CHALLENGE');
        } else {
          response = await fetch(`http://localhost:8080/post/challenge/all?category=${selectedCategory}`);
        }
       
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        setPosts(data);
      } catch (error) {
        console.error('Error fetching posts:', error);
        setError(error.message);
      } finally {
        setLoading(false); // 로딩 끝
      }
    };

    fetchPosts();
  }, [selectedCategory]);

  const handleSearchChange = (term) => {
    setSearchTerm(term);
  };

  const filteredPosts = posts.filter(post => {
    const matchesCategory = selectedCategory === 'ALL' || post.category === selectedCategory;
    const matchesSearch = post.title.toLowerCase().includes(searchTerm.toLowerCase());
    return matchesCategory && matchesSearch;
  });

  const totalItems = filteredPosts.length;
  const maxItems = 15; // 최대 게시물 수
  const emptyBoxesCount = Math.max(0, maxItems - totalItems);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div className='post-list-container'>
      <div className="content-wrapper">
        <main className='post-list-main'>
          <div className='header-wrapper'>
            <Header />
          </div>
          <div className='name-link-wrapper'>
            <h2 className='post-list-name'>CHALLENGE</h2>
            <Link to="/posts/create" className="create-post-button">
              <img src={PostLink} className='button-img' alt="Create Post" />
            </Link>
          </div>
          <div className='category-search'>
            <CategoryList
              categories={categories}
              onSelectCategory={setSelectedCategory}
            />
            <SearchBar searchTerm={searchTerm} onSearchChange={handleSearchChange} />
          </div>
          <div className='post-list-wrapper'>
            <ul className='post-list'>
              {filteredPosts.slice(0, maxItems).map(post => (
                <li key={post.id} className='post-item'>
                  <Link to={`/posts/${post.id}`} className='post-link'>
                    <h3 className='post-title'>{post.title}</h3>
                    <p className='post-date'>{post.date}</p>
                    <div className='likes-wrapper'>
                      <img src={LikesImg} className='like-img' alt="Likes" />
                      <p className='post-likes'>{post.likes}</p>
                    </div>
                  </Link>
                </li>
              ))}
              
              {/* 빈 박스 추가 */}
              {Array.from({ length: emptyBoxesCount }).map((_, index) => (
                <li key={`empty-${index}`} className='post-item empty-box'></li>
              ))}
            </ul>
            <img src={DivImg} className='div-img' alt="Advertisement" />
          </div>
        </main>
      </div>
      <Footer />
    </div>
  );
};

export default PostList;
