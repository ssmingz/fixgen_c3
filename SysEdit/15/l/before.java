class PlaceHold {
  private org.eclipse.compare.contentmergeviewer.RGB getStrokeColor(Diff diff) {
    boolean selected = (fCurrentDiff != null) && (fCurrentDiff.fParent == diff);
    if (isThreeWay() && (!isIgnoreAncestor())) {
      switch (diff.fDirection) {
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
