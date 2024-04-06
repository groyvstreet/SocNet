import { useContext, useState } from "react";
import { signUp } from "../api/authenticationService";
import { AppContext } from "../contexts/contexts";
import { useNavigate } from "react-router-dom";
import './signup.css'

export default function Signup() {
    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [birthDate, setBirthDate] = useState('');

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
            <input
                placeholder="Имя"
                onChange={e => setFirstName(e.target.value)}
            />
            <input
                placeholder="Фамилия"
                onChange={e => setLastName(e.target.value)}
            />
            <input
                type="date"
                onChange={e => setBirthDate(e.target.value)}
            />
            <button
                className="background-color-green"
                onClick={signupButtonOnClick}
            >
                Зарегистрироваться
            </button>
        </section>
    );

    async function signupButtonOnClick() {
        const data = await signUp(email, password, firstName, lastName, birthDate);
        appContext.setCookie('userId', data.id);
        appContext.setCookie('token', data.token);
        appContext.setIsAuthenticated(true);
        navigate('/');
    }
}
