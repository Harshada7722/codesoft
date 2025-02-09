import java.util.Scanner;

public class QuizApp {
    private static final int TIME_LIMIT = 30; 
    private static int currentQuestionIndex = 0;
    private static int score = 0;
    private static long startTime;
    private static String[] userAnswers;

    public static void main(String[] args) {
        // Create quiz questions
        QuizQuestion[] questions = {
            new QuizQuestion("What is the capital of France?", 
                             new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 
                             "Paris"),
            new QuizQuestion("What is the largest planet in our solar system?", 
                             new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 
                             "Jupiter"),
            new QuizQuestion("Which element's chemical symbol is 'O'?", 
                             new String[]{"Oxygen", "Osmium", "Ozone", "Oganesson"}, 
                             "Oxygen")
        };

        userAnswers = new String[questions.length];

        
        for (int i = 0; i < questions.length; i++) {
            displayQuestion(questions[i]);
            startTimer();
            getUserAnswer(i);
            currentQuestionIndex++;
        }

        
        showResult(questions);
    }

    
    public static void displayQuestion(QuizQuestion question) {
        System.out.println("\nQuestion: " + question.getQuestion());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    
    public static void startTimer() {
        startTime = System.currentTimeMillis();
    }

    
    public static void getUserAnswer(int questionIndex) {
        Scanner scanner = new Scanner(System.in);
        long timeTaken = 0;

        while (timeTaken < TIME_LIMIT * 1000) {
            System.out.print("Choose your answer (1-4): ");
            if (scanner.hasNextInt()) {
                int answerIndex = scanner.nextInt() - 1;
                if (answerIndex >= 0 && answerIndex < 4) {
                    userAnswers[questionIndex] = String.valueOf(answerIndex);
                    break;
                }
            }
            System.out.println("Invalid input, please enter a number between 1 and 4.");
            timeTaken = System.currentTimeMillis() - startTime;
        }

        if (timeTaken >= TIME_LIMIT * 1000) {
            System.out.println("Time is up!");
            userAnswers[questionIndex] = "3"; r
        }
    }

   
    public static void showResult(QuizQuestion[] questions) {
        score = 0;

        for (int i = 0; i < questions.length; i++) {
            String userAnswer = userAnswers[i];
            String correctAnswer = questions[i].getCorrectAnswer();

            if (questions[i].getOptions()[Integer.parseInt(userAnswer)].equals(correctAnswer)) {
                score++;
            }

            System.out.println("\nQuestion: " + questions[i].getQuestion());
            System.out.println("Your Answer: " + questions[i].getOptions()[Integer.parseInt(userAnswer)]);
            System.out.println("Correct Answer: " + correctAnswer);
        }

        System.out.println("\nYour total score: " + score + " out of " + questions.length);
    }
}


class QuizQuestion {
    private String question;
    private String[] options;
    private String correctAnswer;

    public QuizQuestion(String question, String[] options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
