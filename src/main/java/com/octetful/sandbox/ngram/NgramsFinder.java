package com.octetful.sandbox.ngram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NgramsFinder {
  public static List<String> ngrams(String corpus, int n) {
    List<String> ngrams = new ArrayList<>();
    List<String> tokens = (corpus.isBlank())
        ? new ArrayList<>()
        : Arrays.asList(corpus.split("\\s+"));
    for (int i = 0; i < tokens.size() - n + 1; i++) {
      ngrams.add(String.join(" ", tokens.subList(i, i + n)));
    }
    return ngrams;
  }
}
