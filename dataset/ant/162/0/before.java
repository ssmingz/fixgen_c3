class PlaceHold {
  protected final String getClearToolCommand() {
    String toReturn = m_ClearToolDir;
    if ((!toReturn.equals("")) && (!toReturn.endsWith("/"))) {
      toReturn += "/";
    }
    toReturn += CLEARTOOL_EXE;
    return toReturn;
  }
}
