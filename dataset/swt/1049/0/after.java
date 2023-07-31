class GLCanvas {
  public GLCanvas(Composite parent, int style, GLData data) {
    super(parent, style);
    if (data == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int aglAttrib[] = new int[MAX_ATTRIBUTES];
    int pos = 0;
    aglAttrib[pos++] = AGL.AGL_RGBA;
    if (data.doubleBuffer) {
      aglAttrib[pos++] = AGL.AGL_DOUBLEBUFFER;
    }
    if (data.stereo) {
      aglAttrib[pos++] = AGL.AGL_STEREO;
    }
    if (data.redSize > 0) {
      aglAttrib[pos++] = AGL.AGL_RED_SIZE;
      aglAttrib[pos++] = data.redSize;
    }
    if (data.greenSize > 0) {
      aglAttrib[pos++] = AGL.AGL_GREEN_SIZE;
      aglAttrib[pos++] = data.greenSize;
    }
    if (data.blueSize > 0) {
      aglAttrib[pos++] = AGL.AGL_BLUE_SIZE;
      aglAttrib[pos++] = data.blueSize;
    }
    if (data.alphaSize > 0) {
      aglAttrib[pos++] = AGL.AGL_ALPHA_SIZE;
      aglAttrib[pos++] = data.alphaSize;
    }
    if (data.depthSize > 0) {
      aglAttrib[pos++] = AGL.AGL_DEPTH_SIZE;
      aglAttrib[pos++] = data.depthSize;
    }
    if (data.stencilSize > 0) {
      aglAttrib[pos++] = AGL.AGL_STENCIL_SIZE;
      aglAttrib[pos++] = data.stencilSize;
    }
    if (data.accumRedSize > 0) {
      aglAttrib[pos++] = AGL.AGL_ACCUM_RED_SIZE;
      aglAttrib[pos++] = data.accumRedSize;
    }
    if (data.accumGreenSize > 0) {
      aglAttrib[pos++] = AGL.AGL_ACCUM_GREEN_SIZE;
      aglAttrib[pos++] = data.accumGreenSize;
    }
    if (data.accumBlueSize > 0) {
      aglAttrib[pos++] = AGL.AGL_ACCUM_BLUE_SIZE;
      aglAttrib[pos++] = data.accumBlueSize;
    }
    if (data.accumAlphaSize > 0) {
      aglAttrib[pos++] = AGL.AGL_ACCUM_ALPHA_SIZE;
      aglAttrib[pos++] = data.accumAlphaSize;
    }
    if (data.sampleBuffers > 0) {
      aglAttrib[pos++] = AGL.AGL_SAMPLE_BUFFERS_ARB;
      aglAttrib[pos++] = data.sampleBuffers;
    }
    if (data.samples > 0) {
      aglAttrib[pos++] = AGL.AGL_SAMPLES_ARB;
      aglAttrib[pos++] = data.samples;
    }
    aglAttrib[pos++] = AGL.AGL_NONE;
    pixelFormat = AGL.aglChoosePixelFormat(0, 0, aglAttrib);
    if (pixelFormat == 0) {
      dispose();
      SWT.error(ERROR_UNSUPPORTED_DEPTH);
    }
    context = AGL.aglCreateContext(pixelFormat, 0);
    int window = OS.GetControlOwner(handle);
    int port = OS.GetWindowPort(window);
    AGL.aglSetDrawable(context, port);
    Listener listener =
        new Listener() {
          public void handleEvent(Event event) {
            switch (event.type) {
              case SWT.Dispose:
                AGL.aglDestroyContext(context);
                AGL.aglDestroyPixelFormat(pixelFormat);
                break;
              case SWT.Resize:
              case SWT.Hide:
              case SWT.Show:
                getDisplay()
                    .asyncExec(
                        new Runnable() {
                          public void run() {
                            fixBounds();
                          }
                        });
                break;
            }
          }
        };
    addListener(Resize, listener);
    Shell shell = getShell();
    shell.addListener(Resize, listener);
    shell.addListener(Show, listener);
    shell.addListener(Hide, listener);
    Control c = this;
    do {
      c.addListener(Show, listener);
      c.addListener(Hide, listener);
      c = c.getParent();
    } while (c != shell);
    addListener(Dispose, listener);
  }
}
