import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import utility.ILetterGrader;

public class LetterGrader implements ILetterGrader {
	// Implemented interface ILetterGrader
	
	String letterGradeInput;
	String letterGradeOutput;
	HashMap<String,Character>  allGrades = new HashMap<String, Character>();
	int totTests = 7;
	int totStudents = 0;
	static double[] testWeight = {0.1, 0.1, 0.1, 0.1, 0.2, 0.15, 0.25};	
	TestScore[] allTests = new TestScore[totTests];

	public LetterGrader(String letterGradeInput, String letterGradeOutput) {
		System.out.println("Letter grade has been calculated for students listed in input file "+ letterGradeInput + " and written to output file " + letterGradeOutput + "\n");
		this.letterGradeInput = letterGradeInput;
		this.letterGradeOutput = letterGradeOutput;	
		for(int f = 0; f < totTests; f++) {
			 allTests[f] = new TestScore();
		}
	}
	
	public void readScore() {
		//read input, determines letter grade and stores information 
		
		String name,input;
		try {
			FileReader fileReader = new FileReader(letterGradeInput);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

	        while((input = bufferedReader.readLine()) != null) {
	            totStudents += 1;
				String[] strArray = input.split(",");
				name = strArray[0];
				
	            int[] grades = new int[strArray.length - 1];
	            for(int i = 0; i < strArray.length - 1; i++) {
	            	int k = Integer.parseInt(strArray[i + 1].trim());
	            	grades[i] = k;
	            	// update the correct TestScore object with new input
	            	allTests[i].processNewGrade(k);
	            }
	            double finalGrade = weightedAvg(grades);
	            char gradeLetter = getGrade(finalGrade);
	            allGrades.put(name, gradeLetter);	
	        }
	         bufferedReader.close();   

		} catch (FileNotFoundException ex) {
				System.out.println("Unable to open file '" + letterGradeInput + "'");
		} catch (IOException ex) {
				System.out.println("Error reading file '" + letterGradeInput + "'");
		}
	}
	
	
	public void printGrade() {
		// write grades in output file sorted by student name and use error exceptions
		try {
			FileWriter fileWriter = new FileWriter(letterGradeOutput);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			String intro = "Letter grade for " + totStudents + " students given in " + letterGradeInput + " file is:\n\n";
			bufferedWriter.write(intro);
			
	         SortedSet<String> names = new TreeSet<>(allGrades.keySet());
	         for (String name : names) { 
	            char value = allGrades.get(name);	            	  
	            name += ":";
	            for (int j = name.length(); j < 30; j++) {
	            	name += " ";
	            }
				bufferedWriter.write(name);
	            bufferedWriter.write(value);
	            bufferedWriter.newLine();
	         }  
	         bufferedWriter.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + letterGradeOutput + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + letterGradeOutput + "'");
		}
	}

	public void displayAverages() {
		// Calculate average, minimum and maximum by the test scores 
		if (totStudents == 0) {
			System.out.print("\nNo data to display (number of students is 0)");
		}
		System.out.println("Here is the class averages:");
		System.out.println("\t\tQ1\tQ2\tQ3\tQ4\tMidI\tMidII\tFinal");
		
		System.out.print("Average:\t");
		for(TestScore t: allTests) {
			float avg = (float) t.gradesSum / totStudents;
			System.out.printf("%.2f\t", avg);
		}
		System.out.print("\nMinimum:\t   ");
		for(TestScore t: allTests) {
			System.out.print(t.min + "\t   ");
		}
		System.out.print("\nMaximum:\t  ");
		for(TestScore t: allTests) {
			System.out.print(t.max + "\t  ");
		}
		System.out.println("\n\nPress ENTER to continue . . .");
	}
	
	private static double weightedAvg(int[] score) {
		// Calculate testWeight 
		double result = 0;
		int i;	
		for(i = 0; i < score.length ; i++){
		    result = result + (score[i] * testWeight[i]);
		}
		return result;
	}

	private static char getGrade(double score){
		//Grade by students test scores
		if (score < 0 || score > 100) {
			System.out.println("Score should be 0 to 100");
		}
		char grade =' ';
		if(score <= 59){
		    grade = 'F';
		} else if(score >= 60 && score < 70){
			grade = 'D';
		}else if (score >=70 && score < 80){
			grade = 'C';
		}else if (score >= 80 && score < 90){
			grade = 'B';
		}else if (score >= 90 && score <=100){
			grade = 'A';
		}
		return grade;
	}
}
