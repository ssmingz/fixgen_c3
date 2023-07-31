class PlaceHold {
  public int execute() throws IOException {
    if ((workingDirectory != null) && (!workingDirectory.exists())) {
      throw new BuildException(workingDirectory + " doesn't exists.");
    }
    final Process process =
        launch(project, getCommandline(), getEnvironment(), workingDirectory, useVMLauncher);
    try {
      streamHandler.setProcessInputStream(process.getOutputStream());
      streamHandler.setProcessOutputStream(process.getInputStream());
      streamHandler.setProcessErrorStream(process.getErrorStream());
    } catch (IOException e) {
      process.destroy();
      throw e;
    }
    streamHandler.start();
    try {
      processDestroyer.add(process);
      if (watchdog != null) {
        watchdog.start(process);
      }
      waitFor(process);
      if (watchdog != null) {
        watchdog.stop();
      }
      streamHandler.stop();
      if (watchdog != null) {
        watchdog.checkException();
      }
      return getExitValue();
    } finally {
      processDestroyer.remove(process);
    }
  }
}
