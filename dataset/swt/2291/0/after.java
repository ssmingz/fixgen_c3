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
    } else {
      Control control = findBackgroundControl();
      if (control == null) {
        control = this;
      }
      if (control.backgroundImage == null) {
        setBackgroundPixel(control.getBackgroundPixel());
      }
    }
    updateFullSelection();
  }
}
