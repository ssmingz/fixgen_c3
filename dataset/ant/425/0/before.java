class PlaceHold {
  public int executeAsForked(CommandlineJava commandline) throws TaskException {
    createClasspath();
    if (getClasspath().toString().length() > 0) {
      createJvmarg(commandline).setValue("-classpath");
      createJvmarg(commandline).setValue(getClasspath().toString());
    }
    if (getOutputFile() != null) {
      commandline.createArgument().setValue("-file");
      commandline.createArgument().setValue(_outputFile.getPath());
    }
    PathTokenizer sourcesPath = new PathTokenizer(getSourcespath().toString());
    while (sourcesPath.hasMoreTokens()) {
      File f = new File(sourcesPath.nextToken());
      if ((!f.exists()) || (!f.isDirectory())) {
        throw new TaskException(
            ("\"" + f.getPath()) + "\" does not represent a valid directory. JDepend would fail.");
      }
      commandline.createArgument().setValue(f.getPath());
    }
    final Execute exe = new Execute();
    exe.setOutput(new LogOutputStream(this, Project.MSG_INFO));
    exe.setError(new LogOutputStream(this, Project.MSG_WARN));
    exe.setCommandline(commandline.getCommandline());
    if (getDir() != null) {
      exe.setWorkingDirectory(getDir());
    }
    if (getOutputFile() != null) {
      getLogger().info("Output to be stored in " + getOutputFile().getPath());
    }
    log("Executing: " + commandline.toString(), MSG_VERBOSE);
    try {
      return exe.execute();
    } catch (IOException e) {
      throw new TaskException("Process fork failed.", e);
    }
  }
}
