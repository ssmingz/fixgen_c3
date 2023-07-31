class PlaceHold {
  private int executeAsForked(final CommandlineJava commandline) throws TaskException {
    createClasspath();
    if (m_compileClasspath.toString().length() > 0) {
      commandline.addVmArgument("-classpath");
      commandline.addVmArgument(m_compileClasspath.toString());
    }
    if (m_outputFile != null) {
      commandline.addArgument("-file");
      commandline.addArgument(m_outputFile.getPath());
    }
    final String[] elements = FileUtils.parsePath(m_sourcesPath.toString());
    for (int i = 0; i < elements.length; i++) {
      File f = new File(elements[i]);
      if ((!f.exists()) || (!f.isDirectory())) {
        throw new TaskException(
            ("\"" + f.getPath()) + "\" does not represent a valid directory. JDepend would fail.");
      }
      commandline.addArgument(f.getPath());
    }
    final Execute exe = new Execute();
    final String[] commandline1 = commandline.getCommandline();
    exe.setCommandline(new Commandline(commandline1));
    if (m_dir != null) {
      exe.setWorkingDirectory(m_dir);
    }
    if (m_outputFile != null) {
      getContext().info("Output to be stored in " + m_outputFile.getPath());
    }
    getContext().debug("Executing: " + commandline.toString());
    return exe.execute(getContext());
  }
}
