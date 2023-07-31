class PlaceHold {
  private boolean doFileWasRemoved(Vector entries, String line) {
    if (ignoreRemoved) {
      return false;
    }
    int index = line.indexOf(FILE_WAS_REMOVED);
    if (index == (-1)) {
      return false;
    }
    String filename = line.substring(0, index);
    String rev = null;
    int indexrev = line.indexOf(REVISION, index);
    if (indexrev != (-1)) {
      rev = line.substring(indexrev + REVISION.length());
    }
    CvsTagEntry entry = new CvsTagEntry(filename, null, rev);
    entries.addElement(entry);
    log(entry.toString(), MSG_VERBOSE);
    return true;
  }
}
