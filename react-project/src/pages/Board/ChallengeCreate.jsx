// src/pages/Board/ChallengeCreate.js
import React from 'react';
import PostCreate from '../../components/PostCreate/PostCreate';

const ChallengeCreatePage = ({ onAddPost }) => {
  return (
    <div>
      <PostCreate onAddPost={onAddPost} />
    </div>
  );
};

export default ChallengeCreatePage;
