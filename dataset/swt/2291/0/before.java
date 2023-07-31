class PlaceHold {
  void setBackgroundImage(int hBitmap) {
    super.setBackgroundImage(hBitmap);
    if (hBitmap != 0) {
      if (OS.COMCTL32_MAJOR < 6) {
        if (OS.SendMessage(handle, TVM_GETBKCOLOR, 0, 0) == (-1)) {
          OS.SendMessage(handle, TVM_SETBKCOLOR, 0, -1);
        }
      }
      OS.SendMessage(handle, TVM_SETBKCOLOR, 0, -1);
      if ((style & SWT.FULL_SELECTION) != 0) {
        int bits = OS.GetWindowLong(handle, GWL_STYLE);
        if ((bits & OS.TVS_FULLROWSELECT) != 0) {
          bits &= ~OS.TVS_FULLROWSELECT;
          OS.SetWindowLong(handle, GWL_STYLE, bits);
          OS.InvalidateRect(handle, null, true);
        }
      }
    } else {
      Control control = findBackgroundControl();
      if (control == null) {
        control = this;
      }
      if (control.backgroundImage == null) {
        setBackgroundPixel(control.getBackgroundPixel());
      }
    }
  }
}
