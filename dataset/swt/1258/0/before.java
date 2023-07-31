class PlaceHold {
  void place(StyleItem run) {
    if (run.length == 0) {
      return;
    }
    if ((run.style != null) && (run.style.metrics != null)) {
      GlyphMetrics glyphMetrics = run.style.metrics;
      run.width = glyphMetrics.width * run.length;
      run.baseline = glyphMetrics.ascent;
      run.height = glyphMetrics.ascent + glyphMetrics.descent;
    } else {
      char[] chars = new char[run.length];
      text.getChars(run.start, run.start + run.length, chars, 0);
      int fontList = getItemFont(run).handle;
      byte[] buffer = Converter.wcsToMbcs(null, chars, true);
      short[] width = new short[1];
      short[] height = new short[1];
      int xmString = OS.XmStringCreateLocalized(buffer);
      OS.XmStringExtent(fontList, xmString, width, height);
      run.width = width[0] & 0xffff;
      run.height = height[0] & 0xffff;
      run.baseline = OS.XmStringBaseline(fontList, xmString);
      OS.XmStringFree(xmString);
    }
  }
}
