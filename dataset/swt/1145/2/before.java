class PlaceHold {
  int OnStateChange(int aWebProgress, int aRequest, int aStateFlags, int aStatus) {
    if ((aStateFlags & nsIWebProgressListener.STATE_IS_DOCUMENT) == 0) {
      return XPCOM.NS_OK;
    }
    if ((aStateFlags & nsIWebProgressListener.STATE_START) != 0) {
      if (request == 0) {
        request = aRequest;
      }
      if (!awaitingNavigate) {
        int[] result = new int[1];
        nsIWebProgress progress = new nsIWebProgress(aWebProgress);
        int rc = progress.GetDOMWindow(result);
        if (rc != XPCOM.NS_OK) {
          error(rc);
        }
        if (result[0] == 0) {
          error(NS_NOINTERFACE);
        }
        unhookedDOMWindows.addElement(new LONG(result[0]));
      }
    } else if ((aStateFlags & nsIWebProgressListener.STATE_REDIRECTING) != 0) {
      if (request == aRequest) {
        request = 0;
      }
    } else if ((aStateFlags & nsIWebProgressListener.STATE_STOP) != 0) {
      int[] result = new int[1];
      nsIWebProgress progress = new nsIWebProgress(aWebProgress);
      int rc = progress.GetDOMWindow(result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_NOINTERFACE);
      }
      nsIDOMWindow domWindow = new nsIDOMWindow(result[0]);
      LONG ptrObject = new LONG(result[0]);
      result[0] = 0;
      int index = unhookedDOMWindows.indexOf(ptrObject);
      if (index != (-1)) {
        rc = webBrowser.GetContentDOMWindow(result);
        if (rc != XPCOM.NS_OK) {
          error(rc);
        }
        if (result[0] == 0) {
          error(NS_ERROR_NO_INTERFACE);
        }
        boolean isTop = result[0] == domWindow.getAddress();
        new nsISupports(result[0]).Release();
        result[0] = 0;
        rc = domWindow.QueryInterface(NS_IDOMEVENTTARGET_IID, result);
        if (rc != XPCOM.NS_OK) {
          error(rc);
        }
        if (result[0] == 0) {
          error(NS_ERROR_NO_INTERFACE);
        }
        nsIDOMEventTarget target = new nsIDOMEventTarget(result[0]);
        result[0] = 0;
        hookDOMListeners(target, isTop);
        target.Release();
        unhookedDOMWindows.remove(ptrObject);
        new nsISupports(ptrObject.value).Release();
      }
      domWindow.Release();
      if ((request == aRequest) || (request == 0)) {
        request = 0;
        if (!awaitingNavigate) {
          StatusTextEvent event = new StatusTextEvent(browser);
          event.display = browser.getDisplay();
          event.widget = browser;
          event.text = "";
          for (int i = 0; i < statusTextListeners.length; i++) {
            statusTextListeners[i].changed(event);
          }
          ProgressEvent event2 = new ProgressEvent(browser);
          event2.display = browser.getDisplay();
          event2.widget = browser;
          for (int i = 0; i < progressListeners.length; i++) {
            progressListeners[i].completed(event2);
          }
        }
      }
    } else if ((aStateFlags & nsIWebProgressListener.STATE_TRANSFERRING) != 0) {
      int[] result = new int[1];
      nsIWebProgress progress = new nsIWebProgress(aWebProgress);
      int rc = progress.GetDOMWindow(result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_NOINTERFACE);
      }
      nsIDOMWindow domWindow = new nsIDOMWindow(result[0]);
      LONG ptrObject = new LONG(result[0]);
      result[0] = 0;
      int index = unhookedDOMWindows.indexOf(ptrObject);
      if (index != (-1)) {
        rc = webBrowser.GetContentDOMWindow(result);
        if (rc != XPCOM.NS_OK) {
          error(rc);
        }
        if (result[0] == 0) {
          error(NS_ERROR_NO_INTERFACE);
        }
        boolean isTop = result[0] == domWindow.getAddress();
        new nsISupports(result[0]).Release();
        result[0] = 0;
        rc = domWindow.QueryInterface(NS_IDOMEVENTTARGET_IID, result);
        if (rc != XPCOM.NS_OK) {
          error(rc);
        }
        if (result[0] == 0) {
          error(NS_ERROR_NO_INTERFACE);
        }
        nsIDOMEventTarget target = new nsIDOMEventTarget(result[0]);
        result[0] = 0;
        hookDOMListeners(target, isTop);
        target.Release();
        unhookedDOMWindows.remove(ptrObject);
        new nsISupports(ptrObject.value).Release();
      }
      domWindow.Release();
    }
    return XPCOM.NS_OK;
  }
}
