class PlaceHold {
  public int getDistToPreviousNewline(int relLoc) {
    TokenList.Iterator copyCursor = _cursor.copy();
    int copyOffset = _move(-relLoc, copyCursor, _offset);
    copyCursor.dispose();
    int dist = _getDistToPreviousNewline(copyCursor, copyOffset);
    if (dist == (-1)) {
      return -1;
    }
    return dist + relLoc;
  }
}
