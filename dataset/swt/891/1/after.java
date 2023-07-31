class PlaceHold {
  void createHandle() {
    int[] outControl = new int[1];
    if (((style & SWT.MULTI) != 0) || ((style & (SWT.BORDER | SWT.SEARCH)) == 0)) {
      if ((style & (SWT.H_SCROLL | SWT.V_SCROLL)) != 0) {
        int options = 0;
        if ((style & (SWT.H_SCROLL | SWT.V_SCROLL)) == (SWT.H_SCROLL | SWT.V_SCROLL)) {
          options |= OS.kHIScrollViewOptionsAllowGrow;
        }
        if ((style & SWT.H_SCROLL) != 0) {
          options |= OS.kHIScrollViewOptionsHorizScroll;
        }
        if ((style & SWT.V_SCROLL) != 0) {
          options |= OS.kHIScrollViewOptionsVertScroll;
        }
        OS.HIScrollViewCreate(options, outControl);
        if (outControl[0] == 0) {
          error(ERROR_NO_HANDLES);
        }
        scrolledHandle = outControl[0];
        OS.HIViewSetVisible(scrolledHandle, true);
      }
      int iFrameOptions = OS.kTXNDontDrawCaretWhenInactiveMask | OS.kTXNMonostyledTextMask;
      if ((style & SWT.SINGLE) != 0) {
        iFrameOptions |= OS.kTXNSingleLineOnlyMask;
      }
      if ((style & SWT.WRAP) != 0) {
        iFrameOptions |= OS.kTXNAlwaysWrapAtViewEdgeMask;
      }
      OS.HITextViewCreate(null, 0, iFrameOptions, outControl);
      if (outControl[0] == 0) {
        error(ERROR_NO_HANDLES);
      }
      handle = outControl[0];
      OS.HIViewSetVisible(handle, true);
      txnObject = OS.HITextViewGetTXNObject(handle);
      int ptr = OS.NewPtr(sizeof);
      Rect rect = inset();
      OS.memmove(ptr, rect, sizeof);
      int[] tags =
          new int[] {
            OS.kTXNDisableDragAndDropTag,
            OS.kTXNDoFontSubstitution,
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
      int[] datas = new int[] {1, 1, (style & SWT.READ_ONLY) != 0 ? 1 : 0, ptr, just};
      OS.TXNSetTXNObjectControls(txnObject, false, tags.length, tags, datas);
      OS.DisposePtr(ptr);
    } else {
      if ((style & SWT.SEARCH) != 0) {
        int attributes = ((style & SWT.CANCEL) != 0) ? OS.kHISearchFieldAttributesCancel : 0;
        OS.HISearchFieldCreate(null, attributes, 0, 0, outControl);
      } else {
        int window = OS.GetControlOwner(handle);
        OS.CreateEditUnicodeTextControl(
            window, null, 0, (style & SWT.PASSWORD) != 0, null, outControl);
      }
      if (outControl[0] == 0) {
        error(ERROR_NO_HANDLES);
      }
      handle = outControl[0];
      OS.SetControlData(
          handle, kControlEntireControl, kControlEditTextSingleLineTag, 1, new byte[] {1});
      if ((style & SWT.READ_ONLY) != 0) {
        OS.SetControlData(
            handle, kControlEntireControl, kControlEditTextLockedTag, 1, new byte[] {1});
      }
      if ((style & (SWT.RIGHT | SWT.CENTER)) != 0) {
        ControlFontStyleRec fontStyle = new ControlFontStyleRec();
        fontStyle.flags |= OS.kControlUseJustMask;
        if ((style & SWT.CENTER) != 0) {
          fontStyle.just = OS.teJustCenter;
        }
        if ((style & SWT.RIGHT) != 0) {
          fontStyle.just = OS.teJustRight;
        }
        OS.SetControlFontStyle(handle, fontStyle);
      }
      if ((style & SWT.SEARCH) != 0) {
        OS.HIViewSetVisible(handle, true);
      }
    }
  }
}
