import React from 'react';
import './CategoryList.css'; // CSS 파일 임포트

const CategoryList = ({ categories, onSelectCategory }) => {
  return (
    <nav className="category-nav">
      <ul className="category-list">
        {categories.map((category, index) => (
          <li key={index} className="category-item" onClick={() => onSelectCategory(category)}>
            {category}
          </li>
        ))}
      </ul>
    </nav>
  );
};

export default CategoryList;
