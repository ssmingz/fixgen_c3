class PlaceHold {
  public int GetCancelDownloadOnClose(boolean[] aCancelDownloadOnClose) {
    return XPCOM.VtblCall(nsIDownload_1_8.LAST_METHOD_ID + 2, getAddress(), aCancelDownloadOnClose);
  }
}
