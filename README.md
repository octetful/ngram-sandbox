# ngrams-sandbox

A playground to fiddle with ngrams based language model predictive analysis.

# Problem Statement
Using the [Chain rule of probability](https://en.wikipedia.org/wiki/Chain_rule_(probability)), we could derive a formula as given below: <br />
`P(Humpty Dumpty sat) = P(Humpty Dumpty sat | Humpty Dumpty) * P(Humpty Dumpty | Humpty) * P(Humpty)`

To compute each term in the RHS, we use the sample corpus of text.

For the first term(from right):<br/>
`P(Humpty) = count(Humpty)/total_word_count`

Notice that for unigrams, we consider the total count of words to derive the probability.

For the second term:<br />
`P(Humpty Dumpty | Humpty) = count(Humpty Dumpty) / count(Humpty)`

Likewise, for the third term:<br />
`P(Humpty Dumpty sat | Humpty Dumpty) = count(Humpty Dumpty sat) / count(Humpty Dumpty)`

---

__NOTE__:<br/> 
- `ProbabilityComputer` is a general, non-optimized implementation using chain rule of probability only.
To further optimize, we would use a combination of the Markov Assumption, and a specific n-gram model, for example, bigram, trigram etc.

- `MemoizedMarkovProbabilityModel` is a more optimal implementation, that uses memoization technique, the Markov assumption, maximum likelihood estimation and application of special symbol padding.

---

Using the Markov assumption, usually this can be simplified as follows:

- `P(Humpty Dumpty sat | Humpty Dumpty) = C(Humpty Dumpty sat) / C(Humpty Dumpty), n=3`
- `P(<seq> Humpty Dumpty sat on a wall </seq>) = P(<seq> Humpty)*P(Humpt Dumpty)*...*P(wall </seq>), n=2`

where, `C` is the count function and `P` is the probability function.

For more details on the Markov assumption please refer to the [bibliography](#bibliography) section below.

## Sample Corpus
>Humpty Dumpty sat on a wall, <br />
Humpty Dumpty had a great fall. <br />
All the king's horses and all the king's men <br />
Couldn't put Humpty together again.

Given the above corpus, for example, the 3-grams would be as follows:
- Humpty Dumpty sat 
- Dumpty sat on 
- sat on a 
- on a wall 
- a wall Humpty
- Humpty Dumpty had
- Dumpty had a
- had a great
- a great fall
- great fall All
- fall All the
- the King's horses
- King's horses and
- horses and all
- and all the
- all the king's
- the king's men
- king's men couldn't
- men couldn't put
- couldn't put Humpty
- put Humpty together
- Humpty together again

# User Guide
If you wish to play with the classes provided as a library, consider the following code snippet:

```java
var n = 2; // for 2-grams
var model = new MemoizedMarkovProbabilityModel(n); // create model
var corpus = "Humpty Dumpty sat on a wall"; // training corpus
model.train(corpus); // train the model.
double probability = model.probability("Humpty Dumpty");
// more to come soon...
```

# Bibliography
[](#bibliography)

- [Stanford paper on N-gram language models](https://web.stanford.edu/~jurafsky/slp3/3.pdf)
- [Article on Understanding N-grams and probabilities in NLP](https://towardsdatascience.com/understanding-word-n-grams-and-n-gram-probability-in-natural-language-processing-9d9eef0fa058)
