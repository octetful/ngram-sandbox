package com.octetful.sandbox.ngram;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.octetful.sandbox.ngram.NgramsFinder.ngrams;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NgramsTest {
  @Test
  void shouldReturnEmptyListForEmptyCorpus() {
    assertEquals(0, ngrams("", 1).size());
  }

  @Test
  @DisplayName("Should return the same token for a single token corpus for unigrams")
  void shouldReturnTokenForSingleTokenCorpusForUnigrams() {
    assertEquals("Humpty", ngrams("Humpty", 1).get(0));
  }

  @Test
  @DisplayName("Should return the two tokens for unigrams for a corpus with two tokens")
  void shouldReturnSingleTokenForUnigramsForTwoTokenCorpus() {
    assertEquals("Humpty", ngrams("Humpty Dumpty", 1).get(0));
    assertEquals("Dumpty", ngrams("Humpty Dumpty", 1).get(1));
  }

  @Test
  @DisplayName("Should return all bigrams for a given corpus")
  void shouldReturnAllBigramsForCorpus() {
    final String corpus = "Humpty Dumpty sat";
    int n = 2;
    assertEquals("Humpty Dumpty", ngrams(corpus, n).get(0));
    assertEquals("Dumpty sat", ngrams(corpus, n).get(1));
  }

  @Test
  @DisplayName("Should return exact number of expected bigrams for a given corpus")
  void shouldReturnExactNumberOfExpectedBigramsForCorpus() {
    final String corpus = "Humpty Dumpty sat";
    int n = 2;
    assertEquals(2, ngrams(corpus, n).size());
  }

  @Test
  @DisplayName("Should return all trigrams for a given corpus")
  void shouldReturnAllTrigramsForCorpus() {
    final String corpus = "Humpty Dumpty sat on a wall";
    int n = 3;
    assertEquals("Humpty Dumpty sat", ngrams(corpus, n).get(0));
    assertEquals("Dumpty sat on", ngrams(corpus, n).get(1));
    assertEquals("sat on a", ngrams(corpus, n).get(2));
    assertEquals("on a wall", ngrams(corpus, n).get(3));
  }

  @Test
  @DisplayName("Should return exact number of expected trigrams for a given corpus")
  void shouldReturnExactNumberOfExpectedTrigramsForCorpus() {
    final String corpus = "Humpty Dumpty sat on a wall";
    int n = 3;
    assertEquals(4, ngrams(corpus, n).size());
  }
}
