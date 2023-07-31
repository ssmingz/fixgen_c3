class PlaceHold {
  public boolean execute() throws TaskException {
    getLogger().debug("Using jvc compiler");
    Path classpath = new Path();
    if (m_bootclasspath != null) {
      classpath.append(m_bootclasspath);
    }
    addExtdirs(classpath);
    if ((m_bootclasspath == null) || (m_bootclasspath.size() == 0)) {
      m_includeJavaRuntime = true;
    } else {
    }
    classpath.append(getCompileClasspath());
    classpath.append(src);
    Commandline cmd = new Commandline();
    cmd.setExecutable("jvc");
    if (m_destDir != null) {
      cmd.addArgument("/d");
      cmd.addArgument(m_destDir);
    }
    cmd.addArgument("/cp:p");
    cmd.addArguments(FileUtils.translateCommandline(classpath));
    cmd.addArgument("/x-");
    cmd.addArgument("/nomessage");
    cmd.addArgument("/nologo");
    if (m_debug) {
      cmd.addArgument("/g");
    }
    if (m_optimize) {
      cmd.addArgument("/O");
    }
    if (m_verbose) {
      cmd.addArgument("/verbose");
    }
    addCurrentCompilerArgs(cmd);
    int firstFileName = cmd.size();
    logAndAddFilesToCompile(cmd);
    return executeExternalCompile(cmd.getCommandline(), firstFileName) == 0;
  }
}
