class PlaceHold {
  void decodeDCCoefficient(int[] dataUnit, int iComp, boolean first, int approxBit) {
    int[] sParams = scanHeader.componentParameters[componentIds[iComp]];
    JPEGHuffmanTable dcTable = dcHuffmanTables[sParams[DC]];
    int lastDC = 0;
    if (progressive && (!first)) {
      int bit = nextBit();
      lastDC = dataUnit[0] + (bit << approxBit);
    } else {
      lastDC = precedingDCs[iComp];
      int nBits = decodeUsingTable(dcTable);
      if (nBits != 0) {
        int bits = receive(nBits);
        int diff = extendBy(bits, nBits);
        lastDC = lastDC + diff;
        precedingDCs[iComp] = lastDC;
      }
      if (progressive) {
        lastDC = lastDC << approxBit;
      }
    }
    dataUnit[0] = lastDC;
  }
}
