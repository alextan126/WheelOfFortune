import java.util.*;

public class WheelOfFortune_v1_0_1 {
    public static void main(String[] args) {
        // Create an instance of WheelOfFortuneMain and call its methods
        WheelOfFortuneMain wheelOfFortune = new WheelOfFortuneMain();
        wheelOfFortune.run();
    }


    static class WheelOfFortuneMain {
        void run() {
            //define a set of variables and instantiation of class
            final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            final Random RANDOM = new Random();
            Scanner scanner = new Scanner(System.in);
            StringBuilder sb = new StringBuilder();

            //Ask user to set the length of their secret code
            System.out.println("Choose your code length: 1<= code length <= 20");

            int length  = scanner.nextInt();
            if(length <= 20 && length >= 1){
                System.out.println("Length accepted");

            } else {
                System.out.println("Length not accepted. Please re-enter");
                length = scanner.nextInt();
            }
            // Reinitialize the Scanner
            scanner = new Scanner(System.in);
            // use a string builder to randomly generate the secrete code

            for (int i = 0; i < length; i++) {
                int randomIndex = RANDOM.nextInt(ALPHABET.length());
                char randomChar = ALPHABET.charAt(randomIndex);
                sb.append(randomChar);
            }

            // convert the secrete code to string and use a HashMap for o(1)
            // retrieving of index of certain guess
            String secretCode = sb.toString();

            //testCase
            //String secretCode = "QhKgYKRXDKDsLZK";


            System.out.println(secretCode);
            // only store lowercase in the hashMap
            HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
            for(int i = 0; i < secretCode.length(); i++){
                char cur = secretCode.charAt(i);
                //convert to lower case
                cur = Character.toLowerCase(cur);
                if(!map.containsKey(cur)){
                    ArrayList<Integer> curList = new ArrayList<>();
                    curList.add(i);
                    map.put(cur, curList);
                } else{
                    ArrayList<Integer> curList = map.get(Character.toLowerCase(cur));
                    curList.add(i);
                    map.put(cur, curList);
                }
            }

            //reset the string builder to empty and fill the string builder with * that matches code length
            sb.setLength(0);
            for (int i = 0; i < length; i++) {
                sb.append('*');
            }
            // now user can make guess and use a counter to track number of correct guesses
            // use a set to ensure that the user doesn't get penalized or bugged guessing same char
            int CharacterLeftCount = secretCode.length();
            Set<Character> dedupSet = new HashSet<>();
            System.out.println("Type in 'remind' to show the cur guess");
            while(CharacterLeftCount > 0){
                System.out.println("Make a guess! Input should be ONE character in the Alphabet.");
                String guess  = scanner.nextLine();
                if(guess.equals("remind")){
                    System.out.println("Now, secrete code is:"+ sb.toString());
                    continue;
                }
                // only go into the game when guess length is 1 and in the alphabet
                if(guess.length() == 1){
                    char curGuess = guess.charAt(0);
                    curGuess = Character.toLowerCase(curGuess);
                    if(!Character.isAlphabetic(curGuess)){
                        System.out.println("Invalid input type. A guess must be in alphabet");
                    } else{
                        if(dedupSet.contains(curGuess)){
                            System.out.println("This guess has been made!");
                            continue;
                        } else{
                            dedupSet.add(curGuess);
                        }
                        ArrayList<Integer> listOfIndexofGuess = map.get(curGuess);
                        if(listOfIndexofGuess != null){
                            int NumberOfLetterOfThisGuess = listOfIndexofGuess.size();
                            CharacterLeftCount -= listOfIndexofGuess.size();
                            System.out.println("You got " + CharacterLeftCount + " characters left!");
                            for(Integer correctIndex : listOfIndexofGuess){
                                sb.setCharAt(correctIndex, secretCode.charAt(correctIndex));
                            }
                            System.out.println("Now, secrete code is:"+ sb.toString());
                        } else{
                            System.out.println("Unfortunately, wrong guess.");
                        }
                    }
                } else{
                    System.out.println("Invalid input length");
                };
            }

            System.out.println("Congrats! U got it! The secrete code is:" + sb.toString());



            scanner.close();
        }
    }
}
