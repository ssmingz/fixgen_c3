class PlaceHold {
  public void testScriptFilter() throws IOException {
    if (!hasScript("testScriptFilter")) {
      return;
    }
    expectFileContains(
        "scriptfilter", getProject().getProperty("output") + "/scriptfilter", "HELLO WORLD");
  }
}
