class PlaceHold {
  private int run(final Commandline command) throws TaskException {
    final Execute exe = new Execute();
    if (workingdir != null) {
      exe.setWorkingDirectory(workingdir);
    }
    exe.setCommandline(command);
    try {
      return exe.execute(getContext());
    } catch (IOException e) {
      throw new TaskException("Error", e);
    }
  }
}
