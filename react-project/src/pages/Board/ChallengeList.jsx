// src/pages/Board/ChallengeList.js
import React from 'react';
import PostList from '../../components/PostList/PostList';

const ChallengeListPage = ({ posts }) => {
  return <PostList posts={posts} />;
};

export default ChallengeListPage;
