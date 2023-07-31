class PlaceHold {
  public void getLocalpathCommand(final Commandline cmd) throws TaskException {
    if (m_localPath == null) {
      return;
    } else {
      final File dir = getContext().resolveFile(m_localPath);
      if (!dir.exists()) {
        if (!dir.mkdirs()) {
          final String message =
              (("Directory " + m_localPath) + " creation was not ")
                  + "succesful for an unknown reason";
          throw new TaskException(message);
        } else {
          final String message = "Created dir: " + dir.getAbsolutePath();
          getContext().info(message);
        }
      }
      cmd.addArgument(FLAG_OVERRIDE_WORKING_DIR + m_localPath);
    }
  }
}
