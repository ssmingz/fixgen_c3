class PlaceHold {
  public static Frame new_Frame(final Composite parent) {
    if (parent == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if ((parent.getStyle() & SWT.EMBEDDED) == 0) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    final long handle = parent.view.id;
    Class clazz = null;
    try {
      String className =
          (embeddedFrameClass != null) ? embeddedFrameClass : "apple.awt.CEmbeddedFrame";
      if (embeddedFrameClass == null) {
        clazz = Class.forName(className, true, ClassLoader.getSystemClassLoader());
      } else {
        clazz = Class.forName(className);
      }
    } catch (ClassNotFoundException cne) {
      SWT.error(ERROR_NOT_IMPLEMENTED, cne);
    } catch (Throwable e) {
      SWT.error(ERROR_UNSPECIFIED, e, " [Error while starting AWT]");
    }
    initializeSwing();
    Object value = null;
    Constructor constructor = null;
    try {
      constructor = clazz.getConstructor(new Class[] {long.class});
      value = constructor.newInstance(new Object[] {new Long(handle)});
    } catch (Throwable e) {
      SWT.error(ERROR_NOT_IMPLEMENTED, e);
    }
    final Frame frame = ((Frame) (value));
    frame.addNotify();
    parent.setData(EMBEDDED_FRAME_KEY, frame);
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
                        try {
                          frame.dispose();
                        } catch (Throwable e) {
                        }
                      }
                    });
                break;
              case SWT.FocusIn:
                EventQueue.invokeLater(
                    new Runnable() {
                      public void run() {
                        if (frame.isActive()) {
                          return;
                        }
                        try {
                          Class clazz = frame.getClass();
                          Method method =
                              clazz.getMethod(
                                  "synthesizeWindowActivation", new Class[] {boolean.class});
                          if (method != null) {
                            method.invoke(frame, new Object[] {new Boolean(true)});
                          }
                        } catch (Throwable e) {
                          e.printStackTrace();
                        }
                      }
                    });
                break;
              case SWT.Deactivate:
                EventQueue.invokeLater(
                    new Runnable() {
                      public void run() {
                        if (!frame.isActive()) {
                          return;
                        }
                        try {
                          Class clazz = frame.getClass();
                          Method method =
                              clazz.getMethod(
                                  "synthesizeWindowActivation", new Class[] {boolean.class});
                          if (method != null) {
                            method.invoke(frame, new Object[] {new Boolean(false)});
                          }
                        } catch (Throwable e) {
                          e.printStackTrace();
                        }
                      }
                    });
                break;
            }
          }
        };
    parent.addListener(FocusIn, listener);
    parent.addListener(Deactivate, listener);
    parent.addListener(Dispose, listener);
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
                        frame.setVisible(false);
                        frame.setVisible(true);
                      }
                    });
              }
            });
    return frame;
  }
}
