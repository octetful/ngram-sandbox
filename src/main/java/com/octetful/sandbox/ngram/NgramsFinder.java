package com.octetful.sandbox.ngram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class NgramsFinder {

  public static final String START_TOKEN = "<seq>";
  public static final String END_TOKEN = "</seq>";

  private NgramsFinder() {

  }

  public static List<String> ngramsWithPadding(String corpus, int n) {
    return ngrams(corpus, n, x -> applySpecialSymbolPadding(tokenise(x)));
  }

  public static List<String> ngrams(String corpus, int n) {
    return ngrams(corpus, n, NgramsFinder::tokenise);
  }

  protected static List<String> ngrams(String corpus, int n, Function<String, List<String>> tokeniser) {
    List<String> ngrams = new ArrayList<>();
    List<String> tokens = tokeniser.apply(corpus);
    for (var i = 0; i < tokens.size() - n + 1; i++) {
      ngrams.add(String.join(" ", tokens.subList(i, i + n)));
    }
    return ngrams;
  }

  private static List<String> applySpecialSymbolPadding(List<String> tokens) {
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
