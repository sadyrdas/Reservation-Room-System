package cz.cvut.kbss.ear.mroom.Environment;


import cz.cvut.kbss.ear.mroom.model.User;

import java.util.Random;

public class Generator {

    private static final Random RAND = new Random();

    public static int randomInt() {
        return RAND.nextInt();
    }

    public static int randomInt(int max) {
        return RAND.nextInt(max);
    }

    public static int randomInt(int min, int max) {
        assert min >= 0;
        assert min < max;

        int result;
        do {
            result = randomInt(max);
        } while (result < min);
        return result;
    }

    public static boolean randomBoolean() {
        return RAND.nextBoolean();
    }

    public static User generateUser() {
        final User user = new User();
        user.setFirst_name("FirstName" + randomInt());
        user.setLast_name("LastName" + randomInt());
        user.setEmail("email" + randomInt() + "@kbss.felk.cvut.cz");
        user.setPassword(Integer.toString(randomInt()));
        user.setRole_id(1); //TODO
        return user;
    }

}
