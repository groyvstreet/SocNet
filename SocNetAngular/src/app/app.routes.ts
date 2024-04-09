import { Routes } from '@angular/router';
import { UsersComponent } from './users/users.component';
import { ChatsComponent } from './chats/chats.component';
import { ProfileComponent } from './profile/profile.component';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';
import { ChatComponent } from './chat/chat.component';

export const routes: Routes = [
    {
        path: 'users',
        component: UsersComponent
    },
    {
        path: 'chats',
        component: ChatsComponent
    },
    {
        path: 'chats/:chatId',
        component: ChatComponent
    },
    {
        path: 'profile',
        component: ProfileComponent
    },
    {
        path: 'signup',
        component: SignupComponent
    },
    {
        path: 'signin',
        component: SigninComponent
    },
    {
        path: '**',
        redirectTo: '/users'
    }
];
