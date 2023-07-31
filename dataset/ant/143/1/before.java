class PlaceHold {
  public final Vector topoSort(String[] root, Hashtable targetTable, boolean returnAll)
      throws BuildException {
    Vector ret = new Vector();
    Hashtable state = new Hashtable();
    Stack visiting = new Stack();
    for (int i = 0; i < root.length; i++) {
      String st = ((String) (state.get(root[i])));
      if (st == null) {
        tsort(root[i], targetTable, state, visiting, ret);
      } else if (st == VISITING) {
        throw new RuntimeException("Unexpected node in visiting state: " + root[i]);
      }
    }
    StringBuffer buf = new StringBuffer("Build sequence for target(s)");
    for (int j = 0; j < root.length; j++) {
      buf.append(j == 0 ? " `" : ", `").append(root[j]).append('\'');
    }
    buf.append(" is " + ret);
    log(buf.toString(), MSG_VERBOSE);
    Vector complete = (returnAll) ? ret : new Vector(ret);
    for (Enumeration en = targetTable.keys(); en.hasMoreElements(); ) {
      String curTarget = ((String) (en.nextElement()));
      String st = ((String) (state.get(curTarget)));
      if (st == null) {
        tsort(curTarget, targetTable, state, visiting, complete);
      } else if (st == VISITING) {
        throw new RuntimeException("Unexpected node in visiting state: " + curTarget);
      }
    }
    log("Complete build sequence is " + complete, MSG_VERBOSE);
    return ret;
  }
}
