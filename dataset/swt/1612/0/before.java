class TextLayout {
  public TextLayout(Device device) {
    super(device);
    wrapWidth = ascent = descent = -1;
    lineSpacing = 0;
    orientation = SWT.LEFT_TO_RIGHT;
    styles = new StyleItem[2];
    styles[0] = new StyleItem();
    styles[1] = new StyleItem();
    stylesCount = 2;
    text = "";
    int[] ppv = new int[1];
    OS.OleInitialize(0);
    if (OS.CoCreateInstance(CLSID_CMultiLanguage, 0, CLSCTX_INPROC_SERVER, IID_IMLangFontLink2, ppv)
        == OS.S_OK) {
      mLangFontLink2 = ppv[0];
    }
    init();
  }
}
