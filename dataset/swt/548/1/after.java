class PlaceHold {
  void shape(final int hdc, final StyleItem run) {
    int[] buffer = new int[1];
    char[] chars = new char[run.length];
    segmentsText.getChars(run.start, run.start + run.length, chars, 0);
    int maxGlyphs = ((chars.length * 3) / 2) + 16;
    int hHeap = OS.GetProcessHeap();
    run.glyphs = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, maxGlyphs * 2);
    if (run.glyphs == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    run.clusters = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, maxGlyphs * 2);
    if (run.clusters == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    run.visAttrs = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, maxGlyphs * SCRIPT_VISATTR_SIZEOF);
    if (run.visAttrs == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    run.psc = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, PTR_SIZEOF);
    if (run.psc == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (!shape(hdc, run, chars, buffer, maxGlyphs)) {
      if (mLangFontLink2 != 0) {
        int[] dwCodePages = new int[1];
        int[] cchCodePages = new int[1];
        OS.VtblCall(4, mLangFontLink2, chars, chars.length, 0, dwCodePages, cchCodePages);
        int[] hNewFont = new int[1];
        if (OS.VtblCall(10, mLangFontLink2, hdc, dwCodePages[0], chars[0], hNewFont) == OS.S_OK) {
          int hFont = OS.SelectObject(hdc, hNewFont[0]);
          if (shape(hdc, run, chars, buffer, maxGlyphs)) {
            run.fallbackFont = hNewFont[0];
          } else {
            OS.VtblCall(8, mLangFontLink2, hNewFont[0]);
            OS.SelectObject(hdc, hFont);
            SCRIPT_PROPERTIES properties = new SCRIPT_PROPERTIES();
            OS.MoveMemory(properties, device.scripts[run.analysis.eScript], sizeof);
            if (properties.fPrivateUseArea) {
              run.analysis.fNoGlyphIndex = true;
            }
            OS.ScriptShape(
                hdc,
                run.psc,
                chars,
                chars.length,
                maxGlyphs,
                run.analysis,
                run.glyphs,
                run.clusters,
                run.visAttrs,
                buffer);
            run.glyphCount = buffer[0];
          }
        }
      }
    }
    int[] abc = new int[3];
    run.advances = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, run.glyphCount * 4);
    if (run.advances == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    run.goffsets = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, run.glyphCount * GOFFSET_SIZEOF);
    if (run.goffsets == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.ScriptPlace(
        hdc,
        run.psc,
        run.glyphs,
        run.glyphCount,
        run.visAttrs,
        run.analysis,
        run.advances,
        run.goffsets,
        abc);
    if ((run.style != null) && (run.style.metrics != null)) {
      GlyphMetrics metrics = run.style.metrics;
      run.width = metrics.width * Math.max(1, run.glyphCount);
      run.ascent = metrics.ascent;
      run.descent = metrics.descent;
      run.leading = 0;
    } else {
      run.width = (abc[0] + abc[1]) + abc[2];
      TEXTMETRIC lptm = (OS.IsUnicode) ? ((TEXTMETRIC) (new TEXTMETRICW())) : new TEXTMETRICA();
      OS.GetTextMetrics(hdc, lptm);
      run.ascent = lptm.tmAscent;
      run.descent = lptm.tmDescent;
      run.leading = lptm.tmInternalLeading;
    }
    if (run.style != null) {
      run.ascent += run.style.rise;
      run.descent -= +run.style.rise;
    }
  }
}
