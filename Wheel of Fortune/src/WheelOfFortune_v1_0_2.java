import java.util.*;

//CheatCode : CallMeGod

public class WheelOfFortune_v1_0_2 {
    public static void main(String[] args) {
        //define a set of variables and instantiation of class
        Scanner scanner = new Scanner(System.in);
        //initialize the new tools

        //Ask user to set the length of their secret code
        System.out.println("Guess an animal");

        // use randomPhrase to randomly generate the secrete code
        String secretCode = randomPhrase();
        int length = secretCode.length();



        //testCase
        //String secretCode = "QhKgYKRXDKDsLZK";



        // convert the secrete code to string and use a HashMap for o(1)
        // retrieving of index of certain guess
        // only store lowercase in the hashMap
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0; i < length; i++){
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

        // fill the string builder with * that matches code length
        generateHiddenPhrase(length);
        // now user can make guess and use a counter to track number of correct guesses
        // use a set to ensure that the user doesn't get penalized or bugged guessing same char

        //use int array for reference passing
        int[] CharacterLeftCount = new int[] {length};
        Set<Character> dedupSet = new HashSet<>();
        System.out.println("Type in 'remind' to show the cur guess");

        while(CharacterLeftCount[0] > 0){
            System.out.println("Make a guess! Input should be ONE character in the Alphabet.");
            String guess  = scanner.nextLine();
            if(getGuess(guess)){
                Character curGuess = Character.toLowerCase(guess.charAt(0));
                processGuess(secretCode, dedupSet , curGuess,map, CharacterLeftCount);
            } else if(guess.equals("TellMeTruth")){
                System.out.println(secretCode);
            }
        }



        System.out.println("Congrats! U got it! The secrete code is:" + sb.toString());



        scanner.close();
    }


    //initialize animals and sb
    static final String[] animals = {
            "Lion",
            "Tiger",
            "Elephant",
            "Giraffe",
            "Zebra",
            "Panda",
            "Kangaroo",
            "Bear",
            "Wolf",
            "Fox",
            "Horse",
            "Monkey",
            "Rabbit",
            "Deer",
            "Penguin"
    };
    final static Random RANDOM = new Random();
    static StringBuilder sb = new StringBuilder();

    public static String randomPhrase(){
        StringBuilder sb = new StringBuilder();
        int randomIndex = RANDOM.nextInt(animals.length);
        return animals[randomIndex];
    }



    public static void generateHiddenPhrase(int length){
        // reset sb
        sb.setLength(0);
        for (int i = 0; i < length; i++) {
            sb.append('*');
        }
    }

    public static boolean getGuess(String guess){
        if(guess.equals("remind")){
            System.out.println("Now, secrete code is:"+ sb.toString());
            return false;
        }
        // only go into the game when guess length is 1 and in the alphabet
        if(guess.length() == 1){
            char curGuess = guess.charAt(0);
            curGuess = Character.toLowerCase(curGuess);
            if(!Character.isAlphabetic(curGuess)){
                System.out.println("Invalid input type. A guess must be in alphabet");
                return false;
            } else{
               return true;
            }
        } else{
            System.out.println("Invalid input length");
            return false;
        }
    }


    public static void processGuess(String secretCode, Set<Character> dedupSet, Character curGuess, HashMap<Character,ArrayList<Integer>> map, int[] CharacterLeftCount){
        if(dedupSet.contains(curGuess)){
            System.out.println("This guess has been made!");
            return;
        } else{
            dedupSet.add(curGuess);
        }
        ArrayList<Integer> listOfIndexofGuess = map.get(curGuess);
        if(listOfIndexofGuess != null){
            int NumberOfLetterOfThisGuess = listOfIndexofGuess.size();
            CharacterLeftCount[0] -= listOfIndexofGuess.size();
            System.out.println("You got " + CharacterLeftCount[0] + " characters left!");
            for(Integer correctIndex : listOfIndexofGuess){
                sb.setCharAt(correctIndex, secretCode.charAt(correctIndex));
            }
            System.out.println("Now, secrete code is:"+ sb.toString());
        } else{
            System.out.println("Unfortunately, wrong guess.");
        }
    }
}
