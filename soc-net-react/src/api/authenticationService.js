import axios from "axios";

export async function signIn(email, password) {
    const response = await axios.post(`/auth/signin?email=${email}&password=${password}`);

    return response.data;
}
