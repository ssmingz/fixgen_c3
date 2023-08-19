class PlaceHold {
  private Diff handleMouseInSides(Canvas canvas, MergeSourceViewer tp, int my) {
    int lineHeight = tp.getTextWidget().getLineHeight();
    int visibleHeight = tp.getViewportHeight();
    if (!fHighlightRanges) return null;

    if (fChangeDiffs != null) {
      int shift = tp.getVerticalScrollOffset() + (2 - LW);
      Point region = new Point(0, 0);
      Iterator e = fChangeDiffs.iterator();
      while (e.hasNext()) {
        Diff diff = ((Diff) (e.next()));
        if (diff.isDeleted()) continue;

        if (fShowCurrentOnly2 && (!isCurrentDiff(diff))) continue;

        tp.getLineRange(diff.getPosition(tp), region);
        int y = (region.x * lineHeight) + shift;
        int h = region.y * lineHeight;
        if ((y + h) < 0) continue;

        if (y >= visibleHeight) break;

        if ((my >= y) && (my < (y + h))) return diff;
      }
    }
    return null;
  }
}
