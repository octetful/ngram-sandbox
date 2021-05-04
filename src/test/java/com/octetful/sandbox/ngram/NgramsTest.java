package com.octetful.sandbox.ngram;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.octetful.sandbox.ngram.NgramsFinder.ngramsWithPadding;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NgramsTest {
  @Test
  void shouldReturnEmptyListForEmptyCorpus() {
    assertEquals(0, ngramsWithPadding("", 1).size());
  }

  @Test
  @DisplayName("Should return the same token for a single token corpus for unigrams")
  void shouldReturnTokenForSingleTokenCorpusForUnigrams() {
    String corpus = "Humpty";
    assertEquals(NgramsFinder.START_TOKEN, ngramsWithPadding(corpus, 1).get(0));
    assertEquals("Humpty", ngramsWithPadding(corpus, 1).get(1));
    assertEquals(NgramsFinder.END_TOKEN, ngramsWithPadding(corpus, 1).get(2));
  }

  @Test
  @DisplayName("Should return the two tokens for unigrams for a corpus with two tokens")
  void shouldReturnSingleTokenForUnigramsForTwoTokenCorpus() {
    String corpus = "Humpty Dumpty";
    assertEquals(NgramsFinder.START_TOKEN, ngramsWithPadding(corpus, 1).get(0));
    assertEquals("Humpty", ngramsWithPadding(corpus, 1).get(1));
    assertEquals("Dumpty", ngramsWithPadding(corpus, 1).get(2));
    assertEquals(NgramsFinder.END_TOKEN, ngramsWithPadding(corpus, 1).get(3));
  }

  @Test
  @DisplayName("Should return all bigrams for a given corpus")
  void shouldReturnAllBigramsForCorpus() {
    final String corpus = "Humpty Dumpty sat";
    int n = 2;
    assertEquals(NgramsFinder.START_TOKEN + " Humpty", ngramsWithPadding(corpus, n).get(0));
    assertEquals("Humpty Dumpty", ngramsWithPadding(corpus, n).get(1));
    assertEquals("Dumpty sat", ngramsWithPadding(corpus, n).get(2));
    assertEquals("sat " + NgramsFinder.END_TOKEN, ngramsWithPadding(corpus, n).get(3));
  }

  @Test
  @DisplayName("Should return exact number of expected bigrams for a given corpus")
  void shouldReturnExactNumberOfExpectedBigramsForCorpus() {
    final String corpus = "Humpty Dumpty sat";
    int n = 2;
    assertEquals(4, ngramsWithPadding(corpus, n).size());
  }

  @Test
  @DisplayName("Should return all trigrams for a given corpus")
  void shouldReturnAllTrigramsForCorpus() {
    final String corpus = "Humpty Dumpty sat on a wall";
    int n = 3;
    assertEquals(NgramsFinder.START_TOKEN + " Humpty Dumpty", ngramsWithPadding(corpus, n).get(0));
    assertEquals("Humpty Dumpty sat", ngramsWithPadding(corpus, n).get(1));
    assertEquals("Dumpty sat on", ngramsWithPadding(corpus, n).get(2));
    assertEquals("sat on a", ngramsWithPadding(corpus, n).get(3));
    assertEquals("on a wall", ngramsWithPadding(corpus, n).get(4));
    assertEquals("a wall " + NgramsFinder.END_TOKEN, ngramsWithPadding(corpus, n).get(5));
  }

  @Test
  @DisplayName("Should return exact number of expected trigrams for a given corpus")
  void shouldReturnExactNumberOfExpectedTrigramsForCorpus() {
    final String corpus = "Humpty Dumpty sat on a wall";
    int n = 3;
    assertEquals(6, ngramsWithPadding(corpus, n).size());
  }
}
