class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int[] argList1 =
        new int[] {
          OS.XmNverifyBell,
          0,
          OS.XmNeditMode,
          (style & SWT.SINGLE) != 0 ? OS.XmSINGLE_LINE_EDIT : OS.XmMULTI_LINE_EDIT,
          OS.XmNscrollHorizontal,
          (style & SWT.H_SCROLL) != 0 ? 1 : 0,
          OS.XmNscrollVertical,
          (style & SWT.V_SCROLL) != 0 ? 1 : 0,
          OS.XmNwordWrap,
          (style & SWT.WRAP) != 0 ? 1 : 0,
          OS.XmNeditable,
          (style & SWT.READ_ONLY) != 0 ? 0 : 1,
          OS.XmNcursorPositionVisible,
          ((style & SWT.READ_ONLY) != 0) && ((style & SWT.SINGLE) != 0) ? 0 : 1,
          OS.XmNancestorSensitive,
          1
        };
    int parentHandle = parent.handle;
    if ((style & SWT.SINGLE) != 0) {
      handle = OS.XmCreateTextField(parentHandle, null, argList1, argList1.length / 2);
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      int[] argList2 = new int[] {OS.XmNcursorPositionVisible, 0};
      OS.XtSetValues(handle, argList2, argList2.length / 2);
      if ((style & SWT.BORDER) == 0) {
        int[] argList3 = new int[] {OS.XmNmarginHeight, 0, OS.XmNshadowThickness, 0};
        OS.XtSetValues(handle, argList3, argList3.length / 2);
      }
    } else {
      handle = OS.XmCreateScrolledText(parentHandle, new byte[0], argList1, argList1.length / 2);
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      scrolledHandle = OS.XtParent(handle);
    }
  }
}
