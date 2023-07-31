class PlaceHold {
  public int SetCancelDownloadOnClose(boolean aCancelDownloadOnClose) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 3, getAddress(), aCancelDownloadOnClose);
  }
}
