class PlaceHold {
  int CreateInstance(long aOuter, long iid, long result) {
    if (MozillaVersion.CheckVersion(VERSION_XR10)) {
      FilePicker_10 picker = new FilePicker_10();
      picker.AddRef();
      XPCOM.memmove(result, new long[] {picker.getAddress()}, PTR_SIZEOF);
    } else if (Mozilla.IsXULRunner) {
      FilePicker_1_8 picker = new FilePicker_1_8();
      picker.AddRef();
      XPCOM.memmove(result, new long[] {picker.getAddress()}, PTR_SIZEOF);
    } else {
      FilePicker picker = new FilePicker();
      picker.AddRef();
      XPCOM.memmove(result, new long[] {picker.getAddress()}, PTR_SIZEOF);
    }
    return XPCOM.NS_OK;
  }
}
