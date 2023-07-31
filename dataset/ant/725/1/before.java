class PlaceHold {
  protected void doTest(String target, String expectedStart, String expectedEnd) {
    executeTarget(target);
    String resultContent = read(("result/concat." + target.substring(4)) + ".test");
    assertTrue("First 5 lines differs.", resultContent.startsWith(expectedStart));
    assertTrue("Last 5 lines differs.", resultContent.endsWith(expectedEnd));
  }
}
