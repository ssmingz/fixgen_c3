class PlaceHold {
  void encodeMCUAtXAndY(int xmcu, int ymcu) {
    int nComponentsInScan = scanHeader.getNumberOfImageComponents();
    dataUnit = new int[64];
    for (int iComp = 0; iComp < nComponentsInScan; iComp++) {
      int[] frameComponent = frameComponents[componentIds[iComp]];
      int hi = frameComponent[HI];
      int vi = frameComponent[VI];
      for (int ivi = 0; ivi < vi; ivi++) {
        for (int ihi = 0; ihi < hi; ihi++) {
          extractData(dataUnit, iComp, xmcu, ymcu, ihi, ivi);
          forwardDCT(dataUnit);
          quantizeData(dataUnit, iComp);
          encodeDCCoefficients(dataUnit, iComp);
          encodeACCoefficients(dataUnit, iComp);
        }
      }
    }
  }
}
