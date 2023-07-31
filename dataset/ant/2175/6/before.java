class PlaceHold {
  protected void checkOptions() throws TaskException {
    if (tofile == null) {
      throw new TaskException("'tofile' attribute must be set.");
    }
    if (snapshot == null) {
      throw new TaskException("'snapshot' attribute must be set.");
    }
    if (home == null) {
      throw new TaskException("'home' attribute must be set to JProbe home directory");
    }
    home = new File(home, "coverage");
    File jar = new File(home, "coverage.jar");
    if (!jar.exists()) {
      throw new TaskException("Cannot find Coverage directory: " + home);
    }
    if ((reference != null) && (!"xml".equals(format))) {
      log("Ignored reference. It cannot be used in non XML report.");
      reference = null;
    }
  }
}
