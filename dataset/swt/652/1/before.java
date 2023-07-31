class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    super.setText(string);
    NSButton widget = ((NSButton) (button));
    if (parent.nsToolbar != null) {
      char[] chars = new char[text.length()];
      text.getChars(0, chars.length, chars, 0);
      int length = fixMnemonic(chars);
      nsMenuRep.setTitle(NSString.stringWithCharacters(chars, length));
    }
    widget.setAttributedTitle(createString());
    if ((text.length() != 0) && (image != null)) {
      if ((parent.style & SWT.RIGHT) != 0) {
        widget.setImagePosition(NSImageLeft);
      } else {
        widget.setImagePosition(NSImageAbove);
      }
    } else {
      widget.setImagePosition(text.length() != 0 ? OS.NSNoImage : OS.NSImageOnly);
    }
    parent.relayout();
  }
}
