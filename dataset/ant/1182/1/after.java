class PlaceHold {
  protected Vector tokenize(String args) {
    Vector v = new Vector();
    if (args == null) {
      return v;
    }
    StringTokenizer t = new StringTokenizer(args, " ");
    while (t.hasMoreTokens()) {
      v.addElement(t.nextToken());
    }
    return v;
  }
}
