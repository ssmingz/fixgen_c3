class PlaceHold {
  protected void compile(String[] args) {
    String[] commandArray = null;
    File tmpFile = null;
    try {
      String myos = System.getProperty("os.name");
      if ((myos.toLowerCase().indexOf("windows") >= 0) && (args.length > 250)) {
        PrintWriter out = null;
        try {
          String tempFileName = "jikes" + new Random(System.currentTimeMillis()).nextLong();
          tmpFile = new File(tempFileName);
          out = new PrintWriter(new FileWriter(tmpFile));
          for (int i = 0; i < args.length; i++) {
            out.println(args[i]);
          }
          out.flush();
          commandArray = new String[] {command, "@" + tmpFile.getAbsolutePath()};
        } catch (IOException e) {
          throw new BuildException("Error creating temporary file", e);
        } finally {
          if (out != null) {
            try {
              out.close();
            } catch (Throwable t) {
            }
          }
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
        tmpFile.delete();
      }
    }
  }
}
