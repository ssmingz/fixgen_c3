class PlaceHold {
  public void execute() throws BuildException {
    if (src == null) {
      throw new BuildException("srcdir attribute must be set!", location);
    }
    String[] list = src.list();
    if (list.length == 0) {
      throw new BuildException("srcdir attribute must be set!", location);
    }
    if ((destDir != null) && (!destDir.isDirectory())) {
      throw new BuildException(
          ("destination directory \"" + destDir) + "\" does not exist or is not a directory",
          location);
    }
    resetFileLists();
    for (int i = 0; i < list.length; i++) {
      File srcDir = ((File) (project.resolveFile(list[i])));
      if (!srcDir.exists()) {
        throw new BuildException(("srcdir \"" + srcDir.getPath()) + "\" does not exist!", location);
      }
      DirectoryScanner ds = this.getDirectoryScanner(srcDir);
      String[] files = ds.getIncludedFiles();
      scanDir(srcDir, destDir != null ? destDir : srcDir, files);
    }
    String compiler = project.getProperty("build.compiler");
    if (!"false".equals(fork)) {
      if (compiler != null) {
        if (isJdkCompiler(compiler)) {
          log("Since fork is true, ignoring build.compiler setting.", MSG_WARN);
          compiler = "extJavac";
        } else {
          log(
              "Since build.compiler setting isn't classic or modern, ignoring fork setting.",
              MSG_WARN);
        }
      } else {
        compiler = "extJavac";
      }
    }
    if (compiler == null) {
      if ((Project.getJavaVersion() != Project.JAVA_1_1)
          && (Project.getJavaVersion() != Project.JAVA_1_2)) {
        compiler = "modern";
      } else {
        compiler = "classic";
      }
    }
    if (compileList.length > 0) {
      CompilerAdapter adapter = CompilerAdapterFactory.getCompiler(compiler, this);
      log(
          ((("Compiling " + compileList.length) + " source file")
                  + (compileList.length == 1 ? "" : "s"))
              + (destDir != null ? " to " + destDir : ""));
      adapter.setJavac(this);
      if (!adapter.execute()) {
        if (failOnError) {
          throw new BuildException(FAIL_MSG, location);
        } else {
          log(FAIL_MSG, MSG_ERR);
        }
      }
    }
  }
}
