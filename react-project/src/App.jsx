import {createBrowserRouter, RouterProvider} from 'react-router-dom'
import HomePage from './pages/Home';
import LoginPage from './pages/Login';
import JoinPage from './pages/Join';
import MyPage from './pages/MyPage';
import ChallengeBoardPage from './pages/ChallengeBoard';
;


const router = createBrowserRouter([
   
      {path: '/' , element: <HomePage />},
      {path: '/login', element: <LoginPage />},
      {path: '/join', element: <JoinPage />},
      {path: '/mypage', element: <MyPage />},
      {path: '/challenge', element: <ChallengeBoardPage />}
]);





function App() {
  return <RouterProvider router={router}/>;  
}

export default App;
