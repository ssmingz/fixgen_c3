class PlaceHold {
  public int previousBrace() {
    int relDistance = 0;
    int dist = 0;
    resetLocation();
    TokenList.Iterator copyCursor = _cursor.copy();
    if (!copyCursor.atStart()) {
      copyCursor.prev();
    }
    if (copyCursor.atStart()) {
      copyCursor.dispose();
      return -1;
    }
    dist += _offset;
    relDistance = dist;
    while (!copyCursor.atStart()) {
      if (!copyCursor.current().isGap()) {
        if (stateAtRelLocation(-relDistance) == FREE) {
          copyCursor.dispose();
          return dist + copyCursor.current().getSize();
        }
        relDistance = 0;
      }
      dist += copyCursor.current().getSize();
      relDistance += copyCursor.current().getSize();
      copyCursor.prev();
    }
    copyCursor.dispose();
    return -1;
  }
}
