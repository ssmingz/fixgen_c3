class PlaceHold {
  void decodeACCoefficients(int[] dataUnit, int iComp) {
    int[] sParams = scanHeader.componentParameters[iComp];
    JPEGHuffmanTable acTable = acHuffmanTables[sParams[AC]];
    int k = 1;
    while (k < 64) {
      int rs = decodeUsingTable(acTable);
      int r = rs >> 4;
      int s = rs & 0xf;
      if (s == 0) {
        if (r == 15) {
          k += 16;
        } else {
          break;
        }
      } else {
        k += r;
        int bits = receive(s);
        dataUnit[ZigZag8x8[k]] = extendBy(bits, s);
        k++;
      }
    }
  }
}
