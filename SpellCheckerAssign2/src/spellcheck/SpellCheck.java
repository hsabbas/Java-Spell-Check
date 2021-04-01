package spellcheck;

import java.io.*;

public class SpellCheck {
  public static void main(String[] args){
    SentenceSpellChecker sentenceSpellChecker = new SentenceSpellChecker();
    sentenceSpellChecker.setSpellCheckService(new AgileSpellCheckingService());

    String fileName = "input.txt";
    BufferedReader bufferedReader = null;

    try {
      bufferedReader = new BufferedReader(new FileReader(fileName));
      String sentence;
      while((sentence = bufferedReader.readLine()) != null){ //Feedback: changed == to !=
        
        System.out.println(sentenceSpellChecker.checkSpelling(sentence));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
