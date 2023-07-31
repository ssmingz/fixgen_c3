class PlaceHold {
  public void spawn() throws IOException {
    if ((workingDirectory != null) && (!workingDirectory.exists())) {
      throw new BuildException(workingDirectory + " doesn't exist.");
    }
    final Process process =
        launch(project, getCommandline(), getEnvironment(), workingDirectory, useVMLauncher);
    if (Os.isFamily("windows")) {
      try {
        Thread.sleep(ONE_SECOND);
      } catch (InterruptedException e) {
        project.log("interruption in the sleep after having spawned a" + " process", MSG_VERBOSE);
      }
    }
    OutputStream dummyOut =
        new OutputStream() {
          public void write(int b) throws IOException {}
        };
    ExecuteStreamHandler handler = new PumpStreamHandler(dummyOut);
    handler.setProcessErrorStream(process.getErrorStream());
    handler.setProcessOutputStream(process.getInputStream());
    handler.start();
    process.getOutputStream().close();
    project.log("spawned process " + process.toString(), MSG_VERBOSE);
  }
}
