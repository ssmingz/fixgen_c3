class PlaceHold {
  public static Frame new_Frame(final Composite parent) {
    if (parent == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if ((parent.getStyle() & SWT.EMBEDDED) == 0) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    final int handle = parent.handle;
    final Frame[] result = new Frame[1];
    final Throwable[] exception = new Throwable[1];
    Runnable runnable =
        new Runnable() {
          public void run() {
            try {
              Class clazz = null;
              try {
                String className =
                    (embeddedFrameClass != null)
                        ? embeddedFrameClass
                        : "sun.awt.windows.WEmbeddedFrame";
                clazz = Class.forName(className);
              } catch (Throwable e) {
                exception[0] = e;
                return;
              }
              initializeSwing();
              Object value = null;
              Constructor constructor = null;
              try {
                constructor = clazz.getConstructor(new Class[] {int.class});
                value = constructor.newInstance(new Object[] {new Integer(((int) (handle)))});
              } catch (Throwable e1) {
                try {
                  constructor = clazz.getConstructor(new Class[] {long.class});
                  value = constructor.newInstance(new Object[] {new Long(handle)});
                } catch (Throwable e2) {
                  exception[0] = e2;
                  return;
                }
              }
              final Frame frame = ((Frame) (value));
              frame.addNotify();
              try {
                clazz = Class.forName("sun.awt.windows.WComponentPeer");
                Field field = clazz.getDeclaredField("winGraphicsConfig");
                field.setAccessible(true);
                field.set(frame.getPeer(), frame.getGraphicsConfiguration());
              } catch (Throwable e) {
              }
              result[0] = frame;
            } finally {
              synchronized (result) {
                result.notify();
              }
            }
          }
        };
    if (EventQueue.isDispatchThread() || (parent.getDisplay().getSyncThread() != null)) {
      runnable.run();
    } else {
      EventQueue.invokeLater(runnable);
      OS.ReplyMessage(0);
      boolean interrupted = false;
      MSG msg = new MSG();
      int flags = (OS.PM_NOREMOVE | OS.PM_NOYIELD) | OS.PM_QS_SENDMESSAGE;
      while ((result[0] == null) && (exception[0] == null)) {
        OS.PeekMessage(msg, 0, 0, 0, flags);
        try {
          synchronized (result) {
            result.wait(50);
          }
        } catch (InterruptedException e) {
          interrupted = true;
        }
      }
      if (interrupted) {
        Compatibility.interrupt();
      }
    }
    if (exception[0] != null) {
      SWT.error(ERROR_NOT_IMPLEMENTED, exception[0]);
    }
    final Frame frame = result[0];
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
              case SWT.Activate:
                EventQueue.invokeLater(
                    new Runnable() {
                      public void run() {
                        if (Library.JAVA_VERSION < Library.JAVA_VERSION(1, 4, 0)) {
                          frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_ACTIVATED));
                          frame.dispatchEvent(new FocusEvent(frame, FocusEvent.FOCUS_GAINED));
                        } else if (Library.JAVA_VERSION < Library.JAVA_VERSION(1, 5, 0)) {
                          frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_ACTIVATED));
                          frame.dispatchEvent(new WindowEvent(frame, 207));
                        } else {
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
                          }
                        }
                      }
                    });
                break;
              case SWT.Deactivate:
                EventQueue.invokeLater(
                    new Runnable() {
                      public void run() {
                        if (Library.JAVA_VERSION < Library.JAVA_VERSION(1, 4, 0)) {
                          frame.dispatchEvent(
                              new WindowEvent(frame, WindowEvent.WINDOW_DEACTIVATED));
                          frame.dispatchEvent(new FocusEvent(frame, FocusEvent.FOCUS_LOST));
                        } else if (Library.JAVA_VERSION < Library.JAVA_VERSION(1, 5, 0)) {
                          frame.dispatchEvent(new WindowEvent(frame, 208));
                          frame.dispatchEvent(
                              new WindowEvent(frame, WindowEvent.WINDOW_DEACTIVATED));
                        } else {
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
                          }
                        }
                      }
                    });
                break;
            }
          }
        };
    if (Library.JAVA_VERSION < Library.JAVA_VERSION(1, 5, 0)) {
      parent.addListener(Activate, listener);
    } else {
      parent.addListener(FocusIn, listener);
    }
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
                      }
                    });
              }
            });
    return frame;
  }
}
