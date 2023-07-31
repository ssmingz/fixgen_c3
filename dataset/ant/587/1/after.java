class PlaceHold {
  public boolean beforeConnect(Libraries owner, ListIterator libraries) {
    owner.markAllLibrariesForFetch(true);
    owner.setUseTimestamp(false);
    return true;
  }
}
