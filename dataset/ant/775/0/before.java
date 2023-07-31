class PlaceHold {
  public void execute() throws BuildException {
    if (baseDir == null) {
      throw new BuildException("base attribute must be set!", location);
    }
    if (!baseDir.exists()) {
      throw new BuildException("base does not exist!", location);
    }
    if (verify) {
      log("Verify has been turned on.", MSG_INFO);
    }
    if (iiop) {
      log("IIOP has been turned on.", MSG_INFO);
      if (iiopopts != null) {
        log("IIOP Options: " + iiopopts, MSG_INFO);
      }
    }
    if (idl) {
      log("IDL has been turned on.", MSG_INFO);
      if (idlopts != null) {
        log("IDL Options: " + idlopts, MSG_INFO);
      }
    }
    Path classpath = getCompileClasspath(baseDir);
    loader = new AntClassLoader(project, classpath);
    if (classname == null) {
      DirectoryScanner ds = this.getDirectoryScanner(baseDir);
      String[] files = ds.getIncludedFiles();
      scanDir(baseDir, files);
    } else {
      scanDir(baseDir, new String[] {classname.replace('.', File.separatorChar) + ".class"});
    }
    OutputStream logstr = new LogOutputStream(this, Project.MSG_WARN);
    Main compiler = new Main(logstr, "rmic");
    Commandline cmd = new Commandline();
    cmd.createArgument().setValue("-d");
    cmd.createArgument().setFile(baseDir);
    cmd.createArgument().setValue("-classpath");
    cmd.createArgument().setPath(classpath);
    if (null != stubVersion) {
      if ("1.1".equals(stubVersion)) {
        cmd.createArgument().setValue("-v1.1");
      } else if ("1.2".equals(stubVersion)) {
        cmd.createArgument().setValue("-v1.2");
      } else {
        cmd.createArgument().setValue("-vcompat");
      }
    }
    if (null != sourceBase) {
      cmd.createArgument().setValue("-keepgenerated");
    }
    if (iiop) {
      cmd.createArgument().setValue("-iiop");
      if (iiopopts != null) {
        cmd.createArgument().setValue(iiopopts);
      }
    }
    if (idl) {
      cmd.createArgument().setValue("-idl");
      if (idlopts != null) {
        cmd.createArgument().setValue(idlopts);
      }
    }
    int fileCount = compileList.size();
    if (fileCount > 0) {
      log(
          (((("RMI Compiling " + fileCount) + " class") + (fileCount > 1 ? "es" : "")) + " to ")
              + baseDir,
          MSG_INFO);
      for (int j = 0; j < fileCount; j++) {
        cmd.createArgument().setValue(((String) (compileList.elementAt(j))));
      }
      log("Compilation args: " + cmd.toString(), MSG_VERBOSE);
      compiler.compile(cmd.getArguments());
    }
    if (null != sourceBase) {
      for (int j = 0; j < fileCount; j++) {
        moveGeneratedFile(baseDir, sourceBase, ((String) (compileList.elementAt(j))));
      }
    }
    compileList.removeAllElements();
  }
}
