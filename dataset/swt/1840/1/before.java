class PlaceHold {
  void propagateWidget(boolean enabled) {
    super.propagateWidget(enabled);
    int[] argList = new int[] {OS.XmNmenuBar, 0};
    OS.XtGetValues(scrolledHandle, argList, argList.length / 2);
    if (argList[1] != 0) {
      propagateHandle(enabled, argList[1]);
    }
  }
}
