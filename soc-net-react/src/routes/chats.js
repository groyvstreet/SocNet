import { useContext, useEffect, useState } from "react";
import { getChats, addChat } from "../api/chatService";
import { AppContext } from "../contexts/contexts";
import { useNavigate } from "react-router-dom";

export default function Chats() {
    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    const [chats, setChats] = useState([]);
    const [name, setName] = useState('');

    useEffect(() => {
        loadChats();
    }, []);

    return (
        <section>
            <input
                onChange={e => setName(e.target.value)}
            />
            <button
                onClick={addChatButtonOnClick}
            >
                Создать
            </button>
            {chats.map(chat => (
                <div
                    key={chat.id}
                    onClick={() => navigateToChat(chat.id)}
                >
                    {chat.name}
                </div>
            ))}
        </section>
    );

    async function loadChats() {
        try {
            const chats = await getChats();
            setChats(chats);
        } catch (exception) {
            console.log(exception);
        }
    }

    async function addChatButtonOnClick() {
        await addChat(name, appContext.user.id);
        await loadChats();
    }

    function navigateToChat(chatId) {
        navigate(`/chats/${chatId}`);
    }
}
