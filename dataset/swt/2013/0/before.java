class PlaceHold {
  LRESULT wmCommandChild(int wParam, int lParam) {
    int code = OS.HIWORD(wParam);
    switch (code) {
      case OS.BN_CLICKED:
      case OS.BN_DOUBLECLICKED:
        if ((style & (SWT.CHECK | SWT.TOGGLE)) != 0) {
          setSelection(!getSelection());
        } else if ((style & SWT.RADIO) != 0) {
          if ((parent.getStyle() & SWT.NO_RADIO_GROUP) != 0) {
            setSelection(!getSelection());
          } else {
            selectRadio();
          }
        }
        postEvent(Selection);
    }
    return super.wmCommandChild(wParam, lParam);
  }
}
