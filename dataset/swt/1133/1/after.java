class PlaceHold {
  int gtk_focus_out_event(int widget, int event) {
    sendEvent(FocusOut);
    if (handle == 0) {
      return 0;
    }
    if (hooks(KeyDown) || hooks(KeyUp)) {
      int imHandle = imHandle();
      if (imHandle != 0) {
        OS.gtk_im_context_focus_out(imHandle);
      }
    }
    Shell shell = _getShell();
    if (!shell.isDisposed()) {
      Display display = shell.display;
      Control control = display.getFocusControl();
      if ((control == null) || (shell != control.getShell())) {
        shell.setActiveControl(null);
      }
    }
    return 0;
  }
}
