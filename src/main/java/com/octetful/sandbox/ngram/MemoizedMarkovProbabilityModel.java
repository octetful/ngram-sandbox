package com.octetful.sandbox.ngram;

import java.util.HashMap;
import java.util.Map;

public class MemoizedMarkovProbabilityModel {
  private final int n;
  private final Map<String, Integer> histogram;

  public MemoizedMarkovProbabilityModel(int n) {
    this.n = n;
    this.histogram = new HashMap<>();
  }

  public Map<String, Integer> getHistogram() {
    return histogram;
  }

  public void train(String corpus) {
    NgramsFinder.ngrams(corpus, n)
        .forEach(this::updateHistogram);
  }

  public double probability(String key) {
    String given = key.substring(0,
        key.lastIndexOf(" "));
    return count(key) / count(given);
  }

  private double count(String key) {
    return histogram.keySet().stream()
        .filter(k -> k.startsWith(key))
        .mapToInt(histogram::get)
        .sum();
  }

  private void updateHistogram(String key) {
    histogram.compute(key,
        (k, v) ->
            (v != null) ? v + 1 : 1);
  }
}
