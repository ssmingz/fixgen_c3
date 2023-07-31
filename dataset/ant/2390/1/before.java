class PlaceHold {
  private void testRecreate(String firstTarget, String secondTarget) {
    executeTarget(firstTarget);
    long sleeptime = 3000 + FileUtils.newFileUtils().getFileTimestampGranularity();
    try {
      Thread.sleep(sleeptime);
    } catch (InterruptedException e) {
    }
    File jarFile = new File(getProjectDir(), tempJar);
    long jarModifiedDate = jarFile.lastModified();
    executeTarget(secondTarget);
    jarFile = new File(getProjectDir(), tempJar);
    assertTrue(
        "jar has been recreated in " + secondTarget, jarModifiedDate < jarFile.lastModified());
  }
}
