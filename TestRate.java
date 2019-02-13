
public abstract class TestRate extends TestLetterGrader {
		
	
	public static double weightedAvg(int[] score) {
		
				
			//TODO check why double and not float
				double result = 0;
				double[] weight = {0.1, 0.1, 0.1, 0.1, 0.2, 0.15, 0.25};
				int i;	
					for(i =0; i < score.length; i++){
					    result = result + (score[i] * weight[i]);
					}
					
			return result;
								
	}


			public char getGrade(int score){
			
				char grade =' ';
				
				
					if(score <= 59){
					    grade = 'F';
					}else
					if(score >= 60 && score <=69){
						grade = 'D';
					}else
					if (score >=70 && score <=79){
						grade = 'C';
					}else
					if (score >= 80 && score <=89){
						grade = 'B';
					}else
					if (score >= 90 && score <=100){
						grade = 'A';
					}
					return grade;
			}
		}

