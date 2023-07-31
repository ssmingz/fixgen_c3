class PlaceHold {
  void setCaretLocation(Point location, int direction) {
    Caret caret = getCaret();
    if (caret != null) {
      boolean isDefaultCaret = caret == defaultCaret;
      int lineHeight = renderer.getLineHeight();
      int caretHeight = lineHeight;
      if ((!isFixedLineHeight()) && isDefaultCaret) {
        caretHeight = getBoundsAtOffset(caretOffset).height;
        if (caretHeight != lineHeight) {
          direction = SWT.DEFAULT;
        }
      }
      int imageDirection = direction;
      if (isMirrored()) {
        if (imageDirection == SWT.LEFT) {
          imageDirection = SWT.RIGHT;
        } else if (imageDirection == SWT.RIGHT) {
          imageDirection = SWT.LEFT;
        }
      }
      if (isDefaultCaret && (imageDirection == SWT.RIGHT)) {
        location.x -= caret.getSize().x - 1;
      }
      if (isDefaultCaret) {
        caret.setBounds(location.x, location.y, caretWidth, caretHeight);
      } else {
        caret.setLocation(location);
      }
      getAccessible().textCaretMoved(getCaretOffset());
      if (direction != caretDirection) {
        caretDirection = direction;
        if (isDefaultCaret) {
          if (imageDirection == SWT.DEFAULT) {
            defaultCaret.setImage(null);
          } else if (imageDirection == SWT.LEFT) {
            defaultCaret.setImage(leftCaretBitmap);
          } else if (imageDirection == SWT.RIGHT) {
            defaultCaret.setImage(rightCaretBitmap);
          }
        }
        if (caretDirection == SWT.LEFT) {
          BidiUtil.setKeyboardLanguage(KEYBOARD_NON_BIDI);
        } else if (caretDirection == SWT.RIGHT) {
          BidiUtil.setKeyboardLanguage(KEYBOARD_BIDI);
        }
      }
    }
    columnX = location.x;
  }
}
