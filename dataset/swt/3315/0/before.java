class PlaceHold {
  public void setSelection(boolean selected) {
    checkWidget();
    if ((style & (SWT.CHECK | SWT.RADIO)) == 0) {
      return;
    }
    int inMark = (selected) ? (style & SWT.RADIO) != 0 ? OS.diamondMark : OS.checkMark : 0;
    if (OS.SetMenuCommandMark(parent.handle, id, ((char) (inMark))) != OS.kNoErr) {
      error(ERROR_CANNOT_SET_SELECTION);
    }
  }
}
