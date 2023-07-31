class PlaceHold {
  void storeData(int[] dataUnit, int iComp, int xmcu, int ymcu, int hi, int ihi, int vi, int ivi) {
    byte[] compImage = imageComponents[iComp];
    int[] frameComponent = frameComponents[componentIds[iComp]];
    int compWidth = frameComponent[CW];
    int destIndex = ((((ymcu * vi) + ivi) * compWidth) * DCTSIZE) + (((xmcu * hi) + ihi) * DCTSIZE);
    int srcIndex = 0;
    for (int i = 0; i < DCTSIZE; i++) {
      for (int col = 0; col < DCTSIZE; col++) {
        int x = dataUnit[srcIndex] + 128;
        if (x < 0) {
          x = 0;
        } else if (x > 255) {
          x = 255;
        }
        compImage[destIndex + col] = ((byte) (x));
        srcIndex++;
      }
      destIndex += compWidth;
    }
  }
}
