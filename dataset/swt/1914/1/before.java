class PlaceHold {
  int kEventTextInputUpdateActiveInputArea(int nextHandler, int theEvent, int userData) {
    if (!isInlineIMEEnabled()) {
      return OS.eventNotHandledErr;
    }
    ranges = null;
    styles = null;
    caretOffset = commitCount = 0;
    int[] length = new int[1];
    OS.GetEventParameter(
        theEvent,
        kEventParamTextInputSendText,
        typeUnicodeText,
        null,
        0,
        length,
        ((char[]) (null)));
    char[] chars = new char[length[0]];
    OS.GetEventParameter(
        theEvent, kEventParamTextInputSendText, typeUnicodeText, null, length[0], null, chars);
    int[] fixed_length = new int[1];
    OS.GetEventParameter(
        theEvent, kEventParamTextInputSendFixLen, typeLongInteger, null, 4, null, fixed_length);
    int[] rangeSize = new int[1];
    int rc =
        OS.GetEventParameter(
            theEvent,
            kEventParamTextInputSendHiliteRng,
            typeTextRangeArray,
            null,
            0,
            rangeSize,
            ((byte[]) (null)));
    if (rc == OS.noErr) {
      int firstSelectedConverted = -1;
      boolean hasConvertedText = false;
      int textRanges = OS.NewPtr(rangeSize[0]);
      OS.GetEventParameter(
          theEvent,
          kEventParamTextInputSendHiliteRng,
          typeTextRangeArray,
          null,
          rangeSize[0],
          null,
          textRanges);
      short[] nRanges = new short[1];
      OS.memmove(nRanges, textRanges, 2);
      int count = nRanges[0];
      if (count > 0) {
        TextRange range = new TextRange();
        ranges = new int[(count - 1) * 2];
        styles = new TextStyle[count - 1];
        for (int i = 0, j = 0; i < count; i++) {
          OS.memmove(range, (textRanges + 2) + (i * TextRange.sizeof), sizeof);
          switch (range.fHiliteStyle) {
            case OS.kCaretPosition:
              caretOffset = range.fStart / 2;
              break;
            case OS.kConvertedText:
            case OS.kSelectedConvertedText:
            case OS.kSelectedRawText:
            case OS.kRawText:
              ranges[j * 2] = range.fStart / 2;
              ranges[(j * 2) + 1] = ((range.fEnd / 2) - (range.fStart / 2)) + 0;
              styles[j] = new TextStyle();
              styles[j].underline = true;
              styles[j].underlineStyle = UNDERLINE_IME_INPUT;
              if (range.fHiliteStyle == OS.kConvertedText) {
                styles[j].underlineStyle = UNDERLINE_IME_CONVERTED;
                hasConvertedText = true;
              }
              if (range.fHiliteStyle == OS.kSelectedConvertedText) {
                styles[j].underlineStyle = UNDERLINE_IME_TARGET_CONVERTED;
                if (firstSelectedConverted == (-1)) {
                  firstSelectedConverted = range.fStart;
                }
              }
              j++;
              break;
          }
        }
      }
      OS.DisposePtr(textRanges);
      if (hasConvertedText && (firstSelectedConverted != (-1))) {
        caretOffset = firstSelectedConverted / 2;
      }
    }
    if (startOffset == (-1)) {
      Caret caret = parent.getCaret();
      startOffset = (caret != null) ? caret.getOffset() : 0;
    }
    Event event = new Event();
    event.detail = SWT.COMPOSITION_CHANGED;
    event.start = startOffset;
    event.end = startOffset + text.length();
    event.text = text = new String(chars, 0, length[0] / 2);
    commitCount = (fixed_length[0] != (-1)) ? fixed_length[0] / 2 : length[0] / 2;
    sendEvent(ImeComposition, event);
    if (commitCount == text.length()) {
      text = "";
      caretOffset = commitCount = 0;
      startOffset = -1;
    }
    if (event.doit) {
      if ((fixed_length[0] == (-1)) || (fixed_length[0] == length[0])) {
        for (int i = 0; i < chars.length; i++) {
          if (chars[i] == 0) {
            break;
          }
          event = new Event();
          event.character = chars[i];
          parent.sendKeyEvent(KeyDown, event);
        }
      }
    }
    return OS.noErr;
  }
}
