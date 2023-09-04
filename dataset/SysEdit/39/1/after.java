class PlaceHold {
  private Diff handleMouseInSides(Canvas canvas, MergeSourceViewer tp, int my) {

    int lineHeight = tp.getTextWidget().getLineHeight();
    int visibleHeight = tp.getViewportHeight();

    if (!fHighlightRanges) return null;

    if (fMerger.hasChanges()) {
      int shift = tp.getVerticalScrollOffset() + (2 - LW);

      Point region = new Point(0, 0);
      char leg = getLeg(tp);
      for (Iterator iterator = fMerger.changesIterator(); iterator.hasNext(); ) {
        Diff diff = (Diff) iterator.next();
        if (diff.isDeleted()) continue;

        if (fShowCurrentOnly2 && !isCurrentDiff(diff)) continue;

        tp.getLineRange(diff.getPosition(leg), region);
        int y = (region.x * lineHeight) + shift;
        int h = region.y * lineHeight;

        if (y + h < 0) continue;
        if (y >= visibleHeight) break;

        if (my >= y && my < y + h) return diff;
      }
    }
    return null;
  }
}
