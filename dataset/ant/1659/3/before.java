class PlaceHold {
  public void testCustomTokenFilter() throws IOException {
    expectFileContains(
        "customtokenfilter", getProject().getProperty("output") + "/custom", "Hello World");
  }
}
