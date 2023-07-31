class PlaceHold {
  protected void init() {
    colorspace = OS.CGColorSpaceCreateDeviceRGB();
    if (colorspace == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    COLOR_BLACK = new Color(this, 0, 0, 0);
    COLOR_DARK_RED = new Color(this, 0x80, 0, 0);
    COLOR_DARK_GREEN = new Color(this, 0, 0x80, 0);
    COLOR_DARK_YELLOW = new Color(this, 0x80, 0x80, 0);
    COLOR_DARK_BLUE = new Color(this, 0, 0, 0x80);
    COLOR_DARK_MAGENTA = new Color(this, 0x80, 0, 0x80);
    COLOR_DARK_CYAN = new Color(this, 0, 0x80, 0x80);
    COLOR_GRAY = new Color(this, 0xc0, 0xc0, 0xc0);
    COLOR_DARK_GRAY = new Color(this, 0x80, 0x80, 0x80);
    COLOR_RED = new Color(this, 0xff, 0, 0);
    COLOR_GREEN = new Color(this, 0, 0xff, 0);
    COLOR_YELLOW = new Color(this, 0xff, 0xff, 0);
    COLOR_BLUE = new Color(this, 0, 0, 0xff);
    COLOR_MAGENTA = new Color(this, 0xff, 0, 0xff);
    COLOR_CYAN = new Color(this, 0, 0xff, 0xff);
    COLOR_WHITE = new Color(this, 0xff, 0xff, 0xff);
    boolean smallFonts = System.getProperty("org.eclipse.swt.internal.carbon.smallFonts") != null;
    byte[] family = new byte[256];
    short[] size = new short[1];
    byte[] style = new byte[1];
    int themeFont = (smallFonts) ? OS.kThemeSmallSystemFont : OS.kThemeSystemFont;
    OS.GetThemeFont(((short) (themeFont)), ((short) (smSystemScript)), family, size, style);
    short id = OS.FMGetFontFamilyFromName(family);
    int[] font = new int[1];
    OS.FMGetFontFromFontFamilyInstance(id, style[0], font, null);
    systemFont = Font.carbon_new(this, font[0], id, style[0], size[0]);
  }
}
