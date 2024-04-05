import { Link, useLocation } from "react-router-dom";

export default function Navbar({ isAuthenticated }) {
    const location = useLocation();

    return (
        <nav>
            <ul>
                <li>
                    <Link to={`/users`}>Пользователи</Link>
                </li>
                { isAuthenticated ? (
                    <>
                        <li>
                            <Link to={`/chats`}>Чаты</Link>
                        </li>
                        <li>
                            <Link to={`/profile`}>Профиль</Link>
                        </li>
                    </>
                ) : (
                    <>
                        <li>
                            <Link to={`/signup`}>Регистрация</Link>
                        </li>
                        <li>
                            <Link to={`/signin`}>Авторизация</Link>
                        </li>
                    </>
                )}
            </ul>
        </nav>
    );
}
