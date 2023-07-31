class PlaceHold {
  void hookCustomListener(final String eventName) {
    if (eventName == "CloseWindowListener") {
      browser.addCloseWindowListener(
          new CloseWindowListener() {
            public void close(WindowEvent event) {
              log(eventName, event);
            }
          });
    }
    if (eventName == "LocationListener") {
      browser.addLocationListener(
          new LocationListener() {
            public void changed(LocationEvent event) {
              log(eventName + ".changed", event);
            }

            public void changing(LocationEvent event) {
              log(eventName + ".changing", event);
            }
          });
    }
    if (eventName == "OpenWindowListener") {
      browser.addOpenWindowListener(
          new OpenWindowListener() {
            public void open(WindowEvent event) {
              log(eventName, event);
            }
          });
    }
    if (eventName == "ProgressListener") {
      browser.addProgressListener(
          new ProgressListener() {
            public void changed(ProgressEvent event) {
              log(eventName + ".changed", event);
            }

            public void completed(ProgressEvent event) {
              log(eventName + ".completed", event);
            }
          });
    }
    if (eventName == "StatusTextListener") {
      browser.addStatusTextListener(
          new StatusTextListener() {
            public void changed(StatusTextEvent event) {
              log(eventName, event);
            }
          });
    }
    if (eventName == "TitleListener") {
      browser.addTitleListener(
          new TitleListener() {
            public void changed(TitleEvent event) {
              log(eventName, event);
            }
          });
    }
    if (eventName == "VisibilityWindowListener") {
      browser.addVisibilityWindowListener(
          new VisibilityWindowListener() {
            public void hide(WindowEvent event) {
              log(eventName + ".hide", event);
            }

            public void show(WindowEvent event) {
              log(eventName + ".show", event);
            }
          });
    }
  }
}
