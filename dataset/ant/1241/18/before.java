class PlaceHold {
  public void testReplaceString() throws IOException {
    expectFileContains("replacestring", "result/replacestring", "this is the moon");
  }
}
