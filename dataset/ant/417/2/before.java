class PlaceHold {
  public void setReplace(String replace) {
    if (subs != null) {
      throw new BuildException("Only one substitution expression is allowed");
    }
    subs = new Substitution();
    subs.setExpression(replace);
  }
}
