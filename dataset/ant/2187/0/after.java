class PlaceHold {
  public void testSpawn() {
    File logFile = FILE_UTILS.createTempFile("spawn", "log", project.getBaseDir(), false, false);
    assertTrue("log file not existing", !logFile.exists());
    project.setProperty("logFile", logFile.getAbsolutePath());
    project.setProperty("timeToWait", Long.toString(TIME_TO_WAIT));
    project.executeTarget("testSpawn");
    try {
      Thread.sleep((TIME_TO_WAIT * 1000) + SECURITY_MARGIN);
    } catch (Exception ex) {
      System.out.println("my sleep was interrupted");
    }
    if (!logFile.exists()) {
      System.out.println(
          "suggestion: increase the constant"
              + " SECURITY_MARGIN to give more time for java to start.");
    }
    assertTrue("log file exists", logFile.exists());
  }
}
