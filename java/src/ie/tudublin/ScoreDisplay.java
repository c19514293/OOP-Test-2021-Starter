package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class ScoreDisplay extends PApplet
{
	//String score = "DEFGABcd";
	//String score = "D2E2F2G2A2B2c2d2";
	String score = "DEF2F2F2EFA2A2B2AFD2E2D2D2D2";

	ArrayList<Note> notes = new ArrayList<Note>();
	
	public void settings()
	{
		size(1300, 500);

		// How to convert a character to a number
		/*char c = '7'; // c holds the character 7 (55)
		int i = c - '0'; // i holds the number 7 (55 - 48) 
		println(i); */
	}

	public void setup() 
	{

		loadScore();
	}

	public void draw()
	{
		background(255); //makes background white
		strokeWeight(2); //thickness of lines
		line(100, 170, 1300, 170);
		line(100, 210, 1300, 210);
		line(100, 250, 1300, 250);
		line(100, 290, 1300, 290);
		line(100, 330, 1300, 330);
		drawNotes();
	}

	void loadScore() {
		
		for (int i = 0; i < score.length() - 1; i+=2) { //looping through string
			
			//accounting for odd number of characters in string
			if (i + 1 >= score.length()) {
				char c = score.charAt(i);
				Note note = new Note(c, 1);
				notes.add(note);
			}
			else {
				//Reads in 2 characters at a time
				char c = score.charAt(i);
				char n = score.charAt(i+1);


				if (Character.isDigit(n)) { //isDigit checks whether the second character is a number or not
					
					int duration = n - '0'; //converts duration into an integer
					Note note = new Note(c, duration); //creates note object
					notes.add(note); //adds to the ArrayList
				}
				else { //if both notes are duration 1 or quavers
					Note note1 = new Note(c, 1);
					Note note2 = new Note(n, 1);
					notes.add(note1);
					notes.add(note2);
				}
			}
		}
	}

	void printScores() {
		for (int i = 0; i < notes.size(); i++) { //loop through ArrayList
			Note currentNote = notes.get(i); //returns Array element at index i
			System.out.println(currentNote.toString()); //prints element
		}
	}


	int getNotePos(char note) { //gets y position depending on what note it is
		if (note == 'D') return 348;
		else if (note == 'E') return 330;
		else if (note == 'F') return 308;
		else if (note == 'G') return 290;
		else if (note == 'A') return 268;
		else if (note == 'B') return 250;
		else if (note == 'c') return 228;
		else if (note == 'd') return 210;
		else return -1;
	  }
	  
	void drawNotes()
	{
		int x = 140; //beginning x position
		for (int i = 0; i < notes.size(); i++, x+=60) { //looping through ArrayList, spacing between each element/note
			Note note = notes.get(i);
			int y = getNotePos(note.getNote()); //get y value from function
			if (mouseX > x - 18 && mouseX < x + 18) { //if mouse x position is in between the notes, highlight the note red
				fill(255, 0, 0);
			}
			
			if (note.getDuration() == 1) { //if the note is a quaver
				//draws quaver
				circle(x, y, 36);
				fill(0);
				line(x+18, y, x+18, y-90);
				//displays what musical note it is
				textSize(26);
				text(note.getNote(), x - 5, 100);
			}
			else { //draws crochet
				circle(x, y, 36);
				fill(0);
				line(x+18, y, x+18, y-90);
				line(x+18, y-90, x+36, y-74);
				textSize(26);
				text(note.getNote(), x - 5, 100);
			}
		}
	}
}
