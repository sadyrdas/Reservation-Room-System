package cz.cvut.kbss.ear.mroom.Environment;


import cz.cvut.kbss.ear.mroom.model.StudyRoom;
import cz.cvut.kbss.ear.mroom.model.ReservationDate;
import cz.cvut.kbss.ear.mroom.model.Slot;
import cz.cvut.kbss.ear.mroom.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Random;

public class Generator {

    private static final Random RAND = new Random();

    public static double randomDouble(){
        return RAND.nextDouble();
    }

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
//        user.setRole_id(1); //TODO
        return user;
    }

    public static StudyRoom generateStudyRoom(){
        final StudyRoom studyRoom = new StudyRoom();
        studyRoom.setPrice((randomInt()));
        studyRoom.setCapacity((randomInt()));
        return studyRoom;
    }

    public static String generateUserEmail() {
        return "email" + randomInt() + "@kbss.felk.cvut.cz";
    }

    public static Slot generateSlot(){
        final Slot slot = new Slot();
        DateFormat sdf = new SimpleDateFormat("HH:mm");
        String randomStart = (RAND.nextInt(21) + 4) + ":" + (RAND.nextInt(56) + 5);
        String randomFinish = (RAND.nextInt(21) + 4) + ":" + (RAND.nextInt(56) + 5);
        slot.setDay(generateDay());
        slot.setStudyroom_id(generateStudyRoom());
        slot.setStart(randomStart);
        slot.setFinish(randomFinish);
        return slot;
    }

    public static ReservationDate generateDay() {
        final ReservationDate day = new ReservationDate();
        day.setPosting_date(LocalDate.now());
        return day;
    }
}
