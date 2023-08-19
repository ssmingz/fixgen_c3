class PlaceHold {
  private void paintCenter(org.eclipse.swt.widgets.Canvas canvas, org.eclipse.swt.graphics.GC g) {
    org.eclipse.swt.widgets.Display display = canvas.getDisplay();
    checkForColorUpdate(display);
    if (!fSynchronizedScrolling) return;

    int lineHeight = fLeft.getTextWidget().getLineHeight();
    int visibleHeight = fRight.getViewportHeight();
    org.eclipse.swt.graphics.Point size = canvas.getSize();
    int x = 0;
    int w = size.x;
    g.setBackground(canvas.getBackground());
    g.fillRectangle(x + 1, 0, w - 2, size.y);
    if (!fIsMotif) {
      // draw thin line between center ruler and both texts
      g.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
      g.fillRectangle(0, 0, 1, size.y);
      g.fillRectangle(w - 1, 0, 1, size.y);
    }
    if (!fHighlightRanges) return;

    boolean showResolveUI = showResolveUI();
    if (fChangeDiffs != null) {
      int lshift = fLeft.getVerticalScrollOffset();
      int rshift = fRight.getVerticalScrollOffset();
      org.eclipse.swt.graphics.Point region = new org.eclipse.swt.graphics.Point(0, 0);
      Iterator e = fChangeDiffs.iterator();
      while (e.hasNext()) {
        Diff diff = ((Diff) (e.next()));
        if (diff.isDeleted()) continue;

        if (fShowCurrentOnly2 && (!isCurrentDiff(diff))) continue;

        fLeft.getLineRange(diff.fLeftPos, region);
        int ly = (region.x * lineHeight) + lshift;
        int lh = region.y * lineHeight;
        fRight.getLineRange(diff.fRightPos, region);
        int ry = (region.x * lineHeight) + rshift;
        int rh = region.y * lineHeight;
        if (Math.max(ly + lh, ry + rh) < 0) continue;

        if (Math.min(ly, ry) >= visibleHeight) break;

        fPts[0] = x;
        fPts[1] = ly;
        fPts[2] = w;
        fPts[3] = ry;
        fPts[6] = x;
        fPts[7] = ly + lh;
        fPts[4] = w;
        fPts[5] = ry + rh;
        org.eclipse.swt.graphics.Color fillColor = getColor(display, getFillColor(diff));
        org.eclipse.swt.graphics.Color strokeColor = getColor(display, getStrokeColor(diff));
        if (fUseSingleLine) {
          int w2 = 3;
          g.setBackground(fillColor);
          g.fillRectangle(0, ly, w2, lh); // left

          g.fillRectangle(w - w2, ry, w2, rh); // right

          g.setLineWidth(LW);
          g.setForeground(strokeColor);
          g.drawRectangle(0 - 1, ly, w2, lh); // left

          g.drawRectangle(w - w2, ry, w2, rh); // right

          if (fUseSplines) {
            int[] points = getCenterCurvePoints(w2, ly + (lh / 2), w - w2, ry + (rh / 2));
            for (int i = 1; i < points.length; i++)
              g.drawLine((w2 + i) - 1, points[i - 1], w2 + i, points[i]);

          } else {
            g.drawLine(w2, ly + (lh / 2), w - w2, ry + (rh / 2));
          }
        } else // two lines
        if (fUseSplines) {
          g.setBackground(fillColor);
          g.setLineWidth(LW);
          g.setForeground(strokeColor);
          int[] topPoints = getCenterCurvePoints(fPts[0], fPts[1], fPts[2], fPts[3]);
          int[] bottomPoints = getCenterCurvePoints(fPts[6], fPts[7], fPts[4], fPts[5]);
          g.setForeground(fillColor);
          g.drawLine(0, bottomPoints[0], 0, topPoints[0]);
          for (int i = 1; i < bottomPoints.length; i++) {
            g.setForeground(fillColor);
            g.drawLine(i, bottomPoints[i], i, topPoints[i]);
            g.setForeground(strokeColor);
            g.drawLine(i - 1, topPoints[i - 1], i, topPoints[i]);
            g.drawLine(i - 1, bottomPoints[i - 1], i, bottomPoints[i]);
          }
        } else {
          g.setBackground(fillColor);
          g.fillPolygon(fPts);
          g.setLineWidth(LW);
          g.setForeground(strokeColor);
          g.drawLine(fPts[0], fPts[1], fPts[2], fPts[3]);
          g.drawLine(fPts[6], fPts[7], fPts[4], fPts[5]);
        }
        if ((fUseSingleLine && showResolveUI) && diff.isUnresolvedIncomingOrConflicting()) {
          // draw resolve state
          int cx = (w - RESOLVE_SIZE) / 2;
          int cy = (((ly + (lh / 2)) + (ry + (rh / 2))) - RESOLVE_SIZE) / 2;
          g.setBackground(fillColor);
          g.fillRectangle(cx, cy, RESOLVE_SIZE, RESOLVE_SIZE);
          g.setForeground(strokeColor);
          g.drawRectangle(cx, cy, RESOLVE_SIZE, RESOLVE_SIZE);
        }
      }
    }
  }
}
