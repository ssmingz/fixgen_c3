class PlaceHold {
  public int GetCancelDownloadOnClose(boolean[] aCancelDownloadOnClose) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 2, getAddress(), aCancelDownloadOnClose);
  }
}
