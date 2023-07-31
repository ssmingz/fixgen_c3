class PlaceHold {
  public FontMetrics getLineMetrics(int lineIndex) {
    checkLayout();
    computeRuns();
    int lineCount = breaks.length;
    if (!((0 <= lineIndex) && (lineIndex < lineCount))) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    int length = text.length();
    if (length == 0) {
      Font font = (this.font != null) ? this.font : device.getSystemFont();
      ATSFontMetrics metrics = new ATSFontMetrics();
      OS.ATSFontGetVerticalMetrics(font.handle, kATSOptionFlagsDefault, metrics);
      OS.ATSFontGetHorizontalMetrics(font.handle, kATSOptionFlagsDefault, metrics);
      int ascent = ((int) (0.5F + (metrics.ascent * font.size)));
      int descent = ((int) (0.5F + (((-metrics.descent) + metrics.leading) * font.size)));
      ascent = Math.max(ascent, this.ascent);
      descent = Math.max(descent, this.descent);
      return FontMetrics.carbon_new(ascent, descent, 0, 0, ascent + descent);
    }
    int start = (lineIndex == 0) ? 0 : breaks[lineIndex - 1];
    int lineLength = breaks[lineIndex] - start;
    int[] ascent = new int[1];
    int[] descent = new int[1];
    OS.ATSUGetUnjustifiedBounds(layout, start, lineLength, null, null, ascent, descent);
    int height = OS.Fix2Long(ascent[0]) + OS.Fix2Long(descent[0]);
    return FontMetrics.carbon_new(OS.Fix2Long(ascent[0]), OS.Fix2Long(descent[0]), 0, 0, height);
  }
}
