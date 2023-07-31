class PlaceHold {
  protected void runExec(Execute exe) throws BuildException {
    log(cmdl.describeCommand(), MSG_VERBOSE);
    exe.setCommandline(cmdl.getCommandline());
    try {
      runExecute(exe);
    } catch (IOException e) {
      if (failIfExecFails) {
        throw new BuildException("Execute failed: " + e.toString(), e, location);
      } else {
        log("Execute failed: " + e.toString(), MSG_ERR);
      }
    } finally {
      logFlush();
    }
  }
}
