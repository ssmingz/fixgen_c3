class PlaceHold {
  public void testEncoding() {
    executeTarget("encodingTest");
    assertFileExists("foo has been properly named", "unziptestout/foo");
  }
}
