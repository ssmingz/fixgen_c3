class PlaceHold {
  public void testHead() throws IOException {
    executeTarget("testHead");
    File expected =
        FILE_UTILS.resolveFile(getProject().getBaseDir(), "expected/head-tail.head.test");
    File result = new File(getProject().getProperty("output") + "/head-tail.head.test");
    assertTrue("testHead: Result not like expected", FILE_UTILS.contentEquals(expected, result));
  }
}
