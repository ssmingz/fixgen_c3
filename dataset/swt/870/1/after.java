class PlaceHold {
  boolean setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    boolean changed = super.setBounds(x, y, width, height, move, resize);
    if (changed && resize) {
      if (focusHandle != 0) {
        int[] argList = new int[] {OS.XmNwidth, 0, OS.XmNheight, 0};
        OS.XtGetValues(handle, argList, argList.length / 2);
        OS.XtConfigureWidget(focusHandle, 0, 0, argList[1], argList[3], 0);
      }
      if (layout != null) {
        layout.layout(this, false);
      }
      if (((state & CANVAS) != 0) && ((style & SWT.EMBEDDED) != 0)) {
        resizeClientWindow();
      }
    }
    return changed;
  }
}
