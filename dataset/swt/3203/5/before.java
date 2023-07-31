class PlaceHold {
  void decodeACRefineCoefficients(int[] dataUnit, int iComp, int start, int end, int approxBit) {
    int[] sParams = scanHeader.componentParameters[iComp];
    JPEGHuffmanTable acTable = acHuffmanTables[sParams[AC]];
    int k = start;
    while (k <= end) {
      if (eobrun > 0) {
        while (k <= end) {
          int zzIndex = ZigZag8x8[k];
          if (dataUnit[zzIndex] != 0) {
            dataUnit[zzIndex] = refineAC(dataUnit[zzIndex], approxBit);
          }
          k++;
        }
        eobrun--;
      } else {
        int rs = decodeUsingTable(acTable);
        int r = rs >> 4;
        int s = rs & 0xf;
        if (s == 0) {
          if (r == 15) {
            int zeros = 0;
            while ((zeros < 16) && (k <= end)) {
              int zzIndex = ZigZag8x8[k];
              if (dataUnit[zzIndex] != 0) {
                dataUnit[zzIndex] = refineAC(dataUnit[zzIndex], approxBit);
              } else {
                zeros++;
              }
              k++;
            }
          } else {
            eobrun = (1 << r) + receive(r);
          }
        } else {
          int bit = receive(s);
          int zeros = 0;
          int zzIndex = ZigZag8x8[k];
          while (((zeros < r) || (dataUnit[zzIndex] != 0)) && (k <= end)) {
            if (dataUnit[zzIndex] != 0) {
              dataUnit[zzIndex] = refineAC(dataUnit[zzIndex], approxBit);
            } else {
              zeros++;
            }
            k++;
            zzIndex = ZigZag8x8[k];
          }
          if (bit != 0) {
            dataUnit[zzIndex] = 1 << approxBit;
          } else {
            dataUnit[zzIndex] = (-1) << approxBit;
          }
          k++;
        }
      }
    }
  }
}
