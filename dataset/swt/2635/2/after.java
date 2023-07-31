class PlaceHold {
  void createDropDownButton() {
    down = new Button(this, SWT.ARROW | SWT.DOWN);
    gtk_widget_set_can_focus(down.handle, false);
    down.addListener(
        Selection,
        new Listener() {
          public void handleEvent(Event event) {
            boolean dropped = isDropped();
            popupCalendar.calendarDisplayed = !dropped;
            setFocus();
            dropDownCalendar(!dropped);
          }
        });
    popupListener =
        new Listener() {
          public void handleEvent(Event event) {
            if (event.widget == popupShell) {
              popupShellEvent(event);
              return;
            }
            if (event.widget == popupCalendar) {
              popupCalendarEvent(event);
              return;
            }
            if (event.widget == DateTime.this) {
              onDispose(event);
              return;
            }
            if (event.widget == getShell()) {
              getDisplay()
                  .asyncExec(
                      new Runnable() {
                        public void run() {
                          if (isDisposed()) {
                            return;
                          }
                          handleFocus(FocusOut);
                        }
                      });
            }
          }
        };
    popupFilter =
        new Listener() {
          public void handleEvent(Event event) {
            Shell shell = ((Control) (event.widget)).getShell();
            if (shell == DateTime.this.getShell()) {
              handleFocus(FocusOut);
            }
          }
        };
  }
}
