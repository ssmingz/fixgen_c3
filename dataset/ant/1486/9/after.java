class PlaceHold {
  private void testNoRecreate(String secondTarget) {
    executeTarget("test4");
    File jarFile = new File(getOutputDir(), tempJar);
    long jarModifiedDate = jarFile.lastModified();
    try {
      Thread.sleep(2500);
    } catch (InterruptedException e) {
    }
    executeTarget(secondTarget);
    assertEquals(
        "jar has not been recreated in " + secondTarget, jarModifiedDate, jarFile.lastModified());
  }
}
