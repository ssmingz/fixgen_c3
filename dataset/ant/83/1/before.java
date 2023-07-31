class PlaceHold{
public int read(byte[] buf, int offset, int numToRead) throws IOException {
    int totalRead = 0;
    if (this.entryOffset >= this.entrySize) {
        return -1;
    }
    if ((numToRead + this.entryOffset) > this.entrySize) {
        numToRead = this.entrySize - this.entryOffset;
    }
    if (this.readBuf != null) {
        int sz = (numToRead > this.readBuf.length) ? this.readBuf.length : numToRead;
        System.arraycopy(this.readBuf, 0, buf, offset, sz);
        if (sz >= this.readBuf.length) {
            this.readBuf = null;
        } else {
            int newLen = this.readBuf.length - sz;
            byte[] newBuf = new byte[newLen];
            System.arraycopy(this.readBuf, sz, newBuf, 0, newLen);
            this.readBuf = newBuf;
        }
        totalRead += sz;
        numToRead -= sz;
        offset += sz;
    }
    while (numToRead > 0) {
        byte[] rec = this.buffer.readRecord();
        if (rec == null) {
            throw new IOException(("unexpected EOF with " + numToRead) + " bytes unread");
        }
        int sz = numToRead;
        int recLen = rec.length;
        if (recLen > sz) {
            System.arraycopy(rec, 0, buf, offset, sz);
            this.readBuf = new byte[recLen - sz];
            System.arraycopy(rec, sz, this.readBuf, 0, recLen - sz);
        } else {
            sz = recLen;
            System.arraycopy(rec, 0, buf, offset, recLen);
        }
        totalRead += sz;
        numToRead -= sz;
        offset += sz;
    } 
    this.entryOffset += totalRead;
    return totalRead;
}
}