class PlaceHold {
  int widgetStyle() {
    int bits = (super.widgetStyle() | OS.CCS_NODIVIDER) | OS.CCS_NORESIZE;
    bits |= OS.RBS_VARHEIGHT | OS.RBS_DBLCLKTOGGLE;
    if ((style & SWT.FLAT) == 0) {
      bits |= OS.RBS_BANDBORDERS;
    }
    return bits;
  }
}
