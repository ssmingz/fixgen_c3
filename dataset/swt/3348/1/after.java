class PlaceHold {
  public boolean forceFocus() {
    checkWidget();
    Control[] children = _getChildren();
    int[] traversals = new int[children.length];
    int[] argList = new int[] {OS.XmNtraversalOn, 0};
    for (int i = 0; i < children.length; i++) {
      OS.XtGetValues(children[i].handle, argList, argList.length / 2);
      traversals[i] = argList[1];
      argList[1] = 0;
      OS.XtSetValues(children[i].handle, argList, argList.length / 2);
    }
    boolean result = super.forceFocus();
    for (int i = 0; i < children.length; i++) {
      argList[1] = traversals[i];
      OS.XtSetValues(children[i].handle, argList, argList.length / 2);
      if (argList[1] != 0) {
        Display display = getDisplay();
        OS.XtOverrideTranslations(handle, display.tabTranslations);
        OS.XtOverrideTranslations(handle, display.arrowTranslations);
      }
    }
    return result;
  }
}
