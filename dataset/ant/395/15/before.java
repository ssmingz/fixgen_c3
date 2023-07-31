class PlaceHold {
  protected Execute prepareExec() throws BuildException {
    if (dir == null) {
      dir = project.getBaseDir();
    }
    Execute exe = new Execute(createHandler(), createWatchdog());
    exe.setAntRun(getProject());
    exe.setWorkingDirectory(dir);
    exe.setVMLauncher(vmLauncher);
    String[] environment = env.getVariables();
    if (environment != null) {
      for (int i = 0; i < environment.length; i++) {
        log("Setting environment variable: " + environment[i], MSG_VERBOSE);
      }
    }
    exe.setNewenvironment(newEnvironment);
    exe.setEnvironment(environment);
    return exe;
  }
}
