class PlaceHold {
  protected void execute0(ExecuteStreamHandler handler) throws TaskException {
    final Execute process = new Execute(handler);
    log(cmdl.toString(), MSG_VERBOSE);
    process.setCommandline(cmdl.getCommandline());
    try {
      if (process.execute() != 0) {
        throw new TaskException("Metamata task failed.");
      }
    } catch (IOException e) {
      throw new TaskException("Failed to launch Metamata task: " + e);
    }
  }
}
