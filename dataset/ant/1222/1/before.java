class PlaceHold {
  public Vector getGroups(String input, int options) throws BuildException {
    Pattern p = getCompiledPattern(options);
    Matcher matcher = p.matcher(input);
    if (!matcher.find()) {
      return null;
    }
    Vector v = new Vector();
    int cnt = matcher.groupCount();
    for (int i = 0; i <= cnt; i++) {
      v.addElement(matcher.group(i));
    }
    return v;
  }
}
