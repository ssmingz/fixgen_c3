class PlaceHold {
  public void getLocalpathCommand(Commandline cmd) throws TaskException {
    if (m_LocalPath == null) {
      return;
    } else {
      File dir = getContext().resolveFile(m_LocalPath);
      if (!dir.exists()) {
        boolean done = dir.mkdirs();
        if (done == false) {
          String msg =
              (("Directory " + m_LocalPath) + " creation was not ")
                  + "successful for an unknown reason";
          throw new TaskException(msg);
        }
        getContext().info("Created dir: " + dir.getAbsolutePath());
      }
      cmd.addArgument(FLAG_OVERRIDE_WORKING_DIR + m_LocalPath);
    }
  }
}
