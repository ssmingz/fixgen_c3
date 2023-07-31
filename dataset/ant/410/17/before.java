class PlaceHold {
  public boolean execute() throws TaskException {
    getLogger().debug("Using jikes compiler");
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
    String jikesPath = System.getProperty("jikes.class.path");
    if (jikesPath != null) {
      classpath.append(new Path(jikesPath));
    }
    Commandline cmd = new Commandline();
    cmd.setExecutable("jikes");
    if (m_deprecation == true) {
      cmd.addArgument("-deprecation");
    }
    if (m_destDir != null) {
      cmd.addArgument("-d");
      cmd.addArgument(m_destDir);
    }
    cmd.addArgument("-classpath");
    cmd.addArguments(FileUtils.translateCommandline(classpath));
    if (m_encoding != null) {
      cmd.addArgument("-encoding");
      cmd.addArgument(m_encoding);
    }
    if (m_debug) {
      cmd.addArgument("-g");
    }
    if (m_optimize) {
      cmd.addArgument("-O");
    }
    if (m_verbose) {
      cmd.addArgument("-verbose");
    }
    if (m_depend) {
      cmd.addArgument("-depend");
    }
    if (m_attributes.getNowarn()) {
      cmd.addArgument("-nowarn");
    }
    addCurrentCompilerArgs(cmd);
    int firstFileName = cmd.size();
    logAndAddFilesToCompile(cmd);
    return executeExternalCompile(cmd.getCommandline(), firstFileName) == 0;
  }
}
