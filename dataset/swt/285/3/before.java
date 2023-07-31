class PlaceHold {
  public FontMetrics getLineMetrics(int lineIndex) {
    checkLayout();
    computeRuns();
    if (!((0 <= lineIndex) && (lineIndex < runs.length))) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    GC gc = new GC(device);
    Font font = (this.font != null) ? this.font : device.getSystemFont();
    FontMetrics metrics = null;
    if (text.length() == 0) {
      gc.setFont(font);
      metrics = gc.getFontMetrics();
      metrics.ascent = Math.max(metrics.ascent, this.ascent);
      metrics.descent = Math.max(metrics.descent, this.descent);
    } else {
      int ascent = this.ascent;
      int descent = this.descent;
      int leading = 0;
      int aveCharWidth = 0;
      int height = 0;
      StyleItem[] lineRuns = runs[lineIndex];
      for (int i = 0; i < lineRuns.length; i++) {
        StyleItem run = lineRuns[i];
        Font runFont = (run.style != null) ? run.style.font : null;
        if (runFont == null) {
          runFont = font;
        }
        gc.setFont(font);
        metrics = gc.getFontMetrics();
        ascent = Math.max(ascent, metrics.getAscent());
        descent = Math.max(descent, metrics.getDescent());
        height = Math.max(height, metrics.getHeight());
        leading = Math.max(leading, metrics.getLeading());
        aveCharWidth += metrics.getAverageCharWidth();
      }
      metrics =
          FontMetrics.motif_new(ascent, descent, aveCharWidth / lineRuns.length, leading, height);
    }
    gc.dispose();
    return metrics;
  }
}
