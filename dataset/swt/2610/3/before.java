class PlaceHold {
  void createHandle(int index) {
    int parentHandle = parent.topHandle();
    if ((style & SWT.BAR) != 0) {
      int[] args =
          new int[] {
            OS.Pt_ARG_FLAGS,
            0,
            OS.Pt_GETS_FOCUS,
            OS.Pt_ARG_FLAGS,
            OS.Pt_DELAY_REALIZE,
            OS.Pt_DELAY_REALIZE
          };
      handle = OS.PtCreateWidget(OS.PtMenuBar(), parentHandle, args.length / 3, args);
    } else {
      handle = OS.PtCreateWidget(OS.PtMenu(), parentHandle, 0, null);
    }
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
