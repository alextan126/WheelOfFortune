import java.util.*;

public class WheelOfFortune_v1_0_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WheelOfFortune wheelOfFortune = new WheelOfFortune();
        System.out.println("Guess an animal");
        while(WheelOfFortune.characterLeft > 0){
            String curGuess = scanner.nextLine();
            if(WheelOfFortune.getGuess(curGuess)){
                WheelOfFortune.processGuess(curGuess.charAt(0));
            };
        }
        System.out.println("Congrats! U got it! The secrete code is:" + WheelOfFortune.hiddenPhrase.toString());

        scanner.close();

    }
    static class WheelOfFortune{
        //final variables
        static final String[] ANIMALS = {
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

        //Variables
        static StringBuilder hiddenPhrase;
        static String phrase;
        static int characterLeft;
        //use a map for fast retrieve of character index
        static HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        //use a set to avoid duplicated guess
        static Set<Character> dedupSet = new HashSet<>();



        //constructor
        public WheelOfFortune(){
            phrase = randomPhrase();
            phraseToMap();
            characterLeft = phrase.length();
            generateHiddenPhrase();
            //System.out.println(characterLeft);
        }




        //initialize random and sb
        final static Random RANDOM = new Random();
        static StringBuilder sb = new StringBuilder();

        //randomly pick a phrase
        public static String randomPhrase(){
            int randomIndex = RANDOM.nextInt(ANIMALS.length);
            return ANIMALS[randomIndex];
        }

        //phrase to map with lowerCase character as key and index list as value
        public static void phraseToMap(){
            for(int i = 0; i < phrase.length(); i++){
                char cur = phrase.charAt(i);
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
        }

        //generateHiddenPhrase
        public static void generateHiddenPhrase(){
            // reset sb
            sb.setLength(0);
            for (int i = 0; i < phrase.length(); i++) {
                sb.append('*');
            }
            hiddenPhrase = new StringBuilder(sb);
        }




        public static boolean getGuess(String guess){
            if(guess.equals("remind")){
                System.out.println("Now, secrete code is:"+ hiddenPhrase.toString());
                return false;
            }
            //cheat code section
            if(guess.equals("TellMeTruth")){
                System.out.println(phrase);
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


        public static void processGuess(Character curGuess){
            if(dedupSet.contains(curGuess)){
                System.out.println("This guess has been made!");
            } else{
                dedupSet.add(curGuess);
                ArrayList<Integer> listOfIndexofGuess = map.get(curGuess);
                if(listOfIndexofGuess != null){
                    for(Integer correctIndex : listOfIndexofGuess){
                        hiddenPhrase.setCharAt(correctIndex, phrase.charAt(correctIndex));
                        characterLeft--;
                        //System.out.println(characterLeft);
                    }
                    System.out.println("You got " + characterLeft + " characters left!");
                    System.out.println("Now, secrete code is:"+ hiddenPhrase.toString());
                } else{
                    System.out.println("Unfortunately, wrong guess.");
                }
            }
        }
    }

}

