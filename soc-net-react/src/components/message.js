import './message.css'
import PropTypes from 'prop-types';

export default function Message({ message, updateMessageButtonOnClick, removeMessageButtonOnClick }) {
    return (
        <section
            className="message-card"
        >
            <p
                className="message-text"
            >
                <strong>{message.user.firstName} {message.user.lastName}:</strong> {message.text}
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
Message.propTypes = {
    message: PropTypes.shape({
        user: PropTypes.shape({
            firstName: PropTypes.string,
            lastName: PropTypes.string
        }),
        text: PropTypes.string,
        dateTime: PropTypes.any,
        id: PropTypes.string
    }),
    updateMessageButtonOnClick: PropTypes.func,
    removeMessageButtonOnClick: PropTypes.func
}
