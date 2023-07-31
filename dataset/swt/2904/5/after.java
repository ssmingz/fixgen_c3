class PlaceHold {
  public void setGrayed(boolean grayed) {
    checkWidget();
    if ((style & SWT.CHECK) == 0) {
      return;
    }
    this.grayed = grayed;
    long flags = OS.SendMessage(handle, BM_GETCHECK, 0, 0);
    if (grayed) {
      if (flags == OS.BST_CHECKED) {
        updateSelection(BST_INDETERMINATE);
      }
    } else if (flags == OS.BST_INDETERMINATE) {
      updateSelection(BST_CHECKED);
    }
  }
}
