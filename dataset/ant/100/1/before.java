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
      commandline.createArgument().setLine(command);
      String[] cmdline = commandline.getCommandline();
      String cmdl = "";
      for (int i = 0; i < cmdline.length; i++) {
        cmdl += cmdline[i] + " ";
      }
      log("Execing " + cmdl, MSG_VERBOSE);
      if (handler == null) {
        handler = new SimpleP4OutputHandler(this);
      }
      Execute exe = new Execute(handler, null);
      exe.setAntRun(project);
      exe.setCommandline(commandline.getCommandline());
      try {
        exe.execute();
      } catch (IOException e) {
        throw new BuildException(e);
      } finally {
        try {
          handler.stop();
        } catch (Exception e) {
        }
      }
    } catch (Exception e) {
      throw new BuildException("Problem exec'ing P4 command: " + e.getMessage());
    }
  }
}
