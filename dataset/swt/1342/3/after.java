class PlaceHold {
  int setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    long topHandle = topHandle();
    boolean sendMove = move;
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(topHandle, allocation);
    if ((parent.style & SWT.MIRRORED) != 0) {
      int clientWidth = parent.getClientWidth();
      int oldWidth = ((state & ZERO_WIDTH) != 0) ? 0 : allocation.width;
      int oldX = (clientWidth - oldWidth) - allocation.x;
      if (move) {
        sendMove &= x != oldX;
        x = (clientWidth - (resize ? width : oldWidth)) - x;
      } else {
        move = true;
        x = (clientWidth - (resize ? width : oldWidth)) - oldX;
        y = allocation.y;
      }
    }
    boolean sameOrigin = true;
    boolean sameExtent = true;
    if (move) {
      int oldX = allocation.x;
      int oldY = allocation.y;
      sameOrigin = (x == oldX) && (y == oldY);
      if (!sameOrigin) {
        if (enableWindow != 0) {
          OS.gdk_window_move(enableWindow, x, y);
        }
        moveHandle(x, y);
      }
    }
    int clientWidth = 0;
    if (resize) {
      int oldWidth = ((state & ZERO_WIDTH) != 0) ? 0 : allocation.width;
      int oldHeight = ((state & ZERO_HEIGHT) != 0) ? 0 : allocation.height;
      sameExtent = (width == oldWidth) && (height == oldHeight);
      if ((!sameExtent) && ((style & SWT.MIRRORED) != 0)) {
        clientWidth = getClientWidth();
      }
      if ((!sameExtent) && (!((width == 0) && (height == 0)))) {
        int newWidth = Math.max(1, width);
        int newHeight = Math.max(1, height);
        if (redrawWindow != 0) {
          OS.gdk_window_resize(redrawWindow, newWidth, newHeight);
        }
        if (enableWindow != 0) {
          OS.gdk_window_resize(enableWindow, newWidth, newHeight);
        }
        resizeHandle(newWidth, newHeight);
      }
    }
    if ((!sameOrigin) || (!sameExtent)) {
      GtkRequisition requisition = new GtkRequisition();
      gtk_widget_size_request(topHandle, requisition);
      if (move) {
        allocation.x = x;
        allocation.y = y;
      }
      if (resize) {
        allocation.width = width;
        allocation.height = height;
      }
      OS.gtk_widget_size_allocate(topHandle, allocation);
    }
    if (!sameExtent) {
      state = (width == 0) ? state | ZERO_WIDTH : state & (~ZERO_WIDTH);
      state = (height == 0) ? state | ZERO_HEIGHT : state & (~ZERO_HEIGHT);
      if ((state & (ZERO_WIDTH | ZERO_HEIGHT)) != 0) {
        if (enableWindow != 0) {
          OS.gdk_window_hide(enableWindow);
        }
        OS.gtk_widget_hide(topHandle);
      } else if ((state & HIDDEN) == 0) {
        if (enableWindow != 0) {
          OS.gdk_window_show_unraised(enableWindow);
        }
        OS.gtk_widget_show(topHandle);
      }
      if ((style & SWT.MIRRORED) != 0) {
        moveChildren(clientWidth);
      }
    }
    int result = 0;
    if (move && (!sameOrigin)) {
      Control control = findBackgroundControl();
      if ((control != null) && (control.backgroundImage != null)) {
        if (isVisible()) {
          redrawWidget(0, 0, 0, 0, true, true, true);
        }
      }
      if (sendMove) {
        sendEvent(Move);
      }
      result |= MOVED;
    }
    if (resize && (!sameExtent)) {
      sendEvent(Resize);
      result |= RESIZED;
    }
    return result;
  }
}
