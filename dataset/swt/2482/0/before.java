class PlaceHold {
  Font defaultFont() {
    byte[] family = new byte[256];
    short[] size = new short[1];
    byte[] style = new byte[1];
    OS.GetThemeFont(
        ((short) (defaultThemeFont())), ((short) (smSystemScript)), family, size, style);
    short id = OS.FMGetFontFamilyFromName(family);
    int[] font = new int[1];
    OS.FMGetFontFromFontFamilyInstance(id, style[0], font, null);
    return Font.carbon_new(display, font[0], id, style[0], size[0]);
  }
}
