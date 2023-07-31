class PlaceHold {
  public void execute() throws BuildException {
    compileList.clear();
    File outputDir = getOutputDir();
    if (outputDir == null) {
      throw new BuildException(ERROR_BASE_NOT_SET, getLocation());
    }
    if (!outputDir.exists()) {
      throw new BuildException(ERROR_NO_BASE_EXISTS + outputDir, getLocation());
    }
    if (!outputDir.isDirectory()) {
      throw new BuildException(ERROR_NOT_A_DIR + outputDir, getLocation());
    }
    if (verify) {
      log("Verify has been turned on.", MSG_VERBOSE);
    }
    RmicAdapter adapter =
        (nestedAdapter != null)
            ? nestedAdapter
            : RmicAdapterFactory.getRmic(getCompiler(), this, createCompilerClasspath());
    adapter.setRmic(this);
    Path classpath = adapter.getClasspath();
    loader = getProject().createClassLoader(classpath);
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
              + outputDir,
          MSG_INFO);
      if (listFiles) {
        for (int i = 0; i < fileCount; i++) {
          log(compileList.get(i).toString());
        }
      }
      if (!adapter.execute()) {
        throw new BuildException(ERROR_RMIC_FAILED, getLocation());
      }
    }
    if (((null != sourceBase) && (!outputDir.equals(sourceBase))) && (fileCount > 0)) {
      if (idl) {
        log("Cannot determine sourcefiles in idl mode, ", MSG_WARN);
        log("sourcebase attribute will be ignored.", MSG_WARN);
      } else {
        for (int j = 0; j < fileCount; j++) {
          moveGeneratedFile(outputDir, sourceBase, ((String) (compileList.elementAt(j))), adapter);
        }
      }
    }
  }
}
