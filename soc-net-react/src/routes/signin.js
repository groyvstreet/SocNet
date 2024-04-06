import { useContext, useState } from "react";
import { signIn } from "../api/authenticationService";
import { AppContext } from "../contexts/contexts";
import { useNavigate } from "react-router-dom";
import './signin.css'

export default function Signin() {
    const appContext = useContext(AppContext);
    
    const navigate = useNavigate();
    
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    return (
        <section
            className="container"
        >
            <input
                placeholder="Email"
                type="email"
                onChange={e => setEmail(e.target.value)}
            />
            <input
                placeholder="Пароль"
                type="password"
                onChange={e => setPassword(e.target.value)}
            />
            <button
                className="background-color-green"
                onClick={signinButtonOnClick}
            >
                Войти
            </button>
        </section>
    );

    async function signinButtonOnClick() {
        const data = await signIn(email, password);
        appContext.setCookie('userId', data.id);
        appContext.setCookie('token', data.token);
        appContext.setIsAuthenticated(true);
        navigate('/');
    }
}
