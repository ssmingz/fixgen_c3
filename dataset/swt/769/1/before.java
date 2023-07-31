class Browser {
  public Browser(Composite parent, int style) {
    super(parent, style);
    int[] result = new int[1];
    if (!mozilla) {
      String mozillaPath = null;
      int ptr = OS.getenv(Converter.wcsToMbcs(null, MOZILLA_FIVE_HOME, true));
      if (ptr != 0) {
        int length = OS.strlen(ptr);
        byte[] buffer = new byte[length];
        OS.memmove(buffer, ptr, length);
        mozillaPath = new String(Converter.mbcsToWcs(null, buffer));
      }
      if (mozillaPath == null) {
        dispose();
        SWT.error(ERROR_NO_HANDLES, null, " [Unknown Mozilla path (MOZILLA_FIVE_HOME not set)]");
      }
      File file = new File(mozillaPath, "components/libwidget_gtk.so");
      if (file.exists()) {
        dispose();
        SWT.error(ERROR_NO_HANDLES, null, " [Mozilla GTK2 required (GTK1.2 detected)]");
      }
      try {
        Library.loadLibrary("swt-mozilla");
      } catch (UnsatisfiedLinkError e) {
        dispose();
        SWT.error(ERROR_NO_HANDLES, e);
      }
      int[] retVal = new int[1];
      nsEmbedString path = new nsEmbedString(mozillaPath);
      int rc = XPCOM.NS_NewLocalFile(path.getAddress(), true, retVal);
      path.dispose();
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (retVal[0] == 0) {
        error(NS_ERROR_NULL_POINTER);
      }
      nsILocalFile localFile = new nsILocalFile(retVal[0]);
      rc = XPCOM.NS_InitEmbedding(localFile.getAddress(), 0);
      localFile.Release();
      if (rc != XPCOM.NS_OK) {
        dispose();
        SWT.error(
            ERROR_NO_HANDLES,
            null,
            (((" [NS_InitEmbedding " + mozillaPath) + " error ") + rc) + "]");
      }
      rc = XPCOM.NS_GetComponentManager(result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_NOINTERFACE);
      }
      nsIComponentManager componentManager = new nsIComponentManager(result[0]);
      result[0] = 0;
      rc = componentManager.CreateInstance(NS_APPSHELL_CID, 0, NS_IAPPSHELL_IID, result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_NOINTERFACE);
      }
      AppShell = new nsIAppShell(result[0]);
      rc = AppShell.Create(0, null);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      rc = AppShell.Spinup();
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      WindowCreator = new WindowCreator();
      WindowCreator.AddRef();
      rc = XPCOM.NS_GetServiceManager(result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_NOINTERFACE);
      }
      nsIServiceManager serviceManager = new nsIServiceManager(result[0]);
      result[0] = 0;
      byte[] buffer = NS_WINDOWWATCHER_CONTRACTID.getBytes();
      byte[] aContractID = new byte[buffer.length + 1];
      System.arraycopy(buffer, 0, aContractID, 0, buffer.length);
      rc = serviceManager.GetServiceByContractID(aContractID, NS_IWINDOWWATCHER_IID, result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_NOINTERFACE);
      }
      serviceManager.Release();
      nsIWindowWatcher windowWatcher = new nsIWindowWatcher(result[0]);
      result[0] = 0;
      rc = windowWatcher.SetWindowCreator(WindowCreator.getAddress());
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      windowWatcher.Release();
      PromptServiceFactory factory = new PromptServiceFactory();
      factory.AddRef();
      rc = componentManager.QueryInterface(NS_ICOMPONENTREGISTRAR_IID, result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_NOINTERFACE);
      }
      nsIComponentRegistrar componentRegistrar = new nsIComponentRegistrar(result[0]);
      result[0] = 0;
      buffer = NS_PROMPTSERVICE_CONTRACTID.getBytes();
      aContractID = new byte[buffer.length + 1];
      System.arraycopy(buffer, 0, aContractID, 0, buffer.length);
      buffer = "Prompt Service".getBytes();
      byte[] aClassName = new byte[buffer.length + 1];
      System.arraycopy(buffer, 0, aClassName, 0, buffer.length);
      rc =
          componentRegistrar.RegisterFactory(
              NS_PROMPTSERVICE_CID, aClassName, aContractID, factory.getAddress());
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      factory.Release();
      HelperAppLauncherDialogFactory dialogFactory = new HelperAppLauncherDialogFactory();
      dialogFactory.AddRef();
      buffer = NS_HELPERAPPLAUNCHERDIALOG_CONTRACTID.getBytes();
      aContractID = new byte[buffer.length + 1];
      System.arraycopy(buffer, 0, aContractID, 0, buffer.length);
      buffer = "Helper App Launcher Dialog".getBytes();
      aClassName = new byte[buffer.length + 1];
      System.arraycopy(buffer, 0, aClassName, 0, buffer.length);
      rc =
          componentRegistrar.RegisterFactory(
              NS_HELPERAPPLAUNCHERDIALOG_CID, aClassName, aContractID, dialogFactory.getAddress());
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      dialogFactory.Release();
      DownloadFactory downloadFactory = new DownloadFactory();
      downloadFactory.AddRef();
      buffer = NS_DOWNLOAD_CONTRACTID.getBytes();
      aContractID = new byte[buffer.length + 1];
      System.arraycopy(buffer, 0, aContractID, 0, buffer.length);
      buffer = "Download".getBytes();
      aClassName = new byte[buffer.length + 1];
      System.arraycopy(buffer, 0, aClassName, 0, buffer.length);
      rc =
          componentRegistrar.RegisterFactory(
              NS_DOWNLOAD_CID, aClassName, aContractID, downloadFactory.getAddress());
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      downloadFactory.Release();
      componentRegistrar.Release();
      componentManager.Release();
      mozilla = true;
    }
    BrowserCount++;
    int rc = XPCOM.NS_GetComponentManager(result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_NOINTERFACE);
    }
    nsIComponentManager componentManager = new nsIComponentManager(result[0]);
    result[0] = 0;
    nsID NS_IWEBBROWSER_CID = new nsID("F1EAC761-87E9-11d3-AF80-00A024FFC08C");
    rc = componentManager.CreateInstance(NS_IWEBBROWSER_CID, 0, NS_IWEBBROWSER_IID, result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_NOINTERFACE);
    }
    componentManager.Release();
    webBrowser = new nsIWebBrowser(result[0]);
    createCOMInterfaces();
    AddRef();
    rc = webBrowser.SetContainerWindow(webBrowserChrome.getAddress());
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    rc = webBrowser.QueryInterface(NS_IBASEWINDOW_IID, result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_ERROR_NO_INTERFACE);
    }
    nsIBaseWindow baseWindow = new nsIBaseWindow(result[0]);
    Rectangle rect = getClientArea();
    if (rect.isEmpty()) {
      rect.width = 1;
      rect.height = 1;
    }
    embedHandle = OS.gtk_hbox_new(false, 0);
    OS.gtk_container_add(handle, embedHandle);
    OS.gtk_widget_show(embedHandle);
    rc = baseWindow.InitWindow(embedHandle, 0, 0, 0, rect.width, rect.height);
    if (rc != XPCOM.NS_OK) {
      error(NS_ERROR_FAILURE);
    }
    rc = baseWindow.Create();
    if (rc != XPCOM.NS_OK) {
      error(NS_ERROR_FAILURE);
    }
    rc = baseWindow.SetVisibility(true);
    if (rc != XPCOM.NS_OK) {
      error(NS_ERROR_FAILURE);
    }
    baseWindow.Release();
    rc = webBrowser.AddWebBrowserListener(weakReference.getAddress(), NS_IWEBPROGRESSLISTENER_IID);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    rc = webBrowser.SetParentURIContentListener(uriContentListener.getAddress());
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (eventCallback == null) {
      eventCallback = new Callback(Browser.class, "eventProc", 3);
      eventProc = eventCallback.getAddress();
      if (eventProc == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
    }
    int list = OS.gtk_container_get_children(embedHandle);
    if (list != 0) {
      mozillaHandle = OS.g_list_data(list);
      OS.g_list_free(list);
      if (mozillaHandle != 0) {
        getDisplay().setData(ADD_WIDGET_KEY, new Object[] {new Integer(mozillaHandle), this});
        OS.g_signal_connect(mozillaHandle, event, eventProc, 1);
        OS.g_signal_connect(mozillaHandle, key_press_event, eventProc, 2);
      }
    }
    Listener listener =
        new Listener() {
          public void handleEvent(Event event) {
            switch (event.type) {
              case SWT.Dispose:
                onDispose(event.display);
                break;
              case SWT.Resize:
                onResize();
                break;
              case SWT.FocusIn:
                Activate();
                break;
              case SWT.Deactivate:
                {
                  Display display = event.display;
                  if (Browser.this == display.getFocusControl()) {
                    Deactivate();
                  }
                  break;
                }
              case SWT.Show:
                {
                  Display display = event.display;
                  display.asyncExec(
                      new Runnable() {
                        public void run() {
                          if (Browser.this.isDisposed()) {
                            return;
                          }
                          onResize();
                        }
                      });
                  break;
                }
            }
          }
        };
    int[] folderEvents =
        new int[] {SWT.Dispose, SWT.Resize, SWT.FocusIn, SWT.KeyDown, SWT.Deactivate, SWT.Show};
    for (int i = 0; i < folderEvents.length; i++) {
      addListener(folderEvents[i], listener);
    }
  }
}
