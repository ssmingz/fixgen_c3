class PlaceHold {
  private void assertResultFilesExist(String target, String extension) {
    executeTarget(target);
    assertResultFileExists("JUnitClassLoader", extension);
    assertResultFileExists("JUnitTestRunner", extension);
    assertResultFileExists("JUnitVersionHelper", extension);
  }
}
