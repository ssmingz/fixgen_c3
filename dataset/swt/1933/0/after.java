class PlaceHold {
  int GetSiteWindow(int aSiteWindow) {
    XPCOM.memmove(aSiteWindow, new int[] {gtkHandle}, 4);
    return XPCOM.NS_OK;
  }
}
