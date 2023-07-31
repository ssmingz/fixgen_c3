class PlaceHold {
  public int GetPermissionsOfLink(int[] aPermissionsOfLink) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 18, getAddress(), aPermissionsOfLink);
  }
}
