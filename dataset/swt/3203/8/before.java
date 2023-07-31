class PlaceHold {
  void expandImageComponents() {
    for (int iComp = 0; iComp < nComponents; iComp++) {
      int[] frameComponent = frameComponents[iComp];
      int hi = frameComponent[HI];
      int vi = frameComponent[VI];
      int upH = maxH / hi;
      int upV = maxV / vi;
      if ((upH * upV) > 1) {
        byte[] component = imageComponents[iComp];
        int compWidth = frameComponent[CW];
        int compHeight = frameComponent[CH];
        int upCompWidth = compWidth * upH;
        int upCompHeight = compHeight * upV;
        RGB[] rgbs =
            new RGB[] {
              new RGB(0, 0, 0),
              new RGB(0x80, 0, 0),
              new RGB(0, 0x80, 0),
              new RGB(0x80, 0x80, 0),
              new RGB(0, 0, 0x80),
              new RGB(0x80, 0, 0x80),
              new RGB(0, 0x80, 0x80),
              new RGB(0xc0, 0xc0, 0xc0),
              new RGB(0x80, 0x80, 0x80),
              new RGB(0xff, 0, 0),
              new RGB(0, 0xff, 0),
              new RGB(0xff, 0xff, 0),
              new RGB(0, 0, 0xff),
              new RGB(0xff, 0, 0xff),
              new RGB(0, 0xff, 0xff),
              new RGB(0xff, 0xff, 0xff)
            };
        ImageData src =
            new ImageData(compWidth, compHeight, 8, new PaletteData(rgbs), 4, component);
        ImageData dest = src.scaledTo(upCompWidth, upCompHeight);
        imageComponents[iComp] = dest.data;
      }
    }
  }
}
