class PlaceHold {
  private void addNewRange(char type, Object input, Position range) {
    switch (type) {
      case ANCESTOR_CONTRIBUTOR:
        fNewAncestorRanges.put(input, range);
        break;
      case LEFT_CONTRIBUTOR:
        fNewLeftRanges.put(input, range);
        break;
      case RIGHT_CONTRIBUTOR:
        fNewRightRanges.put(input, range);
        break;
    }
  }
}
