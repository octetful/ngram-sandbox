package com.octetful.sandbox.ngram;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MemoizedMarkovProbabilityModelTest {
  @Test
  @DisplayName("when trained, should memorize all n-grams")
  void shouldMemorizeNgramsWhenTrained() {
    final int n = 2;
    final MemoizedMarkovProbabilityModel model =
        new MemoizedMarkovProbabilityModel(n);
    final String corpus =
        "Humpty Dumpty sat on a wall " +
            "Humpty Dumpty had a great fall";
    model.train(corpus);
    Map<String, Integer> histogram = model.getHistogram();
    assertEquals(12, histogram.size());
  }

  @Test
  @DisplayName("given toy corpus, should calculate probability")
  void shouldCalculateProbability() {
    final int n = 2;
    final MemoizedMarkovProbabilityModel model =
        new MemoizedMarkovProbabilityModel(n);
    model.train("I am Sam");
    model.train("Sam I am");
    model.train("I do not like green eggs and ham");
    assertEquals((1d/3d), model.probability("I do"));
    assertEquals((2d/3d), model.probability("I am"));
  }

  @Test
  @DisplayName("given a sequence longer than the n-grams model, should be able to calculate using chain rule")
  void shouldApplyChainRuleForLongerSequences() {
    final int n = 2;
    final MemoizedMarkovProbabilityModel model =
        new MemoizedMarkovProbabilityModel(n);
    model.train("I am Sam");
    model.train("Sam I am");
    model.train("I do not like green eggs and ham");
    assertEquals((1d/3d), model.computeProbability("I do not"));
  }

  @Test
  @DisplayName("given a corpus and a sequence, should be able to predict next word")
  void shouldPredictNextWord() {
    final int n = 2;
    final MemoizedMarkovProbabilityModel model =
        new MemoizedMarkovProbabilityModel(n);
    model.train("I wake up");
    model.train("I do breakfast");
    model.train("You do Yoga");
    model.train("How can I do Yoga");
    model.train("Teach me how to do Yoga");
    assertTrue(model.predictNextWord("can I do").isPresent());
    assertEquals("Yoga", model.predictNextWord("can I do").get());
  }

}
