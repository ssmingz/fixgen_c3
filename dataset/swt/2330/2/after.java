class PlaceHold {
  public int getTextHeight() {
    checkWidget();
    if ((style & SWT.DROP_DOWN) != 0) {
      if (!OS.XtIsRealized(handle)) {
        getShell().realizeWidget();
      }
      XtWidgetGeometry result = new XtWidgetGeometry();
      result.request_mode = OS.CWHeight;
      OS.XtQueryGeometry(handle, null, result);
      return result.height;
    } else {
      int[] argList = new int[] {OS.XmNtextField, 0};
      OS.XtGetValues(handle, argList, argList.length / 2);
      int[] argList2 = new int[] {OS.XmNmarginHeight, 0};
      OS.XtGetValues(argList[1], argList2, argList2.length / 2);
      int height = getFontHeight(handle);
      XRectangle rect = new XRectangle();
      OS.XmWidgetGetDisplayRect(argList[1], rect);
      height += (rect.y * 2) + (2 * argList2[1]);
      int[] argList3 =
          new int[] {OS.XmNmarginHeight, 0, OS.XmNshadowThickness, 0, OS.XmNhighlightThickness, 0};
      OS.XtGetValues(handle, argList3, argList3.length / 2);
      height += ((2 * argList3[1]) + (2 * argList3[3])) + (2 * argList3[5]);
      return height;
    }
  }
}
