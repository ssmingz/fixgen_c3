class PlaceHold {
  int gtk_clicked(int widget) {
    if ((style & SWT.RADIO) != 0) {
      if ((parent.getStyle() & SWT.NO_RADIO_GROUP) != 0) {
        setSelection(!selected);
      } else {
        selectRadio();
      }
    }
    postEvent(Selection);
    return 0;
  }
}
