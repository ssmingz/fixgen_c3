class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int clazz = Display.PtComboBox;
    int parentHandle = parent.parentingHandle();
    int textFlags = ((style & SWT.READ_ONLY) != 0) ? 0 : OS.Pt_EDITABLE;
    int[] args =
        new int[] {
          OS.Pt_ARG_TEXT_FLAGS,
          textFlags,
          OS.Pt_EDITABLE,
          OS.Pt_ARG_CBOX_MAX_VISIBLE_COUNT,
          5,
          0,
          OS.Pt_ARG_CBOX_FLAGS,
          (style & SWT.SIMPLE) != 0 ? OS.Pt_COMBOBOX_STATIC : 0,
          OS.Pt_COMBOBOX_STATIC,
          OS.Pt_ARG_RESIZE_FLAGS,
          0,
          OS.Pt_RESIZE_XY_BITS
        };
    handle = OS.PtCreateWidget(clazz, parentHandle, args.length / 3, args);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
