class PlaceHold {
  private void getLocalpathCommand(final Commandline cmd) throws TaskException {
    if (m_localPath == null) {
      return;
    } else {
      final File dir = getContext().resolveFile(m_localPath);
      if (!dir.exists()) {
        final boolean done = dir.mkdirs();
        if (!done) {
          final String message =
              (("Directory " + m_localPath) + " creation was not ")
                  + "succesful for an unknown reason";
          throw new TaskException(message);
        }
        final String message = "Created dir: " + dir.getAbsolutePath();
        getContext().info(message);
      }
      cmd.addArgument(FLAG_OVERRIDE_WORKING_DIR + m_localPath);
    }
  }
}
