class PlaceHold {
  public void setVisible(boolean visible) {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (!isValidWidget()) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if (visible == OS.PtWidgetIsRealized(handle)) {
      return;
    }
    int[] args =
        new int[] {OS.Pt_ARG_FLAGS, visible ? 0 : OS.Pt_DELAY_REALIZE, OS.Pt_DELAY_REALIZE};
    OS.PtSetResources(handle, args.length / 3, args);
    if (visible) {
      sendEvent(Show);
      OS.PtRealizeWidget(handle);
      parent.resizeClientArea();
    } else {
      OS.PtUnrealizeWidget(handle);
      parent.resizeClientArea();
      sendEvent(Hide);
    }
  }
}
