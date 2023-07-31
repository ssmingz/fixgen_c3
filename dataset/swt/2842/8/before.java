class PlaceHold {
  void enableWidget(boolean enabled) {
    super.enableWidget(enabled);
    TextStyle linkStyle = new TextStyle(null, enabled ? linkColor : linkDisabledColor, null);
    linkStyle.underline = true;
    for (int i = 0; i < offsets.length; i++) {
      Point point = offsets[i];
      layout.setStyle(linkStyle, point.x, point.y);
    }
    redraw();
  }
}
