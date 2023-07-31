class PlaceHold {
  public void execute() throws BuildException {
    try {
      long start = System.currentTimeMillis();
      if (srcPath == null) {
        throw new BuildException("srcdir attribute must be set", getLocation());
      }
      srcPathList = srcPath.list();
      if (srcPathList.length == 0) {
        throw new BuildException("srcdir attribute must be non-empty", getLocation());
      }
      if (destPath == null) {
        destPath = srcPath;
      }
      if (((cache != null) && cache.exists()) && (!cache.isDirectory())) {
        throw new BuildException("The cache, if specified, must " + "point to a directory");
      }
      if ((cache != null) && (!cache.exists())) {
        cache.mkdirs();
      }
      determineDependencies();
      if (dump) {
        dumpDependencies();
      }
      determineOutOfDateClasses();
      int count = deleteAllAffectedFiles();
      long duration = (System.currentTimeMillis() - start) / ONE_SECOND;
      final int summaryLogLevel;
      if (count > 0) {
        summaryLogLevel = Project.MSG_INFO;
      } else {
        summaryLogLevel = Project.MSG_DEBUG;
      }
      log(
          ((("Deleted " + count) + " out of date files in ") + duration) + " seconds",
          summaryLogLevel);
    } catch (Exception e) {
      throw new BuildException(e);
    }
  }
}
