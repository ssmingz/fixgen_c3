class PlaceHold {
  public void execute() throws TaskException {
    if (src == null) {
      throw new TaskException("srcdir attribute must be set!");
    }
    String[] list = src.list();
    if (list.length == 0) {
      throw new TaskException("srcdir attribute must be set!");
    }
    if ((destDir != null) && (!destDir.isDirectory())) {
      throw new TaskException(
          ("destination directory \"" + destDir) + "\" does not exist or is not a directory");
    }
    File dest = null;
    if (packageName == null) {
      dest = destDir;
    } else {
      String path =
          (destDir.getPath() + File.separatorChar) + packageName.replace('.', File.separatorChar);
      dest = new File(path);
    }
    resetFileLists();
    int filecount = 0;
    for (int i = 0; i < list.length; i++) {
      File srcDir = ((File) (resolveFile(list[i])));
      if (!srcDir.exists()) {
        throw new TaskException(("srcdir \"" + srcDir.getPath()) + "\" does not exist!");
      }
      DirectoryScanner ds = this.getDirectoryScanner(srcDir);
      String[] files = ds.getIncludedFiles();
      filecount = files.length;
      scanDir(srcDir, dest, files);
    }
    String compiler = getProject().getProperty("jsp.compiler");
    if (compiler == null) {
      compiler = "jasper";
    }
    log(("compiling " + compileList.size()) + " files", MSG_VERBOSE);
    if (compileList.size() > 0) {
      CompilerAdapter adapter = CompilerAdapterFactory.getCompiler(compiler, this);
      getLogger()
          .info(
              ((("Compiling " + compileList.size()) + " source file")
                      + (compileList.size() == 1 ? "" : "s"))
                  + (destDir != null ? " to " + destDir : ""));
      adapter.setJspc(this);
      if (!adapter.execute()) {
        if (failOnError) {
          throw new TaskException(FAIL_MSG);
        } else {
          log(FAIL_MSG, MSG_ERR);
        }
      }
    } else if (filecount == 0) {
      log("there were no files to compile", MSG_INFO);
    } else {
      log("all files are up to date", MSG_VERBOSE);
    }
  }
}
