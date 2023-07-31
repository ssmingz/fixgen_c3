class PlaceHold {
  public static String[] translateCommandline(String to_process) {
    if ((to_process == null) || (to_process.length() == 0)) {
      return new String[0];
    }
    final int normal = 0;
    final int inQuote = 1;
    final int inDoubleQuote = 2;
    int state = normal;
    StringTokenizer tok = new StringTokenizer(to_process, "\"\' ", true);
    Vector v = new Vector();
    StringBuffer current = new StringBuffer();
    boolean lastTokenHasBeenQuoted = false;
    while (tok.hasMoreTokens()) {
      String nextTok = tok.nextToken();
      switch (state) {
        case inQuote:
          if ("\'".equals(nextTok)) {
            lastTokenHasBeenQuoted = true;
            state = normal;
          } else {
            current.append(nextTok);
          }
          break;
        case inDoubleQuote:
          if ("\"".equals(nextTok)) {
            lastTokenHasBeenQuoted = true;
            state = normal;
          } else {
            current.append(nextTok);
          }
          break;
        default:
          if ("\'".equals(nextTok)) {
            state = inQuote;
          } else if ("\"".equals(nextTok)) {
            state = inDoubleQuote;
          } else if (" ".equals(nextTok)) {
            if (lastTokenHasBeenQuoted || (current.length() != 0)) {
              v.addElement(current.toString());
              current.setLength(0);
            }
          } else {
            current.append(nextTok);
          }
          lastTokenHasBeenQuoted = false;
          break;
      }
    }
    if (lastTokenHasBeenQuoted || (current.length() != 0)) {
      v.addElement(current.toString());
    }
    if ((state == inQuote) || (state == inDoubleQuote)) {
      throw new BuildException("unbalanced quotes in " + to_process);
    }
    String[] args = new String[v.size()];
    v.copyInto(args);
    return args;
  }
}
