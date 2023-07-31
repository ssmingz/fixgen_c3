class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | CANVAS;
    if (handle != 0) {
      int clazz = display.PtContainer;
      int[] args =
          new int[] {
            OS.Pt_ARG_FILL_COLOR,
            OS.Pg_TRANSPARENT,
            0,
            OS.Pt_ARG_CONTAINER_FLAGS,
            OS.Pt_HOTKEYS_FIRST,
            OS.Pt_HOTKEYS_FIRST,
            OS.Pt_ARG_RESIZE_FLAGS,
            0,
            OS.Pt_RESIZE_XY_BITS
          };
      shellHandle = OS.PtCreateWidget(clazz, handle, args.length / 3, args);
      if (shellHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
    } else {
      int parentHandle = 0;
      if (parent != null) {
        parentHandle = parent.topHandle();
      }
      PhRect_t rect = new PhRect_t();
      OS.PhWindowQueryVisible(Ph_QUERY_GRAPHICS, 0, OS.PhInputGroup(0), rect);
      int width = ((short) ((((rect.lr_x - rect.ul_x) + 1) * 5) / 8));
      int height = ((short) ((((rect.lr_y - rect.ul_y) + 1) * 5) / 8));
      int decorations = 0;
      int flags =
          (((((OS.Ph_WM_RENDER_MIN | OS.Ph_WM_RENDER_MAX) | OS.Ph_WM_RENDER_RESIZE)
                          | OS.Ph_WM_RENDER_BORDER)
                      | OS.Ph_WM_RENDER_MENU)
                  | OS.Ph_WM_RENDER_MIN)
              | OS.Ph_WM_RENDER_TITLE;
      if ((style & SWT.NO_TRIM) == 0) {
        if ((style & SWT.MIN) != 0) {
          decorations |= OS.Ph_WM_RENDER_MIN;
        }
        if ((style & SWT.MAX) != 0) {
          decorations |= OS.Ph_WM_RENDER_MAX;
        }
        if ((style & SWT.RESIZE) != 0) {
          decorations |= OS.Ph_WM_RENDER_BORDER | OS.Ph_WM_RENDER_RESIZE;
        }
        if ((style & SWT.BORDER) != 0) {
          decorations |= OS.Ph_WM_RENDER_BORDER;
        }
        if ((style & SWT.MENU) != 0) {
          decorations |= OS.Ph_WM_RENDER_MENU;
        }
        if ((style & SWT.TITLE) != 0) {
          decorations |= OS.Ph_WM_RENDER_TITLE;
        }
      }
      int notifyFlags = ((OS.Ph_WM_ICON | OS.Ph_WM_FOCUS) | OS.Ph_WM_MOVE) | OS.Ph_WM_RESIZE;
      int windowState = OS.Ph_WM_STATE_ISFOCUS;
      if ((style & SWT.ON_TOP) != 0) {
        windowState = OS.Ph_WM_STATE_ISFRONT;
      }
      int titlePtr = OS.malloc(1);
      int[] args =
          new int[] {
            OS.Pt_ARG_WIDTH,
            width,
            0,
            OS.Pt_ARG_HEIGHT,
            height,
            0,
            OS.Pt_ARG_WINDOW_TITLE,
            titlePtr,
            0,
            OS.Pt_ARG_WINDOW_RENDER_FLAGS,
            decorations,
            flags,
            OS.Pt_ARG_WINDOW_MANAGED_FLAGS,
            0,
            OS.Ph_WM_CLOSE,
            OS.Pt_ARG_WINDOW_NOTIFY_FLAGS,
            notifyFlags,
            notifyFlags,
            OS.Pt_ARG_WINDOW_STATE,
            windowState,
            ~0,
            OS.Pt_ARG_FLAGS,
            OS.Pt_DELAY_REALIZE,
            OS.Pt_DELAY_REALIZE,
            OS.Pt_ARG_FILL_COLOR,
            OS.Pg_TRANSPARENT,
            0,
            OS.Pt_ARG_CONTAINER_FLAGS,
            OS.Pt_HOTKEYS_FIRST,
            OS.Pt_HOTKEYS_FIRST,
            OS.Pt_ARG_RESIZE_FLAGS,
            0,
            OS.Pt_RESIZE_XY_BITS
          };
      OS.PtSetParentWidget(parentHandle);
      shellHandle = OS.PtCreateWidget(OS.PtWindow(), parentHandle, args.length / 3, args);
      OS.free(titlePtr);
      if (shellHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
    }
    createScrolledHandle(shellHandle);
    if ((style & ((SWT.NO_TRIM | SWT.BORDER) | SWT.RESIZE)) == 0) {
      int[] args =
          new int[] {
            OS.Pt_ARG_FLAGS,
            OS.Pt_HIGHLIGHTED,
            OS.Pt_HIGHLIGHTED,
            OS.Pt_ARG_BASIC_FLAGS,
            OS.Pt_ALL_OUTLINES,
            ~0
          };
      OS.PtSetResources(scrolledHandle, args.length / 3, args);
    }
    int[] args = new int[] {OS.Pt_ARG_WIDTH, 0, 0, OS.Pt_ARG_HEIGHT, 0, 0};
    OS.PtGetResources(shellHandle, args.length / 3, args);
    resizeBounds(args[1], args[4]);
  }
}
