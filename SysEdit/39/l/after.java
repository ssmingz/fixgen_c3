class PlaceHold {
  private void paintSides(GC g, MergeSourceViewer tp, Canvas canvas, boolean right) {
    Display display = canvas.getDisplay();
    int lineHeight = tp.getTextWidget().getLineHeight();
    int visibleHeight = tp.getViewportHeight();
    Point size = canvas.getSize();
    int x = 0;
    int w = fMarginWidth;
    int w2 = w / 2;
    g.setBackground(canvas.getBackground());
    g.fillRectangle(x, 0, w, size.y);
    if (!fIsMotif) {
      // draw thin line between ruler and text
      g.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
      if (right) g.fillRectangle(0, 0, 1, size.y);
      else g.fillRectangle(size.x - 1, 0, 1, size.y);
    }
    if (!fHighlightRanges) return;

    if (fMerger.hasChanges()) {
      int shift = tp.getVerticalScrollOffset() + (2 - LW);
      Point region = new Point(0, 0);
      char leg = getLeg(tp);
      for (Iterator iterator = fMerger.changesIterator(); iterator.hasNext(); ) {
        org.eclipse.compare.internal.merge.DocumentMerger.Diff diff =
            ((org.eclipse.compare.internal.merge.DocumentMerger.Diff) (iterator.next()));
        if (diff.isDeleted()) continue;

        if (fShowCurrentOnly2 && (!isCurrentDiff(diff))) continue;

        tp.getLineRange(diff.getPosition(leg), region);
        int y = (region.x * lineHeight) + shift;
        int h = region.y * lineHeight;
        if ((y + h) < 0) continue;

        if (y >= visibleHeight) break;

        g.setBackground(getColor(display, getFillColor(diff)));
        if (right) g.fillRectangle(x, y, w2, h);
        else g.fillRectangle(x + w2, y, w2, h);

        /* LW */
        g.setLineWidth(0);
        g.setForeground(getColor(display, getStrokeColor(diff)));
        if (right) g.drawRectangle(x - 1, y - 1, w2, h);
        else g.drawRectangle(x + w2, y - 1, w2, h);
      }
    }
  }
}
