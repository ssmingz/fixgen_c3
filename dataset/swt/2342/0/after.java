class PlaceHold {
  int helpProc(
      int inControl, int inGlobalMouse, int inRequest, int outContentProvided, int ioHelpContent) {
    switch (inRequest) {
      case OS.kHMSupplyContent:
        {
          if (!((toolTipText != null) && (toolTipText.length() != 0))) {
            Rect rect = new Rect();
            int window = OS.GetControlOwner(handle);
            OS.GetWindowBounds(window, ((short) (kWindowContentRgn)), rect);
            short windowLeft = rect.left;
            short windowTop = rect.top;
            Point pt = new Point();
            pt.h = ((short) ((inGlobalMouse & 0xffff) - windowLeft));
            pt.v = ((short) ((inGlobalMouse >> 16) - windowTop));
            if (!contains(pt.h, pt.v)) {
              break;
            }
            String toolTipText = null;
            int tagSide = OS.kHMAbsoluteCenterAligned;
            int x;
            int y;
            if (OS.HIVIEW) {
              CGPoint inPt = new CGPoint();
              int[] contentView = new int[1];
              OS.HIViewFindByID(
                  OS.HIViewGetRoot(OS.GetControlOwner(handle)),
                  OS.kHIViewWindowContentID(),
                  contentView);
              OS.HIViewConvertPoint(inPt, handle, contentView[0]);
              pt.h -= ((int) (inPt.x));
              pt.v -= ((int) (inPt.y));
              windowLeft += ((int) (inPt.x));
              windowTop += ((int) (inPt.y));
              x = pt.h;
              y = pt.v;
            } else {
              OS.GetControlBounds(handle, rect);
              x = pt.h - rect.left;
              y = pt.v - rect.top;
            }
            int headerHeight = getHeaderHeight();
            if ((headerHeight != 0) && ((0 <= y) && (y < headerHeight))) {
              int startX = 0;
              for (int i = 0; i < columnCount; i++) {
                TableColumn column = columns[i];
                int width = column.lastWidth;
                if ((startX <= x) && (x < (startX + width))) {
                  toolTipText = column.toolTipText;
                  rect.left = ((short) (startX));
                  rect.right = ((short) (rect.left + width));
                  rect.bottom = ((short) (rect.top + headerHeight));
                  tagSide = OS.kHMOutsideBottomRightAligned;
                  break;
                }
                startX += width;
              }
            } else {
              int columnIndex = 0;
              TableItem item = null;
              TableColumn column = null;
              int rc = OS.noErr;
              for (int i = getTopIndex();
                  ((i < itemCount) && (item == null)) && (rc == OS.noErr);
                  i++) {
                if (columnCount == 0) {
                  if ((rc =
                          OS.GetDataBrowserItemPartBounds(
                              handle, i + 1, column_id, kDataBrowserPropertyContentPart, rect))
                      == OS.noErr) {
                    if (OS.PtInRect(pt, rect)) {
                      item = _getItem(i);
                      break;
                    }
                  }
                } else {
                  for (int j = 0; j < columnCount; j++) {
                    column = columns[j];
                    if ((rc =
                            OS.GetDataBrowserItemPartBounds(
                                handle, i + 1, column.id, kDataBrowserPropertyContentPart, rect))
                        == OS.noErr) {
                      if (OS.PtInRect(pt, rect)) {
                        item = _getItem(i);
                        columnIndex = j;
                        break;
                      }
                    }
                  }
                }
              }
              if (item != null) {
                GC gc = new GC(this);
                int inset = getInsetWidth();
                int width = item.calculateWidth(columnIndex, gc) + inset;
                gc.dispose();
                short[] w = new short[1];
                OS.GetDataBrowserTableViewNamedColumnWidth(
                    handle, column == null ? column_id : column.id, w);
                if (width > w[0]) {
                  toolTipText = item.getText(columnIndex);
                  Image image = item.getImage(columnIndex);
                  int imageWidth = (image != null) ? image.getBounds().width + getGap() : 0;
                  int style = (column == null) ? SWT.LEFT : column.style;
                  if ((style & SWT.LEFT) != 0) {
                    rect.left += imageWidth;
                    rect.right = ((short) (((rect.left + width) - imageWidth) - inset));
                  }
                  if ((style & SWT.RIGHT) != 0) {
                    rect.left = ((short) (((rect.right - width) + imageWidth) + inset));
                  }
                  if ((style & SWT.CENTER) != 0) {
                    rect.left += imageWidth;
                  }
                }
              }
            }
            if ((toolTipText != null) && (toolTipText.length() != 0)) {
              char[] buffer = new char[toolTipText.length()];
              toolTipText.getChars(0, buffer.length, buffer, 0);
              int length = fixMnemonic(buffer);
              if (display.helpString != 0) {
                OS.CFRelease(helpString);
              }
              display.helpString =
                  OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, length);
              HMHelpContentRec helpContent = new HMHelpContentRec();
              OS.memcpy(helpContent, ioHelpContent, sizeof);
              display.helpWidget = this;
              helpContent.version = OS.kMacHelpVersion;
              helpContent.tagSide = ((short) (tagSide));
              helpContent.absHotRect_left = ((short) (rect.left + windowLeft));
              helpContent.absHotRect_top = ((short) (rect.top + windowTop));
              helpContent.absHotRect_right = ((short) (rect.right + windowLeft));
              helpContent.absHotRect_bottom = ((short) (rect.bottom + windowTop));
              helpContent.content0_contentType = OS.kHMCFStringContent;
              helpContent.content0_tagCFString = display.helpString;
              helpContent.content1_contentType = OS.kHMCFStringContent;
              helpContent.content1_tagCFString = display.helpString;
              OS.memcpy(ioHelpContent, helpContent, sizeof);
              OS.memcpy(outContentProvided, new int[] {OS.kHMContentProvided}, 4);
              return OS.noErr;
            }
          }
          break;
        }
    }
    return super.helpProc(inControl, inGlobalMouse, inRequest, outContentProvided, ioHelpContent);
  }
}
