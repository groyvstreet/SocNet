import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import Root from './routes/root';
import App from './App';
import Users from './routes/users';
import Chats from './routes/chats';
import Profile from './routes/profile';
import Signup from './routes/signup';
import Signin from './routes/signin';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <App />,
    children: [
      {
        path: "/users",
        element: <Users />
      },
      {
        path: "/chats",
        element: <Chats />
      },
      {
        path: "/profile",
        element: <Profile />
      },
      {
        path: "/signup",
        element: <Signup />
      },
      {
        path: "/signin",
        element: <Signin />
      }
    ]
  }
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

reportWebVitals();
