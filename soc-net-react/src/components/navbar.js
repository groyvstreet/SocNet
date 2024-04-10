import { Link, useLocation } from "react-router-dom";
import './navbar.css'
import PropTypes from 'prop-types';

export default function Navbar({ isAuthenticated }) {
    const location = useLocation();

    return (
        <header
            className="navigation-header"
        >
            <nav
                className="navigation-menu"
            >
                <ul>
                    <li>
                        <Link
                            className={`menu-item ${location.pathname === '/users' ? 'active' : ''}`}
                            to={`/users`}
                        >
                            Пользователи
                        </Link>
                    </li>
                    { isAuthenticated ? (
                        <>
                            <li>
                                <Link
                                    className={`menu-item ${location.pathname.startsWith('/chats') ? 'active' : ''}`}
                                    to={`/chats`}
                                >
                                    Чаты
                                </Link>
                            </li>
                            <li>
                                <Link
                                    className={`menu-item ${location.pathname === '/profile' ? 'active' : ''}`}
                                    to={`/profile`}
                                >
                                    Профиль
                                </Link>
                            </li>
                        </>
                    ) : (
                        <>
                            <li>
                                <Link
                                    className={`menu-item ${location.pathname === '/signup' ? 'active' : ''}`}
                                    to={`/signup`}
                                >
                                    Регистрация
                                </Link>
                            </li>
                            <li>
                                <Link
                                    className={`menu-item ${location.pathname === '/signin' ? 'active' : ''}`}
                                    to={`/signin`}
                                >
                                    Авторизация
                                </Link>
                            </li>
                        </>
                    )}
                </ul>
            </nav>
        </header>
    );
}
Navbar.propTypes = {
    isAuthenticated: PropTypes.bool
}
