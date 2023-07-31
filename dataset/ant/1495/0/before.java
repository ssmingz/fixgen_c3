class PlaceHold {
  private void execute(final String[] args) throws Exception {
    setupDefaultParameters();
    if (!parseCommandLineOptions(args)) {
      return;
    }
    final String logLevel = m_parameters.getParameter("log.level", null);
    enableLogging(new LogKitLogger(createLogger(logLevel)));
    final String home = m_parameters.getParameter("myrmidon.home", null);
    final File homeDir = new File(home).getAbsoluteFile();
    if (!homeDir.isDirectory()) {
      final String message = REZ.getString("home-not-dir.error", homeDir);
      throw new Exception(message);
    }
    final String filename = m_parameters.getParameter("filename", null);
    final File buildFile = new File(filename).getCanonicalFile();
    if (!buildFile.isFile()) {
      final String message = REZ.getString("bad-file.error", buildFile);
      throw new Exception(message);
    }
    final String listenerName = m_parameters.getParameter("listener", null);
    final ProjectListener listener = createListener(listenerName);
    if (getLogger().isInfoEnabled()) {
      final String message = REZ.getString("buildfile.notice", buildFile);
      getLogger().warn(message);
    }
    if (getLogger().isInfoEnabled()) {
      final String message = REZ.getString("homedir.notice", homeDir);
      getLogger().info(message);
    }
    if (m_dryRun) {
      m_parameters.setParameter(ROLE, "org.apache.myrmidon.components.executor.PrintingExecutor");
    }
    final Embeddor embeddor = createEmbeddor();
    setupLogger(embeddor);
    embeddor.parameterize(m_parameters);
    embeddor.initialize();
    embeddor.start();
    final Project project = embeddor.createProject(buildFile.toString(), null, m_builderParameters);
    BufferedReader reader = null;
    final boolean incremental = m_parameters.getParameterAsBoolean("incremental", false);
    while (true) {
      final Workspace workspace = embeddor.createWorkspace(m_defines);
      workspace.addProjectListener(listener);
      doBuild(workspace, project, m_targets);
      if (!incremental) {
        break;
      }
      final String message = REZ.getString("repeat.notice");
      System.out.println(message);
      if (null == reader) {
        reader = new BufferedReader(new InputStreamReader(System.in));
      }
      String line = reader.readLine();
      if (line.equalsIgnoreCase("no")) {
        break;
      }
    }
    embeddor.stop();
    embeddor.dispose();
  }
}
