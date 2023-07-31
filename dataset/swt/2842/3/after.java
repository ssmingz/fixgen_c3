class PlaceHold {
  void enableWidget(boolean enabled) {
    if (OS.COMCTL32_MAJOR >= 6) {
      LITEM item = new LITEM();
      item.mask = OS.LIF_ITEMINDEX | OS.LIF_STATE;
      item.stateMask = OS.LIS_ENABLED;
      item.state = (enabled) ? OS.LIS_ENABLED : 0;
      while (OS.SendMessage(handle, LM_SETITEM, 0, item) != 0) {
        item.iLink++;
      }
    } else {
      TextStyle linkStyle = new TextStyle(null, enabled ? linkColor : disabledColor, null);
      linkStyle.underline = true;
      for (int i = 0; i < offsets.length; i++) {
        Point point = offsets[i];
        layout.setStyle(linkStyle, point.x, point.y);
      }
      redraw();
    }
    super.enableWidget(enabled);
  }
}
