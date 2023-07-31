class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int border = ((style & SWT.BORDER) != 0) ? 1 : 0;
    int[] argList1 = new int[] {OS.XmNancestorSensitive, 1, OS.XmNborderWidth, border};
    int parentHandle = parent.handle;
    formHandle = OS.XmCreateForm(parentHandle, null, argList1, argList1.length / 2);
    if (formHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    int[] argList2 =
        new int[] {
          OS.XmNshadowType,
          shadowType(),
          OS.XmNtopAttachment,
          OS.XmATTACH_FORM,
          OS.XmNbottomAttachment,
          OS.XmATTACH_FORM,
          OS.XmNleftAttachment,
          OS.XmATTACH_FORM,
          OS.XmNrightAttachment,
          OS.XmATTACH_FORM,
          OS.XmNresizable,
          0
        };
    handle = OS.XmCreateFrame(formHandle, null, argList2, argList2.length / 2);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    int[] argList3 = new int[] {OS.XmNchildType, OS.XmFRAME_TITLE_CHILD};
    labelHandle = OS.XmCreateLabel(handle, null, argList3, argList3.length / 2);
    if (labelHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
