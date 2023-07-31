class PlaceHold {
  public Color getSystemColor(int id) {
    checkDevice();
    switch (id) {
      case SWT.COLOR_BLACK:
        return COLOR_BLACK;
      case SWT.COLOR_DARK_RED:
        return COLOR_DARK_RED;
      case SWT.COLOR_DARK_GREEN:
        return COLOR_DARK_GREEN;
      case SWT.COLOR_DARK_YELLOW:
        return COLOR_DARK_YELLOW;
      case SWT.COLOR_DARK_BLUE:
        return COLOR_DARK_BLUE;
      case SWT.COLOR_DARK_MAGENTA:
        return COLOR_DARK_MAGENTA;
      case SWT.COLOR_DARK_CYAN:
        return COLOR_DARK_CYAN;
      case SWT.COLOR_GRAY:
        return COLOR_GRAY;
      case SWT.COLOR_DARK_GRAY:
        return COLOR_DARK_GRAY;
      case SWT.COLOR_RED:
        return COLOR_RED;
      case SWT.COLOR_GREEN:
        return COLOR_GREEN;
      case SWT.COLOR_YELLOW:
        return COLOR_YELLOW;
      case SWT.COLOR_BLUE:
        return COLOR_BLUE;
      case SWT.COLOR_MAGENTA:
        return COLOR_MAGENTA;
      case SWT.COLOR_CYAN:
        return COLOR_CYAN;
      case SWT.COLOR_WHITE:
        return COLOR_WHITE;
    }
    return Color.carbon_new(this, 0x0);
  }
}
