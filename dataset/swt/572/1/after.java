class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if (visible) {
      if ((state & HIDDEN) == 0) {
        return;
      }
      state &= ~HIDDEN;
    } else {
      if ((state & HIDDEN) != 0) {
        return;
      }
      state |= HIDDEN;
    }
    OS.SetControlVisibility(handle, visible, true);
    sendEvent(visible ? SWT.Show : SWT.Hide);
    parent.layoutControl();
  }
}
