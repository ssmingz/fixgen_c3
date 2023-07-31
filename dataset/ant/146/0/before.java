class TokenizedPath {
  public TokenizedPath(String path) {
    this.path = path;
    this.tokenizedPath = SelectorUtils.tokenizePathAsArray(path);
  }
}
