class PlaceHold {
  protected void init() {
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
    paragraphStyle = ((NSMutableParagraphStyle) (new NSMutableParagraphStyle().alloc().init()));
    paragraphStyle.setAlignment(NSLeftTextAlignment);
    paragraphStyle.setLineBreakMode(NSLineBreakByClipping);
    NSArray tabs = new NSArray(new NSArray().alloc().init());
    paragraphStyle.setTabStops(tabs);
    tabs.release();
    boolean smallFonts = System.getProperty("org.eclipse.swt.internal.carbon.smallFonts") != null;
    float systemFontSize = (smallFonts) ? NSFont.smallSystemFontSize() : NSFont.systemFontSize();
    Point dpi = this.dpi = getDPI();
    Point screenDPI = getScreenDPI();
    NSFont font = NSFont.systemFontOfSize((systemFontSize * dpi.y) / screenDPI.y);
    font.retain();
    systemFont = Font.cocoa_new(this, font);
  }
}
