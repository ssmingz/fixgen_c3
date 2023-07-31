class PlaceHold {
  public void test4() {
    executeTarget("test4");
    File jarFile = new File(getProjectDir(), tempJar);
    assert jarFile.exists();
    jarModifiedDate = jarFile.lastModified();
  }
}
