class PlaceHold {
  public void runCommand() throws BuildException {
    int err = -1;
    try {
      if (traceCommandLine) {
        owner.log(commandLine.describeCommand());
      } else {
        logVerbose(commandLine.describeCommand());
      }
      executable.setCommandline(commandLine.getCommandline());
      err = executable.execute();
      if (err != 0) {
        if (failOnError) {
          throw new BuildException((title + " returned: ") + err, owner.getLocation());
        } else {
          owner.log((title + "  Result: ") + err, MSG_ERR);
        }
      }
    } catch (IOException e) {
      throw new BuildException((title + " failed: ") + e, e, owner.getLocation());
    }
  }
}
