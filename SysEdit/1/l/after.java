class PlaceHold {
  private org.eclipse.compare.contentmergeviewer.Position getNewRange(char type, Object input) {
    switch (type) {
      case ANCESTOR_CONTRIBUTOR:
        return ((Position) (fNewAncestorRanges.get(input)));
      case LEFT_CONTRIBUTOR:
        return ((Position) (fNewLeftRanges.get(input)));
      case RIGHT_CONTRIBUTOR:
        return ((Position) (fNewRightRanges.get(input)));
    }
    return null;
  }
}
