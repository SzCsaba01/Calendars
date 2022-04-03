import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String calendar1 = scanner.nextLine();
        String range1 = scanner.nextLine();
        String calendar2 = scanner.nextLine();
        String range2 = scanner.nextLine();

        int meetingTimeMinutes = scanner.nextInt();

        scanner.close();

        People p1 = new People(People.calendars(range1));
        People p2 = new People(People.calendars(range2));

        p1.stringToBusyCalendars(calendar1);
        Collections.sort(p1.getBusyCalendar());

        p2.stringToBusyCalendars(calendar2);
        Collections.sort(p2.getBusyCalendar());

        p1.createFreeCalendar();
        p2.createFreeCalendar();

        System.out.println(People.intersectionOf2CalendarsList(p1.getFreeCalendar(), p2.getFreeCalendar(), meetingTimeMinutes));
    }
}
