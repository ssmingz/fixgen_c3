class PlaceHold {
  protected Pattern getCompiledPattern(int options) throws BuildException {
    try {
      Pattern p = compiler.compile(pattern, getCompilerOptions(options));
      return p;
    } catch (Exception e) {
      throw new BuildException(e);
    }
  }
}
