class PlaceHold {
  protected void compile() {
    String compilerImpl = getCompiler();
    if (compileList.length > 0) {
      log(
          ((("Compiling " + compileList.length) + " source file")
                  + (compileList.length == 1 ? "" : "s"))
              + (destDir != null ? " to " + destDir : ""));
      if (listFiles) {
        for (int i = 0; i < compileList.length; i++) {
          String filename = compileList[i].getAbsolutePath();
          log(filename);
        }
      }
      CompilerAdapter adapter =
          (nestedAdapter != null)
              ? nestedAdapter
              : CompilerAdapterFactory.getCompiler(compilerImpl, this, createCompilerClasspath());
      adapter.setJavac(this);
      if (adapter.execute()) {
        try {
          generateMissingPackageInfoClasses();
        } catch (IOException x) {
          throw new BuildException(x, getLocation());
        }
      } else {
        this.taskSuccess = false;
        if (errorProperty != null) {
          getProject().setNewProperty(errorProperty, "true");
        }
        if (failOnError) {
          throw new BuildException(FAIL_MSG, getLocation());
        } else {
          log(FAIL_MSG, MSG_ERR);
        }
      }
    }
  }
}
