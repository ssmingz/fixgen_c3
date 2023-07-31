class PlaceHold {
  public void XXXtest5() {
    executeTarget("test5");
    File jarFile = new File(getProjectDir(), tempJar);
    assertEquals(jarModifiedDate, jarFile.lastModified());
  }
}
