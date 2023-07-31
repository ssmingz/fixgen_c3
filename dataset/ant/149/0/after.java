class PlaceHold {
  private void run(CommandlineJava command) throws BuildException {
    ExecuteJava exe = new ExecuteJava();
    exe.setJavaCommand(command.getJavaCommand());
    exe.setClasspath(command.getClasspath());
    exe.setSystemProperties(command.getSystemProperties());
    if (out != null) {
      try {
        outStream = new PrintStream(new FileOutputStream(out.getAbsolutePath(), append));
        exe.execute(project);
      } catch (IOException io) {
        throw new BuildException(io, location);
      } finally {
        if (outStream != null) {
          outStream.close();
        }
      }
    } else {
      exe.execute(project);
    }
  }
}
