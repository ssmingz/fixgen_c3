class PlaceHold {
  public void setMenuBar(Menu menu) {
    checkWidget();
    if (menuBar == menu) {
      return;
    }
    if (menu != null) {
      if (menu.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      if ((menu.style & SWT.BAR) == 0) {
        error(ERROR_MENU_NOT_BAR);
      }
      if (menu.parent != this) {
        error(ERROR_INVALID_PARENT);
      }
    }
    if (menuBar != null) {
      if ((!isEnabled()) && menuBar.getEnabled()) {
        propagateHandle(true, menuBar.handle, None);
      }
      menuBar.removeAccelerators();
    }
    if (menu != null) {
      if (!isEnabled()) {
        propagateHandle(false, menu.handle, None);
      }
      menu.addAccelerators();
    }
    int[] argList1 = new int[] {OS.XmNwidth, 0, OS.XmNheight, 0};
    OS.XtGetValues(handle, argList1, argList1.length / 2);
    int newHandle = (menu != null) ? menu.handle : 0;
    int oldHandle = (menuBar != null) ? menuBar.handle : 0;
    menuBar = menu;
    int hHandle = (horizontalBar != null) ? horizontalBar.handle : 0;
    int vHandle = (verticalBar != null) ? verticalBar.handle : 0;
    if (newHandle != 0) {
      OS.XtSetMappedWhenManaged(newHandle, false);
      OS.XtManageChild(newHandle);
    }
    int clientHandle = (formHandle != 0) ? formHandle : handle;
    OS.XmMainWindowSetAreas(scrolledHandle, newHandle, 0, hHandle, vHandle, clientHandle);
    if (oldHandle != 0) {
      OS.XtUnmanageChild(oldHandle);
    }
    if (newHandle != 0) {
      OS.XtSetMappedWhenManaged(newHandle, true);
    }
    if ((newHandle == 0) && OS.XtIsRealized(scrolledHandle)) {
      int[] argList = new int[] {OS.XmNwidth, 0, OS.XmNheight, 0, OS.XmNborderWidth, 0};
      OS.XtGetValues(scrolledHandle, argList, argList.length / 2);
      OS.XtResizeWidget(scrolledHandle, argList[1] + 1, argList[3], argList[5]);
      OS.XtResizeWidget(scrolledHandle, argList[1], argList[3], argList[5]);
    }
    int[] argList2 = new int[] {OS.XmNwidth, 0, OS.XmNheight, 0};
    OS.XtGetValues(handle, argList2, argList2.length / 2);
    if ((argList1[1] != argList2[1]) || (argList1[3] != argList2[3])) {
      sendEvent(Resize);
      if (layout != null) {
        markLayout(false, false);
        updateLayout(false);
      }
    }
  }
}
