class PlaceHold {
  void initializeButton() {
    int shellHandle;
    int widgetHandle;
    int widgetClass = OS.TopLevelShellWidgetClass();
    shellHandle = OS.XtAppCreateShell(appName, appClass, widgetClass, xDisplay, null, 0);
    widgetHandle = OS.XmCreatePushButton(shellHandle, null, null, 0);
    OS.XtManageChild(widgetHandle);
    OS.XtSetMappedWhenManaged(shellHandle, false);
    OS.XtRealizeWidget(shellHandle);
    int[] argList =
        new int[] {
          OS.XmNforeground, 0, OS.XmNbackground, 0, OS.XmNshadowThickness, 0, OS.XmNfontList, 0
        };
    OS.XtGetValues(widgetHandle, argList, argList.length / 2);
    buttonForeground = argList[1];
    buttonBackground = argList[3];
    buttonShadowThickness = argList[5];
    buttonFont = Font.motif_new(this, OS.XmFontListCopy(argList[7]));
    OS.XtDestroyWidget(shellHandle);
  }
}
