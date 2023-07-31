class PlaceHold {
  public void testCustomTokenFilter() throws IOException {
    expectFileContains("customtokenfilter", "result/custom", "Hello World");
  }
}
