package ru.vmsoftware.parser.builder.iterators;

/**
* @author Vyacheslav Mayorov
* @since 2014-17-01
*/
public class CharSequenceIterator implements CharIterator {
    private final CharSequence seq;
    private int pos = 0;

    public CharSequenceIterator(CharSequence seq) {
        this.seq = seq;
    }

    @Override
    public boolean hasChar() {
        return pos < seq.length();
    }

    @Override
    public char getChar() {
        return seq.charAt(pos);
    }

    @Override
    public void next() {
        ++pos;
    }

    @Override
    public int position() {
        return pos;
    }

    @Override
    public void moveTo(int pos) {
        this.pos = pos;
    }

    @Override
    public CharSequence subSequence(int startPos) {
        return seq.subSequence(startPos, pos);
    }

    @Override
    public String toString() {
        return seq.subSequence(0, pos) + "-->" + seq.subSequence(pos, seq.length());
    }
}
