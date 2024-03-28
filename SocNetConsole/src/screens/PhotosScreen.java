package screens;

import constants.Constants;
import entities.Photo;
import identity.IdentityUser;
import repositories.PhotoRepository;

import java.io.IOException;
import java.sql.Connection;
import java.util.UUID;

import static java.lang.System.*;

public class PhotosScreen {
    private PhotosScreen() {}

    public static void getPhotosScreen(Connection connection) throws IOException {
        var photoRepository = new PhotoRepository(connection);

        int option;

        while (true) {
            var photos = photoRepository.getPhotosByUserId(IdentityUser.getUser().getId());
            console().printf("\033[H\033[2J");
            console().flush();

            for (var photo : photos) {
                console().printf(Constants.GREEN);
                console().printf("Photo:\n");
                console().printf("Id: ");
                console().printf(Constants.WHITE);
                console().printf(STR."\{photo.getId()}\n");
                console().printf(Constants.GREEN);
                console().printf("Source: ");
                console().printf(Constants.WHITE);
                console().printf(STR."\{photo.getSource()}\n");
            }

            console().printf("Select option:\n");
            console().printf("0 - Back\n");
            console().printf("1 - Add photo\n");
            console().printf("2 - Edit photo\n");
            console().printf("3 - Remove photo\n");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    console().printf("Enter source: ");
                    var addedPhotoSource = console().readLine();
                    var addedPhoto = new Photo(addedPhotoSource, IdentityUser.getUser().getId());
                    photoRepository.addPhoto(addedPhoto);
                    break;
                case '2':
                    console().printf("Enter id: ");
                    var editedPhotoId = console().readLine();
                    console().printf("Enter source: ");
                    var editedPhotoSource = console().readLine();
                    var editedPhoto = photoRepository.getPhotoById(UUID.fromString(editedPhotoId));
                    editedPhoto.setSource(editedPhotoSource);
                    photoRepository.updatePhoto(editedPhoto);
                    break;
                case '3':
                    console().printf("Enter id: ");
                    var removedPhotoId = console().readLine();
                    var removedPhoto = photoRepository.getPhotoById(UUID.fromString(removedPhotoId));
                    photoRepository.removePhoto(removedPhoto);
                    break;
                default:
                    break;
            }
        }
    }
}
