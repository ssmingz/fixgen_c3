class PlaceHold {
  public Color getSystemColor(int id) {
    checkDevice();
    NSColor color = null;
    switch (id) {
      case SWT.COLOR_INFO_FOREGROUND:
        return super.getSystemColor(COLOR_BLACK);
      case SWT.COLOR_INFO_BACKGROUND:
        return Color.cocoa_new(this, new float[] {0xff / 255.0F, 0xff / 255.0F, 0xe1 / 255.0F, 1});
      case SWT.COLOR_TITLE_FOREGROUND:
        color = NSColor.windowFrameTextColor();
        break;
      case SWT.COLOR_TITLE_BACKGROUND:
        color = NSColor.secondarySelectedControlColor();
        break;
      case SWT.COLOR_TITLE_BACKGROUND_GRADIENT:
        color = NSColor.windowFrameColor();
        break;
      case SWT.COLOR_TITLE_INACTIVE_FOREGROUND:
        color = NSColor.disabledControlTextColor();
        break;
      case SWT.COLOR_TITLE_INACTIVE_BACKGROUND:
        color = NSColor.secondarySelectedControlColor();
        break;
      case SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT:
        color = NSColor.secondarySelectedControlColor();
        break;
      case SWT.COLOR_WIDGET_DARK_SHADOW:
        color = NSColor.controlDarkShadowColor();
        break;
      case SWT.COLOR_WIDGET_NORMAL_SHADOW:
        color = NSColor.controlShadowColor();
        break;
      case SWT.COLOR_WIDGET_LIGHT_SHADOW:
        color = NSColor.controlHighlightColor();
        break;
      case SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW:
        color = NSColor.controlLightHighlightColor();
        break;
      case SWT.COLOR_WIDGET_BACKGROUND:
        color = NSColor.controlHighlightColor();
        break;
      case SWT.COLOR_WIDGET_FOREGROUND:
        color = NSColor.controlTextColor();
        break;
      case SWT.COLOR_WIDGET_BORDER:
        return super.getSystemColor(COLOR_BLACK);
      case SWT.COLOR_LIST_FOREGROUND:
        color = NSColor.textColor();
        break;
      case SWT.COLOR_LIST_BACKGROUND:
        color = NSColor.textBackgroundColor();
        break;
      case SWT.COLOR_LIST_SELECTION_TEXT:
        color = NSColor.selectedTextColor();
        break;
      case SWT.COLOR_LIST_SELECTION:
        color = NSColor.selectedTextBackgroundColor();
        break;
      default:
        return super.getSystemColor(id);
    }
    if (color == null) {
      return super.getSystemColor(id);
    }
    color = color.colorUsingColorSpace(NSColorSpace.deviceRGBColorSpace());
    if (color == null) {
      return super.getSystemColor(id);
    }
    float[] components = new float[color.numberOfComponents()];
    color.getComponents(components);
    return Color.cocoa_new(
        this, new float[] {components[0], components[1], components[2], components[3]});
  }
}
