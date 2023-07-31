class PlaceHold {
  void createHandle(int index) {
    int[] argList1 =
        new int[] {
          OS.XmNcolumns,
          2,
          OS.XmNdecimalPoints,
          0,
          OS.XmNincrementValue,
          1,
          OS.XmNminimumValue,
          0,
          OS.XmNmaximumValue,
          100,
          OS.XmNspinBoxChildType,
          OS.XmNUMERIC,
          OS.XmNeditable,
          (style & SWT.READ_ONLY) != 0 ? 0 : 1,
          OS.XmNshadowThickness,
          0,
          OS.XmNancestorSensitive,
          1
        };
    int parentHandle = parent.handle;
    handle = OS.XmCreateSimpleSpinBox(parentHandle, null, argList1, argList1.length / 2);
    int[] argList2 = new int[] {OS.XmNtextField, 0};
    OS.XtGetValues(handle, argList2, argList2.length / 2);
    int textHandle = argList2[1];
    int[] argList3 =
        new int[] {
          OS.XmNverifyBell, 0, OS.XmNcursorPositionVisible, (style & SWT.READ_ONLY) != 0 ? 0 : 1
        };
    OS.XtSetValues(textHandle, argList3, argList3.length / 2);
    if ((style & SWT.BORDER) == 0) {
      int[] argList4 = new int[] {OS.XmNmarginHeight, 0, OS.XmNshadowThickness, 0};
      OS.XtSetValues(textHandle, argList4, argList4.length / 2);
    }
  }
}
