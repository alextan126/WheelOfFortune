import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class WheelOfFortune_v1_0_4 {
    public static void main(String[] args) {
        WheelOfFortune_v1_0_3.WheelOfFortune wheelOfFortune = new WheelOfFortune_v1_0_3.WheelOfFortune();
        Bot bot = new Bot();
        System.out.println("Guess an animal");
        while(WheelOfFortune_v1_0_3.WheelOfFortune.characterLeft > 0){
            char curGuess = bot.guess();
            String stringGuess = Character.toString(curGuess);
            if(WheelOfFortune_v1_0_3.WheelOfFortune.getGuess(stringGuess)){
                WheelOfFortune_v1_0_3.WheelOfFortune.processGuess(stringGuess.charAt(0));
            };
        }
        System.out.println("Congrats! U got it! The secrete code is:" + WheelOfFortune_v1_0_3.WheelOfFortune.hiddenPhrase.toString());
    }

    public static class Bot{
        static Set<Character> previousGuess = new HashSet<>();
        //constructor
        public Bot(){

        };
        //initialize random
        final static Random RANDOM = new Random();
        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        public Character guess(){
            char curGuess = generateRandomCharacter();
            while(previousGuess.contains(curGuess)){
                curGuess = generateRandomCharacter();
            }
            previousGuess.add(curGuess);
            return curGuess;
        }

        public Character generateRandomCharacter(){
            int randIndex = RANDOM.nextInt(ALPHABET.length());
            Character randChar = ALPHABET.charAt(randIndex);
            randChar = Character.toLowerCase(randChar);
            return randChar;
        }
    }
}
