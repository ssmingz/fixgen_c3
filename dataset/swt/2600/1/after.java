class PlaceHold {
  public void setVisible(boolean visible) {
    if (helpString != 0) {
      OS.CFRelease(helpString);
    }
    helpString = 0;
    if (runnable != null) {
      display.timerExec(-1, runnable);
    }
    runnable = null;
    if (visible) {
      OS.HMHideTag();
      display.helpWidget = this;
      if ((!autohide) || ((style & SWT.BALLOON) != 0)) {
        if (tip == null) {
          tip = new Shell(parent, (SWT.ON_TOP | SWT.NO_TRIM) | SWT.TOOL);
          Color background = display.getSystemColor(COLOR_INFO_BACKGROUND);
          tip.setBackground(background);
          Listener listener =
              new Listener() {
                public void handleEvent(Event event) {
                  switch (event.type) {
                    case SWT.Paint:
                      onPaint(event);
                      break;
                    case SWT.MouseDown:
                      onMouseDown(event);
                      break;
                  }
                }
              };
          tip.addListener(Paint, listener);
          tip.addListener(MouseDown, listener);
          layoutText = new TextLayout(display);
          layoutText.setText(text);
          Font font = display.getSystemFont();
          FontData data = font.getFontData()[0];
          boldFont = new Font(display, data.getName(), data.getHeight(), SWT.BOLD);
          TextStyle style = new TextStyle(boldFont, null, null);
          layoutText.setStyle(style, 0, text.length());
          layoutMessage = new TextLayout(display);
          layoutMessage.setText(message);
        }
        configure();
        tip.setVisible(true);
        if (autohide) {
          runnable =
              new Runnable() {
                public void run() {
                  if (!isDisposed()) {
                    setVisible(false);
                  }
                }
              };
          display.timerExec(DELAY, runnable);
        }
      } else {
        if (tip != null) {
          disposeTip();
        }
        if ((x == (-1)) || (y == (-1))) {
          Point point;
          if (item != null) {
            point = item.getLocation();
            x = point.x;
            y = point.y;
          } else {
            Point pt = new Point();
            OS.GetGlobalMouse(pt);
            x = pt.h;
            y = pt.v;
          }
        }
        StringBuffer string = new StringBuffer(text);
        if (text.length() > 0) {
          string.append("\n\n");
        }
        string.append(message);
        char[] buffer = new char[string.length()];
        string.getChars(0, buffer.length, buffer, 0);
        helpString = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
        HMHelpContentRec helpContent = new HMHelpContentRec();
        helpContent.tagSide = ((short) (OS.kHMAbsoluteCenterAligned));
        helpContent.absHotRect_left = ((short) (x));
        helpContent.absHotRect_top = ((short) (y));
        helpContent.absHotRect_right = ((short) (x + 1));
        helpContent.absHotRect_bottom = ((short) (y + 1));
        helpContent.content0_contentType = OS.kHMCFStringContent;
        helpContent.content0_tagCFString = helpString;
        helpContent.content1_contentType = OS.kHMCFStringContent;
        helpContent.content1_tagCFString = helpString;
        OS.HMDisplayTag(helpContent);
      }
    } else if (display.helpWidget == this) {
      display.helpWidget = null;
      OS.HMHideTag();
      if (tip != null) {
        tip.setVisible(false);
      }
    }
  }
}
