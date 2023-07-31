class PlaceHold {
  public void test4() {
    executeTarget("test4");
    File jarFile = new File(getOutputDir(), tempJar);
    assertTrue(jarFile.exists());
  }
}
