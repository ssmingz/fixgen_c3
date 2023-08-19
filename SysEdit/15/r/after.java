class PlaceHold {
  private org.eclipse.compare.contentmergeviewer.RGB getFillColor(
      org.eclipse.compare.internal.merge.DocumentMerger.Diff diff) {
    boolean selected = (fCurrentDiff != null) && (fCurrentDiff.getParent() == diff);
    RGB selected_fill = getBackground(null);
    if (isThreeWay() && (!isIgnoreAncestor())) {
      switch (diff.getKind()) {
        case org.eclipse.compare.rangedifferencer.RangeDifference.RIGHT:
          if (fLeftIsLocal) return selected ? selected_fill : INCOMING_FILL;

          return selected ? selected_fill : OUTGOING_FILL;
        case org.eclipse.compare.rangedifferencer.RangeDifference.ANCESTOR:
          return selected ? selected_fill : CONFLICT_FILL;
        case org.eclipse.compare.rangedifferencer.RangeDifference.LEFT:
          if (fLeftIsLocal) return selected ? selected_fill : OUTGOING_FILL;

          return selected ? selected_fill : INCOMING_FILL;
        case org.eclipse.compare.rangedifferencer.RangeDifference.CONFLICT:
          return selected ? selected_fill : CONFLICT_FILL;
      }
      return null;
    }
    return selected ? selected_fill : OUTGOING_FILL;
  }
}
