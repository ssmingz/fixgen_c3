class PlaceHold {
  public void testspawn() {
    project.executeTarget("init");
    if (project.getProperty("test.can.run") == null) {
      return;
    }
    myBuild = new MonitoredBuild(new File(System.getProperty("root"), BUILD_FILE), "spawn");
    logFile = FILE_UTILS.createTempFile("spawn", "log", project.getBaseDir(), false, false);
    assertTrue("log file not existing", !logFile.exists());
    myBuild.setTimeToWait(TIME_TO_WAIT);
    myBuild.setLogFile(logFile.getAbsolutePath());
    myBuild.addBuildListener(new MonitoredBuildListener());
    myBuild.start();
    GregorianCalendar startwait = new GregorianCalendar();
    while (!buildFinished) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        System.out.println("my sleep was interrupted");
      }
      GregorianCalendar now = new GregorianCalendar();
      if ((now.getTime().getTime() - startwait.getTime().getTime()) > MAX_BUILD_TIME) {
        System.out.println(
            ("aborting wait, too long " + (now.getTime().getTime() - startwait.getTime().getTime()))
                + "milliseconds");
        break;
      }
    }
    try {
      Thread.sleep((TIME_TO_WAIT * 1000) + SECURITY_MARGIN);
    } catch (InterruptedException e) {
      System.out.println("my sleep was interrupted");
    }
    long elapsed = myBuild.getTimeElapsed();
    assertTrue(
        "we waited more than the process lasted",
        ((TIME_TO_WAIT * 1000) + SECURITY_MARGIN) > elapsed);
    logFile = new File(logFile.getAbsolutePath());
    assertTrue("log file found after spawn", logFile.exists());
  }
}
