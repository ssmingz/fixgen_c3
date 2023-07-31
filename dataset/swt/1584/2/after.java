class PlaceHold {
  public Point getLocation(int offset, boolean trailing) {
    checkLayout();
    computeRuns(null);
    int length = text.length();
    if (!((0 <= offset) && (offset <= length))) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    length = segmentsText.length();
    offset = translateOffset(offset);
    int line;
    for (line = 0; line < runs.length; line++) {
      if (lineOffset[line + 1] > offset) {
        break;
      }
    }
    line = Math.min(line, runs.length - 1);
    if (offset == length) {
      return new Point(getLineIndent(line) + lineWidth[line], lineY[line]);
    }
    char ch = segmentsText.charAt(offset);
    if (trailing) {
      if ((0xd800 <= ch) && (ch <= 0xdbff)) {
        if ((offset + 1) < length) {
          ch = segmentsText.charAt(offset + 1);
          if ((0xdc00 <= ch) && (ch <= 0xdfff)) {
            offset++;
          }
        }
      }
    } else if ((0xdc00 <= ch) && (ch <= 0xdfff)) {
      if ((offset - 1) >= 0) {
        ch = segmentsText.charAt(offset - 1);
        if ((0xd800 <= ch) && (ch <= 0xdbff)) {
          offset--;
        }
      }
    }
    int low = -1;
    int high = allRuns.length;
    while ((high - low) > 1) {
      int index = (high + low) / 2;
      StyleItem run = allRuns[index];
      if (run.start > offset) {
        high = index;
      } else if ((run.start + run.length) <= offset) {
        low = index;
      } else {
        int width;
        if ((run.style != null) && (run.style.metrics != null)) {
          GlyphMetrics metrics = run.style.metrics;
          width = metrics.width * ((offset - run.start) + (trailing ? 1 : 0));
        } else if (run.tab) {
          width = (trailing || (offset == length)) ? run.width : 0;
        } else {
          int runOffset = offset - run.start;
          int cChars = run.length;
          int gGlyphs = run.glyphCount;
          int[] piX = new int[1];
          long advances = (run.justify != 0) ? run.justify : run.advances;
          OS.ScriptCPtoX(
              runOffset,
              trailing,
              cChars,
              gGlyphs,
              run.clusters,
              run.visAttrs,
              advances,
              run.analysis,
              piX);
          width = ((orientation & SWT.RIGHT_TO_LEFT) != 0) ? run.width - piX[0] : piX[0];
        }
        return new Point(run.x + width, lineY[line]);
      }
    }
    return new Point(0, 0);
  }
}
