class PlaceHold {
  public int executeAsForked(CommandlineJava commandline, ExecuteWatchdog watchdog)
      throws BuildException {
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
        throw new BuildException(
            (("\"" + f.getPath()) + "\" does not ")
                + "represent a valid directory. JDepend would fail.");
      }
      commandline.createArgument().setValue(f.getPath());
    }
    Execute execute =
        new Execute(new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN), watchdog);
    execute.setCommandline(commandline.getCommandline());
    if (getDir() != null) {
      execute.setWorkingDirectory(getDir());
      execute.setAntRun(project);
    }
    if (getOutputFile() != null) {
      log("Output to be stored in " + getOutputFile().getPath());
    }
    log(commandline.describeCommand(), MSG_VERBOSE);
    try {
      return execute.execute();
    } catch (IOException e) {
      throw new BuildException("Process fork failed.", e, location);
    }
  }
}
