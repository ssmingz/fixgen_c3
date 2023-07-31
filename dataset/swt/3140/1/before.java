class GLCanvas {
  public GLCanvas(Composite parent, int style, GLData data) {
    super(parent, style);
    if (data == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int glxAttrib[] = new int[MAX_ATTRIBUTES];
    int pos = 0;
    glxAttrib[pos++] = GLX.GLX_RGBA;
    if (data.doubleBuffer) {
      glxAttrib[pos++] = GLX.GLX_DOUBLEBUFFER;
    }
    if (data.stereo) {
      glxAttrib[pos++] = GLX.GLX_STEREO;
    }
    if (data.redSize > 0) {
      glxAttrib[pos++] = GLX.GLX_RED_SIZE;
      glxAttrib[pos++] = data.redSize;
    }
    if (data.greenSize > 0) {
      glxAttrib[pos++] = GLX.GLX_GREEN_SIZE;
      glxAttrib[pos++] = data.greenSize;
    }
    if (data.blueSize > 0) {
      glxAttrib[pos++] = GLX.GLX_BLUE_SIZE;
      glxAttrib[pos++] = data.blueSize;
    }
    if (data.alphaSize > 0) {
      glxAttrib[pos++] = GLX.GLX_ALPHA_SIZE;
      glxAttrib[pos++] = data.alphaSize;
    }
    if (data.depthSize > 0) {
      glxAttrib[pos++] = GLX.GLX_DEPTH_SIZE;
      glxAttrib[pos++] = data.depthSize;
    }
    if (data.stencilSize > 0) {
      glxAttrib[pos++] = GLX.GLX_STENCIL_SIZE;
      glxAttrib[pos++] = data.stencilSize;
    }
    if (data.accumRedSize > 0) {
      glxAttrib[pos++] = GLX.GLX_ACCUM_RED_SIZE;
      glxAttrib[pos++] = data.accumRedSize;
    }
    if (data.accumGreenSize > 0) {
      glxAttrib[pos++] = GLX.GLX_ACCUM_GREEN_SIZE;
      glxAttrib[pos++] = data.accumGreenSize;
    }
    if (data.accumBlueSize > 0) {
      glxAttrib[pos++] = GLX.GLX_ACCUM_BLUE_SIZE;
      glxAttrib[pos++] = data.accumBlueSize;
    }
    if (data.accumAlphaSize > 0) {
      glxAttrib[pos++] = GLX.GLX_ACCUM_ALPHA_SIZE;
      glxAttrib[pos++] = data.accumAlphaSize;
    }
    if (data.sampleBuffers > 0) {
      glxAttrib[pos++] = GLX.GLX_SAMPLE_BUFFERS;
      glxAttrib[pos++] = data.sampleBuffers;
    }
    if (data.samples > 0) {
      glxAttrib[pos++] = GLX.GLX_SAMPLES;
      glxAttrib[pos++] = data.samples;
    }
    glxAttrib[pos++] = 0;
    OS.gtk_widget_realize(handle);
    long window;
    if (OS.GTK_VERSION >= OS.VERSION(2, 14, 0)) {
      window = OS.gtk_widget_get_window(handle);
    } else {
      window = OS.GTK_WIDGET_WINDOW(handle);
    }
    long xDisplay = OS.gdk_x11_drawable_get_xdisplay(window);
    long infoPtr = GLX.glXChooseVisual(xDisplay, OS.XDefaultScreen(xDisplay), glxAttrib);
    if (infoPtr == 0) {
      dispose();
      SWT.error(ERROR_UNSUPPORTED_DEPTH);
    }
    vinfo = new XVisualInfo();
    GLX.memmove(vinfo, infoPtr, sizeof);
    OS.XFree(infoPtr);
    long screen = OS.gdk_screen_get_default();
    long gdkvisual = OS.gdk_x11_screen_lookup_visual(screen, vinfo.visualid);
    long share = (data.shareContext != null) ? data.shareContext.context : 0;
    context = GLX.glXCreateContext(xDisplay, vinfo, share, true);
    if (context == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    GdkWindowAttr attrs = new GdkWindowAttr();
    attrs.width = 1;
    attrs.height = 1;
    attrs.event_mask =
        (((((((((OS.GDK_KEY_PRESS_MASK | OS.GDK_KEY_RELEASE_MASK) | OS.GDK_FOCUS_CHANGE_MASK)
                                        | OS.GDK_POINTER_MOTION_MASK)
                                    | OS.GDK_BUTTON_PRESS_MASK)
                                | OS.GDK_BUTTON_RELEASE_MASK)
                            | OS.GDK_ENTER_NOTIFY_MASK)
                        | OS.GDK_LEAVE_NOTIFY_MASK)
                    | OS.GDK_EXPOSURE_MASK)
                | OS.GDK_VISIBILITY_NOTIFY_MASK)
            | OS.GDK_POINTER_MOTION_HINT_MASK;
    attrs.window_type = OS.GDK_WINDOW_CHILD;
    attrs.visual = gdkvisual;
    glWindow = OS.gdk_window_new(window, attrs, GDK_WA_VISUAL);
    OS.gdk_window_set_user_data(glWindow, handle);
    if ((style & SWT.NO_BACKGROUND) != 0) {
      OS.gdk_window_set_back_pixmap(window, 0, false);
    }
    if (OS.GTK_VERSION >= OS.VERSION(3, 0, 0)) {
      xWindow = OS.gdk_x11_window_get_xid(glWindow);
    } else {
      xWindow = OS.gdk_x11_drawable_get_xid(glWindow);
    }
    OS.gdk_window_show(glWindow);
    Listener listener =
        new Listener() {
          public void handleEvent(Event event) {
            switch (event.type) {
              case SWT.Paint:
                int[] viewport = new int[4];
                GLX.glGetIntegerv(GL_VIEWPORT, viewport);
                GLX.glViewport(viewport[0], viewport[1], viewport[2], viewport[3]);
                break;
              case SWT.Resize:
                Rectangle clientArea = getClientArea();
                OS.gdk_window_move(glWindow, clientArea.x, clientArea.y);
                OS.gdk_window_resize(glWindow, clientArea.width, clientArea.height);
                break;
              case SWT.Dispose:
                long window;
                if (OS.GTK_VERSION >= OS.VERSION(2, 14, 0)) {
                  window = OS.gtk_widget_get_window(handle);
                } else {
                  window = OS.GTK_WIDGET_WINDOW(handle);
                }
                long xDisplay = OS.gdk_x11_drawable_get_xdisplay(window);
                if (context != 0) {
                  if (GLX.glXGetCurrentContext() == context) {
                    GLX.glXMakeCurrent(xDisplay, 0, 0);
                  }
                  GLX.glXDestroyContext(xDisplay, context);
                  context = 0;
                }
                if (glWindow != 0) {
                  OS.gdk_window_destroy(glWindow);
                  glWindow = 0;
                }
                break;
            }
          }
        };
    addListener(Resize, listener);
    addListener(Paint, listener);
    addListener(Dispose, listener);
  }
}
