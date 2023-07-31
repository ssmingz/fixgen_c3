class PlaceHold {
  @Override
  public int[] getAcceptableTokens() {
    final Set<String> tokenNames = getTokenNames();
    final int[] result = new int[tokenNames.size()];
    int i = 0;
    for (final String name : tokenNames) {
      result[i] = TokenTypes.getTokenId(name);
      i++;
    }
    return result;
  }
}
