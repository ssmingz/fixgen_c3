class PlaceHold {
  public void fillRectangle(int x, int y, int width, int height) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int rop2 = 0;
    if (OS.IsWinCE) {
      rop2 = OS.SetROP2(handle, R2_COPYPEN);
      OS.SetROP2(handle, rop2);
    } else {
      rop2 = OS.GetROP2(handle);
    }
    int dwRop = (rop2 == OS.R2_XORPEN) ? OS.PATINVERT : OS.PATCOPY;
    OS.PatBlt(handle, x, y, width, height, dwRop);
  }
}
