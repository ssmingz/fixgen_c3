class PlaceHold {
  public int CreateInstance(int aOuter, int iid, int result) {
    HelperAppLauncherDialog helperAppLauncherDialog = new HelperAppLauncherDialog();
    helperAppLauncherDialog.AddRef();
    XPCOM.memmove(result, new int[] {helperAppLauncherDialog.getAddress()}, PTR_SIZEOF);
    return XPCOM.NS_OK;
  }
}
