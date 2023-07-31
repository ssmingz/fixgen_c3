class PlaceHold {
  void createHandle() {
    state |= HIDDEN;
    if ((window == null) && (view == null)) {
      window = ((NSWindow) (new SWTWindow().alloc()));
      int styleMask = OS.NSBorderlessWindowMask;
      if ((style & SWT.NO_TRIM) == 0) {
        if ((style & SWT.TITLE) != 0) {
          styleMask |= OS.NSTitledWindowMask;
        }
        if ((style & SWT.CLOSE) != 0) {
          styleMask |= OS.NSClosableWindowMask;
        }
        if ((style & SWT.MIN) != 0) {
          styleMask |= OS.NSMiniaturizableWindowMask;
        }
        if ((style & SWT.MAX) != 0) {
          styleMask |= OS.NSResizableWindowMask;
        }
        if ((style & SWT.RESIZE) != 0) {
          styleMask |= OS.NSResizableWindowMask;
        }
      }
      NSScreen screen = null;
      NSScreen primaryScreen = new NSScreen(NSScreen.screens().objectAtIndex(0));
      if (parent != null) {
        screen = parent.getShell().window.screen();
      }
      if (screen == null) {
        screen = primaryScreen;
      }
      window =
          window.initWithContentRect(
              new NSRect(), styleMask, NSBackingStoreBuffered, false, screen);
      if (((style & ((SWT.NO_TRIM | SWT.BORDER) | SWT.SHELL_TRIM)) == 0)
          || ((style & (SWT.TOOL | SWT.SHEET)) != 0)) {
        window.setHasShadow(true);
      }
      if ((style & SWT.NO_TRIM) == 0) {
        NSSize size = window.minSize();
        size.width = NSWindow.minFrameWidthWithTitle(NSString.string(), styleMask);
        window.setMinSize(size);
      }
      if (fixResize()) {
        if (window.respondsToSelector(sel_setMovable_)) {
          OS.objc_msgSend(window.id, sel_setMovable_, 0);
        }
      }
      display.cascadeWindow(window, screen);
      NSRect screenFrame = screen.frame();
      float width = (screenFrame.width * 5) / 8;
      float height = (screenFrame.height * 5) / 8;
      NSRect frame = window.frame();
      NSRect primaryFrame = primaryScreen.frame();
      frame.y = primaryFrame.height - ((primaryFrame.height - (frame.y + frame.height)) + height);
      frame.width = width;
      frame.height = height;
      window.setFrame(frame, false);
      if ((style & SWT.ON_TOP) != 0) {
        window.setLevel(NSStatusWindowLevel);
      }
      super.createHandle();
      topView().setHidden(true);
    } else {
      state &= ~HIDDEN;
      if (window != null) {
        view = window.contentView();
        if (view == null) {
          super.createHandle();
        } else {
          view.retain();
        }
      } else {
        NSView parentView = view;
        super.createHandle();
        parentView.addSubview(topView());
      }
      style |= SWT.NO_BACKGROUND;
    }
    if (window != null) {
      if (parent != null) {
        window.setCollectionBehavior(NSWindowCollectionBehaviorMoveToActiveSpace);
      }
      window.setAcceptsMouseMovedEvents(true);
      windowDelegate = ((SWTWindowDelegate) (new SWTWindowDelegate().alloc().init()));
      window.setDelegate(windowDelegate);
    }
    NSWindow fieldEditorWindow = window;
    if (fieldEditorWindow == null) {
      fieldEditorWindow = view.window();
    }
    id id = fieldEditorWindow.fieldEditor(true, null);
    if (id != null) {
      OS.object_setClass(id.id, OS.objc_getClass("SWTEditorView"));
    }
  }
}
