class PlaceHold {
  int Pt_CB_WEB_NEW_WINDOW(int info) {
    PtCallbackInfo_t cbinfo_t = new PtCallbackInfo_t();
    OS.memmove(cbinfo_t, info, sizeof);
    final PtWebWindowCallback_t webwin_t = new PtWebWindowCallback_t();
    OS.memmove(webwin_t, cbinfo_t.cbdata, sizeof);
    final Browser hidden = new Browser(getParent(), SWT.NONE);
    hidden.addLocationListener(
        new LocationListener() {
          public void changed(LocationEvent event) {
            if (event.location.length() == 0) {
              hidden.refresh();
              return;
            }
            hidden.dispose();
          }

          public void changing(final LocationEvent event) {
            Browser redirect = hidden.browser;
            if ((redirect != null) && (!redirect.isDisposed())) {
              WindowEvent newEvent = new WindowEvent(redirect);
              newEvent.display = getDisplay();
              newEvent.widget = redirect;
              newEvent.location = null;
              newEvent.size =
                  ((webwin_t.size_w == 0) && (webwin_t.size_h == 0))
                      ? null
                      : new Point(webwin_t.size_w, webwin_t.size_h);
              for (int i = 0; i < redirect.visibilityWindowListeners.length; i++) {
                redirect.visibilityWindowListeners[i].show(newEvent);
              }
              redirect.setUrl(event.location);
            }
          }
        });
    WindowEvent event = new WindowEvent(this);
    event.display = getDisplay();
    event.widget = this;
    for (int i = 0; i < openWindowListeners.length; i++) {
      openWindowListeners[i].open(event);
    }
    if ((event.browser != null) && (!event.browser.isDisposed())) {
      hidden.browser = event.browser;
    }
    return OS.Pt_CONTINUE;
  }
}
