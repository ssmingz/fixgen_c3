class PlaceHold {
  public static Frame new_Frame(final Composite parent) {
    int handle = parent.handle;
    Class clazz = null;
    try {
      clazz = Class.forName("sun.awt.windows.WEmbeddedFrame");
    } catch (Throwable e) {
      SWT.error(ERROR_NOT_IMPLEMENTED, e);
    }
    Constructor constructor = null;
    try {
      constructor = clazz.getConstructor(new Class[] {int.class});
    } catch (Throwable e1) {
      try {
        constructor = clazz.getConstructor(new Class[] {long.class});
      } catch (Throwable e2) {
        SWT.error(ERROR_NOT_IMPLEMENTED, e2);
      }
    }
    Object value = null;
    try {
      value = constructor.newInstance(new Object[] {new Integer(handle)});
    } catch (Throwable e) {
      SWT.error(ERROR_NOT_IMPLEMENTED, e);
    }
    final Frame frame = ((Frame) (value));
    if ("1.3".equals(System.getProperty("java.specification.version"))) {
      parent.addListener(
          Activate,
          new Listener() {
            public void handleEvent(Event e) {
              EventQueue.invokeLater(
                  new Runnable() {
                    public void run() {
                      frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_ACTIVATED));
                      frame.dispatchEvent(new FocusEvent(frame, FocusEvent.FOCUS_GAINED));
                    }
                  });
            }
          });
      parent.addListener(
          Deactivate,
          new Listener() {
            public void handleEvent(Event e) {
              EventQueue.invokeLater(
                  new Runnable() {
                    public void run() {
                      frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_DEACTIVATED));
                      frame.dispatchEvent(new FocusEvent(frame, FocusEvent.FOCUS_LOST));
                    }
                  });
            }
          });
    }
    parent
        .getShell()
        .addListener(
            Move,
            new Listener() {
              public void handleEvent(Event e) {
                EventQueue.invokeLater(
                    new Runnable() {
                      public void run() {
                        frame.dispatchEvent(
                            new ComponentEvent(frame, ComponentEvent.COMPONENT_MOVED));
                      }
                    });
              }
            });
    parent.addListener(
        Dispose,
        new Listener() {
          public void handleEvent(Event event) {
            parent.setVisible(false);
            frame.dispose();
          }
        });
    return frame;
  }
}
