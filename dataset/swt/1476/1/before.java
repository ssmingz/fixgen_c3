class PlaceHold {
  public int CreateInstance(int aOuter, int iid, int result) {
    Download_1_8 download = new Download_1_8();
    download.AddRef();
    XPCOM.memmove(result, new int[] {download.getAddress()}, 4);
    return XPCOM.NS_OK;
  }
}
