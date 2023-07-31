class PlaceHold {
  public int CreateInstance(int aOuter, int iid, int result) {
    Download download = new Download();
    download.AddRef();
    XPCOM.memmove(result, new int[] {download.getAddress()}, PTR_SIZEOF);
    return XPCOM.NS_OK;
  }
}
