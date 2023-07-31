class PlaceHold {
  public Vector getGroups(String input, int options) throws BuildException {
    if (!matches(input, options)) {
      return null;
    }
    Vector v = new Vector();
    MatchResult mr = matcher.getMatch();
    int cnt = mr.groups();
    for (int i = 0; i < cnt; i++) {
      v.addElement(mr.group(i));
    }
    return v;
  }
}
