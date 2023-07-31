class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (string.equals(text)) {
      return;
    }
    text = string;
    layout.setText(parse(string));
    focusIndex = (offsets.length > 0) ? 0 : -1;
    selection.x = selection.y = -1;
    boolean enabled = (state & DISABLED) == 0;
    TextStyle linkStyle = new TextStyle(null, enabled ? linkColor : linkDisabledColor, null);
    linkStyle.underline = true;
    for (int i = 0; i < offsets.length; i++) {
      Point point = offsets[i];
      layout.setStyle(linkStyle, point.x, point.y);
    }
    TextStyle mnemonicStyle = new TextStyle(null, null, null);
    mnemonicStyle.underline = true;
    for (int i = 0; i < mnemonics.length; i++) {
      int mnemonic = mnemonics[i];
      if (mnemonic != (-1)) {
        layout.setStyle(mnemonicStyle, mnemonic, mnemonic);
      }
    }
    redraw();
  }
}
