class PlaceHold {
  public int getOffset(int x, int y, int[] trailing) {
    checkLayout();
    computeRuns(null);
    if ((trailing != null) && (trailing.length < 1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int line;
    int lineCount = runs.length;
    for (line = 0; line < lineCount; line++) {
      if (lineY[line + 1] > y) {
        break;
      }
    }
    line = Math.min(line, runs.length - 1);
    StyleItem[] lineRuns = runs[line];
    int lineIndent = getLineIndent(line);
    if (x >= (lineIndent + lineWidth[line])) {
      x = (lineIndent + lineWidth[line]) - 1;
    }
    if (x < lineIndent) {
      x = lineIndent;
    }
    int low = -1;
    int high = lineRuns.length;
    while ((high - low) > 1) {
      int index = (high + low) / 2;
      StyleItem run = lineRuns[index];
      if (run.x > x) {
        high = index;
      } else if ((run.x + run.width) <= x) {
        low = index;
      } else {
        if (run.lineBreak && (!run.softBreak)) {
          return untranslateOffset(run.start);
        }
        int xRun = x - run.x;
        if ((run.style != null) && (run.style.metrics != null)) {
          GlyphMetrics metrics = run.style.metrics;
          if (metrics.width > 0) {
            if (trailing != null) {
              trailing[0] = ((xRun % metrics.width) < (metrics.width / 2)) ? 0 : 1;
            }
            return untranslateOffset(run.start + (xRun / metrics.width));
          }
        }
        if (run.tab) {
          if (trailing != null) {
            trailing[0] = (x < (run.x + (run.width / 2))) ? 0 : 1;
          }
          return untranslateOffset(run.start);
        }
        int cChars = run.length;
        int cGlyphs = run.glyphCount;
        int[] piCP = new int[1];
        int[] piTrailing = new int[1];
        if ((orientation & SWT.RIGHT_TO_LEFT) != 0) {
          xRun = run.width - xRun;
        }
        long advances = (run.justify != 0) ? run.justify : run.advances;
        OS.ScriptXtoCP(
            xRun,
            cChars,
            cGlyphs,
            run.clusters,
            run.visAttrs,
            advances,
            run.analysis,
            piCP,
            piTrailing);
        int offset = run.start + piCP[0];
        int length = segmentsText.length();
        char ch = (offset < length) ? segmentsText.charAt(offset) : 0;
        if (((0xd800 <= ch) && (ch <= 0xdbff)) && (piTrailing[0] <= 1)) {
          if ((offset + 1) < length) {
            ch = segmentsText.charAt(offset + 1);
            if ((0xdc00 <= ch) && (ch <= 0xdfff)) {
              if (trailing != null) {
                trailing[0] = 0;
              }
            }
          }
        } else if (((0xdc00 <= ch) && (ch <= 0xdfff)) && (piTrailing[0] <= 1)) {
          if ((offset - 1) >= 0) {
            ch = segmentsText.charAt(offset - 1);
            if ((0xd800 <= ch) && (ch <= 0xdbff)) {
              offset--;
              if (trailing != null) {
                trailing[0] = 2;
              }
            }
          }
        } else if (trailing != null) {
          trailing[0] = piTrailing[0];
        }
        return untranslateOffset(offset);
      }
    }
    if (trailing != null) {
      trailing[0] = 0;
    }
    if (lineRuns.length == 1) {
      StyleItem run = lineRuns[0];
      if (run.lineBreak && (!run.softBreak)) {
        return untranslateOffset(run.start);
      }
    }
    return untranslateOffset(lineOffset[line + 1]);
  }
}
