class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    int mask = (SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL) | SWT.SYSTEM_MODAL;
    if ((style & mask) != 0) {
      if (visible) {
        display.setModalShell(this);
        OS.gtk_window_set_modal(shellHandle, true);
      } else {
        display.clearModal(this);
        OS.gtk_window_set_modal(shellHandle, false);
      }
    } else {
      updateModal();
    }
    showWithParent = visible;
    if (OS.GTK_WIDGET_MAPPED(shellHandle) == visible) {
      return;
    }
    if (visible) {
      if (center && (!moved)) {
        center();
        if (isDisposed()) {
          return;
        }
      }
      sendEvent(Show);
      if (isDisposed()) {
        return;
      }
      OS.gtk_widget_show(shellHandle);
      if (enableWindow != 0) {
        OS.gdk_window_raise(enableWindow);
      }
      if (!OS.GTK_IS_PLUG(shellHandle)) {
        mapped = false;
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
          update(true, true);
          if (isDisposed()) {
            return;
          }
          adjustTrim();
        }
      }
      mapped = true;
      if ((style & mask) != 0) {
        OS.gdk_pointer_ungrab(GDK_CURRENT_TIME);
      }
      opened = true;
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
      fixActiveShell();
      OS.gtk_widget_hide(shellHandle);
      sendEvent(Hide);
    }
  }
}
