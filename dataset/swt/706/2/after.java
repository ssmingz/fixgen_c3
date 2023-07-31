class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if (OS.GTK_WIDGET_MAPPED(shellHandle) == visible) {
      return;
    }
    if (visible) {
      sendEvent(Show);
      if (isDisposed()) {
        return;
      }
      mapped = false;
      OS.gtk_widget_show(shellHandle);
      if (isDisposed()) {
        return;
      }
      display.dispatchEvents =
          new int[] {
            OS.GDK_EXPOSE,
            OS.GDK_FOCUS_CHANGE,
            OS.GDK_CONFIGURE,
            OS.GDK_MAP,
            OS.GDK_UNMAP,
            OS.GDK_NO_EXPOSE
          };
      Display display = this.display;
      display.putGdkEvents();
      boolean iconic = false;
      Shell shell = (parent != null) ? parent.getShell() : null;
      do {
        OS.g_main_context_iteration(0, false);
        if (isDisposed()) {
          break;
        }
        iconic = minimized || ((shell != null) && shell.minimized);
      } while ((!mapped) && (!iconic));
      display.dispatchEvents = null;
      if (isDisposed()) {
        return;
      }
      if (!iconic) {
        update(true);
        if (isDisposed()) {
          return;
        }
        adjustTrim();
      }
      mapped = true;
      int mask = (SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL) | SWT.SYSTEM_MODAL;
      if ((style & mask) != 0) {
        OS.gdk_pointer_ungrab(GDK_CURRENT_TIME);
      }
      if (!moved) {
        moved = true;
        Point location = getLocation();
        oldX = location.x;
        oldY = location.y;
        sendEvent(Move);
        if (isDisposed()) {
          return;
        }
      }
      if (!resized) {
        resized = true;
        Point size = getSize();
        oldWidth = size.x - trimWidth();
        oldHeight = size.y - trimHeight();
        sendEvent(Resize);
        if (isDisposed()) {
          return;
        }
        if (layout != null) {
          markLayout(false, false);
          updateLayout(false);
        }
      }
    } else {
      OS.gtk_widget_hide(shellHandle);
      sendEvent(Hide);
    }
  }
}
