package screens;

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
            var photos = photoRepository.getPhotosByUserId(IdentityUser.user.id);
            out.print("\033[H\033[2J");
            out.flush();

            for (var photo : photos) {
                out.print("\u001B[32m");
                out.println("Photo:");
                out.print("Id: ");
                out.print("\u001B[0m");
                out.println(photo.id);
                out.print("\u001B[32m");
                out.print("Source: ");
                out.print("\u001B[0m");
                out.println(photo.source);
            }

            out.println("Select option:");
            out.println("0 - Back");
            out.println("1 - Add photo");
            out.println("2 - Edit photo");
            out.println("3 - Remove photo");
            option = in.read();

            switch (option) {
                case '0':
                    return;
                case '1':
                    out.print("Enter source: ");
                    var addedPhotoSource = console().readLine();
                    var addedPhoto = new Photo(addedPhotoSource, IdentityUser.user.id);
                    photoRepository.addPhoto(addedPhoto);
                    break;
                case '2':
                    out.print("Enter id: ");
                    var editedPhotoId = console().readLine();
                    out.print("Enter source: ");
                    var editedPhotoSource = console().readLine();
                    var editedPhoto = photoRepository.getPhotoById(UUID.fromString(editedPhotoId));
                    editedPhoto.source = editedPhotoSource;
                    photoRepository.updatePhoto(editedPhoto);
                    break;
                case '3':
                    out.print("Enter id: ");
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
