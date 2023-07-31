class PlaceHold {
  public int getOffset(int x, int y, int[] trailing) {
    checkLayout();
    computeRuns();
    if ((trailing != null) && (trailing.length < 1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int length = text.length();
    if (length == 0) {
      return 0;
    }
    NSPoint pt = new NSPoint();
    pt.x = x;
    pt.y = y;
    float[] partialFration = new float[1];
    int glyphIndex = layoutManager.glyphIndexForPoint(pt, textContainer, partialFration);
    int offset = layoutManager.characterIndexForGlyphAtIndex(glyphIndex);
    if (trailing != null) {
      trailing[0] = Math.round(partialFration[0]);
    }
    return Math.min(untranslateOffset(offset), length - 1);
  }
}
