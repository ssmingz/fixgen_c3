class TokenizedPattern {
  public TokenizedPattern(String pattern) {
    this.pattern = pattern;
    this.tokenizedPattern = SelectorUtils.tokenizePathAsArray(pattern);
  }
}
