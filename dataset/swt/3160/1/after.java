class PlaceHold {
  void arrowEvent(Event event) {
    switch (event.type) {
      case SWT.FocusIn:
        {
          handleFocus(FocusIn);
          break;
        }
      case SWT.MouseDown:
        {
          Event mouseEvent = new Event();
          mouseEvent.button = event.button;
          mouseEvent.count = event.count;
          mouseEvent.stateMask = event.stateMask;
          mouseEvent.time = event.time;
          mouseEvent.x = event.x;
          mouseEvent.y = event.y;
          notifyListeners(MouseDown, mouseEvent);
          event.doit = mouseEvent.doit;
          break;
        }
      case SWT.MouseUp:
        {
          Event mouseEvent = new Event();
          mouseEvent.button = event.button;
          mouseEvent.count = event.count;
          mouseEvent.stateMask = event.stateMask;
          mouseEvent.time = event.time;
          mouseEvent.x = event.x;
          mouseEvent.y = event.y;
          notifyListeners(MouseUp, mouseEvent);
          event.doit = mouseEvent.doit;
          break;
        }
      case SWT.Selection:
        {
          text.setFocus();
          dropDown(!isDropped());
          break;
        }
    }
  }
}
