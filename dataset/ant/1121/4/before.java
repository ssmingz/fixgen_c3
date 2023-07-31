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
      CompilerAdapter adapter = CompilerAdapterFactory.getCompiler(compilerImpl, this);
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
