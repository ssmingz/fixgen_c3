class PlaceHold {
  private void setExecutableCommandLine() {
    String[] commands = commandLine.getCommandline();
    if ((automaticResponseFileThreshold > 0)
        && (commands.length > automaticResponseFileThreshold)) {
      useResponseFile = true;
    }
    if ((!useResponseFile) || (commands.length <= 1)) {
      executable.setCommandline(commands);
    } else {
      FileOutputStream fos = null;
      temporaryCommandFile = FILE_UTILS.createTempFile("cmd", ".txt", null);
      owner.log("Using response file " + temporaryCommandFile, MSG_VERBOSE);
      try {
        fos = new FileOutputStream(temporaryCommandFile);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(fos));
        for (int i = 1; i < commands.length; ++i) {
          out.println(commands[i]);
        }
        out.flush();
        out.close();
      } catch (IOException ex) {
        throw new BuildException("saving command stream to " + temporaryCommandFile, ex);
      }
      String[] newCommandLine = new String[2];
      newCommandLine[0] = commands[0];
      newCommandLine[1] = "@" + temporaryCommandFile.getAbsolutePath();
      logVerbose(Commandline.describeCommand(newCommandLine));
      executable.setCommandline(newCommandLine);
    }
  }
}
