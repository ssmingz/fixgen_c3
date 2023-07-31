class PlaceHold {
  protected void _assertTokenized(String typed, String[] expected, boolean stringify) {
    List<String> actual = ArgumentTokenizer.tokenize(typed, stringify);
    List expectedList = Arrays.asList(expected);
    assertEquals("tokenized argument list should match expected", expectedList, actual);
  }
}
