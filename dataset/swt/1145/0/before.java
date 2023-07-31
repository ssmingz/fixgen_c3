class PlaceHold {
  void didFinishLoadForFrame(int frame) {
    hookDOMFocusListeners(frame);
    hookDOMMouseListeners(frame);
    if (frame == Cocoa.objc_msgSend(webView, S_mainFrame)) {
      hookDOMKeyListeners(frame);
      final Display display = browser.getDisplay();
      int dataSource = Cocoa.objc_msgSend(frame, S_dataSource);
      if (dataSource != 0) {
        int title = Cocoa.objc_msgSend(dataSource, S_pageTitle);
        if (title == 0) {
          final TitleEvent newEvent = new TitleEvent(browser);
          newEvent.display = display;
          newEvent.widget = browser;
          newEvent.title = url;
          for (int i = 0; i < titleListeners.length; i++) {
            final TitleListener listener = titleListeners[i];
            display.asyncExec(
                new Runnable() {
                  public void run() {
                    if ((!display.isDisposed()) && (!browser.isDisposed())) {
                      listener.changed(newEvent);
                    }
                  }
                });
          }
        }
      }
      final ProgressEvent progress = new ProgressEvent(browser);
      progress.display = display;
      progress.widget = browser;
      progress.current = MAX_PROGRESS;
      progress.total = MAX_PROGRESS;
      for (int i = 0; i < progressListeners.length; i++) {
        final ProgressListener listener = progressListeners[i];
        display.asyncExec(
            new Runnable() {
              public void run() {
                if ((!display.isDisposed()) && (!browser.isDisposed())) {
                  listener.completed(progress);
                }
              }
            });
      }
      identifier = 0;
    }
  }
}
