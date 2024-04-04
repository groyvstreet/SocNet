import { useContext, useState } from "react";
import { AppContext } from "../contexts/contexts";
import { useNavigate } from "react-router-dom";
import { removeUserById, updateUser } from "../api/userService";

export default function Profile() {
    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [birthDate, setBirthDate] = useState('');

    return (
        <section>
            <h1>{appContext.user.firstName} {appContext.user.lastName}</h1>
            <input
                placeholder='Имя'
                onChange={e => setFirstName(e.target.value)}
            />
            <input
                placeholder='Фамилия'
                onChange={e => setLastName(e.target.value)}
            />
            <input
                type='date'
                onChange={e => setBirthDate(e.target.value)}
            />
            <button
                onClick={updateUserButtonOnClick}
            >
                Редактировать
            </button>
            <button
                onClick={logoutButtonOnClick}
            >
                Выйти
            </button>
            <button
                onClick={removeUserButtonOnClick}
            >
                Удалить аккаунт
            </button>
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
