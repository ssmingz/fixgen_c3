class PlaceHold {
  int GetSiteWindow(int aSiteWindow) {
    XPCOM.memmove(aSiteWindow, new int[] {handle}, 4);
    return XPCOM.NS_OK;
  }
}
