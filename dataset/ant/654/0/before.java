class PlaceHold {
  protected void execute0(ExecuteStreamHandler handler) throws BuildException {
    final Execute process = new Execute(handler);
    log(cmdl.toString(), MSG_VERBOSE);
    process.setCommandline(cmdl.getCommandline());
    try {
      if (process.execute() != 0) {
        throw new BuildException("Metamata task failed.");
      }
    } catch (IOException e) {
      throw new BuildException("Failed to launch Metamata task: " + e);
    }
  }
}
