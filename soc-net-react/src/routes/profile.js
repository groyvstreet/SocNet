import { useContext, useEffect, useState } from "react";
import { AppContext } from "../contexts/contexts";
import { useNavigate } from "react-router-dom";
import { removeUserById, updateUser } from "../api/userService";
import './profile.css'

export default function Profile() {
    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    const [firstName, setFirstName] = useState(appContext.user.firstName);
    const [lastName, setLastName] = useState(appContext.user.lastName);
    const [birthDate, setBirthDate] = useState(appContext.user.birthDate);

    useEffect(() => {
        setFirstName(appContext.user.firstName);
        setLastName(appContext.user.lastName);
        setBirthDate(appContext.user.birthDate);
    }, [])

    return (
        <section className="profile-container">
            <div className="profile-card">
                <input
                    placeholder='Имя'
                    value={firstName}
                    onChange={e => setFirstName(e.target.value)}
                />
                <input
                    placeholder='Фамилия'
                    value={lastName}
                    onChange={e => setLastName(e.target.value)}
                />
                <input
                    type='date'
                    value={birthDate}
                    onChange={e => setBirthDate(e.target.value)}
                />
                <button
                    className="background-color-green"
                    onClick={updateUserButtonOnClick}
                >
                    Редактировать
                </button>
                <button
                    className="background-color-gray"
                    onClick={logoutButtonOnClick}
                >
                    Выйти
                </button>
                <button
                    className="background-color-red"
                    onClick={removeUserButtonOnClick}
                >
                    Удалить аккаунт
                </button>
            </div>
        </section>
    );

    async function updateUserButtonOnClick() {
        await updateUser(appContext.user.id, firstName, lastName, birthDate, appContext.user.image);
        const user = JSON.parse(JSON.stringify(appContext.user));
        user.firstName = firstName;
        user.lastName = lastName;
        user.birthDate = birthDate;
        appContext.setUser(user);
    }

    async function logoutButtonOnClick() {
        appContext.removeCookie('userId');
        appContext.removeCookie('token');
        appContext.setIsAuthenticated(false);
        navigate('/');
    }

    async function removeUserButtonOnClick() {
        await removeUserById(appContext.user.id);
        logoutButtonOnClick();
    }
}
