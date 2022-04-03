import java.util.ArrayList;
import java.util.Calendar;

public class People {
    private Calendars range;
    private ArrayList<Calendars> busyCalendar;
    private ArrayList<Calendars> freeCalendar;

    public People(Calendars calendar) {
        range = calendar;
        busyCalendar = new ArrayList<Calendars>();
        freeCalendar = new ArrayList<Calendars>();
    }



    public static ArrayList<Calendars> intersectionOf2CalendarsList(ArrayList<Calendars> c1, ArrayList<Calendars> c2, int meetingTimeMinutes){
        int meetingTimeHours = meetingTimeMinutes/60;
        meetingTimeMinutes = meetingTimeMinutes % 60;
        ArrayList<Calendars> calendars = new ArrayList<Calendars>();
        Calendars tmp;
        for(Calendars calendars1 : c1){
            for(Calendars calendars2 : c2){
                tmp = People.intersectionOf2Calendars(calendars1, calendars2);
                if(tmp != null){
                    tmp = People.checkTimeInterval(tmp, meetingTimeHours, meetingTimeMinutes);
                    if(tmp != null)
                        calendars.add(tmp);
                }
            }
        }
        return calendars;
    }

    public static Calendars checkTimeInterval(Calendars calendar, int meetingTimeHours, int meetingTimeMinutes){
        int ok = 0;
        int endHour = calendar.getStartHour();
        int endMinute = calendar.getStartMinute();
        while(true){
            if(endHour > calendar.getEndHour())
                break;
            if(endHour == calendar.getEndHour() && endMinute >= calendar.getEndMinute())
                break;
            endHour += meetingTimeHours;
            endMinute += meetingTimeMinutes;
            if(endMinute >= 60){
                endMinute = endMinute % 60;
                endHour++;
            }
        }
        if(endHour > calendar.getEndHour() || endMinute > calendar.getEndMinute()){
            endHour -= meetingTimeHours;
            endMinute -=meetingTimeMinutes;
            if(endMinute < 0){
                endMinute = 60 + endMinute;
                endHour--;
            }
        }
        if(endHour != calendar.getStartHour() || endMinute != calendar.getStartMinute()) {
            calendar.setEndHour(endHour);
            calendar.setEndMinute(endMinute);
            return  calendar;
        }
        return null;
    }

