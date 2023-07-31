class PlaceHold {
  int CreateChromeWindow2(
      int parent, int chromeFlags, int contextFlags, int uri, int cancel, int _retval) {
    if ((parent == 0) && ((chromeFlags & nsIWebBrowserChrome.CHROME_MODAL) == 0)) {
      return XPCOM.NS_ERROR_NOT_IMPLEMENTED;
    }
    Browser src = null;
    if (parent != 0) {
      nsIWebBrowserChrome browserChromeParent = new nsIWebBrowserChrome(parent);
      int[] aWebBrowser = new int[1];
      int rc = browserChromeParent.GetWebBrowser(aWebBrowser);
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      if (aWebBrowser[0] == 0) {
        Mozilla.error(NS_ERROR_NO_INTERFACE);
      }
      nsIWebBrowser webBrowser = new nsIWebBrowser(aWebBrowser[0]);
      int[] result = new int[1];
      rc = webBrowser.QueryInterface(NS_IBASEWINDOW_IID, result);
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      if (result[0] == 0) {
        Mozilla.error(NS_ERROR_NO_INTERFACE);
      }
      webBrowser.Release();
      nsIBaseWindow baseWindow = new nsIBaseWindow(result[0]);
      result[0] = 0;
      int[] aParentNativeWindow = new int[1];
      rc = baseWindow.GetParentNativeWindow(aParentNativeWindow);
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      if (aParentNativeWindow[0] == 0) {
        Mozilla.error(NS_ERROR_NO_INTERFACE);
      }
      baseWindow.Release();
      src = Mozilla.findBrowser(aParentNativeWindow[0]);
    }
    final Browser browser;
    boolean doit = true;
    if ((chromeFlags & nsIWebBrowserChrome.CHROME_MODAL) != 0) {
      final Shell shell =
          (src == null)
              ? new Shell(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL)
              : new Shell(src.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setLayout(new FillLayout());
      browser = new Browser(shell, src == null ? SWT.MOZILLA : src.getStyle() & SWT.MOZILLA);
      browser.addVisibilityWindowListener(
          new VisibilityWindowListener() {
            public void hide(WindowEvent event) {}

            public void show(WindowEvent event) {
              if (event.location != null) {
                shell.setLocation(event.location);
              }
              if (event.size != null) {
                Point size = event.size;
                shell.setSize(shell.computeSize(size.x, size.y));
              }
              shell.open();
            }
          });
      browser.addCloseWindowListener(
          new CloseWindowListener() {
            public void close(WindowEvent event) {
              shell.close();
            }
          });
    } else {
      WindowEvent event = new WindowEvent(src);
      event.display = src.getDisplay();
      event.widget = src;
      event.required = true;
      for (int i = 0; i < src.webBrowser.openWindowListeners.length; i++) {
        src.webBrowser.openWindowListeners[i].open(event);
      }
      browser = event.browser;
      doit = (browser != null) && (!browser.isDisposed());
    }
    if (doit) {
      int chromePtr = ((Mozilla) (browser.webBrowser)).webBrowserChrome.getAddress();
      nsIWebBrowserChrome webBrowserChrome = new nsIWebBrowserChrome(chromePtr);
      webBrowserChrome.SetChromeFlags(chromeFlags);
      webBrowserChrome.AddRef();
      XPCOM.memmove(_retval, new int[] {chromePtr}, PTR_SIZEOF);
      if (uri != 0) {
        nsIURI location = new nsIURI(uri);
        int aSpec = XPCOM.nsEmbedCString_new();
        if (location.GetSpec(aSpec) == XPCOM.NS_OK) {
          int length = XPCOM.nsEmbedCString_Length(aSpec);
          if (length > 0) {
            int buffer = XPCOM.nsEmbedCString_get(aSpec);
            byte[] dest = new byte[length];
            XPCOM.memmove(dest, buffer, length);
            browser.setUrl(new String(dest));
          }
        }
        XPCOM.nsEmbedCString_delete(aSpec);
      }
    } else if (cancel != 0) {
      C.memmove(cancel, new int[] {1}, 4);
    }
    return doit ? XPCOM.NS_OK : XPCOM.NS_ERROR_NOT_IMPLEMENTED;
  }
}
