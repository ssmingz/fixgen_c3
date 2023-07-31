class PlaceHold {
  void updateText(boolean selected) {
    if (attriStr != null) {
      attriStr.release();
    }
    float[] foreground = parent.foreground;
    if (((foreground == null) && selected) && (OS.VERSION >= 0x1070)) {
      foreground = display.getNSColorRGB(NSColor.alternateSelectedControlTextColor());
    }
    attriStr = parent.createString(text, null, foreground, 0, false, true, true);
    nsItem.setLabel(NSString.string());
  }
}
