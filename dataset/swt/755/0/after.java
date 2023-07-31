class PlaceHold {
  boolean setMarkedText_selectedRange(int id, int sel, int string, int selRange) {
    if (!isInlineEnabled()) {
      return true;
    }
    resetStyles();
    caretOffset = commitCount = 0;
    int end = startOffset + text.length();
    if (startOffset == (-1)) {
      Event event = new Event();
      event.detail = SWT.COMPOSITION_SELECTION;
      sendEvent(ImeComposition, event);
      startOffset = event.start;
      end = event.end;
    }
    NSString str = new NSString(string);
    if (str.isKindOfClass(class_NSAttributedString)) {
      NSAttributedString attribStr = new NSAttributedString(string);
      str = attribStr.string();
      int length = ((int) (str.length()));
      styles = new TextStyle[length];
      ranges = new int[length * 2];
      NSRange rangeLimit = new NSRange();
      NSRange effectiveRange = new NSRange();
      rangeLimit.length = length;
      int rangeCount = 0;
      int ptr = OS.malloc(sizeof);
      for (int i = 0; i < length; ) {
        NSDictionary attribs = attribStr.attributesAtIndex(i, ptr, rangeLimit);
        OS.memmove(effectiveRange, ptr, sizeof);
        i = ((int) (effectiveRange.location + effectiveRange.length));
        ranges[rangeCount * 2] = ((int) (effectiveRange.location));
        ranges[(rangeCount * 2) + 1] =
            ((int) ((effectiveRange.location + effectiveRange.length) - 1));
        styles[rangeCount++] = getStyle(attribs);
      }
      OS.free(ptr);
      if (rangeCount != styles.length) {
        TextStyle[] newStyles = new TextStyle[rangeCount];
        System.arraycopy(styles, 0, newStyles, 0, newStyles.length);
        styles = newStyles;
        int[] newRanges = new int[rangeCount * 2];
        System.arraycopy(ranges, 0, newRanges, 0, newRanges.length);
        ranges = newRanges;
      }
    }
    int length = ((int) (str.length()));
    if ((ranges == null) && (length > 0)) {
      styles = new TextStyle[] {getStyle(markedAttributes)};
      ranges = new int[] {0, length - 1};
    }
    NSRange range = new NSRange();
    OS.memmove(range, selRange, sizeof);
    caretOffset = ((int) (range.location));
    Event event = new Event();
    event.detail = SWT.COMPOSITION_CHANGED;
    event.start = startOffset;
    event.end = end;
    event.text = text = str.getString();
    sendEvent(ImeComposition, event);
    if (isDisposed()) {
      return false;
    }
    if (text.length() == 0) {
      Shell s = parent.getShell();
      s.keyInputHappened = true;
      startOffset = -1;
      resetStyles();
    }
    return true;
  }
}
