package com.octetful.sandbox.ngram;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProbabilityComputerTest {
  @Test
  @DisplayName("Should be able to count number of 1-grams")
  void shouldCountOneGrams() {
    final String corpus = "Humpty Dumpty sat on a wall Humpty Dumpty had a great fall";
    ProbabilityComputer computer = new ProbabilityComputer(corpus);
    assertEquals(2, computer.count("Humpty"));
    assertEquals(1, computer.count("wall"));
  }

  @Test
  @DisplayName("Should be able to count number of 2-grams")
  void shouldCountTwoGrams() {
    final String corpus = "Humpty Dumpty sat on a wall Humpty Dumpty had a great fall";
    ProbabilityComputer computer = new ProbabilityComputer(corpus);
    assertEquals(2, computer.count("Humpty Dumpty"));
  }

  @Test
  @DisplayName("Should be able to compute probability of single word tokens")
  void shouldPredictSingleTokenSequenceProbability() {
    final String corpus = "Humpty Dumpty sat on a wall Humpty Dumpty had a great fall";
    ProbabilityComputer computer = new ProbabilityComputer(corpus);
    assertEquals((1d / 12d), computer.probability("wall"));
    assertEquals((1d / 6d), computer.probability("Humpty"));
  }

  @Test
  @DisplayName("Should be able to compute probability of double word tokens")
  void shouldPredictDoubleWordSequenceProbability() {
    final String corpus = "Humpty Dumpty sat on a wall Humpty Dumpty had a great fall";
    ProbabilityComputer computer = new ProbabilityComputer(corpus);
    assertEquals((1d / 2d) * (1d / 6d), computer.probability("a wall"));
  }
}
