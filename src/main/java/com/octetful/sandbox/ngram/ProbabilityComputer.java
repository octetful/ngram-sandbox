package com.octetful.sandbox.ngram;

import java.util.List;

public class ProbabilityComputer {
  private final String corpus;
  private final int totalWordsCount;

  public ProbabilityComputer(String corpus) {
    this.corpus = corpus;
    totalWordsCount = wordCount(this.corpus);
  }

  public double count(String sequence) {
    List<String> ngrams = NgramsFinder.ngrams(corpus, wordCount(sequence));
    return ngrams
        .stream()
        .filter(sequence::equalsIgnoreCase)
        .count();
  }

  public double probability(String sequence) {
    if ( wordCount(sequence) <= 1 ) {
      return count(sequence)/ totalWordsCount;
    }

    String remainingSequence = sequence.substring(0, sequence.lastIndexOf(" "));
    return (count(sequence)/count(remainingSequence))*probability(remainingSequence);
  }

  private int wordCount(String sequence) {
    return sequence.trim().split("\\s+").length;
  }
}
