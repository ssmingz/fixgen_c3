class PlaceHold {
  void initClasses() {
    if (OS.objc_lookUpClass("SWTView") != 0) {
      return;
    }
    Class clazz = getClass();
    dialogCallback3 = new Callback(clazz, "dialogProc", 3);
    int dialogProc3 = dialogCallback3.getAddress();
    if (dialogProc3 == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    dialogCallback4 = new Callback(clazz, "dialogProc", 4);
    int dialogProc4 = dialogCallback4.getAddress();
    if (dialogProc4 == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    dialogCallback5 = new Callback(clazz, "dialogProc", 5);
    int dialogProc5 = dialogCallback5.getAddress();
    if (dialogProc5 == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    windowCallback3 = new Callback(clazz, "windowProc", 3);
    int proc3 = windowCallback3.getAddress();
    if (proc3 == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    windowCallback2 = new Callback(clazz, "windowProc", 2);
    int proc2 = windowCallback2.getAddress();
    if (proc2 == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    windowCallback4 = new Callback(clazz, "windowProc", 4);
    int proc4 = windowCallback4.getAddress();
    if (proc4 == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    windowCallback5 = new Callback(clazz, "windowProc", 5);
    int proc5 = windowCallback5.getAddress();
    if (proc5 == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    windowCallback6 = new Callback(clazz, "windowProc", 6);
    int proc6 = windowCallback6.getAddress();
    if (proc6 == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    int isFlippedProc = OS.isFlipped_CALLBACK();
    int drawRectProc = OS.CALLBACK_drawRect_(proc3);
    int drawInteriorWithFrameInViewProc = OS.CALLBACK_drawInteriorWithFrame_inView_(proc4);
    int drawWithExpansionFrameProc = OS.CALLBACK_drawWithExpansionFrame_inView_(proc4);
    int imageRectForBoundsProc = OS.CALLBACK_imageRectForBounds_(proc3);
    int titleRectForBoundsProc = OS.CALLBACK_titleRectForBounds_(proc3);
    int cellSizeForBoundsProc = OS.CALLBACK_cellSizeForBounds_(proc3);
    int hitTestForEvent_inRect_ofViewProc = OS.CALLBACK_hitTestForEvent_inRect_ofView_(proc5);
    int cellSizeProc = OS.CALLBACK_cellSize(proc2);
    int drawImageWithFrameInViewProc = OS.CALLBACK_drawImage_withFrame_inView_(proc5);
    int drawTitleWithFrameInViewProc = OS.CALLBACK_drawTitle_withFrame_inView_(proc5);
    int setFrameOriginProc = OS.CALLBACK_setFrameOrigin_(proc3);
    int setFrameSizeProc = OS.CALLBACK_setFrameSize_(proc3);
    int hitTestProc = OS.CALLBACK_hitTest_(proc3);
    int markedRangeProc = OS.CALLBACK_markedRange(proc2);
    int selectedRangeProc = OS.CALLBACK_selectedRange(proc2);
    int highlightSelectionInClipRectProc = OS.CALLBACK_highlightSelectionInClipRect_(proc3);
    int setMarkedText_selectedRangeProc = OS.CALLBACK_setMarkedText_selectedRange_(proc4);
    int attributedSubstringFromRangeProc = OS.CALLBACK_attributedSubstringFromRange_(proc3);
    int characterIndexForPointProc = OS.CALLBACK_characterIndexForPoint_(proc3);
    int firstRectForCharacterRangeProc = OS.CALLBACK_firstRectForCharacterRange_(proc3);
    int textWillChangeSelectionProc =
        OS.CALLBACK_textView_willChangeSelectionFromCharacterRange_toCharacterRange_(proc5);
    int accessibilityHitTestProc = OS.CALLBACK_accessibilityHitTest_(proc3);
    int shouldChangeTextInRange_replacementString_Proc =
        OS.CALLBACK_shouldChangeTextInRange_replacementString_(proc4);
    int view_stringForToolTip_point_userDataProc =
        OS.CALLBACK_view_stringForToolTip_point_userData_(proc6);
    int canDragRowsWithIndexes_atPoint_Proc = OS.CALLBACK_canDragRowsWithIndexes_atPoint_(proc4);
    int setNeedsDisplayInRectProc = OS.CALLBACK_setNeedsDisplayInRect_(proc3);
    int expansionFrameWithFrameProc = OS.CALLBACK_expansionFrameWithFrame_inView_(proc4);
    int sizeOfLabelProc = OS.CALLBACK_sizeOfLabel_(proc3);
    int drawLabelInRectProc = OS.CALLBACK_drawLabel_inRect_(proc4);
    int drawViewBackgroundInRectProc = OS.CALLBACK_drawViewBackgroundInRect_(proc3);
    int drawBackgroundInClipRectProc = OS.CALLBACK_drawBackgroundInClipRect_(proc3);
    int scrollClipView_ToPointProc = OS.CALLBACK_scrollClipView_toPoint_(proc4);
    int headerRectOfColumnProc = OS.CALLBACK_headerRectOfColumn_(proc3);
    int columnAtPointProc = OS.CALLBACK_columnAtPoint_(proc3);
    String className;
    int cls;
    className = "SWTBox";
    cls = OS.objc_allocateClassPair(class_NSBox, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTButton";
    cls = OS.objc_allocateClassPair(class_NSButton, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_sendSelection, proc2, "@:");
    OS.class_addMethod(cls, sel_validateMenuItem_, proc3, "@:@");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    OS.objc_registerClassPair(cls);
    cls = registerCellSubclass(NSButton.cellClass(), size, align, types);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.class_addMethod(cls, sel_nextState, proc2, "@:");
    NSButton.setCellClass(cls);
    className = "SWTButtonCell";
    cls = OS.objc_allocateClassPair(class_NSButtonCell, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.class_addMethod(
        cls, sel_drawImage_withFrame_inView_, drawImageWithFrameInViewProc, "@:@{NSRect}@");
    OS.class_addMethod(
        cls, sel_drawTitle_withFrame_inView_, drawTitleWithFrameInViewProc, "@:@{NSRect}@");
    OS.class_addMethod(
        cls, sel_drawInteriorWithFrame_inView_, drawInteriorWithFrameInViewProc, "@:{NSRect}@");
    OS.class_addMethod(cls, sel_titleRectForBounds_, titleRectForBoundsProc, "@:{NSRect}");
    OS.class_addMethod(cls, sel_cellSizeForBounds_, cellSizeForBoundsProc, "@:{NSRect}");
    OS.objc_registerClassPair(cls);
    className = "SWTCanvasView";
    cls = OS.objc_allocateClassPair(class_NSView, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addProtocol(cls, protocol_NSTextInput);
    OS.class_addMethod(cls, sel_hasMarkedText, proc2, "@:");
    OS.class_addMethod(cls, sel_markedRange, markedRangeProc, "@:");
    OS.class_addMethod(cls, sel_selectedRange, selectedRangeProc, "@:");
    OS.class_addMethod(
        cls, sel_setMarkedText_selectedRange_, setMarkedText_selectedRangeProc, "@:@{NSRange}");
    OS.class_addMethod(cls, sel_unmarkText, proc2, "@:");
    OS.class_addMethod(cls, sel_validAttributesForMarkedText, proc2, "@:");
    OS.class_addMethod(
        cls, sel_attributedSubstringFromRange_, attributedSubstringFromRangeProc, "@:{NSRange}");
    OS.class_addMethod(cls, sel_insertText_, proc3, "@:@");
    OS.class_addMethod(cls, sel_characterIndexForPoint_, characterIndexForPointProc, "@:{NSPoint}");
    OS.class_addMethod(
        cls, sel_firstRectForCharacterRange_, firstRectForCharacterRangeProc, "@:{NSRange}");
    OS.class_addMethod(cls, sel_doCommandBySelector_, proc3, "@::");
    OS.class_addMethod(cls, sel_canBecomeKeyView, proc2, "@:");
    OS.class_addMethod(cls, sel_isFlipped, isFlippedProc, "@:");
    OS.class_addMethod(cls, sel_acceptsFirstResponder, proc2, "@:");
    OS.class_addMethod(cls, sel_isOpaque, proc2, "@:");
    OS.class_addMethod(cls, sel_updateOpenGLContext_, proc3, "@:@");
    OS.class_addMethod(cls, sel_clearDeferFlushing, proc2, "@:");
    OS.class_addMethod(cls, sel_validRequestorForSendType_returnType_, proc4, "@:@@");
    OS.class_addMethod(cls, sel_readSelectionFromPasteboard_, proc3, "@:@");
    OS.class_addMethod(cls, sel_writeSelectionToPasteboard_types_, proc4, "@:@@");
    OS.class_addMethod(cls, sel_viewWillMoveToWindow_, proc3, "@:@");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTComboBox";
    cls = OS.objc_allocateClassPair(class_NSComboBox, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_sendSelection, proc2, "@:");
    OS.class_addMethod(cls, sel_textDidChange_, proc3, "@:@");
    OS.class_addMethod(cls, sel_textViewDidChangeSelection_, proc3, "@:@");
    OS.class_addMethod(cls, sel_comboBoxSelectionDidChange_, proc3, "@:@");
    OS.class_addMethod(cls, sel_comboBoxWillDismiss_, proc3, "@:@");
    OS.class_addMethod(cls, sel_comboBoxWillPopUp_, proc3, "@:@");
    OS.class_addMethod(
        cls,
        sel_textView_willChangeSelectionFromCharacterRange_toCharacterRange_,
        textWillChangeSelectionProc,
        "@:@{NSRange}{NSRange}");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    cls = registerCellSubclass(NSComboBox.cellClass(), size, align, types);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.class_addMethod(cls, sel_setObjectValue_, proc3, "@:@");
    NSComboBox.setCellClass(cls);
    className = "SWTDatePicker";
    cls = OS.objc_allocateClassPair(class_NSDatePicker, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_isFlipped, proc2, "@:");
    OS.class_addMethod(cls, sel_sendSelection, proc2, "@:");
    OS.class_addMethod(cls, sel_sendVerticalSelection, proc2, "@:");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTEditorView";
    cls = OS.objc_allocateClassPair(class_NSTextView, className, 0);
    addEventMethods(cls, 0, proc3, drawRectProc, 0, 0);
    OS.class_addMethod(cls, sel_insertText_, proc3, "@:@");
    OS.class_addMethod(cls, sel_doCommandBySelector_, proc3, "@::");
    OS.class_addMethod(
        cls,
        sel_shouldChangeTextInRange_replacementString_,
        shouldChangeTextInRange_replacementString_Proc,
        "@:{NSRange}@");
    OS.objc_registerClassPair(cls);
    className = "SWTImageView";
    cls = OS.objc_allocateClassPair(class_NSImageView, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_isFlipped, isFlippedProc, "@:");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    cls = registerCellSubclass(NSImageView.cellClass(), size, align, types);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    NSImageView.setCellClass(cls);
    className = "SWTImageTextCell";
    cls = OS.objc_allocateClassPair(class_NSTextFieldCell, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addIvar(cls, SWT_IMAGE, size, ((byte) (align)), types);
    OS.class_addIvar(cls, SWT_ROW, size, ((byte) (align)), types);
    OS.class_addIvar(cls, SWT_COLUMN, size, ((byte) (align)), types);
    OS.class_addMethod(
        cls, sel_drawInteriorWithFrame_inView_, drawInteriorWithFrameInViewProc, "@:{NSRect}@");
    OS.class_addMethod(
        cls, sel_drawWithExpansionFrame_inView_, drawWithExpansionFrameProc, "@:{NSRect}@");
    OS.class_addMethod(cls, sel_imageRectForBounds_, imageRectForBoundsProc, "@:{NSRect}");
    OS.class_addMethod(cls, sel_titleRectForBounds_, titleRectForBoundsProc, "@:{NSRect}");
    OS.class_addMethod(
        cls, sel_hitTestForEvent_inRect_ofView_, hitTestForEvent_inRect_ofViewProc, "@:@{NSRect}@");
    OS.class_addMethod(cls, sel_cellSize, cellSizeProc, "@:");
    OS.class_addMethod(cls, sel_image, proc2, "@:");
    OS.class_addMethod(cls, sel_setImage_, proc3, "@:@");
    OS.class_addMethod(
        cls, sel_expansionFrameWithFrame_inView_, expansionFrameWithFrameProc, "@:{NSRect}@");
    OS.objc_registerClassPair(cls);
    className = "SWTMenu";
    cls = OS.objc_allocateClassPair(class_NSMenu, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_menuWillOpen_, proc3, "@:@");
    OS.class_addMethod(cls, sel_menuDidClose_, proc3, "@:@");
    OS.class_addMethod(cls, sel_menu_willHighlightItem_, proc4, "@:@@");
    OS.class_addMethod(cls, sel_menuNeedsUpdate_, proc3, "@:@");
    OS.objc_registerClassPair(cls);
    className = "SWTMenuItem";
    cls = OS.objc_allocateClassPair(class_NSMenuItem, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_sendSelection, proc2, "@:");
    OS.objc_registerClassPair(cls);
    className = "SWTOutlineView";
    cls = OS.objc_allocateClassPair(class_NSOutlineView, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(
        cls, sel_highlightSelectionInClipRect_, highlightSelectionInClipRectProc, "@:{NSRect}");
    OS.class_addMethod(cls, sel_sendDoubleSelection, proc2, "@:");
    OS.class_addMethod(cls, sel_outlineViewSelectionDidChange_, proc3, "@:@");
    OS.class_addMethod(cls, sel_outlineViewSelectionIsChanging_, proc3, "@:@");
    OS.class_addMethod(cls, sel_outlineView_child_ofItem_, proc5, "@:@i@");
    OS.class_addMethod(cls, sel_outlineView_isItemExpandable_, proc4, "@:@@");
    OS.class_addMethod(cls, sel_outlineView_numberOfChildrenOfItem_, proc4, "@:@@");
    OS.class_addMethod(cls, sel_outlineView_selectionIndexesForProposedSelection_, proc4, "@:@@");
    OS.class_addMethod(cls, sel_outlineView_objectValueForTableColumn_byItem_, proc5, "@:@@@");
    OS.class_addMethod(cls, sel_outlineView_willDisplayCell_forTableColumn_item_, proc6, "@:@@@@");
    OS.class_addMethod(cls, sel_outlineView_shouldReorderColumn_toColumn_, proc5, "@:@ii");
    OS.class_addMethod(cls, sel_outlineView_setObjectValue_forTableColumn_byItem_, proc6, "@:@@@@");
    OS.class_addMethod(cls, sel_outlineView_shouldEditTableColumn_item_, proc5, "@:@@@");
    OS.class_addMethod(cls, sel_outlineView_shouldTrackCell_forTableColumn_item_, proc6, "@:@@@i");
    OS.class_addMethod(cls, sel_outlineViewColumnDidMove_, proc3, "@:@");
    OS.class_addMethod(cls, sel_outlineViewColumnDidResize_, proc3, "@:@");
    OS.class_addMethod(cls, sel_outlineView_didClickTableColumn_, proc4, "@:@@");
    OS.class_addMethod(
        cls,
        sel_canDragRowsWithIndexes_atPoint_,
        canDragRowsWithIndexes_atPoint_Proc,
        "@:@{NSPoint=ff}");
    OS.class_addMethod(cls, sel_outlineView_writeItems_toPasteboard_, proc5, "@:@@@");
    OS.class_addMethod(cls, sel_expandItem_expandChildren_, proc4, "@:@Z");
    OS.class_addMethod(cls, sel_collapseItem_collapseChildren_, proc4, "@:@Z");
    OS.class_addMethod(
        cls, sel_drawBackgroundInClipRect_, drawBackgroundInClipRectProc, "@:{NSRect}");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTPanelDelegate";
    cls = OS.objc_allocateClassPair(class_NSObject, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_windowWillClose_, dialogProc3, "@:@");
    OS.class_addMethod(cls, sel_changeColor_, dialogProc3, "@:@");
    OS.class_addMethod(cls, sel_setColor_forAttribute_, dialogProc4, "@:@@");
    OS.class_addMethod(cls, sel_changeFont_, dialogProc3, "@:@");
    OS.class_addMethod(cls, sel_sendSelection_, dialogProc3, "@:@");
    OS.class_addMethod(cls, sel_panel_shouldShowFilename_, dialogProc4, "@:@@");
    OS.class_addMethod(cls, sel_panelDidEnd_returnCode_contextInfo_, dialogProc5, "@:@i@");
    OS.objc_registerClassPair(cls);
    className = "SWTPopUpButton";
    cls = OS.objc_allocateClassPair(class_NSPopUpButton, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_sendSelection, proc2, "@:");
    OS.class_addMethod(cls, sel_menuWillOpen_, proc3, "@:@");
    OS.class_addMethod(cls, sel_menuDidClose_, proc3, "@:@");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    cls = registerCellSubclass(NSPopUpButton.cellClass(), size, align, types);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    NSPopUpButton.setCellClass(cls);
    className = "SWTProgressIndicator";
    cls = OS.objc_allocateClassPair(class_NSProgressIndicator, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_viewDidMoveToWindow, proc2, "@:");
    OS.class_addMethod(cls, sel__drawThemeProgressArea_, proc3, "@:c");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTScroller";
    cls = OS.objc_allocateClassPair(class_NSScroller, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_sendSelection, proc2, "@:");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTScrollView";
    cls = OS.objc_allocateClassPair(class_NSScrollView, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_sendVerticalSelection, proc2, "@:");
    OS.class_addMethod(cls, sel_sendHorizontalSelection, proc2, "@:");
    OS.class_addMethod(cls, sel_pageDown_, proc3, "@:@");
    OS.class_addMethod(cls, sel_pageUp_, proc3, "@:@");
    OS.class_addMethod(cls, sel_reflectScrolledClipView_, proc3, "@:@");
    OS.class_addMethod(
        cls, sel_scrollClipView_toPoint_, scrollClipView_ToPointProc, "@:@{NSPoint}");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTSearchField";
    cls = OS.objc_allocateClassPair(class_NSSearchField, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.class_addMethod(cls, sel_textDidChange_, proc3, "@:@");
    OS.class_addMethod(cls, sel_textViewDidChangeSelection_, proc3, "@:@");
    OS.class_addMethod(
        cls,
        sel_textView_willChangeSelectionFromCharacterRange_toCharacterRange_,
        textWillChangeSelectionProc,
        "@:@{NSRange}{NSRange}");
    OS.class_addMethod(cls, sel_sendSearchSelection, proc2, "@:");
    OS.class_addMethod(cls, sel_sendCancelSelection, proc2, "@:");
    OS.objc_registerClassPair(cls);
    cls = registerCellSubclass(NSSearchField.cellClass(), size, align, types);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    NSSearchField.setCellClass(cls);
    className = "SWTSecureTextField";
    cls = OS.objc_allocateClassPair(class_NSSecureTextField, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.class_addMethod(cls, sel_textDidChange_, proc3, "@:@");
    OS.class_addMethod(cls, sel_textViewDidChangeSelection_, proc3, "@:@");
    OS.class_addMethod(
        cls,
        sel_textView_willChangeSelectionFromCharacterRange_toCharacterRange_,
        textWillChangeSelectionProc,
        "@:@{NSRange}{NSRange}");
    OS.objc_registerClassPair(cls);
    int nsSecureTextViewClass = OS.objc_lookUpClass("NSSecureTextView");
    if (nsSecureTextViewClass != 0) {
      className = "SWTSecureEditorView";
      cls = OS.objc_allocateClassPair(nsSecureTextViewClass, className, 0);
      addEventMethods(cls, 0, proc3, drawRectProc, 0, 0);
      OS.class_addMethod(cls, sel_insertText_, proc3, "@:@");
      OS.class_addMethod(cls, sel_doCommandBySelector_, proc3, "@::");
      OS.class_addMethod(
          cls,
          sel_shouldChangeTextInRange_replacementString_,
          shouldChangeTextInRange_replacementString_Proc,
          "@:{NSRange}@");
      OS.objc_registerClassPair(cls);
    }
    className = "SWTSlider";
    cls = OS.objc_allocateClassPair(class_NSSlider, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_sendSelection, proc2, "@:");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    cls = registerCellSubclass(NSSlider.cellClass(), size, align, types);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    NSSlider.setCellClass(cls);
    className = "SWTStepper";
    cls = OS.objc_allocateClassPair(class_NSStepper, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_sendSelection, proc2, "@:");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    cls = registerCellSubclass(NSStepper.cellClass(), size, align, types);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    NSStepper.setCellClass(cls);
    className = "SWTTableHeaderCell";
    cls = OS.objc_allocateClassPair(class_NSTableHeaderCell, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(
        cls, sel_drawInteriorWithFrame_inView_, drawInteriorWithFrameInViewProc, "@:{NSRect}@");
    OS.objc_registerClassPair(cls);
    className = "SWTTableHeaderView";
    cls = OS.objc_allocateClassPair(class_NSTableHeaderView, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_mouseDown_, proc3, "@:@");
    OS.class_addMethod(cls, sel_resetCursorRects, proc2, "@:");
    OS.class_addMethod(cls, sel_updateTrackingAreas, proc2, "@:");
    OS.class_addMethod(cls, sel_menuForEvent_, proc3, "@:@");
    OS.class_addMethod(cls, sel_headerRectOfColumn_, headerRectOfColumnProc, "@:i");
    OS.class_addMethod(cls, sel_columnAtPoint_, columnAtPointProc, "@:{NSPoint}");
    OS.objc_registerClassPair(cls);
    className = "SWTTableView";
    cls = OS.objc_allocateClassPair(class_NSTableView, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(
        cls, sel_highlightSelectionInClipRect_, highlightSelectionInClipRectProc, "@:{NSRect}");
    OS.class_addMethod(cls, sel_sendDoubleSelection, proc2, "@:");
    OS.class_addMethod(cls, sel_numberOfRowsInTableView_, proc3, "@:@");
    OS.class_addMethod(cls, sel_tableView_objectValueForTableColumn_row_, proc5, "@:@@i");
    OS.class_addMethod(cls, sel_tableView_shouldEditTableColumn_row_, proc5, "@:@@i");
    OS.class_addMethod(cls, sel_tableView_shouldReorderColumn_toColumn_, proc5, "@:@ii");
    OS.class_addMethod(cls, sel_tableView_shouldTrackCell_forTableColumn_row_, proc6, "@:@@@i");
    OS.class_addMethod(cls, sel_tableViewSelectionIsChanging_, proc3, "@:@");
    OS.class_addMethod(cls, sel_tableViewSelectionDidChange_, proc3, "@:@");
    OS.class_addMethod(cls, sel_tableView_willDisplayCell_forTableColumn_row_, proc6, "@:@@@i");
    OS.class_addMethod(cls, sel_tableView_setObjectValue_forTableColumn_row_, proc6, "@:@@@i");
    OS.class_addMethod(cls, sel_tableViewColumnDidMove_, proc3, "@:@");
    OS.class_addMethod(cls, sel_tableViewColumnDidResize_, proc3, "@:@");
    OS.class_addMethod(cls, sel_tableView_didClickTableColumn_, proc4, "@:@@");
    OS.class_addMethod(cls, sel_tableView_selectionIndexesForProposedSelection_, proc4, "@:@@");
    OS.class_addMethod(
        cls,
        sel_canDragRowsWithIndexes_atPoint_,
        canDragRowsWithIndexes_atPoint_Proc,
        "@:@{NSPoint=ff}");
    OS.class_addMethod(cls, sel_tableView_writeRowsWithIndexes_toPasteboard_, proc5, "@:@@@");
    OS.class_addMethod(
        cls, sel_drawBackgroundInClipRect_, drawBackgroundInClipRectProc, "@:{NSRect}");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTTabView";
    cls = OS.objc_allocateClassPair(class_NSTabView, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_tabView_willSelectTabViewItem_, proc4, "@:@@");
    OS.class_addMethod(cls, sel_tabView_didSelectTabViewItem_, proc4, "@:@@");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTTabViewItem";
    cls = OS.objc_allocateClassPair(class_NSTabViewItem, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_sizeOfLabel_, sizeOfLabelProc, "@::");
    OS.class_addMethod(cls, sel_drawLabel_inRect_, drawLabelInRectProc, "@::{NSRect}");
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTTextView";
    cls = OS.objc_allocateClassPair(class_NSTextView, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.class_addMethod(cls, sel_insertText_, proc3, "@:@");
    OS.class_addMethod(cls, sel_doCommandBySelector_, proc3, "@::");
    OS.class_addMethod(cls, sel_textDidChange_, proc3, "@:@");
    OS.class_addMethod(cls, sel_textView_clickedOnLink_atIndex_, proc5, "@:@@@");
    OS.class_addMethod(cls, sel_dragSelectionWithEvent_offset_slideBack_, proc5, "@:@@@");
    OS.class_addMethod(
        cls,
        sel_shouldChangeTextInRange_replacementString_,
        shouldChangeTextInRange_replacementString_Proc,
        "@:{NSRange}@");
    OS.class_addMethod(
        cls, sel_drawViewBackgroundInRect_, drawViewBackgroundInRectProc, "@:{NSRect}");
    OS.class_addMethod(cls, sel_shouldDrawInsertionPoint, proc2, "@:");
    OS.objc_registerClassPair(cls);
    className = "SWTTextField";
    cls = OS.objc_allocateClassPair(class_NSTextField, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.class_addMethod(cls, sel_acceptsFirstResponder, proc2, "@:");
    OS.class_addMethod(cls, sel_textDidChange_, proc3, "@:@");
    OS.class_addMethod(cls, sel_textDidEndEditing_, proc3, "@:@");
    OS.class_addMethod(cls, sel_textViewDidChangeSelection_, proc3, "@:@");
    OS.class_addMethod(
        cls,
        sel_textView_willChangeSelectionFromCharacterRange_toCharacterRange_,
        textWillChangeSelectionProc,
        "@:@{NSRange}{NSRange}");
    OS.objc_registerClassPair(cls);
    cls = registerCellSubclass(NSTextField.cellClass(), size, align, types);
    OS.class_addMethod(
        cls, sel_drawInteriorWithFrame_inView_, drawInteriorWithFrameInViewProc, "@:{NSRect}@");
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    NSTextField.setCellClass(cls);
    className = "SWTTreeItem";
    cls = OS.objc_allocateClassPair(class_NSObject, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.objc_registerClassPair(cls);
    className = "SWTView";
    cls = OS.objc_allocateClassPair(class_NSView, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_canBecomeKeyView, proc2, "@:");
    OS.class_addMethod(cls, sel_isFlipped, isFlippedProc, "@:");
    OS.class_addMethod(cls, sel_acceptsFirstResponder, proc2, "@:");
    OS.class_addMethod(cls, sel_isOpaque, proc2, "@:");
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTWindow";
    createWindowSubclass(class_NSWindow, className, false);
    className = "SWTPanel";
    cls = OS.objc_allocateClassPair(class_NSPanel, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_sendEvent_, proc3, "@:@");
    OS.class_addMethod(cls, sel_helpRequested_, proc3, "@:@");
    OS.class_addMethod(cls, sel_canBecomeKeyWindow, proc2, "@:");
    OS.class_addMethod(cls, sel_becomeKeyWindow, proc2, "@:");
    OS.class_addMethod(cls, sel_makeFirstResponder_, proc3, "@:@");
    OS.class_addMethod(cls, sel_noResponderFor_, proc3, "@:@");
    OS.class_addMethod(
        cls,
        sel_view_stringForToolTip_point_userData_,
        view_stringForToolTip_point_userDataProc,
        "@:@i{NSPoint}@");
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTToolbar";
    cls = OS.objc_allocateClassPair(class_NSToolbar, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(
        cls, sel_toolbar_itemForItemIdentifier_willBeInsertedIntoToolbar_, proc5, "@:@@Z");
    OS.class_addMethod(cls, sel_toolbarAllowedItemIdentifiers_, proc3, "@:@");
    OS.class_addMethod(cls, sel_toolbarDefaultItemIdentifiers_, proc3, "@:@");
    OS.class_addMethod(cls, sel_toolbarSelectableItemIdentifiers_, proc3, "@:@");
    addAccessibilityMethods(cls, proc2, proc3, proc4, accessibilityHitTestProc);
    OS.objc_registerClassPair(cls);
    className = "SWTToolbarView";
    cls = OS.objc_allocateClassPair(class_NSToolbarView, className, 0);
    addEventMethods(cls, proc2, proc3, drawRectProc, hitTestProc, setNeedsDisplayInRectProc);
    addFrameMethods(cls, setFrameOriginProc, setFrameSizeProc);
    OS.objc_registerClassPair(cls);
    className = "SWTWindowDelegate";
    cls = OS.objc_allocateClassPair(class_NSObject, className, 0);
    OS.class_addIvar(cls, SWT_OBJECT, size, ((byte) (align)), types);
    OS.class_addMethod(cls, sel_windowDidResize_, proc3, "@:@");
    OS.class_addMethod(cls, sel_windowDidMove_, proc3, "@:@");
    OS.class_addMethod(cls, sel_windowShouldClose_, proc3, "@:@");
    OS.class_addMethod(cls, sel_windowWillClose_, proc3, "@:@");
    OS.class_addMethod(cls, sel_windowDidResignKey_, proc3, "@:@");
    OS.class_addMethod(cls, sel_windowDidBecomeKey_, proc3, "@:@");
    OS.class_addMethod(cls, sel_timerProc_, proc3, "@:@");
    OS.class_addMethod(cls, sel_systemSettingsChanged_, proc3, "@:@");
    OS.class_addMethod(cls, sel_windowDidMiniaturize_, proc3, "@:@");
    OS.class_addMethod(cls, sel_windowDidDeminiaturize_, proc3, "@:@");
    OS.objc_registerClassPair(cls);
  }
}
