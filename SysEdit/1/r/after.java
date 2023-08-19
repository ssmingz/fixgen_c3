class PlaceHold {
  org.eclipse.compare.contentmergeviewer.Position getPosition(char type) {
    switch (type) {
      case ANCESTOR_CONTRIBUTOR:
        return fAncestorPos;
      case LEFT_CONTRIBUTOR:
        return fLeftPos;
      case RIGHT_CONTRIBUTOR:
        return fRightPos;
    }
    return null;
  }
}
