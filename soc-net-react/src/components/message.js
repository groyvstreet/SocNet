import './message.css'

export default function Message({ message, updateMessageButtonOnClick, removeMessageButtonOnClick }) {
    return (
        <section
            className="message-card"
        >
            <p
                className="message-text"
            >
                <strong>:</strong> {message.text}
            </p>
            <span
                className="message-time"
            >
                {message.dateTime.toString().replace('T', ' ').split('.')[0]}
            </span>
            <button
                className="background-color-green"
                onClick={() => updateMessageButtonOnClick(message.id)}
            >
                Изменить
            </button>
            <button
                className="background-color-red"
                onClick={() => removeMessageButtonOnClick(message.id)}
            >
                Удалить
            </button>
        </section>
    );
}
