 import java.util.Random;
import java.util.Scanner;

    public class main {// beginning of main class

        public static void main(String[] args) {// beginning of the main method
            Scanner s = new Scanner(System.in);
            Random r = new Random();
           //variable is used to find out the users input using a char variable.
            String input = "";// the variable input is used to find out the user's string input.
            int topic = 0;// this topic variable is used in order to identify the topic in which the user plays
            int word = 0;// the word is used to show the random word in which the user picks.
            String[] days = {"DAYS", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};// array made up of days
            String[] months = {"MONTHS", "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};// this array is made up of words related to the months
            String[] holidays = {"HOLIDAYS", "VALENTINES", "EASTER", "THANKSGIVING", "CHRISTMAS"};// this array is related to some of the holidays we celebrate
            String[][] categories = {days, months, holidays}; // this 2d array is used to randomize all the categories
            String dashLine = "";// this variable is used to signify the amount of dashes. By combining the word and the dash method helps create the dashLine variable.
            int index = -1;// the index is used to find out where each letter should be placed.
            String decision = "y";// string decision is used for the while loop if the user wants to keep on playing.
            int guesses = 7;// the user only has 7 guesses in order to guess the word.
            boolean letterFound = false;// by using a boolean variable helps to show if there are letters that aren't in the word.
            String secretWord = ""; //variable that the word is stored in
            StringBuilder lettersGuessed = new StringBuilder();// variable lettersGuessed is used to show what letter the user has entered
            int wins = 0; // this helps calculate the amount of wins the user has.
            int games = 0; // this helps calculate the amount of games the user has played.


            while (decision.equals("y")) {// beginning of big while decision loop
                secretWord = ranWord();
                int guess = 0;// this helps reset the variable each time the user loops.
                input = "";
                guesses = 7;
                System.out.println("Welcome To Hangman!");
                games = games + 1;
                dashLine = dash(secretWord);
                while (guess != '!') {// beginning of guess loop
                    System.out.println(dashLine);
                    if (!lettersGuessed.isEmpty()) {
                        System.out.println("You have " + guesses + " guesses left.");
                    }
                    System.out.println("\nLetters Guessed: " + lettersGuessed + " ");
                    System.out.println("\nPlease guess a letter in this word. Please enter an exclamation mark '!' to guess the word.");
                    input = s.next().toUpperCase();
                    index = -1;
                    while (lettersGuessed.toString().contains(input)) {// beginning of lettersGuessed while loop
                        System.out.println("Letter has already been guessed.");
                        System.out.println("Please guess another letter.");
                        input = s.next().toUpperCase();
                    }// end of lettersGuessed while loop
                    lettersGuessed.append(input);
                    if (input.equals("!")) {
                        guess = '!';
                        System.out.println("Guess the word:");
                        input = s.next().toUpperCase();
                        if (input.equals(secretWord)) {
                            System.out.println("Congratulations! You Won! Great Job!");
                            wins = wins + 1;
                        } else {
                            System.out.println("Better luck next time!");
                            System.out.println("The word was " + secretWord + ".");
                        }
                    } else {
                        letterFound = false;
                        do {
                            index = guessingLetter(secretWord, input, (index + 1));

                            if (index != -1) {
                                dashLine = dashLine.substring(0, index) + input + dashLine.substring((index + 1));// to find out repeated letter
                                letterFound = true;
                            }
                        } while (index >= 0);

                        if (dashLine.equals(secretWord)) {
                            System.out.println("Congratulations! You Win! Great Job!");
                            System.out.println("You had " + guesses + " guesses left.");
                            wins = wins + 1;
                            guess = '!';
                        }
                        if (!letterFound) {
                            guesses = guesses - 1;
                            System.out.println("Letter not found.");
                            if (guesses == 0) {
                                guess = '!';
                            }
                        }
                    }
                    hangMan(guesses);
                }// end of while guess loop
                System.out.println("Do you want to play again? Please enter y to continue playing or enter any other key to end the game.");// When the user inputs no to when they want to play again the program ends.
                decision = s.next();
                lettersGuessed = new StringBuilder();
            }// end of the big decision while loop
            average (games, wins);
        }// end of main method
        /**
         * Description: This method helps determine the amount of dashes the word
         * needs. This method is an example of a single parameter method.
         * Precondition: The word is required. Postcondition: The amount of dashes
         * for the word is outputted.
         * @param secretWord - the parameter secretWord is used in order to find out
         * the amount of dashes.
         * @return line - by returning the amount of lines helps to find out how
         * many dashes the word has.
         */
        public static String dash(String secretWord) {// beginning of dash method
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < secretWord.length(); i++) {
                line.append("-");
            }
            return line.toString();
        }// end of dash method
        /**
         * Description: This method is an example of a multi parameter method. This
         * method helps determine where each letter goes in the word. By finding out
         * the index of each dash helps determine the placement of each letter.
         * Precondition: The word is required. Postcondition: The letter is placed
         * in the correct spot.
         * @param secretWord - this parameter helps to find where the letter should
         * be placed.
         * @param input - this parameter helps the computer find out what the input
         * is and where the input should be placed.
         * @param start - this parameter is used to find out if the parameter where
         * the parameter starts.
         * @return - by returning the index helps find out which index the letter
         * should go.
         */
        public static int guessingLetter(String secretWord, String input, int start) {// beginning of guessingWord method
            int index = -1;
            char guess = input.charAt(0);
            for (int i = start; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == (guess)) {
                    index = i;
                    i = secretWord.length();
                }
            }
            return index;
        }// end of method guessingWord
        /**
         * Description: This method is an example of a single parameter method. This
         * method is used in order to show the hangMan. This method uses arrays in
         * order to display each body part in order.
         * Precondition: The guess amount is required.
         * Postcondition: The amount of body parts is outputted in
         * order to show how the hangman looks.
         * @param guesses - guesses is needed as each time the amount of guesses is
         * decreased the computer outputs the amount of bodyParts which are
         * necessary.
         */
        public static void hangMan(int guesses) {// beginning of hangMan method
            int bodyParts = 7 - guesses;
            String body[] = {" 0 ", "\n/", "|", "\\", "\n | ", "\n/", " \\ "};

            for (int i = 0; i < bodyParts; i++) {
                System.out.print(body[i]);
            }
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
        }// end of hangMan method
        /**
         * Description: This method helps randomize which word is going to be
         * chosen. This method is an example of a single parameter method.
         * Precondition: Array shouldn't be empty.
         * Postcondition: Randomizes the word.
         * @return - this helps return what the randomized word is.
         */
        public static String ranWord() {// beginning of ranWord method
            Random r = new Random();
            int topic = 0;
            int word = 0;
            String[] days = {"DAYS", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
            String[] months = {"MONTHS", "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
            String[] holidays = {"HOLIDAYS", "VALENTINES", "EASTER", "THANKSGIVING", "CHRISTMAS"};
            String[][] categories = {days, months, holidays}; //2d array
            String secretWord = "";
            topic = r.nextInt(3);
            while (word == 0) {
                word = r.nextInt(categories[topic].length);
                secretWord = categories[topic][word];
            }
            System.out.println("Your topic is: " + categories[topic][0]);
            return secretWord;
        } //end of method ranWord
        /** Description: This method is an example of a multi parameter method. This method
         * is used in order to calculate the user's win percentage and to show the amount of
         * games and the amount of wins.
         * Precondition: Amount Of Games And Amount Of Wins is needed.
         * Postcondition: The win percentage is found and the rest is outputted to the user.
         * @param games
         * @param wins
         */
        public static void average (int games, int wins) {// beginning of average method


            System.out.println("The amount of games you have won is: " + wins); // this helps finds out the player's win percentage.
            System.out.println("The amount of games you have played is: " + games);
            System.out.println("Your win percentage is: " + (wins * 100/games) + "%");
            System.out.println("Thank you for playing!");
        }// end of average method
    } //end of main class

