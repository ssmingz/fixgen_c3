class PlaceHold {
  public boolean matches(String input, int options) throws BuildException {
    try {
      Pattern p = getCompiledPattern(options);
      return p.matcher(input).find();
    } catch (Exception e) {
      throw new BuildException(e);
    }
  }
}
