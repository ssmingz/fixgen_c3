class PlaceHold {
  public Rectangle getBounds() {
    checkLayout();
    computeRuns();
    int width = 0;
    int height = 0;
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
      height = ascent + descent;
    } else {
      for (int i = 0; i < breaks.length; i++) {
        width = Math.max(width, lineWidth[i]);
        height += lineHeight[i];
      }
    }
    int[] buffer = new int[1];
    OS.ATSUGetLayoutControl(layout, kATSULineWidthTag, 4, buffer, null);
    int wrapWidth = OS.Fix2Long(buffer[0]);
    if (wrapWidth != 0) {
      width = Math.max(width, wrapWidth);
    }
    return new Rectangle(0, 0, width, height);
  }
}
