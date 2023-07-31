class PlaceHold {
  void createHandle() {
    int[] outControl = new int[1];
    int window = OS.GetControlOwner(handle);
    OS.CreateDataBrowserControl(window, null, kDataBrowserListView, outControl);
    if (outControl[0] == 0) {
      error(ERROR_NO_HANDLES);
    }
    handle = outControl[0];
    if (!drawFocusRing()) {
      OS.SetControlData(
          handle,
          kControlEntireControl,
          kControlDataBrowserIncludesFrameAndFocusTag,
          1,
          new byte[] {0});
    }
    int selectionFlags =
        ((style & SWT.SINGLE) != 0)
            ? OS.kDataBrowserSelectOnlyOne | OS.kDataBrowserNeverEmptySelectionSet
            : OS.kDataBrowserCmdTogglesSelection;
    OS.SetDataBrowserSelectionFlags(handle, selectionFlags);
    OS.SetDataBrowserListViewHeaderBtnHeight(handle, ((short) (0)));
    OS.SetDataBrowserTableViewHiliteStyle(handle, kDataBrowserTableViewFillHilite);
    OS.SetDataBrowserHasScrollBars(
        handle, (style & SWT.H_SCROLL) != 0, (style & SWT.V_SCROLL) != 0);
    if (OS.VERSION >= 0x1050) {
      OS.DataBrowserChangeAttributes(handle, kDataBrowserAttributeAutoHideScrollBars, 0);
    }
    DataBrowserListViewColumnDesc column = new DataBrowserListViewColumnDesc();
    column.headerBtnDesc_version = OS.kDataBrowserListViewLatestHeaderDesc;
    column.propertyDesc_propertyID = COLUMN_ID;
    column.propertyDesc_propertyType = OS.kDataBrowserTextType;
    column.propertyDesc_propertyFlags =
        OS.kDataBrowserListViewSelectionColumn | OS.kDataBrowserDefaultPropertyFlags;
    column.headerBtnDesc_maximumWidth = 0x7fff;
    column.headerBtnDesc_initialOrder = ((short) (OS.kDataBrowserOrderIncreasing));
    OS.AddDataBrowserListViewColumn(handle, column, 0);
    if (OS.VERSION < 0x1040) {
      OS.HIViewSetDrawingEnabled(handle, false);
      int size = 50;
      Rect rect = new Rect();
      rect.right = rect.bottom = ((short) (size));
      OS.SetControlBounds(handle, rect);
      int bpl = size * 4;
      int[] gWorld = new int[1];
      int data = OS.NewPtr(bpl * size);
      OS.NewGWorldFromPtr(gWorld, k32ARGBPixelFormat, rect, 0, 0, 0, data, bpl);
      int[] curPort = new int[1];
      int[] curGWorld = new int[1];
      OS.GetGWorld(curPort, curGWorld);
      OS.SetGWorld(gWorld[0], curGWorld[0]);
      OS.DrawControlInCurrentPort(handle);
      OS.SetGWorld(curPort[0], curGWorld[0]);
      OS.DisposeGWorld(gWorld[0]);
      OS.DisposePtr(data);
      rect.right = rect.bottom = ((short) (0));
      OS.SetControlBounds(handle, rect);
      OS.HIViewSetDrawingEnabled(handle, true);
    }
  }
}
