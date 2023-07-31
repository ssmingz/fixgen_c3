class PlaceHold {
  public void execute() throws TaskException {
    if (null == m_srcDir) {
      m_srcDir = getBaseDirectory();
    }
    final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
    final Execute exe = new Execute(execManager);
    exe.setReturnCode(0);
    final Commandline cmd = exe.getCommandline();
    cmd.setExecutable(EXE_NAME);
    addArgument(cmd, "/nologo");
    addArgument(cmd, getAdditionalModulesParameter());
    addArgument(cmd, getDefinitionsParameter());
    addArgument(cmd, getDebugParameter());
    addArgument(cmd, getDocFileParameter());
    addArgument(cmd, getIncrementalParameter());
    addArgument(cmd, getMainClassParameter());
    addArgument(cmd, getOptimizeParameter());
    addArgument(cmd, getReferencesParameter());
    addArgument(cmd, getTargetTypeParameter());
    addArgument(cmd, getUnsafeParameter());
    addArgument(cmd, getWarnLevelParameter());
    addArgument(cmd, getWin32IconParameter());
    addArgument(cmd, getOutputFileParameter());
    addArgument(cmd, getIncludeDefaultReferencesParameter());
    addArgument(cmd, getDefaultReferenceParameter());
    addArgument(cmd, getWin32ResParameter());
    addArgument(cmd, getUtf8OutpuParameter());
    addArgument(cmd, getFullPathsParameter());
    addArgument(cmd, getExtraOptionsParameter());
    final DirectoryScanner scanner = super.getDirectoryScanner(m_srcDir);
    final String[] dependencies = scanner.getIncludedFiles();
    final String message =
        (("compiling " + dependencies.length) + " file") + (dependencies.length == 1 ? "" : "s");
    getLogger().info(message);
    final String baseDir = scanner.getBasedir().toString();
    for (int i = 0; i < dependencies.length; i++) {
      final String targetFile = (baseDir + File.separator) + dependencies[i];
      addArgument(cmd, targetFile);
    }
    exe.execute();
  }
}
