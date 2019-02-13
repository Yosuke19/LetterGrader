
public class TestLetterGrader{
	
	public static void main(String[] args) {
		
		if (args.length < 2) {
			System.out.print("Usage: java Assignment Final inputFile outputFile\n");
			return;
		} else {
			
			LetterGrader tlg = new LetterGrader(args[0], args[1]);
			
			tlg.readScore();
			tlg.printGrade();
			tlg.displayAverages();
			
		}

	}
}
