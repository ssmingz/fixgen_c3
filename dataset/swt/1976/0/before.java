class PlaceHold {
  void webView_didFinishLoadForFrame(int sender, int frameID) {
    if (frameID == webView.mainFrame().id) {
      final Display display = browser.getDisplay();
      WebFrame frame = new WebFrame(frameID);
      WebDataSource dataSource = frame.dataSource();
      if (dataSource != null) {
        NSString title = dataSource.pageTitle();
        if (title == null) {
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
