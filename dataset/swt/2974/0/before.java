class PlaceHold {
  void createHandle() {
    int features =
        (OS.kControlSupportsEmbedding | OS.kControlSupportsFocus) | OS.kControlGetsFocusOnClick;
    int[] outControl = new int[1];
    int window = OS.GetControlOwner(handle);
    OS.CreateUserPaneControl(window, null, features, outControl);
    if (outControl[0] == 0) {
      error(ERROR_NO_HANDLES);
    }
    handle = outControl[0];
    int[] theRoot = new int[1];
    OS.GetRootControl(window, theRoot);
    short[] oldCount = new short[1];
    OS.CountSubControls(theRoot[0], oldCount);
    int iFrameOptions = OS.kTXNDontDrawCaretWhenInactiveMask | OS.kTXNMonostyledTextMask;
    if ((style & SWT.H_SCROLL) != 0) {
      iFrameOptions |= OS.kTXNWantHScrollBarMask;
    }
    if ((style & SWT.V_SCROLL) != 0) {
      iFrameOptions |= OS.kTXNWantVScrollBarMask;
    }
    if ((style & SWT.SINGLE) != 0) {
      iFrameOptions |= OS.kTXNSingleLineOnlyMask;
    }
    if ((style & SWT.WRAP) != 0) {
      iFrameOptions |= OS.kTXNAlwaysWrapAtViewEdgeMask;
    }
    int[] oTXNObject = new int[1];
    int[] oTXNFrameID = new int[1];
    OS.TXNNewObject(
        0,
        window,
        null,
        iFrameOptions,
        kTXNTextEditStyleFrameType,
        kTXNUnicodeTextFile,
        kTXNSystemDefaultEncoding,
        oTXNObject,
        oTXNFrameID,
        0);
    if (oTXNObject[0] == 0) {
      error(ERROR_NO_HANDLES);
    }
    txnObject = oTXNObject[0];
    txnFrameID = oTXNFrameID[0];
    short[] newCount = new short[1];
    OS.CountSubControls(theRoot[0], newCount);
    int[] scrollBar = new int[1];
    for (int i = newCount[0]; i > oldCount[0]; --i) {
      OS.GetIndexedSubControl(theRoot[0], ((short) (i)), scrollBar);
      OS.HIViewRemoveFromSuperview(scrollBar[0]);
      OS.HIViewAddSubview(handle, scrollBar[0]);
    }
    int ptr = OS.NewPtr(sizeof);
    Rect rect = new Rect();
    if (hasBorder()) {
      OS.SetRect(rect, ((short) (1)), ((short) (1)), ((short) (1)), ((short) (1)));
    }
    OS.memcpy(ptr, rect, sizeof);
    int[] tags =
        new int[] {
          OS.kTXNDisableDragAndDropTag,
          OS.kTXNIOPrivilegesTag,
          OS.kTXNMarginsTag,
          OS.kTXNJustificationTag
        };
    int just = OS.kTXNFlushLeft;
    if ((style & SWT.CENTER) != 0) {
      just = OS.kTXNCenter;
    }
    if ((style & SWT.RIGHT) != 0) {
      just = OS.kTXNFlushRight;
    }
    int[] datas = new int[] {1, (style & SWT.READ_ONLY) != 0 ? 1 : 0, ptr, just};
    OS.TXNSetTXNObjectControls(txnObject, false, tags.length, tags, datas);
    OS.TXNSetFrameBounds(txnObject, 0, 0, 0, 0, txnFrameID);
    OS.DisposePtr(ptr);
    char[] buffer = new char[] {' '};
    OS.TXNSetData(txnObject, kTXNUnicodeTextData, buffer, 2, kTXNStartOffset, kTXNEndOffset);
    OS.TXNSetData(txnObject, kTXNUnicodeTextData, buffer, 0, kTXNStartOffset, kTXNEndOffset);
  }
}
