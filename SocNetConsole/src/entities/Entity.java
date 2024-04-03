package entities;

import constants.Constants;

import java.util.ArrayList;

import static java.lang.System.console;

public class Entity {
    public void print() {
        print(0);
    }

    public void print(int tabCount) {
        var tabs = "\t".repeat(tabCount);

        var fields = getClass().getDeclaredFields();

        console().printf(Constants.GREEN);
        console().printf(STR."\{tabs}\{getClass().getSimpleName()}:\n");

        for (var field : fields) {
            field.setAccessible(true);

            console().printf(Constants.GREEN);
            console().printf(STR."\{tabs}\{field.getName()}: ");
            console().printf(Constants.WHITE);

            try {
                if (field.getType() == ArrayList.class) {
                    console().printf("\n");

                    var values = (ArrayList) field.get(this);

                    for (var value : values) {
                        console().printf(STR."\{tabs}\t\{value}\n");
                    }
                }
                else {
                    console().printf(STR."\{field.get(this)}\n");
                }
            }
            catch (Exception exception) {
                console().printf("\n");
            }
        }
    }
}
