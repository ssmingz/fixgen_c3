class PlaceHold {
  void arrowEvent(Event event) {
    switch (event.type) {
      case SWT.FocusIn:
        {
          if (hasFocus) {
            return;
          }
          hasFocus = true;
          if (getEditable()) {
            text.selectAll();
          }
          Event e = new Event();
          e.time = event.time;
          notifyListeners(FocusIn, e);
          break;
        }
      case SWT.FocusOut:
        {
          event.display.asyncExec(
              new Runnable() {
                public void run() {
                  if (CCombo.this.isDisposed()) {
                    return;
                  }
                  Control focusControl = getDisplay().getFocusControl();
                  if ((focusControl == list) || (focusControl == text)) {
                    return;
                  }
                  hasFocus = false;
                  Event e = new Event();
                  notifyListeners(FocusOut, e);
                }
              });
          break;
        }
      case SWT.Selection:
        {
          dropDown(!isDropped());
          break;
        }
    }
  }
}
