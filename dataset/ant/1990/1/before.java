class PlaceHold {
  public void testContainsRegex2() throws IOException {
    if (!hasRegex("testContainsRegex2")) {
      return;
    }
    String contents = getFileString("containsregex2", "result/containsregex2");
    assertStringContains(contents, "void register_bits();");
  }
}
