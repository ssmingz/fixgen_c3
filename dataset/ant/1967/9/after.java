class PlaceHold {
  protected void compile(String[] args) {
    String[] commandArray = null;
    File tmpFile = null;
    try {
      String myos = System.getProperty("os.name");
      if ((myos.toLowerCase(Locale.ENGLISH).indexOf("windows") >= 0)
          && (args.length > MAX_FILES_ON_COMMAND_LINE)) {
        BufferedWriter out = null;
        try {
          tmpFile = FileUtils.getFileUtils().createTempFile("jikes", "tmp", null, false, true);
          out = new BufferedWriter(new FileWriter(tmpFile));
          for (int i = 0; i < args.length; i++) {
            out.write(args[i]);
            out.newLine();
          }
          out.flush();
          commandArray = new String[] {command, "@" + tmpFile.getAbsolutePath()};
        } catch (IOException e) {
          throw new BuildException("Error creating temporary file", e);
        } finally {
          FileUtils.close(out);
        }
      } else {
        commandArray = new String[args.length + 1];
        commandArray[0] = command;
        System.arraycopy(args, 0, commandArray, 1, args.length);
      }
      try {
        Execute exe = new Execute(jop);
        exe.setAntRun(project);
        exe.setWorkingDirectory(project.getBaseDir());
        exe.setCommandline(commandArray);
        exe.execute();
      } catch (IOException e) {
        throw new BuildException("Error running Jikes compiler", e);
      }
    } finally {
      if (tmpFile != null) {
        if (!tmpFile.delete()) {
          tmpFile.deleteOnExit();
        }
      }
    }
  }
}
