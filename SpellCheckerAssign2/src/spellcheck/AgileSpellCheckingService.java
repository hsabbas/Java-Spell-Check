package spellcheck;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class AgileSpellCheckingService implements SpellCheckingService{
  private String queryURL = "http://agile.cs.uh.edu/spell?check=";

  @Override
  public boolean wordIsSpelledCorrectly(String word) {
    try {
      URL url = new URL(queryURL + word);
      URLConnection connection = url.openConnection();
      InputStream inputStream = connection.getInputStream();

      Scanner scanner = new Scanner(inputStream);
      String result = scanner.next();

      return result.equalsIgnoreCase("true");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }
}
