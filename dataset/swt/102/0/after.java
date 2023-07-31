class PlaceHold {
  int widgetStyle() {
    int bits = super.widgetStyle();
    if ((style & SWT.FLAT) != 0) {
      bits |= OS.BS_FLAT;
    }
    if ((style & SWT.ARROW) != 0) {
      return bits | OS.BS_OWNERDRAW;
    }
    if ((style & SWT.LEFT) != 0) {
      bits |= OS.BS_LEFT;
    }
    if ((style & SWT.CENTER) != 0) {
      bits |= OS.BS_CENTER;
    }
    if ((style & SWT.RIGHT) != 0) {
      bits |= OS.BS_RIGHT;
    }
    if ((style & SWT.WRAP) != 0) {
      bits |= OS.BS_MULTILINE;
    }
    if ((style & SWT.PUSH) != 0) {
      return (bits | OS.BS_PUSHBUTTON) | OS.WS_TABSTOP;
    }
    if ((style & SWT.CHECK) != 0) {
      return (bits | OS.BS_CHECKBOX) | OS.WS_TABSTOP;
    }
    if ((style & SWT.RADIO) != 0) {
      return bits | OS.BS_RADIOBUTTON;
    }
    if ((style & SWT.TOGGLE) != 0) {
      return ((bits | OS.BS_PUSHLIKE) | OS.BS_CHECKBOX) | OS.WS_TABSTOP;
    }
    if ((style & SWT.COMMAND) != 0) {
      return (bits | OS.BS_COMMANDLINK) | OS.WS_TABSTOP;
    }
    return (bits | OS.BS_PUSHBUTTON) | OS.WS_TABSTOP;
  }
}
