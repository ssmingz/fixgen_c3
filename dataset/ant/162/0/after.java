class PlaceHold {
  protected final String getClearToolCommand() {
    String toReturn = mClearToolDir;
    if ((!toReturn.equals("")) && (!toReturn.endsWith("/"))) {
      toReturn += "/";
    }
    toReturn += CLEARTOOL_EXE;
    return toReturn;
  }
}