    public static Calendars intersectionOf2Calendars(Calendars calendar1, Calendars calendar2){
      if(calendar1.getEndHour() < calendar2.getStartHour())
          return null;
      if(calendar1.getStartHour() > calendar2.getEndHour())
          return null;
      if(calendar1.getStartHour() == calendar2.getEndHour()){
          if(calendar1.getStartMinute() > calendar2.getEndMinute())
              return null;
      }
      if(calendar2.getStartHour() == calendar1.getEndHour()){
          if(calendar2.getStartMinute() > calendar1.getEndMinute())
              return null;
      }

      if(calendar1.getStartHour() < calendar2.getStartHour() && calendar1.getEndHour() < calendar2.getEndHour() &&
              calendar1.getEndHour() >= calendar2.getStartHour()){
          return new Calendars(calendar2.getStartHour(), calendar2.getStartMinute(), calendar1.getEndHour(), calendar1.getEndMinute());
      }
      if(calendar1.getStartHour() < calendar2.getStartHour() && calendar1.getEndHour() > calendar2.getEndHour()){
          return new Calendars(calendar2.getStartHour(), calendar2.getStartMinute(), calendar2.getEndHour(), calendar2.getEndMinute());
      }
      if(calendar1.getStartHour() > calendar2.getStartHour() &&  calendar1.getStartHour() < calendar2.getEndHour()
       && calendar1.getEndHour() > calendar2.getEndHour())
          return new Calendars(calendar1.getStartHour(), calendar1.getStartMinute(), calendar2.getEndHour(), calendar2.getEndMinute());


        if(calendar2.getStartHour() < calendar1.getStartHour() && calendar2.getEndHour() < calendar1.getEndHour() &&
                calendar2.getEndHour() >= calendar1.getStartHour()){
            return new Calendars(calendar1.getStartHour(), calendar1.getStartMinute(), calendar2.getEndHour(), calendar2.getEndMinute());
        }
        if(calendar2.getStartHour() < calendar1.getStartHour() && calendar2.getEndHour() > calendar1.getEndHour()){
            return new Calendars(calendar1.getStartHour(), calendar1.getStartMinute(), calendar1.getEndHour(), calendar1.getEndMinute());
        }
        if(calendar2.getStartHour() > calendar1.getStartHour() &&  calendar2.getStartHour() < calendar1.getEndHour()
                && calendar2.getEndHour() > calendar1.getEndHour())
            return new Calendars(calendar2.getStartHour(), calendar2.getStartMinute(), calendar1.getEndHour(), calendar1.getEndMinute());

        if(calendar1.getStartHour() == calendar2.getStartHour()){
            if(calendar1.getStartMinute() > calendar2.getStartMinute()){
                if(calendar1.getEndHour() < calendar2.getEndHour())
                    return new Calendars(calendar1.getStartHour(), calendar1.getStartMinute(), calendar1.getEndHour(), calendar1.getEndMinute());

                if(calendar1.getEndHour() > calendar2.getEndHour())
                    return new Calendars(calendar1.getStartHour(), calendar1.getStartMinute(), calendar2.getEndHour(), calendar2.getEndMinute());

                if(calendar1.getEndMinute() < calendar2.getEndMinute())
                    return new Calendars(calendar1.getStartHour(), calendar1.getStartMinute(), calendar1.getEndHour(), calendar1.getEndMinute());

                return new Calendars(calendar1.getStartHour(), calendar1.getStartMinute(), calendar1.getEndHour(), calendar2.getEndMinute());
            }
            if(calendar1.getStartMinute() <= calendar2.getStartMinute()){
                if(calendar1.getEndHour() < calendar2.getEndHour())
                    return new Calendars(calendar2.getStartHour(), calendar2.getStartMinute(), calendar1.getEndHour(), calendar1.getEndMinute());

                if(calendar1.getEndHour() > calendar2.getEndHour())
                    return new Calendars(calendar2.getStartHour(), calendar2.getStartMinute(), calendar2.getEndHour(), calendar2.getEndMinute());

                if(calendar1.getEndMinute() < calendar2.getEndMinute())
                    return new Calendars(calendar2.getStartHour(), calendar2.getStartMinute(), calendar1.getEndHour(), calendar1.getEndMinute());

                return new Calendars(calendar2.getStartHour(), calendar2.getStartMinute(), calendar1.getEndHour(), calendar2.getEndMinute());
            }
        }
        if(calendar1.getEndHour() == calendar2.getEndHour()){
            if(calendar1.getEndMinute() > calendar2.getEndMinute()){
                if(calendar1.getStartHour() < calendar2.getStartHour())
                    return new Calendars(calendar2.getStartHour(), calendar2.getStartMinute(), calendar2.getEndHour(), calendar2.getEndMinute());

                if(calendar1.getStartHour() > calendar2.getStartHour())
                    return new Calendars(calendar1.getStartHour(), calendar1.getStartMinute(), calendar2.getEndHour(), calendar2.getEndMinute());
            }
            if(calendar1.getEndMinute() <= calendar2.getEndMinute()){
                if(calendar1.getStartHour() < calendar2.getStartHour())
                    return new Calendars(calendar2.getStartHour(),calendar2.getStartMinute(), calendar1.getEndHour(), calendar1.getEndMinute());

                if(calendar1.getStartHour() > calendar2.getStartHour())
                    return new Calendars(calendar1.getStartHour(), calendar1.getStartMinute(), calendar1.getEndHour(), calendar1.getEndMinute());
            }
        }
        return null;
    }

