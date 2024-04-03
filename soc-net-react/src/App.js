import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import Root from './routes/root';
import NotFound from './routes/notFound'
import Users from './routes/users';
import Chats from './routes/chats';
import Profile from './routes/profile';
import Signup from './routes/signup';
import Signin from './routes/signin';
import { useCookies } from "react-cookie";
import { useEffect, useState } from "react";
import { AppContext } from "../src/contexts/contexts";
import { getUserById } from "../src/api/userService";

export default function App() {
  const [cookies, setCookie, removeCookie] = useCookies(['app']);
  const [isAuthenticated, setIsAuthenticated] = useState(cookies.token !== undefined);
  const [user, setUser] = useState({});

  useEffect(() => {
    if (cookies.userId !== undefined) {
      getUserById(cookies.userId).then(user => setUser(user));
    }
  }, [isAuthenticated]);

  const state = {
    setCookie,
    removeCookie,
    isAuthenticated,
    setIsAuthenticated,
    user
  };

  const authenticatedRouter = createBrowserRouter([
    {
      path: "/",
      element: <Root/>,
      errorElement: <NotFound />,
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
        }
      ]
    }
  ]);

  const anonymousRouter = createBrowserRouter([
    {
      path: "/",
      element: <Root/>,
      errorElement: <NotFound />,
      children: [
        {
          path: "/users",
          element: <Users />
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

  return (
    <AppContext.Provider value={state}>
      <RouterProvider router={isAuthenticated ? authenticatedRouter : anonymousRouter} />
    </AppContext.Provider>
  );
}
