class PlaceHold {
  private Position getNewRange(char type, Object input) {
    switch (type) {
      case 'A':
        return ((Position) (fNewAncestorRanges.get(input)));
      case 'L':
        return ((Position) (fNewLeftRanges.get(input)));
      case 'R':
        return ((Position) (fNewRightRanges.get(input)));
    }
    return null;
  }
}
