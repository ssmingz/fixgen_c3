class PlaceHold {
  public void testScriptFilter2() throws IOException {
    if (!hasScript("testScriptFilter")) {
      return;
    }
    expectFileContains("scriptfilter2", "result/scriptfilter2", "HELLO MOON");
  }
}
