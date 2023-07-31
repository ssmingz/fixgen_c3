class PlaceHold {
  public int SetPermissionsOfLink(int aPermissionsOfLink) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 19, getAddress(), aPermissionsOfLink);
  }
}
