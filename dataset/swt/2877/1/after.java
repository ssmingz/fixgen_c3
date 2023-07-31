class PlaceHold {
  void setBackgroundTransparent(boolean transparent) {
    int oldPixel = ((int) (OS.SendMessage(handle, LVM_GETBKCOLOR, 0, 0)));
    if (transparent) {
      if (oldPixel != OS.CLR_NONE) {
        if (OS.WIN32_VERSION < OS.VERSION(6, 3)) {
          OS.SendMessage(handle, LVM_SETBKCOLOR, 0, CLR_NONE);
        }
        OS.SendMessage(handle, LVM_SETTEXTBKCOLOR, 0, CLR_NONE);
        OS.InvalidateRect(handle, null, true);
        if ((!explorerTheme) && ((style & SWT.FULL_SELECTION) != 0)) {
          int bits = OS.LVS_EX_FULLROWSELECT;
          OS.SendMessage(handle, LVM_SETEXTENDEDLISTVIEWSTYLE, bits, 0);
        }
        if ((sortDirection & (SWT.UP | SWT.DOWN)) != 0) {
          if ((sortColumn != null) && (!sortColumn.isDisposed())) {
            OS.SendMessage(handle, LVM_SETSELECTEDCOLUMN, -1, 0);
            OS.InvalidateRect(handle, null, true);
          }
        }
      }
    } else if (oldPixel == OS.CLR_NONE) {
      Control control = findBackgroundControl();
      if (control == null) {
        control = this;
      }
      if (control.backgroundImage == null) {
        int newPixel = control.getBackgroundPixel();
        OS.SendMessage(handle, LVM_SETBKCOLOR, 0, newPixel);
        OS.SendMessage(handle, LVM_SETTEXTBKCOLOR, 0, newPixel);
        if ((style & SWT.CHECK) != 0) {
          fixCheckboxImageListColor(true);
        }
        OS.InvalidateRect(handle, null, true);
      }
      if ((!explorerTheme) && ((style & SWT.FULL_SELECTION) != 0)) {
        if ((!hooks(EraseItem)) && (!hooks(PaintItem))) {
          int bits = OS.LVS_EX_FULLROWSELECT;
          OS.SendMessage(handle, LVM_SETEXTENDEDLISTVIEWSTYLE, bits, bits);
        }
      }
      if ((sortDirection & (SWT.UP | SWT.DOWN)) != 0) {
        if ((sortColumn != null) && (!sortColumn.isDisposed())) {
          int column = indexOf(sortColumn);
          if (column != (-1)) {
            OS.SendMessage(handle, LVM_SETSELECTEDCOLUMN, column, 0);
            OS.InvalidateRect(handle, null, true);
          }
        }
      }
    }
  }
}
