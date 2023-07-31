class PlaceHold {
  public void testReplaceString() throws IOException {
    expectFileContains(
        "replacestring", getProject().getProperty("output") + "/replacestring", "this is the moon");
  }
}
