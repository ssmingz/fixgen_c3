class StyledText {
  public StyledText(Composite parent, int style) {
    super(parent, checkStyle((style | SWT.NO_REDRAW_RESIZE) | SWT.NO_BACKGROUND));
    super.setForeground(getForeground());
    super.setBackground(getBackground());
    Display display = getDisplay();
    boolean isRightOriented = (getStyle() & SWT.RIGHT_TO_LEFT) != 0;
    isBidi = StyledTextBidi.isBidiPlatform() || isRightOriented;
    if ((style & SWT.READ_ONLY) != 0) {
      setEditable(false);
    }
    if (((style & SWT.BORDER) == 0) || ((style & SWT.SINGLE) == 0)) {
      leftMargin = topMargin = rightMargin = bottomMargin = 0;
    }
    clipboard = new Clipboard(display);
    installDefaultContent();
    initializeRenderer();
    if ((style & SWT.WRAP) != 0) {
      setWordWrap(true);
    } else {
      lineCache = new ContentWidthCache(this, content.getLineCount());
    }
    if (isBidi() == false) {
      Caret caret = new Caret(this, SWT.NULL);
      caret.setSize(1, caret.getSize().y);
    } else {
      createCaretBitmaps();
      if (isRightOriented) {
        BidiUtil.setKeyboardLanguage(KEYBOARD_BIDI);
      }
      new Caret(this, SWT.NULL);
      setBidiCaretDirection();
      Runnable runnable =
          new Runnable() {
            public void run() {
              setBidiCaretLocation(null);
            }
          };
      StyledTextBidi.addLanguageListener(this, runnable);
    }
    String platform = SWT.getPlatform();
    isCarbon = "carbon".equals(platform);
    calculateScrollBars();
    createKeyBindings();
    ibeamCursor = new Cursor(display, SWT.CURSOR_IBEAM);
    setCursor(ibeamCursor);
    installListeners();
    installDefaultLineStyler();
  }
}
