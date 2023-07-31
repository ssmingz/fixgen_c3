class PlaceHold {
  void handleDispose(Event event) {
    removeListener(Dispose, listener);
    notifyListeners(Dispose, event);
    event.type = SWT.None;
    clipboard.dispose();
    ibeamCursor.dispose();
    if (renderer != null) {
      renderer.dispose();
      renderer = null;
    }
    if (content != null) {
      content.removeTextChangeListener(textChangeListener);
      content = null;
    }
    if (defaultCaret != null) {
      defaultCaret.dispose();
      defaultCaret = null;
    }
    if (leftCaretBitmap != null) {
      leftCaretBitmap.dispose();
      leftCaretBitmap = null;
    }
    if (rightCaretBitmap != null) {
      rightCaretBitmap.dispose();
      rightCaretBitmap = null;
    }
    if (defaultLineStyler != null) {
      defaultLineStyler.release();
      defaultLineStyler = null;
    }
    if (isBidiCaret()) {
      BidiUtil.removeLanguageListener(handle);
    }
    selectionBackground = null;
    selectionForeground = null;
    logicalContent = null;
    textChangeListener = null;
    lineCache = null;
    ibeamCursor = null;
    selection = null;
    doubleClickSelection = null;
    keyActionMap = null;
    background = null;
    foreground = null;
    clipboard = null;
  }
}
