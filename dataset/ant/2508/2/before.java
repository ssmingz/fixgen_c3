class PlaceHold {
  public void testHeadTail() throws IOException {
    executeTarget("testHeadTail");
    File expected =
        FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/head-tail.headtail.test");
    File result = new File(getProject().getProperty("output") + "/head-tail.headtail.test");
    assertTrue(
        "testHeadTail: Result not like expected", FILE_UTILS.contentEquals(expected, result));
  }
}
