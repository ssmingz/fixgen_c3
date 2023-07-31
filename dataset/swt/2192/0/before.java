class PlaceHold {
  int Pt_CB_WEB_COMPLETE(int info) {
    Display display = getDisplay();
    LocationEvent event = new LocationEvent(this);
    event.display = display;
    event.widget = this;
    event.location = url;
    for (int i = 0; i < locationListeners.length; i++) {
      locationListeners[i].changed(event);
    }
    ProgressEvent progress = new ProgressEvent(this);
    progress.display = display;
    progress.widget = this;
    progress.current = totalProgress;
    progress.total = totalProgress;
    for (int i = 0; i < progressListeners.length; i++) {
      progressListeners[i].completed(progress);
    }
    StatusTextEvent statusevent = new StatusTextEvent(this);
    statusevent.display = display;
    statusevent.widget = this;
    statusevent.text = "";
    for (int i = 0; i < statusTextListeners.length; i++) {
      statusTextListeners[i].changed(statusevent);
    }
    return OS.Pt_CONTINUE;
  }
}
