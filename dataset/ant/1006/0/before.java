class PlaceHold {
  protected Pattern getCompiledPattern(int options) throws BuildException {
    int cOptions = getCompilerOptions(options);
    try {
      Pattern p = Pattern.compile(this.pattern, cOptions);
      return p;
    } catch (PatternSyntaxException e) {
      throw new BuildException(e);
    }
  }
}
