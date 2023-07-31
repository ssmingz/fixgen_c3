class PlaceHold {
  public Vector getGroups(String input, int options) throws BuildException {
    RE reg = getCompiledPattern(options);
    if (!matches(input, reg)) {
      return null;
    }
    Vector v = new Vector();
    int cnt = reg.getParenCount();
    for (int i = 0; i < cnt; i++) {
      v.addElement(reg.getParen(i));
    }
    return v;
  }
}
