class PlaceHold {
  public void getLocalpathCommand(Commandline cmd) {
    if (m_LocalPath == null) {
      return;
    } else {
      File dir = getProject().resolveFile(m_LocalPath);
      if (!dir.exists()) {
        boolean done = dir.mkdirs();
        if (!done) {
          String msg =
              (("Directory " + m_LocalPath) + " creation was not ")
                  + "succesful for an unknown reason";
          throw new BuildException(msg, location);
        }
        getProject().log("Created dir: " + dir.getAbsolutePath());
      }
      cmd.createArgument().setValue(FLAG_OVERRIDE_WORKING_DIR + m_LocalPath);
    }
  }
}
