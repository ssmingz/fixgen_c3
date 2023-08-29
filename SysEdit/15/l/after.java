class PlaceHold {
  private RGB getStrokeColor(Diff diff) {
    boolean selected = fCurrentDiff != null && fCurrentDiff.getParent() == diff;

    if (isThreeWay() && !isIgnoreAncestor()) {
      switch (diff.getKind()) {
        case RangeDifference.RIGHT:
          if (fLeftIsLocal) return selected ? SELECTED_INCOMING : INCOMING;
          return selected ? SELECTED_OUTGOING : OUTGOING;
        case RangeDifference.ANCESTOR:
          return selected ? SELECTED_CONFLICT : CONFLICT;
        case RangeDifference.LEFT:
          if (fLeftIsLocal) return selected ? SELECTED_OUTGOING : OUTGOING;
          return selected ? SELECTED_INCOMING : INCOMING;
        case RangeDifference.CONFLICT:
          return selected ? SELECTED_CONFLICT : CONFLICT;
      }
      return null;
    }
    return selected ? SELECTED_OUTGOING : OUTGOING;
  }
}
