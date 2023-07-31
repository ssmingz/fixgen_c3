class PlaceHold {
  public boolean beforeConnect(Libraries owner, ListIterator libraries) {
    owner.markAllLibrariesForFetch(true);
    owner._setUseTimestamp(true);
    return true;
  }
}
