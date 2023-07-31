class PlaceHold {
  protected void execute0() throws TaskException {
    final Execute exe = new Execute();
    setupStreamHandler(exe);
    log(cmdl.toString(), MSG_VERBOSE);
    exe.setCommandline(cmdl.getCommandline());
    try {
      if (exe.execute() != 0) {
        throw new TaskException("Metamata task failed.");
      }
    } catch (IOException e) {
      throw new TaskException("Failed to launch Metamata task: " + e);
    }
  }
}
