class PlaceHold {
  public FontData[] getFontData() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int fontFamily = OS.Typeface_FontFamily(handle);
    int style = OS.Typeface_Style(handle);
    int weight = OS.Typeface_Weight(handle);
    int stretch = OS.Typeface_Stretch(handle);
    int str = OS.FontFamily_Source(fontFamily);
    int charArray = OS.String_ToCharArray(str);
    char[] chars = new char[OS.String_Length(str)];
    OS.memmove(chars, charArray, chars.length * 2);
    int fontStyle = OS.FontStyles_Normal;
    if (OS.Object_Equals(style, FontStyles_Italic)) {
      fontStyle = OS.FontStyles_Italic;
    }
    if (OS.Object_Equals(style, FontStyles_Oblique)) {
      fontStyle = OS.FontStyles_Oblique;
    }
    int size = ((int) ((this.size * 72) / 96.0F));
    FontData data =
        FontData.wpf_new(
            new String(chars),
            fontStyle,
            OS.FontWeight_ToOpenTypeWeight(weight),
            OS.FontStretch_ToOpenTypeStretch(stretch),
            ((int) (size)));
    OS.GCHandle_Free(charArray);
    OS.GCHandle_Free(str);
    OS.GCHandle_Free(fontFamily);
    OS.GCHandle_Free(style);
    OS.GCHandle_Free(weight);
    OS.GCHandle_Free(stretch);
    return new FontData[] {data};
  }
}
