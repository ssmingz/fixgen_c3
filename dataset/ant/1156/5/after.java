class PlaceHold {
  public void execute() throws TaskException {
    if (baseDir == null) {
      throw new TaskException("base attribute must be set!");
    }
    if (!baseDir.exists()) {
      throw new TaskException("base does not exist!");
    }
    if (verify) {
      getLogger().info("Verify has been turned on.");
    }
    String compiler = getProperty("build.rmic").toString();
    RmicAdapter adapter = RmicAdapterFactory.getRmic(compiler, this);
    adapter.setRmic(this);
    Path classpath = adapter.getClasspath();
    final URL[] urls = PathUtil.toURLs(classpath);
    loader = new URLClassLoader(urls);
    if (classname == null) {
      DirectoryScanner ds = this.getDirectoryScanner(baseDir);
      String[] files = ds.getIncludedFiles();
      scanDir(baseDir, files, adapter.getMapper());
    } else {
      scanDir(
          baseDir,
          new String[] {classname.replace('.', File.separatorChar) + ".class"},
          adapter.getMapper());
    }
    int fileCount = compileList.size();
    if (fileCount > 0) {
      getLogger()
          .info(
              (((("RMI Compiling " + fileCount) + " class") + (fileCount > 1 ? "es" : "")) + " to ")
                  + baseDir);
      if (!adapter.execute()) {
        throw new TaskException(FAIL_MSG);
      }
    }
    if ((null != sourceBase) && (!baseDir.equals(sourceBase))) {
      if (idl) {
        getLogger().warn("Cannot determine sourcefiles in idl mode, ");
        getLogger().warn("sourcebase attribute will be ignored.");
      } else {
        for (int j = 0; j < fileCount; j++) {
          moveGeneratedFile(baseDir, sourceBase, ((String) (compileList.get(j))), adapter);
        }
      }
    }
    compileList.clear();
  }
}
