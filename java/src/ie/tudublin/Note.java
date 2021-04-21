package ie.tudublin;

public class Note {

    private char note;
    private int duration;

    //constructor to initialise values
    Note(char note, int duration) {
        this.note = note;
        this.duration = duration;
    }

    //helper function to return private variables
    public char getNote()
    {
        return note;
    }

    //helper function to return private variables
    public int getDuration()
    {
        return duration;
    }

    //returning string of notes
    public String toString() {
        if (duration == 1) return(note + "    " + duration + "    Quaver");
        else return(note + "    " + duration + "    Crotchet");
    }

}