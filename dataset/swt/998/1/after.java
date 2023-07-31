class PlaceHold {
  WebBrowser createWebBrowser(int style) {
    if (OS.IsWinCE && ((style & (SWT.MOZILLA | SWT.WEBKIT)) != 0)) {
      throw new SWTError(SWT.ERROR_NO_HANDLES, "Unsupported Browser type");
    }
    if ((style & SWT.MOZILLA) != 0) {
      mozillaLibsLoaded = true;
      return new Mozilla();
    }
    if ((style & SWT.WEBKIT) != 0) {
      if (!mozillaLibsLoaded) {
        mozillaLibsLoaded = true;
        Mozilla.LoadLibraries();
      }
      return new WebKit();
    }
    return new IE();
  }
}
