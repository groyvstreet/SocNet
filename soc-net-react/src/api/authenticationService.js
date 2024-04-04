import axios from "axios";

export async function signIn(email, password) {
    const response = await axios.post(`/auth/signin?email=${email}&password=${password}`);

    return response.data;
}

export async function signUp(email, password, firstName, lastName, birthDate) {
    const response = await axios.post('/auth/signup', {
        email,
        password,
        firstName,
        lastName,
        birthDate
    });

    return response.data;
}