    public void createFreeCalendar(){
        if(range.getStartHour() < busyCalendar.get(0).getStartHour()){
            freeCalendar.add(new Calendars(range.getStartHour(), range.getStartMinute(), busyCalendar.get(0).getStartHour(),
                    busyCalendar.get(0).getStartMinute()));
        }
        else{
            if(range.getStartHour() == busyCalendar.get(0).getStartHour()) {
                if (range.getStartMinute() < busyCalendar.get(0).getStartMinute()) {
                    freeCalendar.add(new Calendars(range.getStartHour(), range.getStartMinute(), busyCalendar.get(0).getStartHour(),
                            busyCalendar.get(0).getStartMinute()));
                }
            }
        }
        int i = 1;
        while (i < busyCalendar.size()){
            if(busyCalendar.get(i-1).getEndHour() < busyCalendar.get(i).getStartHour()) {
                freeCalendar.add(new Calendars(busyCalendar.get(i - 1).getEndHour(), busyCalendar.get(i - 1).getEndMinute(),
                        busyCalendar.get(i).getStartHour(), busyCalendar.get(i).getStartMinute()));
            }
            else {
                if (busyCalendar.get(i - 1).getEndHour() == busyCalendar.get(i).getStartHour()) {
                    if (busyCalendar.get(i - 1).getEndMinute() < busyCalendar.get(i).getStartMinute()) {
                        freeCalendar.add(new Calendars(busyCalendar.get(i - 1).getEndHour(), busyCalendar.get(i - 1).getEndMinute(),
                                busyCalendar.get(i).getStartHour(), busyCalendar.get(i).getStartMinute()));
                    }
                }
            }
            i++;
        }
        i--;
        if(range.getEndHour() > busyCalendar.get(i).getEndHour()){
            freeCalendar.add(new Calendars(busyCalendar.get(i).getEndHour(), busyCalendar.get(i).getEndMinute(),
                    range.getEndHour(), range.getEndMinute()));
        }
        else{
            if(range.getEndHour() == busyCalendar.get(i).getEndHour()) {
                if(range.getEndMinute() > busyCalendar.get(i).getEndHour()){
                    freeCalendar.add(new Calendars(busyCalendar.get(i).getEndHour(), busyCalendar.get(i).getEndMinute(),
                            range.getEndHour(), range.getEndMinute()));
                }
            }
        }
    }

    public void stringToBusyCalendars(String c){
        c = c.replace("[", "");
        c = c.replace("'", "");
        c = c.replace("]", "");
        c = c.replace(",", " ");
        c = c.replace(":", " ");
        String[] tmp = c.split(" ");
        int i = 0;
        ArrayList<Calendars> calendars = new ArrayList<Calendars>();
        while(i < tmp.length){
            while(tmp[i].equals("")){
                i++;
            }
            int startHour = Integer.parseInt(tmp[i]);
            i++;
            while(tmp[i].equals("")){
                i++;
            }
            int startMinute = Integer.parseInt(tmp[i]);
            i++;
            while(tmp[i].equals("")){
                i++;
            }
            int endHour = Integer.parseInt(tmp[i]);
            i++;
            while(tmp[i].equals("")){
                i++;
            }
            int endMinute = Integer.parseInt(tmp[i]);
            i++;
            this.busyCalendar.add(new Calendars(startHour, startMinute, endHour, endMinute));
        }
    }

    public static Calendars calendars(String c) {
        c = c.replace("[", "");
        c = c.replace("'", "");
        c = c.replace("]", "");
        c = c.replace(",", " ");
        c = c.replace(":", " ");
        String[] tmp = c.split(" ");
        int i = 0;
        while(tmp[i].equals("")){
            i++;
        }
        int startHour = Integer.parseInt(tmp[i]);
        i++;
        while(tmp[i].equals("")){
            i++;
        }
        int startMinute = Integer.parseInt(tmp[i]);
        i++;
        while(tmp[i].equals("")){
            i++;
        }
        int endHour = Integer.parseInt(tmp[i]);
        i++;
        while(tmp[i].equals("")){
            i++;
        }
        int endMinute = Integer.parseInt(tmp[i]);
        return new Calendars(startHour, startMinute, endHour, endMinute);
    }

    public void addToBusyCalendar(Calendars c){
        busyCalendar.add(c);
    }

    public void addToFreeCalendar(Calendars c){
        freeCalendar.add(c);
    }

    public ArrayList<Calendars> getBusyCalendar() {
        return busyCalendar;
    }

    public void setBusyCalendar(ArrayList<Calendars> busyCalendar) {
        this.busyCalendar = busyCalendar;
    }

    public ArrayList<Calendars> getFreeCalendar() {
        return freeCalendar;
    }

    public void setFreeCalendar(ArrayList<Calendars> freeCalendar) {
        this.freeCalendar = freeCalendar;
    }

    public Calendars getRange() {
        return range;
    }

    public void setRange(Calendars range) {
        this.range = range;
    }
}
