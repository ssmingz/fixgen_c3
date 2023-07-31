class PlaceHold {
  public static Frame new_Frame(final Composite parent) {
    if (parent == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int handle = parent.embeddedHandle;
    Class clazz = null;
    try {
      clazz = Class.forName("sun.awt.X11.XEmbeddedFrame");
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
    try {
      Method method = clazz.getMethod("registerListeners", null);
      if (method != null) {
        method.invoke(value, null);
      }
    } catch (Throwable e) {
    }
    final Frame frame = ((Frame) (value));
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
