class PlaceHold {
  int OnProgressChange(
      int aWebProgress,
      int aRequest,
      int aCurSelfProgress,
      int aMaxSelfProgress,
      int aCurTotalProgress,
      int aMaxTotalProgress) {
    if (progressListeners.length == 0) {
      return XPCOM.NS_OK;
    }
    ProgressEvent event = new ProgressEvent(browser);
    event.display = browser.getDisplay();
    event.widget = browser;
    event.current = aCurTotalProgress;
    event.total = aMaxTotalProgress;
    for (int i = 0; i < progressListeners.length; i++) {
      progressListeners[i].changed(event);
    }
    return XPCOM.NS_OK;
  }
}
