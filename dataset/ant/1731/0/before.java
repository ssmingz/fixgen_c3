class PlaceHold {
  public synchronized boolean isCaseSensitive() {
    return isReference() ? getRef(getProject()).isCaseSensitive() : caseSensitive;
  }
}
