class PlaceHold {
  public void execute() throws BuildException {
    if (baseDir == null) {
      throw new BuildException(ERROR_BASE_NOT_SET, getLocation());
    }
    if (!baseDir.exists()) {
      throw new BuildException(ERROR_NO_BASE_EXISTS + baseDir, getLocation());
    }
    if (!baseDir.isDirectory()) {
      throw new BuildException(ERROR_NOT_A_DIR + baseDir, getLocation());
    }
    if (verify) {
      log("Verify has been turned on.", MSG_VERBOSE);
    }
    RmicAdapter adapter =
        RmicAdapterFactory.getRmic(getCompiler(), this, createCompilerClasspath());
    adapter.setRmic(this);
    Path classpath = adapter.getClasspath();
    loader = getProject().createClassLoader(classpath);
    try {
      if (classname == null) {
        DirectoryScanner ds = this.getDirectoryScanner(baseDir);
        String[] files = ds.getIncludedFiles();
        scanDir(baseDir, files, adapter.getMapper());
      } else {
        String path = classname.replace('.', File.separatorChar) + ".class";
        File f = new File(baseDir, path);
        if (f.isFile()) {
          scanDir(baseDir, new String[] {path}, adapter.getMapper());
        } else {
          compileList.add(classname);
        }
      }
      int fileCount = compileList.size();
      if (fileCount > 0) {
        log(
            (((("RMI Compiling " + fileCount) + " class") + (fileCount > 1 ? "es" : "")) + " to ")
                + baseDir,
            MSG_INFO);
        if (!adapter.execute()) {
          throw new BuildException(ERROR_RMIC_FAILED, getLocation());
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
