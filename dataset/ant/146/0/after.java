class TokenizedPath {
  public TokenizedPath(String path) {
    this(path, SelectorUtils.tokenizePathAsArray(path));
  }
}
