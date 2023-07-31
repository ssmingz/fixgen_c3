class PlaceHold {
  public void testScriptFilter() throws IOException {
    if (!hasScript("testScriptFilter")) {
      return;
    }
    expectFileContains("scriptfilter", "result/scriptfilter", "HELLO WORLD");
  }
}
