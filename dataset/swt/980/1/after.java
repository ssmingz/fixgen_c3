class PlaceHold {
  void initializeLabel() {
    int shellHandle;
    int widgetHandle;
    int widgetClass = OS.TopLevelShellWidgetClass();
    shellHandle = OS.XtAppCreateShell(appName, appClass, widgetClass, xDisplay, null, 0);
    widgetHandle = OS.XmCreateLabel(shellHandle, null, null, 0);
    OS.XtManageChild(widgetHandle);
    OS.XtSetMappedWhenManaged(shellHandle, false);
    OS.XtRealizeWidget(shellHandle);
    int[] argList = new int[] {OS.XmNforeground, 0, OS.XmNbackground, 0, OS.XmNfontList, 0};
    OS.XtGetValues(widgetHandle, argList, argList.length / 2);
    labelForeground = argList[1];
    labelBackground = argList[3];
    labelFont = Font.motif_new(this, OS.XmFontListCopy(argList[5]));
    OS.XtDestroyWidget(shellHandle);
  }
}
