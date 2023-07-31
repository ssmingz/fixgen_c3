class PlaceHold {
  public static Frame new_Frame(final Composite parent) {
    if (parent == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if ((parent.getStyle() & SWT.EMBEDDED) == 0) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int handle = parent.embeddedHandle;
    Class clazz = null;
    try {
      String className =
          (embeddedFrameClass != null) ? embeddedFrameClass : "sun.awt.X11.XEmbeddedFrame";
      clazz = Class.forName(className);
    } catch (Throwable e) {
      SWT.error(ERROR_NOT_IMPLEMENTED, e, " [need JDK 1.5 or greater]");
    }
    initializeSwing();
    Object value = null;
    Constructor constructor = null;
    try {
      constructor = clazz.getConstructor(new Class[] {int.class, boolean.class});
      value = constructor.newInstance(new Object[] {new Integer(((int) (handle))), Boolean.TRUE});
    } catch (Throwable e1) {
      try {
        constructor = clazz.getConstructor(new Class[] {long.class, boolean.class});
        value = constructor.newInstance(new Object[] {new Long(handle), Boolean.TRUE});
      } catch (Throwable e2) {
        SWT.error(ERROR_NOT_IMPLEMENTED, e2);
      }
    }
    final Frame frame = ((Frame) (value));
    parent.setData(EMBEDDED_FRAME_KEY, frame);
    if (Device.DEBUG) {
      loadLibrary();
      setDebug(frame, true);
    }
    try {
      Method method = clazz.getMethod("registerListeners", null);
      if (method != null) {
        method.invoke(value, null);
      }
    } catch (Throwable e) {
    }
    final AWTEventListener awtListener =
        new AWTEventListener() {
          public void eventDispatched(AWTEvent event) {
            if (event.getID() == WindowEvent.WINDOW_OPENED) {
              final Window window = ((Window) (event.getSource()));
              if (window.getParent() == frame) {
                parent
                    .getDisplay()
                    .asyncExec(
                        new Runnable() {
                          public void run() {
                            Shell shell = parent.getShell();
                            loadLibrary();
                            int awtHandle = getAWTHandle(window);
                            if (awtHandle == 0) {
                              return;
                            }
                            int xtParent = OS.XtParent(shell.handle);
                            while ((xtParent != 0)
                                && (!OS.XtIsSubclass(xtParent, OS.shellWidgetClass()))) {
                              xtParent = OS.XtParent(xtParent);
                            }
                            OS.XSetTransientForHint(
                                OS.XtDisplay(xtParent), awtHandle, OS.XtWindow(xtParent));
                          }
                        });
              }
            }
          }
        };
    frame.getToolkit().addAWTEventListener(awtListener, AWTEvent.WINDOW_EVENT_MASK);
    final Listener shellListener =
        new Listener() {
          public void handleEvent(Event e) {
            switch (e.type) {
              case SWT.Deiconify:
                EventQueue.invokeLater(
                    new Runnable() {
                      public void run() {
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_DEICONIFIED));
                      }
                    });
                break;
              case SWT.Iconify:
                EventQueue.invokeLater(
                    new Runnable() {
                      public void run() {
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_ICONIFIED));
                      }
                    });
                break;
            }
          }
        };
    Shell shell = parent.getShell();
    shell.addListener(Deiconify, shellListener);
    shell.addListener(Iconify, shellListener);
    Listener listener =
        new Listener() {
          public void handleEvent(Event e) {
            switch (e.type) {
              case SWT.Dispose:
                Shell shell = parent.getShell();
                shell.removeListener(Deiconify, shellListener);
                shell.removeListener(Iconify, shellListener);
                parent.setVisible(false);
                EventQueue.invokeLater(
                    new Runnable() {
                      public void run() {
                        frame.getToolkit().removeAWTEventListener(awtListener);
                        frame.dispose();
                      }
                    });
                break;
              case SWT.Resize:
                if (Library.JAVA_VERSION >= Library.JAVA_VERSION(1, 6, 0)) {
                  final Rectangle clientArea = parent.getClientArea();
                  EventQueue.invokeLater(
                      new Runnable() {
                        public void run() {
                          frame.setSize(clientArea.width, clientArea.height);
                        }
                      });
                }
                break;
            }
          }
        };
    parent.addListener(Dispose, listener);
    parent.addListener(Resize, listener);
    parent
        .getDisplay()
        .asyncExec(
            new Runnable() {
              public void run() {
                if (parent.isDisposed()) {
                  return;
                }
                final Rectangle clientArea = parent.getClientArea();
                EventQueue.invokeLater(
                    new Runnable() {
                      public void run() {
                        frame.setSize(clientArea.width, clientArea.height);
                        frame.validate();
                      }
                    });
              }
            });
    return frame;
  }
}
