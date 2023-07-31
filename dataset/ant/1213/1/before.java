class PlaceHold {
  private void run(CommandlineJava command) throws BuildException {
    try {
      ExecuteJava exe = new ExecuteJava();
      exe.setJavaCommand(command.getJavaCommand());
      exe.setClasspath(command.getClasspath());
      exe.setSystemProperties(command.getSystemProperties());
      exe.setTimeout(timeout);
      redirector.createStreams();
      exe.execute(getProject());
      redirector.complete();
    } catch (IOException e) {
      throw new BuildException(e);
    }
  }
}
