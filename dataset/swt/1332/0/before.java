class PlaceHold {
  public void swapBuffers() {
    checkWidget();
    glView.openGLContext().flushBuffer();
  }
}
