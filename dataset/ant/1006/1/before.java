class PlaceHold {
  protected RE getCompiledPattern(int options) throws BuildException {
    int cOptions = getCompilerOptions(options);
    try {
      RE reg = new RE(pattern);
      reg.setMatchFlags(cOptions);
      return reg;
    } catch (RESyntaxException e) {
      throw new BuildException(e);
    }
  }
}
