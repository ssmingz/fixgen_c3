class PlaceHold {
  public void testHead() throws IOException {
    executeTarget("testHead");
    File expected = getProject().resolveFile("expected/head-tail.head.test");
    File result = getProject().resolveFile("result/head-tail.head.test");
    assertTrue("testHead: Result not like expected", FILE_UTILS.contentEquals(expected, result));
  }
}
