class PlaceHold {
  public GLData getGLData() {
    checkWidget();
    GLData data = new GLData();
    int[] value = new int[1];
    pixelFormat.getValues(value, NSOpenGLPFADoubleBuffer, 0);
    data.doubleBuffer = value[0] != 0;
    pixelFormat.getValues(value, NSOpenGLPFAStereo, 0);
    data.stereo = value[0] != 0;
    pixelFormat.getValues(value, NSOpenGLPFAAlphaSize, 0);
    data.alphaSize = ((int) (value[0]));
    pixelFormat.getValues(value, NSOpenGLPFAColorSize, 0);
    int colorSize = ((int) (value[0] - data.alphaSize)) / 3;
    data.redSize = colorSize;
    data.greenSize = colorSize;
    data.blueSize = colorSize;
    pixelFormat.getValues(value, NSOpenGLPFADepthSize, 0);
    data.depthSize = ((int) (value[0]));
    pixelFormat.getValues(value, NSOpenGLPFAStencilSize, 0);
    data.stencilSize = ((int) (value[0]));
    pixelFormat.getValues(value, NSOpenGLPFAAccumSize, 0);
    int accumColorSize = ((int) (value[0])) / 4;
    data.accumRedSize = accumColorSize;
    data.accumGreenSize = accumColorSize;
    data.accumBlueSize = accumColorSize;
    data.accumAlphaSize = accumColorSize;
    pixelFormat.getValues(value, NSOpenGLPFASampleBuffers, 0);
    data.sampleBuffers = ((int) (value[0]));
    pixelFormat.getValues(value, NSOpenGLPFASamples, 0);
    data.samples = ((int) (value[0]));
    return data;
  }
}
