package com.octetful.sandbox.ngram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NgramsFinder {

  public static final String START_TOKEN = "<seq>";
  public static final String END_TOKEN = "</seq>";

  private NgramsFinder() {

  }

  public static List<String> ngrams(String corpus, int n) {
    List<String> ngrams = new ArrayList<>();
    List<String> tokens = normalise(tokenise(corpus));
    for (int i = 0; i < tokens.size() - n + 1; i++) {
      ngrams.add(String.join(" ", tokens.subList(i, i + n)));
    }
    return ngrams;
  }

  private static List<String> normalise(List<String> tokens) {
    if ( tokens.isEmpty() )
      return tokens;
    var normalisedList = new ArrayList<String>();
    normalisedList.add(START_TOKEN);
    normalisedList.addAll(tokens);
    normalisedList.add(END_TOKEN);
    return normalisedList;
  }

  private static List<String> tokenise(String corpus) {
    if (corpus.isBlank())
        return new ArrayList<>();

    return Arrays.asList(corpus.split("\\s+"));
  }
}
