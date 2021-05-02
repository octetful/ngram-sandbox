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
Current version is a general, non-optimized implementation using chain rule of probability only.
To further optimize, we would use a combination of the Markov Assumption, and a specific n-gram model, for example, bigram, trigram etc.

---

Notice how each event in the probability equation above, corresponds to an n-gram (unigram, bigram, trigram etc.).
Using this information, we could re-write the term functions as:<br/>
`P(Humpty) = count(filter(ngram(corpus, n=1), token=Humpty)) / total_count` <br />
which reads as:
> count of all 1-grams of the corpus filtered by tokens matching Humpty divided by total count.

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

# Bibliography

- [Stanford paper on N-gram language models](https://web.stanford.edu/~jurafsky/slp3/3.pdf)
- [Article on Understanding N-grams and probabilities in NLP](https://towardsdatascience.com/understanding-word-n-grams-and-n-gram-probability-in-natural-language-processing-9d9eef0fa058)
