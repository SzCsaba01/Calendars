public class Calendars  implements  Comparable<Calendars>{
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;

    public Calendars(int startHour, int startMinute, int endHour, int endMinute){
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }


    @Override
    public int compareTo(Calendars o) {
        if(this.startHour > o.startHour)
            return 1;
        if(this.startHour < o.startHour)
            return -1;
        if(this.startMinute > o.startMinute)
            return 1;
        if(this.startMinute < o.startMinute)
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        if(startMinute < 10){
            String t1 = "0"+startMinute;
            if(endMinute < 10){
                String t2 = "0" + endMinute;
                return "['" + startHour + ":" + t1 + "','" + endHour + ":" + t2 + "']";
            }
            return "['" + startHour + ":" + t1 + "','" + endHour + ":" + endMinute + "']";
        }
        if(endMinute < 10){
            String t2 = "0" + endMinute;
            return "['" + startHour + ":" + startMinute + "','" + endHour + ":" + t2 + "']";
        }
        return "['" + startHour + ":" + startMinute + "','" + endHour + ":" + endMinute + "']";
    }
}
