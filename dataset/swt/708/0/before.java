class PlaceHold {
  int Init(
      int aSource,
      int aTarget,
      int aDisplayName,
      int aMIMEInfo,
      int startTime1,
      int startTime2,
      int aPersist) {
    nsIURI source = new nsIURI(aSource);
    int aSpec = XPCOM.nsEmbedCString_new();
    int rc = source.GetHost(aSpec);
    if (rc != XPCOM.NS_OK) {
      Mozilla.error(rc);
    }
    int length = XPCOM.nsEmbedCString_Length(aSpec);
    int buffer = XPCOM.nsEmbedCString_get(aSpec);
    byte[] dest = new byte[length];
    XPCOM.memmove(dest, buffer, length);
    XPCOM.nsEmbedCString_delete(aSpec);
    String url = new String(dest);
    String filename = null;
    nsISupports supports = new nsISupports(aTarget);
    int[] result = new int[1];
    rc = supports.QueryInterface(NS_IURI_IID, result);
    if (rc == 0) {
      nsIURI target = new nsIURI(result[0]);
      result[0] = 0;
      int aPath = XPCOM.nsEmbedCString_new();
      rc = target.GetPath(aPath);
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      length = XPCOM.nsEmbedCString_Length(aPath);
      buffer = XPCOM.nsEmbedCString_get(aPath);
      dest = new byte[length];
      XPCOM.memmove(dest, buffer, length);
      XPCOM.nsEmbedCString_delete(aPath);
      filename = new String(dest);
      int separator = filename.lastIndexOf(System.getProperty("file.separator"));
      filename = filename.substring(separator + 1);
      target.Release();
    } else {
      nsILocalFile target = new nsILocalFile(aTarget);
      int aNativeTarget = XPCOM.nsEmbedCString_new();
      rc = target.GetNativeLeafName(aNativeTarget);
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      length = XPCOM.nsEmbedCString_Length(aNativeTarget);
      buffer = XPCOM.nsEmbedCString_get(aNativeTarget);
      dest = new byte[length];
      XPCOM.memmove(dest, buffer, length);
      XPCOM.nsEmbedCString_delete(aNativeTarget);
      filename = new String(dest);
    }
    Listener listener =
        new Listener() {
          public void handleEvent(Event event) {
            if (event.widget == cancel) {
              shell.close();
            }
            if (helperAppLauncher != null) {
              helperAppLauncher.Cancel();
              helperAppLauncher.Release();
            }
            shell = null;
            helperAppLauncher = null;
          }
        };
    shell = new Shell(SWT.DIALOG_TRIM);
    String msg = Compatibility.getMessage("SWT_Download_File", new Object[] {filename});
    shell.setText(msg);
    GridLayout gridLayout = new GridLayout();
    gridLayout.marginHeight = 15;
    gridLayout.marginWidth = 15;
    gridLayout.verticalSpacing = 20;
    shell.setLayout(gridLayout);
    msg = Compatibility.getMessage("SWT_Download_Location", new Object[] {filename, url});
    new Label(shell, SWT.SIMPLE).setText(msg);
    status = new Label(shell, SWT.SIMPLE);
    msg = Compatibility.getMessage("SWT_Download_Started");
    status.setText(msg);
    GridData data = new GridData();
    data.grabExcessHorizontalSpace = true;
    data.grabExcessVerticalSpace = true;
    status.setLayoutData(data);
    cancel = new Button(shell, SWT.PUSH);
    cancel.setText(SWT.getMessage("SWT_Cancel"));
    data = new GridData();
    data.horizontalAlignment = GridData.CENTER;
    cancel.setLayoutData(data);
    cancel.addListener(Selection, listener);
    shell.addListener(Close, listener);
    shell.pack();
    shell.open();
    return XPCOM.NS_OK;
  }
}
