class PlaceHold {
  public void test4() {
    executeTarget("test4");
    File jarFile = new File(getProjectDir(), tempJar);
    assertTrue(jarFile.exists());
  }
}
