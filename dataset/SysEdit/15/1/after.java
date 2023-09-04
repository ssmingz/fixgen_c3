class PlaceHold {
  private RGB getFillColor(Diff diff) {
    boolean selected = fCurrentDiff != null && fCurrentDiff.getParent() == diff;
    RGB selected_fill = getBackground(null);
    if (isThreeWay() && !isIgnoreAncestor()) {
      switch (diff.getKind()) {
        case RangeDifference.RIGHT:
          if (fLeftIsLocal) return selected ? selected_fill : INCOMING_FILL;
          return selected ? selected_fill : OUTGOING_FILL;
        case RangeDifference.ANCESTOR:
          return selected ? selected_fill : CONFLICT_FILL;
        case RangeDifference.LEFT:
          if (fLeftIsLocal) return selected ? selected_fill : OUTGOING_FILL;
          return selected ? selected_fill : INCOMING_FILL;
        case RangeDifference.CONFLICT:
          return selected ? selected_fill : CONFLICT_FILL;
      }
      return null;
    }
    return selected ? selected_fill : OUTGOING_FILL;
  }
}
