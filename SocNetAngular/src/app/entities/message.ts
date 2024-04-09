import User from "./user";

export default class Message {
    id!: string;
    dateTime!: string;
    text!: string;
    chatId!: string;
    user!: User;
}
