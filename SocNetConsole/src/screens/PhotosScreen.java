package screens;

import entities.Photo;
import identity.IdentityUser;
import repositories.PhotoRepository;

import java.io.IOException;
import java.sql.Connection;
import java.util.UUID;

import static java.lang.System.console;
import static java.lang.System.in;

public class PhotosScreen {
    private PhotosScreen() {}

    public static void getPhotosScreen(Connection connection) throws IOException {
        var photoRepository = new PhotoRepository(connection);

        int option;

        while (true) {
            var photos = photoRepository.getPhotosByUserId(IdentityUser.getUser().getId());

            console().printf("\n".repeat(50));

            for (var photo : photos) {
                photo.print();
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
                    console().printf("Enter photo id: ");
                    var editedPhotoId = console().readLine();
                    console().printf("Enter source: ");
                    var editedPhotoSource = console().readLine();
                    var editedPhoto = photoRepository.getPhotoById(UUID.fromString(editedPhotoId));
                    editedPhoto.setSource(editedPhotoSource);
                    photoRepository.updatePhoto(editedPhoto);
                    break;
                case '3':
                    console().printf("Enter photo id: ");
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
