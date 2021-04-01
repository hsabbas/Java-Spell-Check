package spellcheck;

public class SentenceSpellChecker {
  private SpellCheckingService spellCheckingService;

  public void setSpellCheckService(SpellCheckingService service){
    spellCheckingService = service;
  }

  public String checkSpelling(String sentence) {
    if(sentence.equals(""))
      return sentence;

    String[] wordsOfSentence = sentence.split(" ");

    for(int i = 0; i < wordsOfSentence.length; i++){
      if(!spellCheckingService.wordIsSpelledCorrectly(wordsOfSentence[i])){
        wordsOfSentence[i] = "[" + wordsOfSentence[i] + "]";
      }
    }

    return String.join(" ", wordsOfSentence);
  }
}