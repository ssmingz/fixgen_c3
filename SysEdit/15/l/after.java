class PlaceHold {
  private org.eclipse.compare.contentmergeviewer.RGB getStrokeColor(
      org.eclipse.compare.internal.merge.DocumentMerger.Diff diff) {
    boolean selected = (fCurrentDiff != null) && (fCurrentDiff.getParent() == diff);
    if (isThreeWay() && (!isIgnoreAncestor())) {
      switch (diff.getKind()) {
        case org.eclipse.compare.rangedifferencer.RangeDifference.RIGHT:
          if (fLeftIsLocal) return selected ? SELECTED_INCOMING : INCOMING;

          return selected ? SELECTED_OUTGOING : OUTGOING;
        case org.eclipse.compare.rangedifferencer.RangeDifference.ANCESTOR:
          return selected ? SELECTED_CONFLICT : CONFLICT;
        case org.eclipse.compare.rangedifferencer.RangeDifference.LEFT:
          if (fLeftIsLocal) return selected ? SELECTED_OUTGOING : OUTGOING;

          return selected ? SELECTED_INCOMING : INCOMING;
        case org.eclipse.compare.rangedifferencer.RangeDifference.CONFLICT:
          return selected ? SELECTED_CONFLICT : CONFLICT;
      }
      return null;
    }
    return selected ? SELECTED_OUTGOING : OUTGOING;
  }
}
