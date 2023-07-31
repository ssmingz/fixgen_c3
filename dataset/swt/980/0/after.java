class PlaceHold {
  void initializeList() {
    int shellHandle;
    int widgetHandle;
    int widgetClass = OS.TopLevelShellWidgetClass();
    shellHandle = OS.XtAppCreateShell(appName, appClass, widgetClass, xDisplay, null, 0);
    widgetHandle = OS.XmCreateScrolledList(shellHandle, null, null, 0);
    OS.XtManageChild(widgetHandle);
    OS.XtSetMappedWhenManaged(shellHandle, false);
    OS.XtRealizeWidget(shellHandle);
    int[] argList =
        new int[] {
          OS.XmNforeground,
          0,
          OS.XmNbackground,
          0,
          OS.XmNfontList,
          0,
          OS.XmNselectColor,
          0,
          OS.XmNhighlightColor,
          0
        };
    OS.XtGetValues(widgetHandle, argList, argList.length / 2);
    listForeground = argList[1];
    listBackground = argList[3];
    listFont = Font.motif_new(this, OS.XmFontListCopy(argList[5]));
    switch (argList[7]) {
      case OS.XmDEFAULT_SELECT_COLOR:
      case OS.XmREVERSED_GROUND_COLORS:
        listSelect = listForeground;
        break;
      case OS.XmHIGHLIGHT_COLOR:
        listSelect = argList[9];
        break;
      default:
        listSelect = argList[7];
    }
    OS.XtDestroyWidget(shellHandle);
  }
}
