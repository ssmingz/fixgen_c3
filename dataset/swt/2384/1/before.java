class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    if ((style & SWT.SEPARATOR) != 0) {
      int border = getBorderWidth();
      int width = border * 2;
      int height = border * 2;
      if ((style & SWT.HORIZONTAL) != 0) {
        width += DEFAULT_WIDTH;
        height += 3;
      } else {
        width += 3;
        height += DEFAULT_HEIGHT;
      }
      if (wHint != SWT.DEFAULT) {
        width = wHint + (border * 2);
      }
      if (hHint != SWT.DEFAULT) {
        height = hHint + (border * 2);
      }
      return new Point(width, height);
    }
    if ((style & SWT.WRAP) != 0) {
      int[] args =
          new int[] {
            OS.Pt_ARG_LABEL_TYPE,
            0,
            0,
            OS.Pt_ARG_TEXT_FONT,
            0,
            0,
            OS.Pt_ARG_LINE_SPACING,
            0,
            0,
            OS.Pt_ARG_MARGIN_WIDTH,
            0,
            0,
            OS.Pt_ARG_MARGIN_HEIGHT,
            0,
            0,
            OS.Pt_ARG_MARGIN_LEFT,
            0,
            0,
            OS.Pt_ARG_MARGIN_RIGHT,
            0,
            0,
            OS.Pt_ARG_MARGIN_TOP,
            0,
            0,
            OS.Pt_ARG_MARGIN_BOTTOM,
            0,
            0
          };
      OS.PtGetResources(handle, args.length / 3, args);
      if (args[1] == OS.Pt_Z_STRING) {
        int width = wHint;
        int height = hHint;
        if ((wHint == SWT.DEFAULT) || (hHint == SWT.DEFAULT)) {
          int length = OS.strlen(args[4]);
          byte[] font = new byte[length + 1];
          OS.memmove(font, args[4], length);
          String string = text;
          if (wHint != SWT.DEFAULT) {
            Display display = getDisplay();
            string = display.wrapText(text, font, wHint);
          }
          byte[] buffer = Converter.wcsToMbcs(null, string, false);
          PhRect_t rect = new PhRect_t();
          OS.PgExtentMultiText(rect, null, font, buffer, buffer.length, args[7]);
          if (wHint == SWT.DEFAULT) {
            width = (rect.lr_x - rect.ul_x) + 1;
          }
          if (hHint == SWT.DEFAULT) {
            height = (rect.lr_y - rect.ul_y) + 1;
          }
        }
        PhArea_t area = new PhArea_t();
        PhRect_t rect = new PhRect_t();
        OS.PtSetAreaFromWidgetCanvas(handle, rect, area);
        width += (((area.size_w - 1) + (args[10] * 2)) + args[16]) + args[19];
        height += (((area.size_h - 1) + (args[13] * 2)) + args[22]) + args[25];
        return new Point(width, height);
      }
    }
    PhDim_t dim = new PhDim_t();
    if (!OS.PtWidgetIsRealized(handle)) {
      OS.PtExtentWidget(handle);
    }
    OS.PtWidgetPreferredSize(handle, dim);
    int width = dim.w;
    int height = dim.h;
    if ((wHint != SWT.DEFAULT) || (hHint != SWT.DEFAULT)) {
      int[] args =
          new int[] {
            OS.Pt_ARG_MARGIN_WIDTH,
            0,
            0,
            OS.Pt_ARG_MARGIN_HEIGHT,
            0,
            0,
            OS.Pt_ARG_MARGIN_LEFT,
            0,
            0,
            OS.Pt_ARG_MARGIN_RIGHT,
            0,
            0,
            OS.Pt_ARG_MARGIN_TOP,
            0,
            0,
            OS.Pt_ARG_MARGIN_BOTTOM,
            0,
            0
          };
      OS.PtGetResources(handle, args.length / 3, args);
      PhRect_t rect = new PhRect_t();
      PhArea_t area = new PhArea_t();
      rect.lr_x = ((short) (wHint - 1));
      rect.lr_y = ((short) (hHint - 1));
      OS.PtSetAreaFromWidgetCanvas(handle, rect, area);
      if (wHint != SWT.DEFAULT) {
        width = ((area.size_w + (args[1] * 2)) + args[7]) + args[10];
      }
      if (hHint != SWT.DEFAULT) {
        height = ((area.size_h + (args[4] * 2)) + args[13]) + args[16];
      }
    }
    return new Point(width, height);
  }
}
