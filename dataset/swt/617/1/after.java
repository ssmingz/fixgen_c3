class PlaceHold {
  int gtk_clicked(int widget) {
    if ((style & SWT.RADIO) != 0) {
      if ((parent.getStyle() & SWT.NO_RADIO_GROUP) != 0) {
        setSelection(!selected);
      } else {
        selectRadio();
      }
    } else if ((style & SWT.CHECK) != 0) {
      if (grayed) {
        if (OS.gtk_toggle_button_get_active(handle)) {
          OS.gtk_toggle_button_set_inconsistent(handle, true);
        } else {
          OS.gtk_toggle_button_set_inconsistent(handle, false);
        }
      }
    }
    postEvent(Selection);
    return 0;
  }
}
