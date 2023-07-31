class PlaceHold {
  public void testTail() throws IOException {
    executeTarget("testTail");
    File expected = getProject().resolveFile("expected/head-tail.tail.test");
    File result = getProject().resolveFile("result/head-tail.tail.test");
    assertTrue("testTail: Result not like expected", FILE_UTILS.contentEquals(expected, result));
  }
}
