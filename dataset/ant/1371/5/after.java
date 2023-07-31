class PlaceHold {
  public void setScope(String verboseScope) throws BuildException {
    verboseScope = verboseScope.toLowerCase(Locale.ENGLISH);
    boolean[] elements = new boolean[Javadoc.SCOPE_ELEMENTS.length];
    boolean gotAll = false;
    boolean gotNotAll = false;
    StringTokenizer tok = new StringTokenizer(verboseScope, ",");
    while (tok.hasMoreTokens()) {
      String next = tok.nextToken().trim();
      if (next.equals("all")) {
        if (gotAll) {
          getProject().log("Repeated tag scope element: all", MSG_VERBOSE);
        }
        gotAll = true;
      } else {
        int i;
        for (i = 0; i < Javadoc.SCOPE_ELEMENTS.length; i++) {
          if (next.equals(SCOPE_ELEMENTS[i])) {
            break;
          }
        }
        if (i == Javadoc.SCOPE_ELEMENTS.length) {
          throw new BuildException("Unrecognised scope element: " + next);
        } else {
          if (elements[i]) {
            getProject().log("Repeated tag scope element: " + next, MSG_VERBOSE);
          }
          elements[i] = true;
          gotNotAll = true;
        }
      }
    }
    if (gotNotAll && gotAll) {
      throw new BuildException(
          "Mixture of \"all\" and other scope " + "elements in tag parameter.");
    }
    if ((!gotNotAll) && (!gotAll)) {
      throw new BuildException("No scope elements specified in tag " + "parameter.");
    }
    if (gotAll) {
      this.scope = "a";
    } else {
      StringBuffer buff = new StringBuffer(elements.length);
      for (int i = 0; i < elements.length; i++) {
        if (elements[i]) {
          buff.append(SCOPE_ELEMENTS[i].charAt(0));
        }
      }
      this.scope = buff.toString();
    }
  }
}
