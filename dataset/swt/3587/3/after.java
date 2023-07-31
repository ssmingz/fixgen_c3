class PlaceHold {
  public int SetCancelDownloadOnClose(boolean aCancelDownloadOnClose) {
    return XPCOM.VtblCall(nsIDownload_1_8.LAST_METHOD_ID + 3, getAddress(), aCancelDownloadOnClose);
  }
}
