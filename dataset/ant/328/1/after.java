class PlaceHold {
  public void spawn() throws IOException {
    if ((workingDirectory != null) && (!workingDirectory.exists())) {
      throw new BuildException(workingDirectory + " doesn't exists.");
    }
    final Process process =
        launch(project, getCommandline(), getEnvironment(), workingDirectory, useVMLauncher);
    if (Os.isFamily("windows")) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        project.log("interruption in the sleep after having spawned a process", MSG_VERBOSE);
      }
    }
    project.log("spawned process " + process.toString(), MSG_VERBOSE);
  }
}
