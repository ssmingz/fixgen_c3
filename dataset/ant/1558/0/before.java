class PlaceHold {
  public void testTail() throws IOException {
    executeTarget("testTail");
    File expected = getProject().resolveFile("expected/head-tail.tail.test");
    File result = getProject().resolveFile("result/head-tail.tail.test");
    FileUtils fu = FileUtils.newFileUtils();
    assertTrue("testTail: Result not like expected", fu.contentEquals(expected, result));
  }
}
