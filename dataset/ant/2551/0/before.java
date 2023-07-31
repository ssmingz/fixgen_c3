class PlaceHold {
  protected int executeExternalCompile(String[] args, int firstFileName) throws TaskException {
    String[] commandArray = null;
    File tmpFile = null;
    try {
      if (Commandline.toString(args).length() > 4096) {
        PrintWriter out = null;
        try {
          tmpFile = File.createTempFile("jikes", "", new File("."));
          out = new PrintWriter(new FileWriter(tmpFile));
          for (int i = firstFileName; i < args.length; i++) {
            out.println(args[i]);
          }
          out.flush();
          commandArray = new String[firstFileName + 1];
          System.arraycopy(args, 0, commandArray, 0, firstFileName);
          commandArray[firstFileName] = "@" + tmpFile.getAbsolutePath();
        } catch (IOException e) {
          throw new TaskException("Error creating temporary file", e);
        } finally {
          if (out != null) {
            try {
              out.close();
            } catch (Throwable t) {
            }
          }
        }
      } else {
        commandArray = args;
      }
      try {
        final Execute2 exe = new Execute2();
        setupLogger(exe);
        exe.setWorkingDirectory(m_baseDir);
        exe.setCommandline(commandArray);
        return exe.execute();
      } catch (IOException e) {
        throw new TaskException(("Error running " + args[0]) + " compiler", e);
      }
    } finally {
      if (tmpFile != null) {
        tmpFile.delete();
      }
    }
  }
}
