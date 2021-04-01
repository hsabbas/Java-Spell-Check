package spellcheck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SentenceSpellCheckerTest {
  SentenceSpellChecker sentenceSpellChecker;

  @BeforeEach
    public void setUp() {
        sentenceSpellChecker = new SentenceSpellChecker();
    }

  @Test
    public void canary() {
        assertTrue(true);
    }

  @Test
  public void spellCheckingAnEmptyStringReturnsTheGivenInput(){
    assertEquals("", sentenceSpellChecker.checkSpelling(""));
  }

  @Test
  public void spellCheckingOneWordReturnsOneWord(){
    sentenceSpellChecker.setSpellCheckService(new AgileSpellCheckingService());
    assertEquals("one", sentenceSpellChecker.checkSpelling("one"));
  }

  @Test
  public void spellCheckingTwoWordsReturnsTwoWords(){
    sentenceSpellChecker.setSpellCheckService(new AgileSpellCheckingService());
    assertEquals("two words", sentenceSpellChecker.checkSpelling("two words"));
  }

  @Test
  public void spellCheckingOneMispelledWordReturnsTheWordInBrackets(){
    SpellCheckingService spellCheckingService = Mockito.mock(SpellCheckingService.class);
    when(spellCheckingService.wordIsSpelledCorrectly("oen")).thenReturn(false);
    sentenceSpellChecker.setSpellCheckService(spellCheckingService);
    assertEquals("[oen]", sentenceSpellChecker.checkSpelling("oen"));
  }

  @Test
  public void spellCheckingTwoWordsWithFirstMispelledReturnsWithFirstWordInBrackets(){
    SpellCheckingService spellCheckingService = Mockito.mock(SpellCheckingService.class);
    when(spellCheckingService.wordIsSpelledCorrectly("oen")).thenReturn(false);
    when(spellCheckingService.wordIsSpelledCorrectly("two")).thenReturn(true);
    sentenceSpellChecker.setSpellCheckService(spellCheckingService);
    assertEquals("[oen] two", sentenceSpellChecker.checkSpelling("oen two"));
  }

  @Test
  public void spellCheckingTwoWordsWithSecondMispelledReturnsWithSecondWordInBrackets(){
    SpellCheckingService spellCheckingService = Mockito.mock(SpellCheckingService.class);
    when(spellCheckingService.wordIsSpelledCorrectly("one")).thenReturn(true);
    when(spellCheckingService.wordIsSpelledCorrectly("wto")).thenReturn(false);
    sentenceSpellChecker.setSpellCheckService(spellCheckingService);
    assertEquals("one [wto]", sentenceSpellChecker.checkSpelling("one wto"));
  }

  @Test
  public void spellCheckingTwoWordsWithBothMispelledReturnsBothWordsInBrackets(){
    SpellCheckingService service = Mockito.mock(SpellCheckingService.class);
    when(service.wordIsSpelledCorrectly("oen")).thenReturn(false);
    when(service.wordIsSpelledCorrectly("wto")).thenReturn(false);
    sentenceSpellChecker.setSpellCheckService(service);
    assertEquals("[oen] [wto]", sentenceSpellChecker.checkSpelling("oen wto"));
  }


}
