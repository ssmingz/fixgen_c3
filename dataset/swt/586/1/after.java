class PlaceHold {
  public boolean open() {
    checkWidget();
    if (rectangles == null) {
      return false;
    }
    int input_group = OS.PhInputGroup(0);
    PhCursorInfo_t info = new PhCursorInfo_t();
    OS.PhQueryCursor(((short) (input_group)), info);
    if ((style & SWT.MENU) == 0) {}
    int region = 0;
    if (info.dragger == 0) {
      PhRect_t rect = new PhRect_t();
      OS.PhWindowQueryVisible(Ph_QUERY_CONSOLE, 0, OS.PhInputGroup(0), rect);
      int sense =
          (((OS.Ph_EV_DRAG | OS.Ph_EV_KEY) | OS.Ph_EV_BUT_PRESS) | OS.Ph_EV_BUT_RELEASE)
              | OS.Ph_EV_PTR_MOTION;
      int[] args =
          new int[] {
            OS.Pt_ARG_WIDTH,
            (rect.lr_x - rect.ul_x) + 1,
            0,
            OS.Pt_ARG_HEIGHT,
            (rect.lr_y - rect.ul_y) + 1,
            0,
            OS.Pt_ARG_REGION_OPAQUE,
            0,
            ~0,
            OS.Pt_ARG_REGION_SENSE,
            sense,
            ~0,
            OS.Pt_ARG_REGION_FLAGS,
            OS.Ph_FORCE_BOUNDARY,
            OS.Ph_FORCE_BOUNDARY,
            OS.Pt_ARG_FILL_COLOR,
            OS.Pg_TRANSPARENT,
            0
          };
      region = OS.PtCreateWidget(OS.PtRegion(), Pt_NO_PARENT, args.length / 3, args);
      OS.PtRealizeWidget(region);
      rect = new PhRect_t();
      int rid = OS.PtWidgetRid(region);
      OS.PhInitDrag(
          rid,
          OS.Ph_DRAG_KEY_MOTION | OS.Ph_TRACK_DRAG,
          rect,
          null,
          input_group,
          null,
          null,
          null,
          null,
          null);
    }
    int oldX;
    int oldY;
    int size = PhEvent_t.sizeof + 1024;
    int buffer = OS.malloc(size);
    PhEvent_t phEvent = new PhEvent_t();
    Event event = new Event();
    Point cursorPos;
    drawRectangles(rectangles);
    if ((style & SWT.MENU) == 0) {
      oldX = info.pos_x;
      oldY = info.pos_y;
    } else {
      if ((style & SWT.RESIZE) != 0) {
        cursorPos = adjustResizeCursor();
      } else {
        cursorPos = adjustMoveCursor();
      }
      oldX = cursorPos.x;
      oldY = cursorPos.y;
    }
    tracking = true;
    boolean cancelled = false;
    while (tracking && (!cancelled)) {
      if ((parent != null) && parent.isDisposed()) {
        break;
      }
      int result = OS.PhEventNext(buffer, size);
      switch (result) {
        case OS.Ph_EVENT_MSG:
          break;
        case OS.Ph_RESIZE_MSG:
          size = OS.PhGetMsgSize(buffer);
          OS.free(buffer);
          buffer = OS.malloc(size);
          continue;
      }
      OS.memmove(phEvent, buffer, sizeof);
      if (phEvent.type == OS.Ph_EV_DRAG) {
        switch (phEvent.subtype) {
          case OS.Ph_EV_DRAG_MOTION_EVENT:
            {
              int data = OS.PhGetData(buffer);
              if (data == 0) {
                break;
              }
              PhPointerEvent_t pe = new PhPointerEvent_t();
              OS.memmove(pe, data, sizeof);
              int newX = pe.pos_x;
              int newY = pe.pos_y;
              if ((newX != oldX) || (newY != oldY)) {
                drawRectangles(rectangles);
                event.x = newX;
                event.y = newY;
                if ((style & SWT.RESIZE) != 0) {
                  resizeRectangles(newX - oldX, newY - oldY);
                  cursorPos = adjustResizeCursor();
                  newX = cursorPos.x;
                  newY = cursorPos.y;
                  sendEvent(Resize, event);
                } else {
                  moveRectangles(newX - oldX, newY - oldY);
                  sendEvent(Move, event);
                }
                if (isDisposed()) {
                  return false;
                }
                drawRectangles(rectangles);
                oldX = newX;
                oldY = newY;
              }
              break;
            }
          case OS.Ph_EV_DRAG_KEY_EVENT:
            {
              int data = OS.PhGetData(buffer);
              if (data == 0) {
                break;
              }
              PhKeyEvent_t ke = new PhKeyEvent_t();
              OS.memmove(ke, data, sizeof);
              if ((ke.key_flags & OS.Pk_KF_Sym_Valid) != 0) {
                int stepSize =
                    ((ke.key_mods & OS.Pk_KM_Ctrl) != 0) ? STEPSIZE_SMALL : STEPSIZE_LARGE;
                int xChange = 0;
                int yChange = 0;
                switch (ke.key_sym) {
                  case OS.Pk_Escape:
                    cancelled = true;
                    tracking = false;
                    break;
                  case OS.Pk_Return:
                    tracking = false;
                    break;
                  case OS.Pk_Left:
                    xChange = -stepSize;
                    break;
                  case OS.Pk_Right:
                    xChange = stepSize;
                    break;
                  case OS.Pk_Up:
                    yChange = -stepSize;
                    break;
                  case OS.Pk_Down:
                    yChange = stepSize;
                    break;
                }
                if ((xChange != 0) || (yChange != 0)) {
                  drawRectangles(rectangles);
                  int newX = oldX + xChange;
                  int newY = oldY + yChange;
                  event.x = newX;
                  event.y = newY;
                  if ((style & SWT.RESIZE) != 0) {
                    resizeRectangles(xChange, yChange);
                    cursorPos = adjustResizeCursor();
                    sendEvent(Resize, event);
                  } else {
                    moveRectangles(xChange, yChange);
                    cursorPos = adjustMoveCursor();
                    sendEvent(Move, event);
                  }
                  if (isDisposed()) {
                    return false;
                  }
                  drawRectangles(rectangles);
                  oldX = cursorPos.x;
                  oldY = cursorPos.y;
                }
              }
              break;
            }
          case OS.Ph_EV_DRAG_COMPLETE:
            {
              tracking = false;
              break;
            }
        }
        if (phEvent.collector_handle != 0) {
          setCursor(phEvent.collector_handle);
        }
        if (tracking && (!cancelled)) {
          continue;
        }
      }
      OS.PtEventHandler(buffer);
    }
    drawRectangles(rectangles);
    tracking = false;
    if (region != 0) {
      OS.PtDestroyWidget(region);
    }
    return !cancelled;
  }
}
