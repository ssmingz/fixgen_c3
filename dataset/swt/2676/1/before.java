class PlaceHold {
  int widgetStyle() {
    int bits = (super.widgetStyle() | OS.CCS_NODIVIDER) | OS.CCS_NORESIZE;
    bits |= (OS.RBS_VARHEIGHT | OS.RBS_BANDBORDERS) | OS.RBS_DBLCLKTOGGLE;
    return bits;
  }
}
