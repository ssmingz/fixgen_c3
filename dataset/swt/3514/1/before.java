class StyledText {
  public StyledText(Composite parent, int style) {
    super(parent, checkStyle(style));
    super.setForeground(getForeground());
    super.setDragDetect(false);
    Display display = getDisplay();
    isMirrored = (super.getStyle() & SWT.MIRRORED) != 0;
    fixedLineHeight = true;
    if ((style & SWT.READ_ONLY) != 0) {
      setEditable(false);
    }
    leftMargin = rightMargin = (isBidiCaret()) ? BIDI_CARET_WIDTH - 1 : 0;
    if (((style & SWT.SINGLE) != 0) && ((style & SWT.BORDER) != 0)) {
      leftMargin = topMargin = rightMargin = bottomMargin = 2;
    }
    alignment = style & ((SWT.LEFT | SWT.RIGHT) | SWT.CENTER);
    if (alignment == 0) {
      alignment = SWT.LEFT;
    }
    clipboard = new Clipboard(display);
    installDefaultContent();
    renderer = new StyledTextRenderer(getDisplay(), this);
    renderer.setContent(content);
    renderer.setFont(getFont(), tabLength);
    ime = new IME(this, SWT.NONE);
    defaultCaret = new Caret(this, SWT.NONE);
    if ((style & SWT.WRAP) != 0) {
      setWordWrap(true);
    }
    if (isBidiCaret()) {
      createCaretBitmaps();
      Runnable runnable =
          new Runnable() {
            public void run() {
              int direction =
                  (BidiUtil.getKeyboardLanguage() == BidiUtil.KEYBOARD_BIDI) ? SWT.RIGHT : SWT.LEFT;
              if (direction == caretDirection) {
                return;
              }
              if (getCaret() != defaultCaret) {
                return;
              }
              Point newCaretPos = getPointAtOffset(caretOffset);
              setCaretLocation(newCaretPos, direction);
            }
          };
      BidiUtil.addLanguageListener(handle, runnable);
    }
    setCaret(defaultCaret);
    calculateScrollBars();
    createKeyBindings();
    setCursor(display.getSystemCursor(CURSOR_IBEAM));
    installListeners();
    initializeAccessible();
    setData("DEFAULT_DROP_TARGET_EFFECT", new StyledTextDropTargetEffect(this));
  }
}
