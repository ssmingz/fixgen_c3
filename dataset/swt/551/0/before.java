class PlaceHold {
  Control _getFocusControl() {
    int focusedElement = OS.Keyboard_FocusedElement();
    Control control = null;
    if (focusedElement != 0) {
      Widget widget = getWidget(focusedElement);
      if (widget instanceof Menu) {
        Shell shell = ((Menu) (widget)).getShell();
        OS.GCHandle_Free(focusedElement);
        focusedElement = OS.FocusManager_GetFocusedElement(shell.shellHandle);
        widget = getWidget(focusedElement);
      }
      if (widget != null) {
        control = widget.getWidgetControl();
      }
      OS.GCHandle_Free(focusedElement);
    }
    return control;
  }
}
