class PlaceHold {
  void getPartialSelection(StyleItem run, int selectionStart, int selectionEnd, RECT rect) {
    int end = (run.start + run.length) - 1;
    int selStart = Math.max(selectionStart, run.start) - run.start;
    int selEnd = Math.min(selectionEnd, end) - run.start;
    int cChars = run.length;
    int gGlyphs = run.glyphCount;
    int[] piX = new int[1];
    int x = rect.left;
    int advances = (run.justify != 0) ? run.justify : run.advances;
    OS.ScriptCPtoX(
        selStart, false, cChars, gGlyphs, run.clusters, run.visAttrs, advances, run.analysis, piX);
    int runX = ((orientation & SWT.RIGHT_TO_LEFT) != 0) ? run.width - piX[0] : piX[0];
    rect.left = x + runX;
    OS.ScriptCPtoX(
        selEnd, true, cChars, gGlyphs, run.clusters, run.visAttrs, advances, run.analysis, piX);
    runX = ((orientation & SWT.RIGHT_TO_LEFT) != 0) ? run.width - piX[0] : piX[0];
    rect.right = x + runX;
  }
}
