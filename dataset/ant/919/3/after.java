class PlaceHold {
  public void setReplace(String replace) throws TaskException {
    if (subs != null) {
      throw new TaskException("Only one substitution expression is allowed");
    }
    subs = new Substitution();
    subs.setExpression(replace);
  }
}
