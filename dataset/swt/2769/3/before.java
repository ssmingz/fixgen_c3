class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int parentHandle = parent.handle;
    int[] argList1 = new int[] {OS.XmNancestorSensitive, 1};
    formHandle = OS.XmCreateForm(parentHandle, null, argList1, argList1.length / 2);
    if (formHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    int selectionPolicy = OS.XmBROWSE_SELECT;
    int listSizePolicy = OS.XmCONSTANT;
    if ((style & SWT.MULTI) != 0) {
      selectionPolicy = OS.XmEXTENDED_SELECT;
      if ((style & SWT.SIMPLE) != 0) {
        selectionPolicy = OS.XmMULTIPLE_SELECT;
      }
    }
    if ((style & SWT.H_SCROLL) == 0) {
      listSizePolicy = OS.XmVARIABLE;
    }
    int[] argList2 =
        new int[] {
          OS.XmNlistSizePolicy,
          listSizePolicy,
          OS.XmNselectionPolicy,
          selectionPolicy,
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
    if ((style & (SWT.H_SCROLL | SWT.V_SCROLL)) == 0) {
      handle = OS.XmCreateList(formHandle, null, argList2, argList2.length / 2);
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
    } else {
      handle = OS.XmCreateScrolledList(formHandle, null, argList2, argList2.length / 2);
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      scrolledHandle = OS.XtParent(handle);
    }
  }
}
