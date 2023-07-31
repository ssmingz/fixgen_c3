class PlaceHold {
  void setBackgroundPixel(int pixel) {
    Control control = findImageControl();
    if (control != null) {
      setBackgroundImage(control.backgroundImage);
      return;
    }
    if ((style & SWT.FULL_SELECTION) != 0) {
      int bits = OS.GetWindowLong(handle, GWL_STYLE);
      if ((bits & OS.TVS_FULLROWSELECT) == 0) {
        bits |= OS.TVS_FULLROWSELECT;
        OS.SetWindowLong(handle, GWL_STYLE, bits);
        OS.InvalidateRect(handle, null, true);
      }
    }
    if (OS.IsWindowEnabled(handle)) {
      _setBackgroundPixel(pixel);
    }
  }
}
