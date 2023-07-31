class PlaceHold {
  int Pt_CB_LOST_FOCUS(int widget, int info) {
    Shell shell = getShell();
    sendEvent(FocusOut);
    if (isDisposed()) {
      return OS.Pt_CONTINUE;
    }
    if (!shell.isDisposed()) {
      Control control = display.getFocusControl();
      if ((control == null) || (shell != control.getShell())) {
        shell.setActiveControl(null);
      }
    }
    return OS.Pt_CONTINUE;
  }
}
