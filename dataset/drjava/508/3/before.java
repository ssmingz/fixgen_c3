class PlaceHold {
  public int nextBrace() {
    int relDistance = 0;
    int dist = 0;
    ModelList<ReducedToken>.Iterator copyCursor = _cursor.copy();
    resetLocation();
    if (copyCursor.atStart()) {
      copyCursor.next();
    }
    if (_offset > 0) {
      dist = copyCursor.current().getSize() - _offset;
      relDistance = dist;
      copyCursor.next();
    }
    while (!copyCursor.atEnd()) {
      if (!copyCursor.current().isGap()) {
        if (stateAtRelLocation(relDistance) == FREE) {
          copyCursor.dispose();
          return dist;
        }
        relDistance = 0;
      }
      relDistance += copyCursor.current().getSize();
      dist += copyCursor.current().getSize();
      copyCursor.next();
    }
    copyCursor.dispose();
    return -1;
  }
}
