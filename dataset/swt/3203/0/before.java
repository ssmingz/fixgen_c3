class PlaceHold {
  void extractData(int[] dataUnit, int iComp, int xmcu, int ymcu, int ihi, int ivi) {
    byte[] compImage = imageComponents[iComp];
    int[] frameComponent = frameComponents[iComp];
    int hi = frameComponent[HI];
    int vi = frameComponent[VI];
    int compWidth = frameComponent[CW];
    int srcIndex = ((((ymcu * vi) + ivi) * compWidth) * DCTSIZE) + (((xmcu * hi) + ihi) * DCTSIZE);
    int destIndex = 0;
    for (int i = 0; i < DCTSIZE; i++) {
      for (int col = 0; col < DCTSIZE; col++) {
        dataUnit[destIndex] = (compImage[srcIndex + col] & 0xff) - 128;
        destIndex++;
      }
      srcIndex += compWidth;
    }
  }
}
