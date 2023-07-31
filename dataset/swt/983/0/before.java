class PlaceHold {
  public GLData getGLData() {
    checkWidget();
    int window = OS.GTK_WIDGET_WINDOW(handle);
    int xDisplay = OS.gdk_x11_drawable_get_xdisplay(window);
    GLData data = new GLData();
    int[] value = new int[1];
    GLX.glXGetConfig(xDisplay, vinfo, GLX_DOUBLEBUFFER, value);
    data.doubleBuffer = value[0] != 0;
    GLX.glXGetConfig(xDisplay, vinfo, GLX_STEREO, value);
    data.stereo = value[0] != 0;
    GLX.glXGetConfig(xDisplay, vinfo, GLX_RED_SIZE, value);
    data.redSize = value[0];
    GLX.glXGetConfig(xDisplay, vinfo, GLX_GREEN_SIZE, value);
    data.greenSize = value[0];
    GLX.glXGetConfig(xDisplay, vinfo, GLX_BLUE_SIZE, value);
    data.blueSize = value[0];
    GLX.glXGetConfig(xDisplay, vinfo, GLX_ALPHA_SIZE, value);
    data.alphaSize = value[0];
    GLX.glXGetConfig(xDisplay, vinfo, GLX_DEPTH_SIZE, value);
    data.depthSize = value[0];
    GLX.glXGetConfig(xDisplay, vinfo, GLX_STENCIL_SIZE, value);
    data.stencilSize = value[0];
    GLX.glXGetConfig(xDisplay, vinfo, GLX_ACCUM_RED_SIZE, value);
    data.accumRedSize = value[0];
    GLX.glXGetConfig(xDisplay, vinfo, GLX_ACCUM_GREEN_SIZE, value);
    data.accumGreenSize = value[0];
    GLX.glXGetConfig(xDisplay, vinfo, GLX_ACCUM_BLUE_SIZE, value);
    data.accumBlueSize = value[0];
    GLX.glXGetConfig(xDisplay, vinfo, GLX_ACCUM_ALPHA_SIZE, value);
    data.accumAlphaSize = value[0];
    GLX.glXGetConfig(xDisplay, vinfo, GLX_SAMPLE_BUFFERS, value);
    data.sampleBuffers = value[0];
    GLX.glXGetConfig(xDisplay, vinfo, GLX_SAMPLES, value);
    data.samples = value[0];
    return data;
  }
}
