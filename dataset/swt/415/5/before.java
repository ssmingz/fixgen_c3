class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int frameOptions = OS.kTXNDontDrawCaretWhenInactiveMask | OS.kTXNMonostyledTextMask;
    if ((style & SWT.H_SCROLL) != 0) {
      frameOptions |= OS.kTXNWantHScrollBarMask;
    }
    if ((style & SWT.V_SCROLL) != 0) {
      frameOptions |= OS.kTXNWantVScrollBarMask;
    }
    if ((style & SWT.SINGLE) != 0) {
      frameOptions |= OS.kTXNSingleLineOnlyMask;
    }
    if ((style & SWT.READ_ONLY) != 0) {
      frameOptions |= OS.kTXNReadOnlyMask;
    }
    if ((style & SWT.WRAP) != 0) {
      frameOptions |= OS.kTXNAlwaysWrapAtViewEdgeMask;
    }
    int parentHandle = parent.handle;
    handle =
        OS.NewControl(
            0,
            new Rect(),
            null,
            false,
            ((short)
                ((OS.kControlSupportsEmbedding | OS.kControlSupportsFocus)
                    | OS.kControlGetsFocusOnClick)),
            ((short) (0)),
            ((short) (0)),
            ((short) (kControlUserPaneProc)),
            0);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    MacUtil.addControl(handle, parentHandle);
    OS.HIViewSetVisible(handle, true);
    int wHandle = OS.GetControlOwner(parentHandle);
    int[] rootHandle = new int[1];
    OS.GetRootControl(wHandle, rootHandle);
    int root = rootHandle[0];
    short[] cnt = new short[1];
    OS.CountSubControls(root, cnt);
    short oldCount = cnt[0];
    int frameType = OS.kTXNTextEditStyleFrameType;
    int iFileType = OS.kTXNUnicodeTextFile;
    int iPermanentEncoding = OS.kTXNSystemDefaultEncoding;
    int[] tnxObject = new int[1];
    int[] frameID = new int[1];
    Rect bounds = new Rect();
    MacUtil.getControlBounds(handle, bounds);
    int status =
        OS.TXNNewObject(
            0,
            wHandle,
            bounds,
            frameOptions,
            frameType,
            iFileType,
            iPermanentEncoding,
            tnxObject,
            frameID,
            handle);
    if (status != OS.noErr) {
      error(ERROR_NO_HANDLES);
    }
    short[] newCnt = new short[1];
    OS.CountSubControls(root, newCnt);
    short newCount = newCnt[0];
    int[] child = new int[1];
    for (short i = newCount; i > oldCount; i--) {
      int rc = OS.GetIndexedSubControl(root, i, child);
      OS.HIViewAddSubview(handle, child[0]);
    }
    fTX = tnxObject[0];
    fFrameID = frameID[0];
    OS.TXNActivate(fTX, fFrameID, kScrollBarsSyncWithFocus);
    OS.TXNFocus(fTX, false);
    OS.TXNSetData(fTX, kTXNUnicodeTextData, new char[] {' '}, 2, 0, 0);
    OS.TXNSetData(fTX, kTXNUnicodeTextData, new char[0], 0, 0, 1);
    OS.setTXNMargins(fTX, ((short) (MARGIN)));
    OS.TXNSetTXNObjectControls(fTX, false, 1, new int[] {OS.kTXNDoFontSubstitution}, new int[] {1});
  }
}
