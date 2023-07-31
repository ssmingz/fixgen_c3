class PlaceHold {
  public void execute() throws BuildException {
    if (baseDir == null) {
      throw new BuildException("base attribute must be set!", getLocation());
    }
    if (!baseDir.exists()) {
      throw new BuildException("base does not exist!", getLocation());
    }
    if (verify) {
      log("Verify has been turned on.", MSG_VERBOSE);
    }
    RmicAdapter adapter = RmicAdapterFactory.getRmic(getCompiler(), this);
    adapter.setRmic(this);
    Path classpath = adapter.getClasspath();
    loader = new AntClassLoader(getProject(), classpath);
    try {
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
        log(
            (((("RMI Compiling " + fileCount) + " class") + (fileCount > 1 ? "es" : "")) + " to ")
                + baseDir,
            MSG_INFO);
        if (!adapter.execute()) {
          throw new BuildException(FAIL_MSG, getLocation());
        }
      }
      if (((null != sourceBase) && (!baseDir.equals(sourceBase))) && (fileCount > 0)) {
        if (idl) {
          log("Cannot determine sourcefiles in idl mode, ", MSG_WARN);
          log("sourcebase attribute will be ignored.", MSG_WARN);
        } else {
          for (int j = 0; j < fileCount; j++) {
            moveGeneratedFile(baseDir, sourceBase, ((String) (compileList.elementAt(j))), adapter);
          }
        }
      }
    } finally {
      compileList.removeAllElements();
    }
  }
}
