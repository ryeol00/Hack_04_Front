// src/App.jsx
import React, { useState } from 'react';
import { createBrowserRouter, RouterProvider, Outlet } from 'react-router-dom';
import HomePage from './pages/Home';
import LoginPage from './pages/LoginJoin/Login';
import JoinPage from './pages/LoginJoin/Join';
import ChallengeListPage from './pages/Board/ChallengeList';
import MainChallenge from './pages/MainChallenge';
import Calendar from './pages/Calendar';
import ChallengePoint from './pages/ChallengePoint';
import InfoShare from './pages/InfoShare';
import Footer from './pages/Footer';
import ChallengeCreatePage from './pages/Board/ChallengeCreate';
import './App.css';
import PostDetail from './components/PostDetail/PostDetail';
import Header from './components/Header';
import ProfileInfo from './pages/Mypage/ProfileInfo';
import ChallengeSuccess from './pages/Mypage/ChallengeSuccess';
import MyCalendar from './pages/Mypage/MyCalendar';

const MainLayout = () => (
  <div className="MainPage">
    <HomePage />
    <MainChallenge />
    <Calendar />
    <ChallengePoint />
    <InfoShare />
    <Footer />
  </div>
);

const PostLayout = () => (
  <div className="PostPage">
    <Outlet />
  </div>
);

const MyPageLayout = () => (
  <div className="MyPage">
    <Header />
    <ProfileInfo />
    <ChallengeSuccess />
    <MyCalendar />
    <Footer />
  </div>
)

const App = () => {
  const [posts, setPosts] = useState([
    { id: 1, title: '헬스 주3회 이상 프로젝트', date: '2024.08.01', category: 'SMOKE', likes: 5, content: 'Content of post 1', comments: [] },
    { id: 2, title: '무알콜 3달 도전', date: '2024.08.02', category: 'DRINK', likes: 10, content: 'Content of post 2', comments: [] },
    { id: 3, title: '채식 다이어트', date: '2024.08.03', category: 'HEALTH', likes: 15, content: 'Content of post 3', comments: [] },
    { id: 4, title: '1달 금주~', date: '2024.08.04', category: 'DRINK', likes: 8, content: 'Content of post 4', comments: [] },
    { id: 5, title: '금연 시작!!', date: '2024.08.05', category: 'SMOKE', likes: 20, content: 'Content of post 5', comments: [] }
  ]);

  const addPost = (newPost) => {
    setPosts([...posts, newPost]);
  };

  const addComment = (postId, comment) => {
    setPosts(posts.map(post => {
      if (post.id === Number(postId)) {
        return {
          ...post,
          comments: [...post.comments, comment]
        };
      }
      return post;
    }));
  };

  const router = createBrowserRouter([
    { path: '/', element: <MainLayout /> },
    { path: '/login', element: <LoginPage /> },
    { path: '/join', element: <JoinPage /> },
    { path: '/mypage', element: <MyPageLayout /> },
    {
      path: '/posts',
      element: <PostLayout />,
      children: [
        { index: true, element: <ChallengeListPage posts={posts} /> },
        { path: 'create', element: <ChallengeCreatePage onAddPost={addPost} /> },
        { path: ':id', element: <PostDetail posts={posts} addComment={addComment} /> },
      ]
    },
  ]);

  return (
    <RouterProvider router={router} />
  );
};

export default App;
