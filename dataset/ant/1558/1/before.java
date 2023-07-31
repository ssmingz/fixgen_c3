class PlaceHold {
  public void testHead() throws IOException {
    executeTarget("testHead");
    File expected = getProject().resolveFile("expected/head-tail.head.test");
    File result = getProject().resolveFile("result/head-tail.head.test");
    FileUtils fu = FileUtils.newFileUtils();
    assertTrue("testHead: Result not like expected", fu.contentEquals(expected, result));
  }
}
