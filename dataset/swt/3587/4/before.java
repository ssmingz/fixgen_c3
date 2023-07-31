class PlaceHold {
  public int GetCancelDownloadOnClose(boolean[] aCancelDownloadOnClose) {
    return XPCOM.VtblCall(nsIDownload.LAST_METHOD_ID + 2, getAddress(), aCancelDownloadOnClose);
  }
}
