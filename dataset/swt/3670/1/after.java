class PlaceHold {
  void place(GC gc, StyleItem run) {
    if ((run.style != null) && (run.style.metrics != null)) {
      GlyphMetrics metrics = run.style.metrics;
      run.ascent = metrics.ascent;
      run.descent = metrics.descent;
      run.width = metrics.width * run.length;
    } else {
      String string = text.substring(run.start, run.start + run.length);
      Point extent = gc.stringExtent(string);
      FontMetrics metrics = gc.getFontMetrics();
      run.width = extent.x;
      run.ascent = metrics.getAscent() + metrics.getLeading();
      run.descent = metrics.getDescent();
    }
    if (run.style != null) {
      run.ascent += run.style.rise;
      run.descent -= run.style.rise;
    }
  }
}
