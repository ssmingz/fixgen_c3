class PlaceHold {
  protected String getLocalPath() {
    if (localPath == null) {
      return getProject().getBaseDir().getAbsolutePath();
    } else {
      File dir = getProject().resolveFile(localPath);
      if (!dir.exists()) {
        boolean done = dir.mkdirs();
        if (!done) {
          String msg =
              (("Directory " + localPath) + " creation was not ")
                  + "successful for an unknown reason";
          throw new BuildException(msg, getLocation());
        }
        getProject().log("Created dir: " + dir.getAbsolutePath());
      }
      return dir.getAbsolutePath();
    }
  }
}
