class PlaceHold {
  private void runCommand(final String[] cmdline) throws TaskException {
    try {
      getLogger().debug(StringUtil.join(cmdline, " "));
      final Execute2 exe = new Execute2();
      setupLogger(exe);
      exe.setCommandline(cmdline);
      int retval = exe.execute();
      if (retval != 0) {
        throw new TaskException((cmdline[0] + " failed with return code ") + retval);
      }
    } catch (final IOException ioe) {
      throw new TaskException((("Could not launch " + cmdline[0]) + ": ") + ioe);
    }
  }
}
