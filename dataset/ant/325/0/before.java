class PlaceHold {
  protected void execP4Command(String command, P4Handler handler) throws BuildException {
    try {
      Commandline commandline = new Commandline();
      commandline.setExecutable("p4");
      if ((P4Port != null) && (P4Port.length() != 0)) {
        commandline.createArgument().setValue(P4Port);
      }
      if ((P4User != null) && (P4User.length() != 0)) {
        commandline.createArgument().setValue(P4User);
      }
      if ((P4Client != null) && (P4Client.length() != 0)) {
        commandline.createArgument().setValue(P4Client);
      }
      if ((P4Opts != null) && (P4Opts.length() != 0)) {
        commandline.createArgument().setValue(P4Opts);
      }
      commandline.createArgument().setLine(command);
      log(commandline.describeCommand(), MSG_VERBOSE);
      if (handler == null) {
        handler = new SimpleP4OutputHandler(this);
      }
      Execute exe = new Execute(handler, null);
      exe.setAntRun(getProject());
      exe.setCommandline(commandline.getCommandline());
      try {
        exe.execute();
      } catch (IOException e) {
        throw new BuildException(e);
      } finally {
        try {
          handler.stop();
        } catch (Exception e) {
          log(e.toString(), MSG_ERR);
        }
      }
    } catch (Exception e) {
      String failMsg = "Problem exec'ing P4 command: " + e.getMessage();
      if (failOnError) {
        throw new BuildException(failMsg);
      } else {
        log(failMsg, MSG_ERR);
      }
    }
  }
}
