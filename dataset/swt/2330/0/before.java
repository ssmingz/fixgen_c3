class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int border = getBorderWidth();
    int width = border * 2;
    int height = border * 2;
    if ((style & SWT.ARROW) != 0) {
      width += display.scrolledMarginX;
      height += display.scrolledMarginY;
      if (wHint != SWT.DEFAULT) {
        width = wHint + (border * 2);
      }
      if (hHint != SWT.DEFAULT) {
        height = hHint + (border * 2);
      }
      return new Point(width, height);
    }
    XtWidgetGeometry result = new XtWidgetGeometry();
    result.request_mode = OS.CWWidth | OS.CWHeight;
    int[] argList2 = new int[] {OS.XmNrecomputeSize, 1};
    OS.XtSetValues(handle, argList2, argList2.length / 2);
    OS.XtQueryGeometry(handle, null, result);
    int[] argList3 = new int[] {OS.XmNrecomputeSize, 0};
    OS.XtSetValues(handle, argList3, argList3.length / 2);
    width += result.width;
    height += result.height;
    int[] argList = new int[] {OS.XmNlabelType, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    if (argList[1] == OS.XmSTRING) {
      int[] argList1 = new int[] {OS.XmNlabelString, 0};
      OS.XtGetValues(handle, argList1, argList1.length / 2);
      int xmString = argList1[1];
      if (OS.XmStringEmpty(xmString)) {
        height += getFontHeight();
      }
      if (xmString != 0) {
        OS.XmStringFree(xmString);
      }
    }
    if ((wHint != SWT.DEFAULT) || (hHint != SWT.DEFAULT)) {
      int[] argList4 =
          new int[] {
            OS.XmNmarginLeft, 0, OS.XmNmarginRight, 0, OS.XmNmarginTop, 0, OS.XmNmarginBottom, 0
          };
      OS.XtGetValues(handle, argList4, argList4.length / 2);
      if (wHint != SWT.DEFAULT) {
        width = ((wHint + argList4[1]) + argList4[3]) + (border * 2);
      }
      if (hHint != SWT.DEFAULT) {
        height = ((hHint + argList4[5]) + argList4[7]) + (border * 2);
      }
    }
    return new Point(width, height);
  }
}
