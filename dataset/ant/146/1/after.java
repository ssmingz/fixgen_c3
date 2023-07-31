class TokenizedPattern {
  public TokenizedPattern(String pattern) {
    this(pattern, SelectorUtils.tokenizePathAsArray(pattern));
  }
}
